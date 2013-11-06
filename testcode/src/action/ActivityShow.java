package action;

import com.opensymphony.xwork2.ActionSupport;
import hbm.UiLuckyCodeTask;
import hbm.UiLuckyCodeTaskId;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class ActivityShow extends ActionSupport
{
	private String type;

	private String urlTag;

	private String phoneString;

	private String random;

	private String PId;

	private String spPid;

	private String PLuckyNum;

	private String anzhuoUrl;

	private String phone;

	private int page;

	private int endPage;

	private String terminalType;

	public String taskOrder()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletResponse Response = ServletActionContext.getResponse();
		if ((this.type == "1") || ("1".equals(this.type)))
		{
			if ((this.anzhuoUrl != "0") && (!("0".equals(this.anzhuoUrl))))
				try
				{
					Response.sendRedirect("ActivityAction!goTask.action?phone="
							+ this.phone + "&random=" + this.random
							+ "&terminalType=" + "2" + "&anzhuoUrl="
							+ this.anzhuoUrl + "&PId=" + this.PId
							+ "&PLuckyNum=" + this.PLuckyNum);
				}
				catch (Exception localException1)
				{
				}
			if ((this.urlTag != "0") && (!("0".equals(this.urlTag))))
				try
				{
					Response.sendRedirect("ActivityAction!" + this.urlTag
							+ ".action?phone=" + this.phone + "&random="
							+ this.random + "&terminalType=" + "2" + "&PId="
							+ this.PId + "&PLuckyNum=" + this.PLuckyNum);
				}
				catch (Exception localException2)
				{
				}
		}
		if ((((this.type == "2") || ("2".equals(this.type))))
				&& (this.urlTag != "0") && (!("0".equals(this.urlTag))))
		{
			try
			{
				Response.sendRedirect("ActivityAction!" + this.urlTag
						+ ".action?phone=" + this.phone + "&terminalType="
						+ "2" + "&random=" + this.random + "&spid=" + this.PId
						+ "&sppid=" + this.spPid + "&PLuckyNum="
						+ this.PLuckyNum);
			}
			catch (Exception localException3)
			{
			}
		}

		if ((((this.type == "3") || ("3".equals(this.type))))
				&& (this.urlTag != "0") && (!("0".equals(this.urlTag))))
		{
			try
			{
				Response.sendRedirect("ActivityAction!" + this.urlTag
						+ ".action?phone=" + this.phone + "&random="
						+ this.random + "&terminalType=" + "2" + "&productid="
						+ this.PId + "&PLuckyNum=" + this.PLuckyNum);
			}
			catch (Exception localException4)
			{
			}
		}

		if ((this.type == "4") || ("4".equals(this.type)))
		{
			try
			{
				Response.sendRedirect("ActivityAction!goTask.action?phone="
						+ this.phone + "&anzhuoUrl=" + this.anzhuoUrl
						+ "&random=" + this.random + "&terminalType=" + "2"
						+ "&pId=" + this.PId);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if ((this.type == "5") || ("5".equals(this.type)))
		{
			try
			{
				Response.sendRedirect("ActivityAction!" + this.urlTag
						+ ".action?phone=" + this.phone + "&random="
						+ this.random + "&terminalType=" + "2" + "&pId="
						+ this.PId);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if ((this.type == "6") || ("6".equals(this.type)))
		{
			try
			{
				Response.sendRedirect("ActivityAction!goTask.action?phone="
						+ this.phone + "&anzhuoUrl=" + this.anzhuoUrl
						+ "&random=" + this.random + "&terminalType=" + "2"
						+ "&pId=" + this.PId);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return taskList2g();
	}

	public String taskList2g()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		List UiLuckyCodeTask = Mem.getTask2gList();
		if ((UiLuckyCodeTask != null) || (UiLuckyCodeTask.size() > 0))
		{
			for (int i = 0; i < UiLuckyCodeTask.size(); ++i)
			{
				UiLuckyCodeTaskId ukt = new UiLuckyCodeTaskId();
				UiLuckyCodeTask ut = new UiLuckyCodeTask();
				UiLuckyCodeTask ss = (UiLuckyCodeTask) UiLuckyCodeTask.get(i);
				ukt.setPName(ss.getId().getPName());
				ukt.setPDesc(ss.getId().getPDesc());
				ukt.setPLuckyNum(ss.getId().getPLuckyNum());
				ukt.setButtonDesc(ss.getId().getButtonDesc());
				ukt.setDescTag(ss.getId().getDescTag());
				ut.setId(ukt);
				int numpage = 10;
				List list = new ArrayList();
				int num = UiLuckyCodeTask.size();
				if ((UiLuckyCodeTask != null) && (num > 0))
				{
					if (this.page < 0)
					{
						this.page = 0;
					}
					this.endPage = (int) Math.floor(num / numpage);
					if (num % numpage == 0)
					{
						this.endPage -= 1;
					}
					if (this.page > this.endPage)
					{
						this.page = this.endPage;
					}
					for (int j = 0; j < num; ++j)
					{
						if ((j >= this.page * 10) && (j < (this.page + 1) * 10))
						{
							list.add((UiLuckyCodeTask) UiLuckyCodeTask.get(j));
						}
					}
				}
				request.setAttribute("task2gList", list);
				request.setAttribute("page", Integer.valueOf(this.page));
				request.setAttribute("endPage", Integer.valueOf(this.endPage));
				list = null;
			}
		}
		request.setAttribute("random", this.random);
		request.setAttribute("type", this.type);
		UiLuckyCodeTask = null;
		return "toShowTask";
	}

	public String fenxiang()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("terminalType", this.terminalType);
		request.setAttribute("random", this.random);
		request.setAttribute("spid", this.PId);
		if ("3".equals(this.terminalType))
		{
			return "fenxiang";
		}
		return "fenxiang-2g";
	}

	public String sign()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("terminalType", this.terminalType);
		request.setAttribute("random", this.random);
		request.setAttribute("spid", this.PId);
		if ("3".equals(this.terminalType))
		{
			return "sign";
		}
		return "sign-2g";
	}

	public String toSeedSms()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("terminalType", this.terminalType);
		request.setAttribute("random", this.random);
		request.setAttribute("spid", this.PId);
		request.setAttribute("sppid", this.spPid);
		request.setAttribute("PLuckyNum", this.PLuckyNum);
		if ("3".equals(this.terminalType))
		{
			return "toSeedSms";
		}
		return "toSeedSms-2g";
	}

	public String jmj()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		request.setAttribute("pId", this.PId);
		if ("3".equals(this.terminalType))
		{
			return "jmj";
		}
		return "jmj-2g";
	}

	public String yidao()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		if ("3".equals(this.terminalType))
		{
			return "yidao";
		}
		return "yidao-2g";
	}

	public String getType()
	{
		return this.type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getUrlTag()
	{
		return this.urlTag;
	}

	public void setUrlTag(String urlTag)
	{
		this.urlTag = urlTag;
	}

	public String getPhoneString()
	{
		return this.phoneString;
	}

	public void setPhoneString(String phoneString)
	{
		this.phoneString = phoneString;
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

	public String getPLuckyNum()
	{
		return this.PLuckyNum;
	}

	public void setPLuckyNum(String luckyNum)
	{
		this.PLuckyNum = luckyNum;
	}

	public String getAnzhuoUrl()
	{
		return this.anzhuoUrl;
	}

	public void setAnzhuoUrl(String anzhuoUrl)
	{
		this.anzhuoUrl = anzhuoUrl;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public int getPage()
	{
		return this.page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getEndPage()
	{
		return this.endPage;
	}

	public void setEndPage(int endPage)
	{
		this.endPage = endPage;
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