package action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class P extends ActionSupport
{
	private String p;

	private String t;

	private String r;

	private String u;

	private String o;

	private String message;

	public String execute()
	{
		this.p = FilterHtml.Html2Text(this.p);
		this.r = FilterHtml.Html2Text(this.r);
		this.t = FilterHtml.Html2Text(this.t);
		this.u = FilterHtml.Html2Text(this.u);
		this.o = FilterHtml.Html2Text(this.o);

		Calendar rightNow = new GregorianCalendar();
		if ((Mem.getUserCodeMap().get(this.p) != null)
				&& (((String) Mem.getUserCodeMap().get(this.p)).length() > 0))
		{
			String[] code = ((String) Mem.getUserCodeMap().get(this.p))
					.split(",");
			if ((code[1] != null) && (code[1].length() > 0)
					&& (code[0] != null) && (code[0].length() > 0)
					&& (rightNow.getTimeInMillis() < Long.parseLong(code[1])))
			{
				if (!(code[0].equals(this.r)))
				{
					this.message = "地址链接已失效,请重新获取";
					return "noMiddleActivity-2g";
				}
				if ((this.u != null)
						&& (this.u.length() > 0)
						&& (this.u
								.matches("^(139|138|137|136|135|134|159|150|158|151|159|157|188|187|152|182|147)\\d{8}$")))
				{
					SeedSmsPhone seedSmsPhone = new SeedSmsPhone();
					seedSmsPhone.querySeedSms(this.p, this.u, this.r, this.t);
				}
				else
				{
					HttpServletResponse response = ServletActionContext
							.getResponse();
					try
					{
						response.sendRedirect("ActivityAction.action?phone="
								+ this.p + "&random=" + this.r
								+ "&terminalType=" + this.t + "&op=" + this.o
								+ "&op_tag=3");
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}

		}

		this.message = "地址链接已失效,请重新获取";
		return "noMiddleActivity-2g";
	}

	public String getP()
	{
		return this.p;
	}

	public void setP(String p)
	{
		this.p = p;
	}

	public String getT()
	{
		return this.t;
	}

	public void setT(String t)
	{
		this.t = t;
	}

	public String getR()
	{
		return this.r;
	}

	public void setR(String r)
	{
		this.r = r;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getU()
	{
		return this.u;
	}

	public void setU(String u)
	{
		this.u = u;
	}

	public String getO()
	{
		return this.o;
	}

	public void setO(String o)
	{
		this.o = o;
	}
}