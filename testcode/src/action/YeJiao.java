package action;

import com.bonc.wo_key.WoCheck;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class YeJiao
{
	private String phonenum;

	private String userkey;

	private String phone;

	private String random;

	private String terminalType;

	public String execute()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);

		HttpServletRequest request = ServletActionContext.getRequest();

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		request.setAttribute("phonenum", this.phone);
		request.setAttribute("userkey", WoCheck.make(this.phone));
		return "gomenhu";
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