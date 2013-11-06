package action;

import hbm.UiLuckyCodeFlow;
import hbm.UiLuckyCodeFlowId;
import hibernate.HibernateSessionFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class M
{
	private static List flowList = null;

	private static int flowListId = 0;

	private static List insertFlowList = new ArrayList();

	private static Map<String, String> flowMap = null;

	static
	{
		flowList = new ArrayList();
		flowMap = new HashMap();
	}

	public static void addFlowList(String phone)
	{
		flowList.add(phone);
		flowListId += 1;
		if (flowListId > 49)
		{
			flowListId = 0;
			insertFlowList = flowList;
			flowList = new ArrayList();
			insertFlowTable();
		}
	}

	public static void insertFlowTable()
	{
		if (insertFlowList.size() > 0)
		{
			String key = "";
			for (int i = 0; i < insertFlowList.size(); ++i)
			{
				key = key + "," + insertFlowList.get(i);
				if (i > 200)
				{
					break;
				}
			}
			insertUiLuckyCodeFlow(key);
		}
	}

	public static void insertUiLuckyCodeFlow(String key)
	{
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		Transaction trans = session.beginTransaction();
		UiLuckyCodeFlowId UiLuckyCodeFlowId = new UiLuckyCodeFlowId();
		UiLuckyCodeFlowId.setPhone(key.toString());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UiLuckyCodeFlowId.setIssue(df.format(new Date()).toString());
		UiLuckyCodeFlow UiLuckyCodeFlow = new UiLuckyCodeFlow();
		UiLuckyCodeFlow.setId(UiLuckyCodeFlowId);
		session.save(UiLuckyCodeFlow);
		trans.commit();
		HibernateSessionFactory.closeSession();
	}

	public static List getFlowList()
	{
		return flowList;
	}

	public static void setFlowList(List flowList)
	{
		flowList = flowList;
	}

	public static int getFlowListId()
	{
		return flowListId;
	}

	public static void setFlowListId(int flowListId)
	{
		flowListId = flowListId;
	}

	public static List getInsertFlowList()
	{
		return insertFlowList;
	}

	public static void setInsertFlowList(List insertFlowList)
	{
		insertFlowList = insertFlowList;
	}

	public static Map<String, String> getFlowMap()
	{
		return flowMap;
	}

	public static void setFlowMap(Map<String, String> flowMap)
	{
		flowMap = flowMap;
	}
}