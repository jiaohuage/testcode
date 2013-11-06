package action;

import com.opensymphony.xwork2.ActionSupport;
import hbm.RwdState;
import hbm.UiLuckyCodeFiveMark;
import hbm.UiLuckyCodeFiveMarkId;
import hibernate.HibernateSessionFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FiveMarkAction extends ActionSupport
{
	private String phone;

	private String random;

	private String terminalType;

	public String execute()
	{
		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		Transaction trans = session.beginTransaction();
		UiLuckyCodeFiveMarkId uiLuckyCodeFiveMarkId = new UiLuckyCodeFiveMarkId();
		uiLuckyCodeFiveMarkId.setPhone(this.phone);
		uiLuckyCodeFiveMarkId.setCreateDate(d.format(new Date()).toString());
		uiLuckyCodeFiveMarkId
				.setIssue(((RwdState) Mem.getRwdStateList().get(0)).getIssue());
		UiLuckyCodeFiveMark uiLuckyCodeFiveMark = new UiLuckyCodeFiveMark();
		uiLuckyCodeFiveMark.setId(uiLuckyCodeFiveMarkId);
		session.save(uiLuckyCodeFiveMark);
		trans.commit();
		if ("3".equals(this.terminalType))
		{
			return "mark";
		}
		return "mark-2g";
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
}