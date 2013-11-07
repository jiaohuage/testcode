package action;

import hbm.RwdState;
import hbm.UiLuckyCodePv;
import hbm.UiLuckyCodePvId;
import hibernate.HibernateSessionFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Statistics
{
	private Map<String, Integer> statisticsPvMap;

	private String message;

	private List list = null;

	private List feedbackList;

	public List getFeedbackList()
	{
		return this.feedbackList;
	}

	public void setFeedbackList(List feedbackList)
	{
		this.feedbackList = feedbackList;
	}

	public String execute()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		this.statisticsPvMap = new HashMap();
		this.statisticsPvMap = showStatisticsPv();
		this.statisticsPvMap.put("uvCount", showStatisticsUv());
		request.setAttribute("statisticsPvMap", this.statisticsPvMap);
		request.setAttribute("list", this.list);
		request.setAttribute("feedbackList", this.feedbackList);
		return "statistics";
	}

	public Map<String, Integer> showStatisticsPv()
	{
		Map pvMap = new HashMap();
		pvMap.put("firstPage", Integer.valueOf(Mem.getFirstPage()));
		pvMap.put("myWinCode", Integer.valueOf(Mem.getMyWinCode()));
		pvMap.put("codeRule", Integer.valueOf(Mem.getCodeRule()));
		pvMap.put("anCodeWin", Integer.valueOf(Mem.getAnCodeWin()));
		pvMap.put("kefu", Integer.valueOf(Mem.getKefu()));
		pvMap.put("fenxiang", Integer.valueOf(Mem.getFenxiang()));
		pvMap.put(
				"pvCount",
				Integer.valueOf(Mem.getFirstPage() + Mem.getMyWinCode()
						+ Mem.getCodeRule() + Mem.getAnCodeWin()
						+ Mem.getKefu() + Mem.getFenxiang()));
		return pvMap;
	}

	public Integer showStatisticsUv()
	{
		return Integer.valueOf(Mem.getUserCodeMap().size());
	}

	public String insertStatistics()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		Transaction trans = session.beginTransaction();
		UiLuckyCodePvId uiLuckyCodePvId = new UiLuckyCodePvId();
		uiLuckyCodePvId.setFirstPage(String.valueOf(Mem.getFirstPage()));
		uiLuckyCodePvId.setMyWinCode(String.valueOf(Mem.getMyWinCode()));
		uiLuckyCodePvId.setCodeRule(String.valueOf(Mem.getCodeRule()));
		uiLuckyCodePvId.setAnCodeWin(String.valueOf(Mem.getAnCodeWin()));
		uiLuckyCodePvId.setKefu(String.valueOf(Mem.getKefu()));
		uiLuckyCodePvId.setFenxiang(String.valueOf(Mem.getFenxiang()));
		uiLuckyCodePvId.setCreateTime(df.format(new Date()).toString());
		uiLuckyCodePvId.setIssue(((RwdState) Mem.getRwdStateList().get(0))
				.getIssue());
		UiLuckyCodePv uiLuckyCodePv = new UiLuckyCodePv();
		uiLuckyCodePv.setId(uiLuckyCodePvId);
		this.message = session.save(uiLuckyCodePv).toString();
		trans.commit();
		if ((this.message != null) && (this.message.length() > 0))
			if (Mem.getUvList().size() > 0)
			{
				String key = "";
				for (int i = 0; i < Mem.getUvList().size(); ++i)
				{
					key = key + "," + Mem.getUvList().get(i);
					if (i > 200)
					{
						break;
					}
				}
				Mem.insertUiLuckyCodeUv(key);
			}
			else
			{
				this.message = "false";
			}
		HibernateSessionFactory.closeSession();
		return execute();
	}

	public String showTaskCount()
	{
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		String show_issue = ((RwdState) Mem.getRwdStateList().get(0))
				.getIssue();
		String hql = "select t.p_name,count(1) from ui_lucky_code_task t,ui_down_page t1 ";
		hql = hql + " where t.p_id = t1.p_id and t.tag = '1' and t1.issue = "
				+ show_issue + " group by t.p_name ";
		hql = hql + " union all ";
		hql = hql
				+ " select t.p_name,count(1) from ui_lucky_code_task t,ui_vac_order t1 ";
		hql = hql
				+ " where t.p_id = t1.spid and t.sp_pid = t1.sppid and t.tag = '1' and t1.issue = "
				+ show_issue + " group by t.p_name ";
		hql = hql + " union all ";
		hql = hql
				+ " select t.p_name,count(1) from ui_lucky_code_task t,ui_music_order t1 ";
		hql = hql
				+ " where t.p_id = t1.productid and t.tag = '1' and t1.issue = "
				+ show_issue + " group by t.p_name ";
		hql = hql + " union all ";
		hql = hql + " select '种子短信',count(*)  from ui_lucky_code_seed_sms t1 ";
		hql = hql + " where  t1.issue = " + show_issue;
		hql = hql + smsSetCount(show_issue);
		hql = hql + " union all ";
		hql = hql + " select '每日签到',count(*)  from ui_lucky_code_sign t1 ";
		hql = hql + " where  t1.issue = " + show_issue;
		hql = hql + " union all ";
		hql = hql + " select '分享',count(*)  from ui_lucky_code_fenxiang t1 ";
		hql = hql + " where  t1.issue = " + show_issue;
		hql = hql + " union all ";
		hql = hql + " select '新闻排行',count(*)  from ui_lucky_code_paihang t1 ";
		hql = hql + " where  t1.issue = " + show_issue;
		hql = hql + " union all ";
		hql = hql
				+ " select '拒绝短信', count(distinct t1.phone) from random_stop t1 ";
		hql = hql + " where  t1.issue = " + show_issue;
		hql = hql + " union all ";
		hql = hql
				+ "  select '提醒短信',count(distinct t1.phone) from random_continue t1 ";
		hql = hql + " where  t1.issue = " + show_issue;
		hql = hql + " union all ";
		hql = hql + "select '用户意见',count(distinct t1.phone) from opinion t1 ";
		hql = hql + "where  t1.issue = " + show_issue;
		hql = hql + "and t1.opinion is not null";
		hql = hql + " union all ";
		hql = hql
				+ "select '狂欢五节',count(distinct t1.phone) from ui_lucky_code_five_mark t1 ";
		hql = hql + "where  t1.issue = " + show_issue;
		hql = hql + " union all ";
		hql = hql
				+ "select '3G流量包',count(t1.phone) from ui_lucky_code_phone_flow_bag t1 ";
		hql = hql + "where  t1.issue = " + show_issue;
		Query query = session.createSQLQuery(hql);
		this.list = query.list();
		HibernateSessionFactory.closeSession();
		return execute();
	}

	public String smsSetCount(String show_issue)
	{
		String hql = " union all ";
		hql = hql + " SELECT '订阅提醒短信',count(A.PHONE) ";
		hql = hql + " FROM (SELECT * ";
		hql = hql + " FROM (SELECT T.PHONE, ";
		hql = hql
				+ " TO_DATE(T.CREATEDATE, 'yyyy-mm-dd hh24:mi:ss') CREATEDATE, ";
		hql = hql
				+ " ROW_NUMBER() OVER(PARTITION BY T.PHONE ORDER BY T.CREATEDATE DESC) TAG ";
		hql = hql + " FROM RANDOM_CONTINUE T ";
		hql = hql + " WHERE LENGTH(PHONE) = 11 ";
		hql = hql + " AND T.ISSUE = '" + show_issue + "') ";
		hql = hql + " WHERE TAG = 1) A, ";
		hql = hql + " (SELECT * ";
		hql = hql + " FROM (SELECT T.PHONE, ";
		hql = hql + " T.CREATEDATE, ";
		hql = hql
				+ " ROW_NUMBER() OVER(PARTITION BY T.PHONE ORDER BY T.CREATEDATE DESC) TAG ";
		hql = hql + " FROM (SELECT PHONE, ";
		hql = hql
				+ " TO_DATE(T.CREATEDATE, 'yyyy-mm-dd hh24:mi:ss') CREATEDATE, ";
		hql = hql + " ISSUE ";
		hql = hql + " FROM RANDOM_STOP T ";
		hql = hql + " WHERE LENGTH(T.PHONE) >= 11 ";
		hql = hql + " AND T.ISSUE = '" + show_issue + "' ";
		hql = hql + " ) T) ";
		hql = hql + " WHERE TAG = 1) B ";
		hql = hql + " WHERE A.PHONE = B.PHONE(+) ";
		hql = hql + " AND (A.CREATEDATE > B.CREATEDATE OR B. PHONE IS NULL) ";
		return hql;
	}

	public Map<String, Integer> getStatisticsPvMap()
	{
		return this.statisticsPvMap;
	}

	public void setStatisticsPvMap(Map<String, Integer> statisticsPvMap)
	{
		this.statisticsPvMap = statisticsPvMap;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}