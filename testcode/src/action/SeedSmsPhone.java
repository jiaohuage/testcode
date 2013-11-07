package action;

import com.opensymphony.xwork2.ActionSupport;
import hbm.RwdState;
import hbm.UiLuckyCodeRwd;
import hbm.UiLuckyCodeRwdId;
import hbm.UiLuckyCodeSeedSms;
import hbm.UiLuckyCodeSeedSmsId;
import hibernate.HibernateSessionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.codehaus.xfire.client.Client;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SeedSmsPhone extends ActionSupport
{
	private String phone;

	private String random;

	private String terminalType;

	private String userPhone;

	public String seedSms()
	{
		String message = "";

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String url = "http://192.168.37.52:80/bonc-vasms/services/SendMSMService?wsdl";
		try
		{
			Client client = new Client(new URL(url));

			String key = MD5Encode("115" + this.phone);
			String firstSms = "我正在参与株洲联通幸运3+3活动，点击  http://33.vrmedia.com.cn/SeedSmsPhone!toIndex.action?userPhone="
					+ this.phone + " 获得幸运密码赢取千元大奖，赶快参加吧";
			String xml1 = "<Massage><key>" + key + "</key><telephone>"
					+ this.phone + "</telephone><content>" + firstSms
					+ "</content></Massage>";
			client.invoke("sendSMSService", new Object[] { xml1 });
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if ((this.terminalType == "2") || ("2".equals(this.terminalType)))
		{
			return "goseedsms-2g";
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

	public String execute()
	{
		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String url = "http://192.168.37.52:80/bonc-vasms/services/SendMSMService?wsdl";
		try
		{
			Client client = new Client(new URL(url));

			String key = MD5Encode("115" + this.phone);
			String firstSms = "[幸运密码种子短信]尊敬的用户您好，点击 http://33.vrmedia.com.cn/SeedSmsPhone!toIndex.action?userPhone="
					+ this.phone + "可获得幸运密码，当期重复点击无效";

			String xml1 = "<Massage><key>" + key + "</key><telephone>"
					+ this.phone + "</telephone><content>" + firstSms
					+ "</content></Massage>";
			client.invoke("sendSMSService", new Object[] { xml1 });
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		try
		{
			response.sendRedirect("ActivityAction.action?phone=" + this.phone
					+ "&random=" + this.random + "&terminalType="
					+ this.terminalType);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return "noMiddleActivity-2g";
	}

	public String MD5Encode(String s)
	{
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

	public String toIndex()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("userPhone", this.userPhone);
		return "toIndex";
	}

	public String querySeedSms(String phone, String userPhone, String random,
			String terminalType)
	{
		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(phone, random)))
		{
			return "noMiddleActivity-2g";
		}
		if ((phone.equals(userPhone)) || (phone == userPhone))
		{
			goUrl(phone, random, terminalType);
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String tag = "";
			String newissue = "";
			if ((Mem.getRwdStateList() != null)
					&& (Mem.getRwdStateList().size() > 0))
			{
				tag = ((RwdState) Mem.getRwdStateList().get(0)).getTag();
				newissue = String
						.valueOf(Integer.parseInt(new StringBuilder(String
								.valueOf(((RwdState) Mem.getRwdStateList().get(
										0)).getIssue())).toString()) + 1);
			}

			String op_issue = "";
			DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			int k = 0;
			try
			{
				Date date1 = sdf.parse(tag);
				Date date2 = sdf.parse(d.format(new Date()).toString());
				k = date1.compareTo(date2);
			}
			catch (ParseException e1)
			{
				e1.printStackTrace();
			}
			if (k > 0)
				op_issue = ((RwdState) Mem.getRwdStateList().get(0)).getIssue();
			else
			{
				op_issue = newissue;
			}
			Session session = HibernateSessionFactory.getSessionFactory()
					.openSession();
			String hql = "SELECT * FROM UI_LUCKY_CODE_SEED_SMS  s where s.issue="
					+ op_issue + " and s.phone=" + phone;
			Query query = session.createSQLQuery(hql);
			List list = query.list();

			if ((list != null) && (list.size() > 0))
			{
				goUrl(phone, random, terminalType);
			}
			else
			{
				Transaction trans2 = session.beginTransaction();
				UiLuckyCodeSeedSmsId uiLuckyCodeSeedSmsId = new UiLuckyCodeSeedSmsId();
				uiLuckyCodeSeedSmsId.setPhone(phone);
				uiLuckyCodeSeedSmsId.setUserphone(userPhone);
				uiLuckyCodeSeedSmsId.setIssue(op_issue);
				UiLuckyCodeSeedSms uiLuckyCodeSeedSms = new UiLuckyCodeSeedSms();
				uiLuckyCodeSeedSms.setId(uiLuckyCodeSeedSmsId);
				String message = session.save(uiLuckyCodeSeedSms).toString();
				trans2.commit();
				HibernateSessionFactory.closeSession();
				addRwd(message, op_issue, phone);
				addRwd(message, op_issue, userPhone);
				goUrl(phone, random, terminalType);
			}
		}
		return "none";
	}

	public String goUrl(String phone, String random, String terminalType)
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		try
		{
			response.sendRedirect("ActivityAction.action?phone=" + phone
					+ "&random=" + random + "&terminalType=" + terminalType);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return "none";
	}

	public void addRwd(String message, String op_issue, String phone)
	{
		if ((message != null) && (message.length() > 0))
		{
			Session session = HibernateSessionFactory.getSessionFactory()
					.openSession();

			String row_str_id = "";
			String row_str_number = "";
			int num = 0;
			String hqlx = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID = "
					+ Mem.getNumberId(num);
			Query queryx = session.createSQLQuery(hqlx);
			List uList = queryx.list();
			if ((uList != null) && (uList.size() > 0))
			{
				String value = "";
				for (int i = 0; i < uList.size(); ++i)
				{
					Object[] row = (Object[]) uList.get(i);
					row_str_id = row[0].toString();
					row_str_number = row[1].toString();
					if ((row_str_number == null)
							|| (row_str_number.length() <= 0))
						continue;
					Transaction trans2 = session.beginTransaction();
					UiLuckyCodeRwdId uiRandom50RwdId = new UiLuckyCodeRwdId();
					uiRandom50RwdId.setIssue(op_issue);
					uiRandom50RwdId.setRandom(row_str_number);
					uiRandom50RwdId.setSvcId(phone);
					uiRandom50RwdId.setTag("4");
					uiRandom50RwdId.setWinTag("0");
					UiLuckyCodeRwd uiRandom50Rwd = new UiLuckyCodeRwd();
					uiRandom50Rwd.setId(uiRandom50RwdId);
					session.save(uiRandom50Rwd);
					trans2.commit();
					if (i % 100 == 0)
					{
						session.flush();
						session.clear();
					}

					if ((value != null) && (value.length() > 0))
						value = value + "," + row_str_number + ":" + "0" + ":"
								+ op_issue + ":" + "4";
					else
					{
						value = row_str_number + ":" + "0" + ":" + op_issue
								+ ":" + "4";
					}

				}

				session.close();
				Mem.addRwd(phone, value);
			}
			HibernateSessionFactory.closeSession();
		}
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

	public String getUserPhone()
	{
		return this.userPhone;
	}

	public void setUserPhone(String userPhone)
	{
		this.userPhone = userPhone;
	}
}