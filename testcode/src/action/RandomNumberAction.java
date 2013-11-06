package action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class RandomNumberAction extends ActionSupport
{
	private static final long serialVersionUID = 1024L;

	private String phone;

	private String random;

	private String message;

	private boolean send;

	private String israndom = "1";

	private String terminalType;

	private String userPhone;

	private String op;

	public static String getRandomNumber()
	{
		Random random = new Random();
		int i = random.nextInt(10000);
		while (i < 1000)
		{
			i += 1000;
		}
		String s = "";
		s = String.valueOf(i);
		return s;
	}

	public String MD5Encode(String s)
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);
		try
		{
			char[] hexDigits = { '0', '1', 't', '3', 'y', '5', '6', 'm', '8',
					'9', 'l', 'h', 'b', 's', 'w', 'j' };
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] str = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; ++i)
			{
				byte byte0 = md[i];
				str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
				str[(k++)] = hexDigits[(byte0 & 0xF)];
			}
			return new String(str);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public String execute()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("terminalType", this.terminalType);
		request.setAttribute("userPhone", this.userPhone);
		request.setAttribute("phone", this.phone);
		if ((this.phone != null)
				&& (this.phone.length() > 0)
				&& (this.phone
						.matches("^(139|138|137|136|135|134|159|150|158|151|159|157|188|187|152|182|147)\\d{8}$")))
		{
			String rondomnumber = getRandomNumber();
			String firstSms = "幸运3+3活动地址 http://33.vrmedia.com.cn/P.action?p="
					+ this.phone + "&r=" + rondomnumber + "&t="
					+ this.terminalType;
			if ((this.userPhone != null)
					&& (this.userPhone.length() > 0)
					&& (this.userPhone
							.matches("^(139|138|137|136|135|134|159|150|158|151|159|157|188|187|152|182|147)\\d{8}$")))
			{
				firstSms = firstSms + "&u=" + this.userPhone;
			}
			if ((this.op != null) && (!("".equals(this.op)))
					&& (!("null".equals(this.op))))
			{
				firstSms = firstSms + "&o=" + this.op;
			}
			if (PostSMS(firstSms, "10658688", this.phone))
			{
				Calendar rightNow = new GregorianCalendar();
				rightNow.add(11, 168);
				String value = "";
				value = (String) Mem.getUserCodeMap().get(this.phone);
				if ((value == null) || (value.length() <= 0))
				{
					Mem.addUvList(this.phone);
				}
				Mem.getUserCodeMap().put(this.phone,
						rondomnumber + "," + rightNow.getTimeInMillis());
			}
			System.out.println("==================短信地址====================");
			System.out.println(firstSms);
			this.message = "地址链接已发送,有效期一周";
		}
		else
		{
			this.message = "手机号码无效";
		}
		return "noMiddleActivity-2g";
	}

	public String dologin()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		Calendar rightNow = new GregorianCalendar();
		if ((Mem.getUserCodeMap().get(this.phone) != null)
				&& (((String) Mem.getUserCodeMap().get(this.phone)).length() > 0))
		{
			String[] code = ((String) Mem.getUserCodeMap().get(this.phone))
					.split(",");
			if ((code[1] != null) && (code[1].length() > 0)
					&& (code[0] != null) && (code[0].length() > 0)
					&& (rightNow.getTimeInMillis() < Long.parseLong(code[1])))
			{
				if (!(code[0].equals(this.random)))
				{
					this.message = "地址链接已失效,请重新获取";
					return "noMiddleActivity-2g";
				}
				if ((this.userPhone != null)
						&& (this.userPhone.length() > 0)
						&& (this.userPhone
								.matches("^(139|138|137|136|135|134|159|150|158|151|159|157|188|187|152|182|147)\\d{8}$")))
				{
					SeedSmsPhone seedSmsPhone = new SeedSmsPhone();
					seedSmsPhone.querySeedSms(this.phone, this.userPhone,
							this.random, this.terminalType);
				}
				else
				{
					HttpServletResponse response = ServletActionContext
							.getResponse();
					try
					{
						response.sendRedirect("ActivityAction.action?phone="
								+ this.phone + "&random=" + this.random
								+ "&terminalType=" + this.terminalType + "&op="
								+ this.op + "&op_tag=3");
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

	public boolean logincheck(String phone, String random)
	{
		phone = FilterHtml.Html2Text(phone);
		random = FilterHtml.Html2Text(random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		if ((Mem.getUserCodeMap().get(phone) == null)
				|| (((String) Mem.getUserCodeMap().get(phone)).length() <= 0)
				|| (!(phone
						.matches("^(139|138|137|136|135|134|159|150|158|151|159|157|188|187|152|182|147)\\d{8}$"))))
			// break label171;
			return false;
		Calendar rightNow = new GregorianCalendar();
		String[] code = ((String) Mem.getUserCodeMap().get(phone)).split(",");

		// label171: return ((code[1] == null) || (code[1].length() <= 0)
		// || (code[0] == null) || (code[0].length() <= 0)
		// || (rightNow.getTimeInMillis() >= Long.parseLong(code[1])) ||
		// (!(code[0]
		// .equals(random))));
		return ((code[1] == null) || (code[1].length() <= 0)
				|| (code[0] == null) || (code[0].length() <= 0)
				|| (rightNow.getTimeInMillis() >= Long.parseLong(code[1])) || (!(code[0]
					.equals(random))));
	}

	public String noMiddle()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		return "noMiddleActivity";
	}

	public String pushRan()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		String mes = "false";
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("terminalType", this.terminalType);
		request.setAttribute("userPhone", this.userPhone);
		request.setAttribute("phone", this.phone);
		if ((this.phone != null)
				&& (this.phone.length() > 0)
				&& (this.phone
						.matches("^(139|138|137|136|135|134|159|150|158|151|159|157|188|187|152|182|147)\\d{8}$")))
		{
			String rondomnumber = getRandomNumber();

			String firstSms = "幸运3+3活动地址 http://33.vrmedia.com.cn/P.action?p="
					+ this.phone + "&r=" + rondomnumber + "&t="
					+ this.terminalType;

			if ((this.userPhone != null)
					&& (this.userPhone.length() > 0)
					&& (this.userPhone
							.matches("^(139|138|137|136|135|134|159|150|158|151|159|157|188|187|152|182|147)\\d{8}$")))
			{
				firstSms = firstSms + "&u=" + this.userPhone;
			}
			if ((this.op != null) && (!("".equals(this.op)))
					&& (!("null".equals(this.op))))
			{
				firstSms = firstSms + "&o=" + this.op;
			}

			if (PostSMS(firstSms, "10658688", this.phone))
			{
				GregorianCalendar rightNow = new GregorianCalendar();
				rightNow.add(11, 168);
				String value = "";
				value = (String) Mem.getUserCodeMap().get(this.phone);
				if ((value == null) || (value.length() <= 0))
				{
					Mem.addUvList(this.phone);
				}
				Mem.getUserCodeMap().put(this.phone,
						rondomnumber + "," + rightNow.getTimeInMillis());
				mes = rondomnumber;
			}
			System.out.println("==================短信地址====================");
			System.out.println(firstSms);
		}

		PrintWriter pw = null;
		try
		{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			pw = response.getWriter();
			pw.print(mes);
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

	boolean PostSMS(String msgcontent, String spnumber, String desttermid)
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		boolean isSuccess = false;
		String user = "zz";
		String password = "zz";
		String cpcode = "zzfgs";
		String serviceid = "HELP";
		String feetype = "1";
		String feecode = "0";
		String linkid = "1";
		String chargetermid = desttermid;

		String sendtime = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		now.setTime(now.getTime() - 180000L);
		sendtime = df.format(now);
		try
		{
			Document document = DocumentHelper.createDocument();
			Element rootElement = document.addElement("smssendmodel");
			Element chidElement = rootElement.addElement("user");
			chidElement.setText(user);
			chidElement = rootElement.addElement("password");
			chidElement.setText(password);
			chidElement = rootElement.addElement("cpcode");
			chidElement.setText(cpcode);
			chidElement = rootElement.addElement("spnumber");
			chidElement.setText(spnumber);
			chidElement = rootElement.addElement("serviceid");
			chidElement.setText(serviceid);
			chidElement = rootElement.addElement("feetype");
			chidElement.setText(feetype);
			chidElement = rootElement.addElement("feecode");
			chidElement.setText(feecode);
			chidElement = rootElement.addElement("desttermid");
			chidElement.setText(desttermid);
			chidElement = rootElement.addElement("chargetermid");
			chidElement.setText(chargetermid);
			chidElement = rootElement.addElement("msgcontent");
			chidElement.setText(msgcontent);
			chidElement = rootElement.addElement("linkid");
			chidElement.setText(linkid);
			chidElement = rootElement.addElement("sendtime");
			chidElement.setText(sendtime);

			int num = 1;
			while (num <= 2)
			{
				++num;
				if (!(send("http://211.142.221.215:9092/JC_SMSGateway.aspx",
						document.asXML())))
					continue;
				num = 4;
				isSuccess = true;
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return isSuccess;
	}

	public boolean send(String urlAddr, String strContent) throws Exception
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		boolean isSuccess = false;
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(urlAddr);

		String sResponseBody = null;
		try
		{
			postMethod.setRequestHeader("Content-Type",
					"text/html;charset=UTF-8");
			postMethod.setRequestHeader("Connection", "close");
			postMethod.setRequestBody(strContent);
			client.executeMethod(postMethod);
			int responseCode = postMethod.getStatusCode();
			if (responseCode == 200)
			{
				sResponseBody = postMethod.getResponseBodyAsString();
				if (sResponseBody.trim().equals("0"))
					isSuccess = true;
			}
		}
		catch (HttpException localHttpException)
		{
		}
		catch (IOException localIOException)
		{
		}
		finally
		{
			postMethod.releaseConnection();
			postMethod.recycle();
		}
		return isSuccess;
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

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public boolean isSend()
	{
		return this.send;
	}

	public void setSend(boolean send)
	{
		this.send = send;
	}

	public String getIsrandom()
	{
		return this.israndom;
	}

	public void setIsrandom(String israndom)
	{
		this.israndom = israndom;
	}

	public static long getSerialVersionUID()
	{
		return 1024L;
	}

	public String getTerminalType()
	{
		return this.terminalType;
	}

	public void setTerminalType(String terminalType)
	{
		this.terminalType = terminalType;
	}

	public String getUserPhone()
	{
		return this.userPhone;
	}

	public void setUserPhone(String userPhone)
	{
		this.userPhone = userPhone;
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