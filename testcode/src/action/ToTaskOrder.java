package action;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class ToTaskOrder
{
	private String phone;

	private String random;

	private String PId;

	private String spPid;

	private String iphoneUrl;

	private String anzhuoUrl;

	private String urlTag;

	private String type;

	private String PLuckyNum;

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

	public String getPId()
	{
		return this.PId;
	}

	public void setPId(String id)
	{
		this.PId = id;
	}

	public String getSpPid()
	{
		return this.spPid;
	}

	public void setSpPid(String spPid)
	{
		this.spPid = spPid;
	}

	public String getIphoneUrl()
	{
		return this.iphoneUrl;
	}

	public void setIphoneUrl(String iphoneUrl)
	{
		this.iphoneUrl = iphoneUrl;
	}

	public String getAnzhuoUrl()
	{
		return this.anzhuoUrl;
	}

	public void setAnzhuoUrl(String anzhuoUrl)
	{
		this.anzhuoUrl = anzhuoUrl;
	}

	public String getUrlTag()
	{
		return this.urlTag;
	}

	public void setUrlTag(String urlTag)
	{
		this.urlTag = urlTag;
	}

	public String getType()
	{
		return this.type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getPLuckyNum()
	{
		return this.PLuckyNum;
	}

	public void setPLuckyNum(String luckyNum)
	{
		this.PLuckyNum = luckyNum;
	}

	public String anzhuo(String phone, String random, String terminalType,
			String PId, String anzhuoUrl)
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		try
		{
			response.sendRedirect("ActivityAction.action?phone=" + phone
					+ "&random=" + random + "&terminalType=" + terminalType
					+ "&PId" + PId + "&anzhuoUrl" + anzhuoUrl);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	public String execute()
	{
		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		try
		{
			response.sendRedirect("ActivityAction.action?phone=" + this.phone
					+ "&random=" + this.random + "&urlTag" + this.urlTag
					+ "&pId" + this.PId);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return "noMiddleActivity-2g";
	}
}