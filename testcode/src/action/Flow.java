package action;

import com.opensymphony.xwork2.ActionSupport;
import hibernate.HibernateSessionFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Flow extends ActionSupport
{
	public String execute()
	{
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		String monthId = "";
		String dayId = "";
		String h2 = "";
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		String hq1 = "select * from (SELECT  MONTH_ID FROM UI_D_USE_OVER_GPRS_CB T order by MONTH_ID desc) where rownum = 1";
		Query q1 = session.createSQLQuery(hq1);
		List li1 = q1.list();
		if ((li1 != null) && (li1.size() > 0))
		{
			monthId = li1.get(0).toString();
			String hq2 = "SELECT * FROM (SELECT  DAY_ID FROM UI_D_USE_OVER_GPRS_CB T WHERE T.MONTH_ID = '"
					+ monthId + "' ORDER BY DAY_ID DESC) WHERE ROWNUM = 1";
			Query q2 = session.createSQLQuery(hq2);
			List li2 = q2.list();
			if ((li2 != null) && (li2.size() > 0))
			{
				dayId = li2.get(0).toString();
				String onMonthFirst = getOnMonthFirst(monthId);
				String day = getOnMonthLastDay(monthId);
				h2 = " select '1',CAST(ROUND(F5*100,2) AS NUMERIC(15,2)),CAST(ROUND(F6*100,2) AS NUMERIC(15,2)),F4";
				h2 = h2 + " from UI_D_USE_OVER_GPRS_CB t where t.month_id = '"
						+ monthId + "' and t.day_id = '" + dayId
						+ "' and t.service_type = '11'";
				h2 = h2 + " union all";
				h2 = h2
						+ " select '2',CAST(ROUND(F5*100,2) AS NUMERIC(15,2)),CAST(ROUND(F6*100,2) AS NUMERIC(15,2)),F4";
				h2 = h2 + " from UI_D_USE_OVER_GPRS_CB t where t.month_id = '"
						+ onMonthFirst + "' and t.day_id = '" + dayId
						+ "' and t.service_type = '11'";
				h2 = h2 + " union all";
				h2 = h2
						+ " select '3',CAST(ROUND(F5*100,2) AS NUMERIC(15,2)),CAST(ROUND(F6*100,2) AS NUMERIC(15,2)),F4";
				h2 = h2 + " from UI_D_USE_OVER_GPRS_CB t where t.month_id = '"
						+ onMonthFirst + "' and t.day_id = '" + day
						+ "' and t.service_type = '11'";
				Query qq = session.createSQLQuery(h2);
				list2 = qq.list();
			}

		}

		request.setAttribute("monthId", monthId);
		request.setAttribute("dayId", dayId);
		request.setAttribute("list1", list1);
		request.setAttribute("list2", list2);
		session.close();
		return "toflow";
	}

	public String getOnMonthFirst(String monthId)
	{
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = null;
		try
		{
			date = sdf.parse(monthId);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.add(2, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	public String getOnMonthLastDay(String monthId)
	{
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = null;
		try
		{
			date = sdf.parse(monthId);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.add(2, -1);
		int day = lastDate.getActualMaximum(5);
		str = String.valueOf(day);
		return str;
	}
}