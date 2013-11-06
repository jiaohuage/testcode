package action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.Element;
import hbm.RwdState;
import hbm.UiLuckyCodeRwd;
import hbm.UiLuckyCodeRwdId;
import hbm.UiLuckyCodeScore;
import hbm.UiLuckyCodeScoreDetail;
import hbm.UiLuckyCodeTask;
import hbm.UiLuckyCodeTaskId;
import hibernate.HibernateSessionFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ScoreAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;

	private List<Map> scoreList;

	private List<Map> scoreDetailList;

	@Element(String.class)
	private Map parameter;

	private String message;

	private String tag;

	private String newissue;

	private String phone;

	private String random;

	private String terminalType;

	private int page = 1;

	private int pageSize = 5;

	private int totalPage;

	public String execute()
	{
		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		scoreSwitch(this.phone, session);
		session.close();
		return "myScore";
	}

	public void giveScore(String changeScore, String phoneNum, String pid)
	{
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		List list = scoreSwitch(phoneNum, session);
		UiLuckyCodeScore uiLuckyCodeScore = null;
		if ((list != null) && (list.size() > 0))
		{
			uiLuckyCodeScore = (UiLuckyCodeScore) list.get(0);
		}
		else
		{
			uiLuckyCodeScore = new UiLuckyCodeScore();
			uiLuckyCodeScore.setFreeNum("0");
			uiLuckyCodeScore.setFrozenNum("0");
		}
		session.flush();
		session.clear();
		Transaction trans = session.beginTransaction();
		modityScore(uiLuckyCodeScore.getFreeNum(),
				uiLuckyCodeScore.getFrozenNum(), null, null, changeScore, null,
				"0", phoneNum, pid, session);
		trans.commit();
		session.close();
	}

	public String getDetail()
	{
		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		scoreSwitch(this.phone, session);
		detailSwitch("0", session);
		session.close();
		return "gsDetail";
	}

	public String exchangeDetail()
	{
		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		detailSwitch("1", session);
		session.close();

		return "exDetail";
	}

	private List<UiLuckyCodeScore> scoreSwitch(String phoneNum, Session session)
	{
		List list = session
				.getNamedQuery("hbm.UiLuckyCodeScore.queryLuckyCodeScore")
				.setString("svcId", phoneNum).list();
		if ((list != null) && (list.size() > 0))
		{
			UiLuckyCodeScore uiLuckyCodeScore = (UiLuckyCodeScore) list.get(0);
			Map map = new HashMap();
			map.put("SCORE_NUM", Integer.valueOf(Integer
					.parseInt(uiLuckyCodeScore.getFreeNum())
					+ Integer.parseInt(uiLuckyCodeScore.getFrozenNum())));
			map.put("FREE_NUM", uiLuckyCodeScore.getFreeNum());
			map.put("FROZEN_NUM", uiLuckyCodeScore.getFrozenNum());
			this.scoreList = new ArrayList();
			this.scoreList.add(map);
		}
		return list;
	}

	public String exchangeScore()
	{
		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}

		if (this.parameter != null)
		{
			Object scoreNum = this.parameter.get("SCORE_NUM");
			Object freeNum = this.parameter.get("FREE_NUM");
			Object frozenNum = this.parameter.get("FROZEN_NUM");
			Object exchangeCount = this.parameter.get("EXCHANGE_COUNT");
			Object exchangeType = this.parameter.get("EXCHANGE_TYPE");
			Object exchangeScore = this.parameter.get("EXCHANGE_SCORE");

			if ((scoreNum != null) && (freeNum != null) && (frozenNum != null)
					&& (exchangeCount != null) && (exchangeType != null)
					&& (exchangeScore != null))
			{
				if (Integer.parseInt(freeNum.toString()) < Integer
						.parseInt(exchangeScore.toString()))
				{
					this.message = "1002";

					Pattern pattern = Pattern.compile("^[1-9]\\d*$");
					Matcher matcher = pattern.matcher(exchangeCount.toString());
					if (!(matcher.matches()))
					{
						this.message = "1003";
					}
					return "";
				}
				Session session = HibernateSessionFactory.getSessionFactory()
						.openSession();
				Transaction trans = session.beginTransaction();

				if ("1".equals(exchangeType))
				{
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					if ((Mem.getRwdStateList() != null)
							&& (Mem.getRwdStateList().size() > 0))
					{
						this.tag = ((RwdState) Mem.getRwdStateList().get(0))
								.getTag();
						this.newissue = String.valueOf(Integer
								.parseInt(new StringBuilder(String
										.valueOf(((RwdState) Mem
												.getRwdStateList().get(0))
												.getIssue())).toString()) + 1);
					}

					String op_issue = "";
					DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					int k = 0;
					try
					{
						Date date1 = sdf.parse(this.tag);
						Date date2 = sdf.parse(d.format(new Date()).toString());
						k = date1.compareTo(date2);
					}
					catch (ParseException e1)
					{
						e1.printStackTrace();
					}
					if (k > 0)
						op_issue = ((RwdState) Mem.getRwdStateList().get(0))
								.getIssue();
					else
					{
						op_issue = this.newissue;
					}
					this.parameter.put("op_issue", op_issue);

					modityScore(freeNum.toString(), frozenNum.toString(),
							exchangeCount.toString(), exchangeType.toString(),
							exchangeScore.toString(), op_issue, "1",
							this.phone, null, session);

					getLuckyNum(Integer.parseInt(exchangeCount.toString()),
							op_issue, session);
				}

				trans.commit();
				session.close();
				this.message = "1000";
			}

		}

		return execute();
	}

	public String rule()
	{
		return "score-rule";
	}

	private void modityScore(String freeNum, String frozenNum,
			String exchangeCount, String exchangeType, String changeScore,
			String op_issue, String scoreType, String phoneNum, String pid,
			Session session)
	{
		UiLuckyCodeScoreDetail uiLuckyCodeScoreDetail = new UiLuckyCodeScoreDetail();
		uiLuckyCodeScoreDetail.setScoreType(scoreType);
		uiLuckyCodeScoreDetail.setSvcId(phoneNum);
		uiLuckyCodeScoreDetail.setBuildTime(new Date());
		uiLuckyCodeScoreDetail.setScoreNum(changeScore);

		UiLuckyCodeScore uiLuckyCodeScore = new UiLuckyCodeScore();
		uiLuckyCodeScore.setSvcId(phoneNum);
		uiLuckyCodeScore.setFrozenNum(frozenNum);

		if ("0".equals(scoreType))
		{
			uiLuckyCodeScoreDetail.setTaskId(pid);
			uiLuckyCodeScoreDetail.setValidTime(new Date());
			uiLuckyCodeScore.setFreeNum(String.valueOf(Integer
					.parseInt(freeNum) + Integer.parseInt(changeScore)));
		}
		else if ("1".equals(scoreType))
		{
			uiLuckyCodeScoreDetail.setExchangeStatus("0");
			uiLuckyCodeScoreDetail.setExchangeType(exchangeType);
			uiLuckyCodeScoreDetail.setExchangeCount(exchangeCount);

			uiLuckyCodeScore.setFreeNum(String.valueOf(Integer
					.parseInt(freeNum) - Integer.parseInt(changeScore)));

			if (op_issue != null)
			{
				uiLuckyCodeScoreDetail.setExchangeTime(op_issue);
			}
			else
			{
				Date date = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(2, 3);
				uiLuckyCodeScoreDetail.setExchangeTime(new SimpleDateFormat(
						"yyyy/MM/dd").format(cal.getTime()));
			}

		}

		session.save(uiLuckyCodeScoreDetail);

		session.saveOrUpdate(uiLuckyCodeScore);
	}

	private void getLuckyNum(int exchangeNum, String op_issue, Session session)
	{
		String row_str_id = "";
		String row_str_number = "";
		int n = Mem.getNumberId(exchangeNum);
		String hqlLucky = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID >= "
				+ (n - exchangeNum + 1) + " and " + n + " >=T.ID";
		Query query = session.createSQLQuery(hqlLucky);
		List uList = query.list();
		if ((uList != null) && (uList.size() > 0))
		{
			String value = "";
			for (int i = 0; i < uList.size(); ++i)
			{
				Object[] row = (Object[]) uList.get(i);
				row_str_id = row[0].toString();
				row_str_number = row[1].toString();
				if ((row_str_number == null) || (row_str_number.length() <= 0))
					continue;
				UiLuckyCodeRwdId uiRandom50RwdId = new UiLuckyCodeRwdId();
				uiRandom50RwdId.setIssue(op_issue);
				uiRandom50RwdId.setRandom(row_str_number);
				uiRandom50RwdId.setSvcId(this.phone);
				uiRandom50RwdId.setTag("5");
				uiRandom50RwdId.setWinTag("0");
				UiLuckyCodeRwd uiRandom50Rwd = new UiLuckyCodeRwd();
				uiRandom50Rwd.setId(uiRandom50RwdId);
				session.save(uiRandom50Rwd);
				if (i % 100 == 0)
				{
					session.flush();
					session.clear();
				}

				if ((value != null) && (value.length() > 0))
					value = value + "," + row_str_number + ":" + "0" + ":"
							+ op_issue + ":" + "5";
				else
				{
					value = row_str_number + ":" + "0" + ":" + op_issue + ":"
							+ "5";
				}
			}

			Mem.addRwd(this.phone, value);
		}
	}

	private void detailSwitch(String scoreType, Session session)
	{
		Query query = session
				.getNamedQuery(
						"hbm.UiLuckyCodeScoreDetail.queryLuckyCodeScoreDetail")
				.setString("scoreType", scoreType)
				.setString("svcId", this.phone);

		pagiSplit(query, "UiLuckyCodeScoreDetail");

		List list = query.list();
		if ((list != null) && (list.size() > 0))
		{
			this.scoreDetailList = new ArrayList();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < list.size(); ++i)
			{
				UiLuckyCodeScoreDetail uiLuckyCodeScoreDetail = null;
				UiLuckyCodeTask uiLuckyCodeTask = null;
				Map map = new HashMap();
				if (((Object[]) list.get(i))[0] instanceof UiLuckyCodeScoreDetail)
				{
					uiLuckyCodeScoreDetail = (UiLuckyCodeScoreDetail) ((Object[]) list
							.get(i))[0];
					map.put("SCORE_NUM", uiLuckyCodeScoreDetail.getScoreNum());
					map.put("BUILD_TIME",
							sf.format(uiLuckyCodeScoreDetail.getBuildTime()));
				}

				if ("0".equals(scoreType))
				{
					if (((Object[]) list.get(i))[1] instanceof UiLuckyCodeTask)
					{
						uiLuckyCodeTask = (UiLuckyCodeTask) ((Object[]) list
								.get(i))[1];
					}
					if ((uiLuckyCodeScoreDetail == null)
							&& (uiLuckyCodeTask == null))
					{
						return;
					}
					map.put("TASK_NAME", uiLuckyCodeTask.getId().getPName());

					if (uiLuckyCodeScoreDetail.getValidTime().after(new Date()))
						map.put("SCORE_FLAG",
								"生效时间:"
										+ sf.format(uiLuckyCodeScoreDetail
												.getValidTime()));
					else
						map.put("SCORE_FLAG", "已生效");
				}
				else
				{
					if (uiLuckyCodeScoreDetail == null)
					{
						return;
					}
					map.put("EXCHANGE_TYPE",
							uiLuckyCodeScoreDetail.getExchangeType());
					map.put("EXCHANGE_COUNT",
							uiLuckyCodeScoreDetail.getExchangeCount());
					map.put("EXCHANGE_STATUS",
							uiLuckyCodeScoreDetail.getExchangeStatus());
					map.put("EXCHANGE_TIME",
							uiLuckyCodeScoreDetail.getExchangeTime());
				}

				this.scoreDetailList.add(map);
			}
		}
	}

	private void pagiSplit(Query query, String obj)
	{
		if (query == null)
		{
			return;
		}

		int totalCount = query.list().size();
		if ((totalCount != 0) && (totalCount % this.pageSize == 0))
			this.totalPage = (query.list().size() / this.pageSize);
		else
		{
			this.totalPage = (query.list().size() / this.pageSize + 1);
		}

		if (this.page <= 0)
			this.page = 1;
		else if ((this.page >= this.totalPage) && (this.totalPage > 0))
		{
			this.page = this.totalPage;
		}
		query.setFirstResult(this.page * this.pageSize - this.pageSize)
				.setMaxResults(this.pageSize);
	}

	public List<Map> getScoreList()
	{
		return this.scoreList;
	}

	public void setScoreList(List<Map> scoreList)
	{
		this.scoreList = scoreList;
	}

	public List<Map> getScoreDetailList()
	{
		return this.scoreDetailList;
	}

	public void setScoreDetailList(List<Map> scoreDetailList)
	{
		this.scoreDetailList = scoreDetailList;
	}

	public Map getParameter()
	{
		return this.parameter;
	}

	public void setParameter(Map parameter)
	{
		this.parameter = parameter;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getTag()
	{
		return this.tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public String getNewissue()
	{
		return this.newissue;
	}

	public void setNewissue(String newissue)
	{
		this.newissue = newissue;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getRandom()
	{
		return this.random;
	}

	public void setRandom(String random)
	{
		this.random = random;
	}

	public String getTerminalType()
	{
		return this.terminalType;
	}

	public void setTerminalType(String terminalType)
	{
		this.terminalType = terminalType;
	}

	public int getPage()
	{
		return this.page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getPageSize()
	{
		return this.pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getTotalPage()
	{
		return this.totalPage;
	}

	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage;
	}
}