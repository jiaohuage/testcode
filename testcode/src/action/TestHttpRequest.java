package action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

public class TestHttpRequest extends ActionSupport
{
	public String doHttpPost(String url, Map<String, String> params)
	{
		HttpURLConnection urlConnection = null;
		String outLine = "";
		try
		{
			String paramStr = "1=1";
			if (params.size() > 0)
			{
				for (String key : params.keySet())
				{
					paramStr = paramStr + "&" + key + "="
							+ ((String) params.get(key));
				}
			}
			URL conurl = new URL(url);
			urlConnection = (HttpURLConnection) conurl.openConnection();
			urlConnection.setConnectTimeout(3000);
			urlConnection.setRequestMethod("POST");
			urlConnection.setUseCaches(false);
			urlConnection.setDoOutput(true);
			urlConnection.getOutputStream().write(paramStr.getBytes("UTF-8"));
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream(), "UTF-8"), 1048576);
			String sCurrentLine = "";
			while ((sCurrentLine = in.readLine()) != null)
			{
				outLine = outLine + sCurrentLine;
			}
			urlConnection.disconnect();
		}
		catch (Exception e)
		{
			System.out
					.println("==================NET计费第三步获取手机号码==用户内网没有权限访问NET计费========");
		}
		return outLine;
	}

	public String getKey()
	{
		String skey = "";
		System.out.println("========Net计费第一步  获取skey============");
		try
		{
			TestHttpRequest httpcon = new TestHttpRequest();
			Map params = new HashMap();
			params.put("authenticationUserName", "bonc");
			params.put("authenticationPassword", "bjbonc");
			JSONObject jsObject = JSONObject.fromObject(httpcon.doHttpPost(
					"http://192.168.72.14:7777/NetBill/skey/create", params));
			skey = jsObject.getString("skey");
			System.out.println("========skey值：============     skey=" + skey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return skey;
	}

	public String getPhone(String skey)
	{
		System.out.println("skey=" + skey);
		String message = "";
		String phone = "";
		if ((!("".equals(skey))) && (skey.length() > 0))
		{
			System.out.println("========Net计费第三步  获取电话号码phone============");
			try
			{
				TestHttpRequest httpcon = new TestHttpRequest();
				Map params = new HashMap();
				params.put("authenticationUserName", "bonc");
				params.put("authenticationPassword", "bjbonc");
				params.put("productid", "Ericsson");
				params.put("skey", skey);
				String get_phone = httpcon.doHttpPost(
						"http://192.168.72.14:7777/NetBill/netbill/applyMisdn",
						params);
				if ((get_phone != null) && (get_phone.length() > 0))
				{
					JSONObject jo = JSONObject.fromObject(get_phone);
					phone = jo.getString("msisdn");
					System.out.println("========phone值：============     phone="
							+ phone);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		message = phone;
		PrintWriter pw = null;
		try
		{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			pw = response.getWriter();
			pw.print(message);
			pw.flush();
		}
		catch (Exception localException1)
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
}