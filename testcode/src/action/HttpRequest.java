package action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;

public class HttpRequest
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
		try
		{
			HttpRequest httpcon = new HttpRequest();
			Map params = new HashMap();
			params.put("authenticationUserName", "bonc");
			params.put("authenticationPassword", "bjbonc");
			JSONObject jsObject = JSONObject.fromObject(httpcon.doHttpPost(
					"http://192.168.72.14:7777/NetBill/skey/create", params));
			skey = jsObject.getString("skey");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return skey;
	}

	public String getPhone(String skey)
	{
		String phone = "";
		try
		{
			HttpRequest httpcon = new HttpRequest();
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
			}
		}
		catch (Exception localException)
		{
		}
		return phone;
	}
}