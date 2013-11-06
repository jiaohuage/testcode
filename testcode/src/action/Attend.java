package action;

import hbm.RwdState;
import hbm.UiLuckyCodePortal;
import hbm.UiLuckyCodePortalId;
import hbm.UiLuckyCodePortalLogin;
import hbm.UiLuckyCodePortalLoginId;
import hbm.UiLuckyCodeRwd;
import hbm.UiLuckyCodeRwdId;
import hbm.UiLuckyCodeYgj;
import hbm.UiLuckyCodeYgjId;
import hibernate.HibernateSessionFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Attend
{
	private String phonenum;

	private String userkey;

	private String op;

	public String execute()
	{
		this.op = FilterHtml.Html2Text(this.op);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("issue",
				((RwdState) Mem.getRwdStateList().get(0)).getIssue());

		request.setAttribute("lotteryDate", ((RwdState) Mem.getRwdStateList()
				.get(0)).getLotteryDate());

		request.setAttribute("tagState",
				((RwdState) Mem.getRwdStateList().get(0)).getTagState());

		request.setAttribute("luckyCode", ((RwdState) Mem.getRwdStateList()
				.get(0)).getLuckyCode());

		request.setAttribute("phonenum", this.phonenum);
		request.setAttribute("userkey", this.userkey);
		return "attend";
	}

	public void insertPortal(String phone, String sources)
	{
		phone = FilterHtml.Html2Text(phone);
		this.op = FilterHtml.Html2Text(this.op);

		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		if ((Mem.getRwdStateList() != null)
				&& (Mem.getRwdStateList().size() > 0))
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Transaction trans = session.beginTransaction();
			UiLuckyCodePortalId uiLuckyCodePortalId = new UiLuckyCodePortalId();
			uiLuckyCodePortalId.setCreateTime(df.format(new Date()).toString());
			uiLuckyCodePortalId.setIssue(((RwdState) Mem.getRwdStateList().get(
					0)).getIssue());
			uiLuckyCodePortalId.setPhone(phone);
			uiLuckyCodePortalId.setSources(sources);
			UiLuckyCodePortal uiLuckyCodePortal = new UiLuckyCodePortal();
			uiLuckyCodePortal.setId(uiLuckyCodePortalId);
			session.save(uiLuckyCodePortal);
			trans.commit();
		}
		HibernateSessionFactory.closeSession();
	}

	public void insertPortalLogin(String phone, String sources, String tag)
	{
		phone = FilterHtml.Html2Text(phone);
		this.op = FilterHtml.Html2Text(this.op);

		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		if ((Mem.getRwdStateList() != null)
				&& (Mem.getRwdStateList().size() > 0))
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Transaction trans = session.beginTransaction();
			UiLuckyCodePortalLoginId uiLuckyCodePortalLoginId = new UiLuckyCodePortalLoginId();
			uiLuckyCodePortalLoginId.setCreateTime(df.format(new Date())
					.toString());
			uiLuckyCodePortalLoginId.setIssue(((RwdState) Mem.getRwdStateList()
					.get(0)).getIssue());
			uiLuckyCodePortalLoginId.setPhone(phone);
			uiLuckyCodePortalLoginId.setSources(sources);
			uiLuckyCodePortalLoginId.setTag(tag);
			UiLuckyCodePortalLogin uiLuckyCodePortalLogin = new UiLuckyCodePortalLogin();
			uiLuckyCodePortalLogin.setId(uiLuckyCodePortalLoginId);
			session.save(uiLuckyCodePortalLogin);
			trans.commit();
		}
		HibernateSessionFactory.closeSession();
	}

	public String queryInUrl()
	{
		this.op = FilterHtml.Html2Text(this.op);

		if ((this.op == "menhu") || ("menhu".equals(this.op)))
		{
			insertPortal(this.phonenum, "menhu");
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phonenum", this.phonenum);
		request.setAttribute("userkey", this.userkey);
		request.setAttribute("op", this.op);
		return "queryInUrl";
	}

	public void ygj(String phone, String sources)
	{
		phone = FilterHtml.Html2Text(phone);
		this.op = FilterHtml.Html2Text(this.op);

		boolean b = false;
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		String hq = "SELECT * FROM (SELECT t.com_date FROM UI_LUCKY_CODE_YGJ T where t.phone = "
				+ phone + " ORDER BY T.com_date desc) WHERE ROWNUM = 1";
		Query q = session.createSQLQuery(hq);
		List list = q.list();
		if ((list != null) && (list.size() > 0))
		{
			String comDate = list.get(0).toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			int k = -2;
			try
			{
				Date date1 = sdf.parse(sdf.format(new Date()).toString());
				Date date2 = sdf.parse(comDate);
				k = date1.compareTo(date2);
			}
			catch (ParseException e1)
			{
				e1.printStackTrace();
			}
			if (k >= 0)
				b = true;
		}
		else
		{
			b = true;
		}
		if (b)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Transaction trans = session.beginTransaction();
			UiLuckyCodeYgjId uiLuckyCodeYgjId = new UiLuckyCodeYgjId();
			uiLuckyCodeYgjId.setPhone(phone);
			uiLuckyCodeYgjId.setIssue(((RwdState) Mem.getRwdStateList().get(0))
					.getIssue());
			uiLuckyCodeYgjId.setComDate(getNextMonthFirst());
			uiLuckyCodeYgjId.setCreateDate(sdf.format(new Date()).toString());
			UiLuckyCodeYgj uiLuckyCodeYgj = new UiLuckyCodeYgj();
			uiLuckyCodeYgj.setId(uiLuckyCodeYgjId);
			String mes = session.save(uiLuckyCodeYgj).toString();
			trans.commit();
			String tag = "";
			String newissue = "";
			int num = 1;

			if ((mes != null) && (mes.length() > 0) && (phone != null)
					&& (phone.length() > 0))
			{
				if ((Mem.getRwdStateList() != null)
						&& (Mem.getRwdStateList().size() > 0))
				{
					tag = ((RwdState) Mem.getRwdStateList().get(0)).getTag();
					newissue = String
							.valueOf(Integer.parseInt(new StringBuilder(String
									.valueOf(((RwdState) Mem.getRwdStateList()
											.get(0)).getIssue())).toString()) + 1);
				}

				String op_issue = "";
				DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				int k = 0;
				try
				{
					Date date1 = sdf.parse(tag);
					Date date2 = sdf.parse(d.format(new Date()).toString());
					k = date1.compareTo(date2);
				}
				catch (ParseException e1)
				{
					e1.printStackTrace();
				}

				String row_str_id = "";
				String row_str_number = "";
				int n = Mem.getNumberId(num);
				String hql = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID >= "
						+ (n - num + 1) + " and " + n + " >=T.ID";
				Query query = session.createSQLQuery(hql);
				List uList = query.list();
				if ((uList != null) && (uList.size() > 0))
				{
					if (k > 0)
					{
						op_issue = ((RwdState) Mem.getRwdStateList().get(0))
								.getIssue();
					}
					else
					{
						op_issue = newissue;
					}
					String value = "";
					Transaction tx = session.beginTransaction();
					for (int i = 0; i < uList.size(); ++i)
					{
						Object[] row = (Object[]) uList.get(i);
						row_str_id = row[0].toString();
						row_str_number = row[1].toString();
						if ((row_str_number == null)
								|| (row_str_number.length() <= 0))
							continue;
						UiLuckyCodeRwdId uiRandom50RwdId = new UiLuckyCodeRwdId();
						uiRandom50RwdId.setIssue(op_issue);
						uiRandom50RwdId.setRandom(row_str_number);
						uiRandom50RwdId.setSvcId(phone);
						uiRandom50RwdId.setTag("10");
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
							value = value + "," + row_str_number + ":" + "0"
									+ ":" + op_issue + ":" + "10";
						else
						{
							value = row_str_number + ":" + "0" + ":" + op_issue
									+ ":" + "10";
						}

					}

					tx.commit();

					session.close();
					Mem.addRwd(phone, value);
				}
			}
		}
		HibernateSessionFactory.closeSession();
	}

	public String getNextMonthFirst()
	{
		this.op = FilterHtml.Html2Text(this.op);

		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(2, 1);
		lastDate.set(5, 1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	public String getPhonenum()
	{
		return this.phonenum;
	}

	public void setPhonenum(String phonenum)
	{
		this.phonenum = phonenum;
	}

	public String getUserkey()
	{
		return this.userkey;
	}

	public void setUserkey(String userkey)
	{
		this.userkey = userkey;
	}

	public String getOp()
	{
		return this.op;
	}

	public void setOp(String op)
	{
		this.op = op;
	}
}