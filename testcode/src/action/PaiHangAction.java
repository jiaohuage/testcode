package action;

import com.opensymphony.xwork2.ActionSupport;
import hbm.RwdState;
import hbm.UiLuckyCodePaihang;
import hbm.UiLuckyCodePaihangId;
import hibernate.HibernateSessionFactory;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PaiHangAction extends ActionSupport
{
	private String phone;

	private String random;

	private String terminalType;

	private String phname;

	private String phurl;

	public String execute()
	{
		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phurl", this.phurl);
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		Transaction trans = session.beginTransaction();
		UiLuckyCodePaihangId uiLuckyCodePaihangId = new UiLuckyCodePaihangId();
		uiLuckyCodePaihangId.setPhone(this.phone);
		uiLuckyCodePaihangId.setPhname(this.phname);
		uiLuckyCodePaihangId.setIssue(((RwdState) Mem.getRwdStateList().get(0))
				.getIssue());
		UiLuckyCodePaihang uiLuckyCodePaihang = new UiLuckyCodePaihang();
		uiLuckyCodePaihang.setId(uiLuckyCodePaihangId);
		session.save(uiLuckyCodePaihang);
		trans.commit();
		return "topaihang";
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

	public String getPhname()
	{
		return this.phname;
	}

	public void setPhname(String phname)
	{
		this.phname = phname;
	}

	public String getTerminalType()
	{
		return this.terminalType;
	}

	public void setTerminalType(String terminalType)
	{
		this.terminalType = terminalType;
	}

	public String getPhurl()
	{
		return this.phurl;
	}

	public void setPhurl(String phurl)
	{
		this.phurl = phurl;
	}
}