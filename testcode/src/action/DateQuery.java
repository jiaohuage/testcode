package action;

import hbm.RwdState;
import hibernate.HibernateSessionFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DateQuery
{
	private List dateList = null;

	private Map<String, Integer> statisticsPvMap;

	private String message;

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

	public List getDateList()
	{
		return this.dateList;
	}

	public void setDateList(List dateList)
	{
		this.dateList = dateList;
	}

	public String execute()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		this.statisticsPvMap = new HashMap();
		request.setAttribute("statisticsPvMap", this.statisticsPvMap);
		request.setAttribute("datelist", this.dateList);
		return "dateQuery";
	}

	public String showTaskAll()
	{
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		String show_issue = ((RwdState) Mem.getRwdStateList().get(0))
				.getIssue();
		String hql = "SELECT '短信回复qx总数',count(DISTINCT TELE_NUMBER)  FROM VASMS.REPLY_SMS@BJZZZX t1";
		hql = hql
				+ " WHERE T.SEND_TIME > TO_DATE('2012-8-1', 'YYYY-MM-DD') AND t1.REPLY_CODE IN ('qx', 'QX') AND (t1.CONTENT LIKE '%数字%')";
		hql = hql + " union all ";
		hql = hql + "select t1.phone,t1.opinion from opinion t1 ";
		hql = hql + "and t1.opinion is not null";
		hql = hql + " union all ";
		hql = hql
				+ " select '谢绝活动信息短信总数',count(distinct t1.phone) from random_stop t1 where t1.issue ="
				+ show_issue;
		hql = hql + " union all ";
		hql = hql
				+ " select '接受活动信息短信总数',count(distinct t1.phone) from from random_continue t1 ";
		hql = hql + " union all ";
		hql = hql
				+ "select t.p_name,count(1) from ui_lucky_code_task t,ui_down_page t1 ";
		hql = hql + " where t.p_id = t1.p_id and t.tag = '1'";
		hql = hql + " group by t.p_name ";
		hql = hql + " union all ";
		hql = hql
				+ " select t.p_name,count(1) from ui_lucky_code_task t,ui_vac_order t1 ";
		hql = hql
				+ " where t.p_id = t1.spid and t.sp_pid = t1.sppid and t.tag = '1' ";
		hql = hql + "group by t.p_name ";
		hql = hql + " union all ";
		hql = hql
				+ " select t.p_name,count(1) from ui_lucky_code_task t,ui_music_order t1 ";
		hql = hql + " where t.p_id = t1.productid and t.tag = '1'";
		hql = hql + " group by t.p_name ";
		hql = hql + " union all ";
		hql = hql + " select '种子短信',count(*)  from ui_lucky_code_seed_sms t1 ";
		hql = hql + smsSetCount(show_issue);
		hql = hql + " union all ";
		hql = hql + " select '每日签到',count(*)  from ui_lucky_code_sign t1 ";
		hql = hql + " union all ";
		hql = hql + " select '分享',count(*)  from ui_lucky_code_fenxiang t1 ";
		hql = hql + " union all ";
		hql = hql + "select '点击页面uv',count(*) from statistics";
		hql = hql + " union all ";
		hql = hql
				+ "select '点击页面pv',count(t.first_page) + count(t.my_win_code) + count(t.code_rule) + count(t.an_code_win) + count(t.fenxiang) + count(t.kefu) from ui_lucky_code_pv t";
		Query query = session.createQuery(hql);
		this.dateList = query.list();
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
		hql = hql + " UNION ALL ";
		hql = hql + " SELECT T.TELE_NUMBER, SEND_TIME, '1' ";
		hql = hql + " FROM VASMS.REPLY_SMS T ";
		hql = hql + " WHERE T.SEND_TIME > TO_DATE('2012-8-7', 'YYYY-MM-DD') ";
		hql = hql + " AND T.REPLY_CODE IN ('qx', 'QX')) T) ";
		hql = hql + " WHERE TAG = 1) B ";
		hql = hql + " WHERE A.PHONE = B.PHONE(+) ";
		hql = hql + " AND (A.CREATEDATE > B.CREATEDATE OR B. PHONE IS NULL) ";
		return hql;
	}
}