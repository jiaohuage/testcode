package action;

import hbm.RwdState;
import hbm.UiLuckyCodeBefore;
import hbm.UiLuckyCodeBeforeId;
import hbm.UiLuckyCodeRwd;
import hbm.UiLuckyCodeRwdId;
import hbm.UiLuckyCodeRwdList;
import hbm.UiLuckyCodeTask;
import hbm.UiLuckyCodeUv;
import hbm.UiLuckyCodeUvId;
import hibernate.HibernateSessionFactory;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class Mem
{
	private static HashMap<String, String> currentIssueMap = null;

	private static List<UiLuckyCodeRwdList> uiRandom50RwdListOneList = null;

	private static List<UiLuckyCodeBefore> uiLuckyNumberUserSList = null;

	private static Map<String, String> showMyActivityMap = null;

	private static List<RwdState> rwdStateList = null;

	private static int numberId;

	private static List numberList = null;

	private static Map<String, String> userCodeMap = null;

	private static int firstPage;

	private static int myWinCode;

	private static int codeRule;

	private static int anCodeWin;

	private static List uvList = new ArrayList();

	private static int uvListId = 0;

	private static List insertUvList = new ArrayList();

	private static List<UiLuckyCodeTask> taskList = new ArrayList();

	private static List<UiLuckyCodeTask> task2gList = new ArrayList();

	private static List<UiLuckyCodeTask> hList = new ArrayList();

	private static List<UiLuckyCodeTask> h2gList = new ArrayList();

	private static int kefu;

	private static int fenxiang;

	private static List dataList = new ArrayList();

	static
	{
		System.out.println("-------static   start--------"
				+ System.currentTimeMillis());
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();

		String rwdHql = "FROM UiLuckyCodeRwd";
		Iterator it = session.createQuery(rwdHql).iterate();
		String tel = "";
		String random = "";
		String status = "";
		String tag = "";
		String iss = "";
		String value = "";
		currentIssueMap = new HashMap();
		while (it.hasNext())
		{
			UiLuckyCodeRwd u = (UiLuckyCodeRwd) it.next();
			if (u != null)
			{
				tel = u.getId().getSvcId();

				random = u.getId().getRandom();

				status = u.getId().getWinTag();

				tag = u.getId().getTag();

				iss = u.getId().getIssue();

				value = (String) currentIssueMap.get(tel);
				if ((value != null) && (value.length() > 0))
					value = value + "," + random + ":" + status + ":" + iss
							+ ":" + tag;
				else
				{
					value = random + ":" + status + ":" + iss + ":" + tag;
				}
				currentIssueMap.put(tel, value);
			}

			session.evict(u);
			HibernateSessionFactory.getSessionFactory().evict(
					UiLuckyCodeRwd.class, u.getId());
		}
		System.out.println("-------1---------");

		Criteria ccc = session.createCriteria(UiLuckyCodeRwdList.class);
		ccc.addOrder(Order.asc("id.winTag"));
		ccc.addOrder(Order.asc("id.svcNo"));
		uiRandom50RwdListOneList = ccc.list();
		System.out.println("-------2---------");

		Criteria cc1 = session.createCriteria(RwdState.class);
		rwdStateList = cc1.list();
		cc1 = null;
		System.out.println("-------3---------");

		String unusHql = "FROM UiLuckyCodeBefore";
		Iterator unusIt = session.createQuery(unusHql).iterate();
		showMyActivityMap = new HashMap();
		String unus_tel = "";
		String unus_random = "";
		String unus_status = "";
		String unus_iss = "";
		String unus_value = "";
		while (unusIt.hasNext())
		{
			UiLuckyCodeBefore uiLuckyNumberUserS = (UiLuckyCodeBefore) unusIt
					.next();
			if (uiLuckyNumberUserS != null)
			{
				unus_tel = uiLuckyNumberUserS.getId().getSvcId();

				unus_random = uiLuckyNumberUserS.getId().getRandom();

				unus_status = uiLuckyNumberUserS.getId().getWinTag();

				unus_iss = uiLuckyNumberUserS.getId().getIssue();

				unus_value = (String) showMyActivityMap.get(unus_tel);
				if ((unus_value != null) && (unus_value.length() > 0))
					unus_value = unus_value + "," + unus_random + ":"
							+ unus_status + ":" + unus_iss;
				else
				{
					unus_value = unus_random + ":" + unus_status + ":"
							+ unus_iss;
				}
				showMyActivityMap.put(unus_tel, unus_value);
			}

			session.evict(uiLuckyNumberUserS);
			HibernateSessionFactory.getSessionFactory().evict(
					UiLuckyCodeBefore.class, uiLuckyNumberUserS.getId());
		}

		String hql = "SELECT * FROM (SELECT * FROM UI_LUCKY_CODE_FREE T ORDER BY T.ID) WHERE ROWNUM = 1";
		Query query = session.createSQLQuery(hql);
		numberList = query.list();
		if ((numberList != null) && (numberList.size() > 0))
		{
			Object[] row = (Object[]) numberList.get(0);
			numberId = Integer.parseInt(row[0].toString());
		}
		numberList = null;
		System.out.println("-------4---------");
		userCodeMap = new HashMap();

		initial();

		Criteria c = session.createCriteria(UiLuckyCodeTask.class);
		c.add(Expression.eq("id.tag", "1"));
		c.addOrder(Order.desc("id.ord"));
		taskList = c.list();

		if ((taskList != null) && (taskList.size() > 0))
		{
			for (int i = 0; i < 4; ++i)
			{
				hList.add((UiLuckyCodeTask) taskList.get(i));
			}
		}

		Criteria tc = session.createCriteria(UiLuckyCodeTask.class);
		tc.add(Expression.eq("id.tag2g", "1"));
		tc.addOrder(Order.desc("id.ord"));
		task2gList = tc.list();

		if ((task2gList != null) && (task2gList.size() > 0))
		{
			for (int i = 0; i < 4; ++i)
			{
				h2gList.add((UiLuckyCodeTask) task2gList.get(i));
			}
		}
		session.clear();
		HibernateSessionFactory.closeSession();
		System.out.println("-------static   end--------"
				+ System.currentTimeMillis());
	}

	public static synchronized int addNumberId(int num)
	{
		CallableStatement cstmt;
		Session session;
		Connection con;
		if (num <= 0)
		{
			if (numberId > 1000000)
			{
				numberId = 1;

				session = HibernateSessionFactory.getSessionFactory()
						.openSession();
				con = session.connection();
				try
				{
					cstmt = con.prepareCall("{call P_UI_LUCKY_CODE_FREE}");
					cstmt.executeUpdate();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				return numberId;
			}
			return (numberId++);
		}

		if (numberId + num > 1000000)
		{
			numberId = num;

			session = HibernateSessionFactory.getSessionFactory().openSession();
			con = session.connection();
			try
			{
				cstmt = con.prepareCall("{call P_UI_LUCKY_CODE_FREE}");
				cstmt.executeUpdate();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return numberId;
		}
		numberId += num;
		return numberId;
	}

	public static synchronized void addRwd(String phone, String value)
	{
		String val = (String) currentIssueMap.get(phone);
		if ((val != null) && (val.length() > 0))
			val = val + "," + value;
		else
		{
			val = value;
		}
		getCurrentIssueMap().put(phone, val);
	}

	public static void initial()
	{
		firstPage = 0;
		myWinCode = 0;
		codeRule = 0;
		anCodeWin = 0;
		kefu = 0;
		fenxiang = 0;
	}

	public static void addFirstPage()
	{
		firstPage += 1;
	}

	public static void addCodeRule()
	{
		codeRule += 1;
	}

	public static void addAnCodeWin()
	{
		anCodeWin += 1;
	}

	public static void addUvList(String phone)
	{
		uvList.add(phone);
		uvListId += 1;
		if (uvListId > 100)
		{
			uvListId = 0;
			insertUvList = uvList;
			uvList = new ArrayList();
			insertUvTable();
		}
	}

	public static void insertUvTable()
	{
		if (insertUvList.size() > 0)
		{
			String key = "";
			for (int i = 0; i < insertUvList.size(); ++i)
			{
				key = key + "," + insertUvList.get(i);
				if (i > 200)
				{
					break;
				}
			}
			insertUiLuckyCodeUv(key);
		}
	}

	public static void addMyWinCode()
	{
		myWinCode += 1;
	}

	public static void addkefu()
	{
		kefu += 1;
	}

	public static void addseedMessage()
	{
		firstPage += 1;
	}

	public static void addfenxiang()
	{
		fenxiang += 1;
	}

	public static void insertUiLuckyCodeUv(String key)
	{
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Transaction trans = session.beginTransaction();
		UiLuckyCodeUvId UiLuckyCodeUvId = new UiLuckyCodeUvId();
		UiLuckyCodeUvId.setPhone(key.toString());
		UiLuckyCodeUvId.setIssue(((RwdState) getRwdStateList().get(0))
				.getIssue());
		UiLuckyCodeUvId.setCreateTime(df.format(new Date()).toString());
		UiLuckyCodeUv UiLuckyCodeUv = new UiLuckyCodeUv();
		UiLuckyCodeUv.setId(UiLuckyCodeUvId);
		session.save(UiLuckyCodeUv);
		trans.commit();
		HibernateSessionFactory.closeSession();
	}

	public static HashMap<String, String> getCurrentIssueMap()
	{
		return currentIssueMap;
	}

	public static List<UiLuckyCodeRwdList> getUiRandom50RwdListOneList()
	{
		return uiRandom50RwdListOneList;
	}

	public static List<UiLuckyCodeBefore> getUiLuckyNumberUserSList()
	{
		return uiLuckyNumberUserSList;
	}

	public static Map<String, String> getShowMyActivityMap()
	{
		return showMyActivityMap;
	}

	public static List<RwdState> getRwdStateList()
	{
		return rwdStateList;
	}

	public static int getNumberId(int num)
	{
		return addNumberId(num);
	}

	public static Map<String, String> getUserCodeMap()
	{
		return userCodeMap;
	}

	public static int getFirstPage()
	{
		return firstPage;
	}

	public static int getMyWinCode()
	{
		return myWinCode;
	}

	public static int getCodeRule()
	{
		return codeRule;
	}

	public static int getAnCodeWin()
	{
		return anCodeWin;
	}

	public static List getUvList()
	{
		return uvList;
	}

	public static int getUvListId()
	{
		return uvListId;
	}

	public static List getInsertUvList()
	{
		return insertUvList;
	}

	public static void setUiRandom50RwdListOneList(
			List<UiLuckyCodeRwdList> uiRandom50RwdListOneList)
	{
		uiRandom50RwdListOneList = uiRandom50RwdListOneList;
	}

	public static void setRwdStateList(List<RwdState> rwdStateList)
	{
		rwdStateList = rwdStateList;
	}

	public static List<UiLuckyCodeTask> getTaskList()
	{
		return taskList;
	}

	public static int getKefu()
	{
		return kefu;
	}

	public static void setFirstPage(int firstPage)
	{
		firstPage = firstPage;
	}

	public static List<UiLuckyCodeTask> getHList()
	{
		return hList;
	}

	public static int getFenxiang()
	{
		return fenxiang;
	}

	public static void setFenxiang(int fenxiang)
	{
		fenxiang = fenxiang;
	}

	public static List<UiLuckyCodeTask> getTask2gList()
	{
		return task2gList;
	}

	public static List<UiLuckyCodeTask> getH2gList()
	{
		return h2gList;
	}

	public static List getDataList()
	{
		return dataList;
	}

	public static void setDataList(List dataList)
	{
		dataList = dataList;
	}
}