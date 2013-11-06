package action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class AAction extends ActionSupport
{
	private String phone;

	public String a()
	{
		String message = "false";
		String value = (String) M.getFlowMap().get(this.phone);
		if ((value == null) || (value.length() <= 0))
		{
			message = "true";
			M.getFlowMap().put(this.phone, "1");
			M.addFlowList(this.phone);
		}
		PrintWriter pw = null;
		try
		{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			pw = response.getWriter();
			pw.print(message);
			pw.flush();
		}
		catch (Exception localException)
		{
		}
		finally
		{
			if (pw != null)
			{
				pw.close();
			}
		}
		return null;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}
}