package action;

import com.bonc.wo_key.WoCheck;
import com.opensymphony.xwork2.ActionSupport;
import hbm.Clicknum;
import hbm.ClicknumId;
import hbm.Opinion;
import hbm.OpinionId;
import hbm.RandomContinue;
import hbm.RandomContinueId;
import hbm.RandomStop;
import hbm.RandomStopId;
import hbm.RwdState;
import hbm.UiDownPage;
import hbm.UiDownPageId;
import hbm.UiLuckyCodeBefore;
import hbm.UiLuckyCodeBeforeId;
import hbm.UiLuckyCodeFenxiang;
import hbm.UiLuckyCodeFenxiangId;
import hbm.UiLuckyCodePhoneFlowBag;
import hbm.UiLuckyCodePhoneFlowBagId;
import hbm.UiLuckyCodeRwd;
import hbm.UiLuckyCodeRwdId;
import hbm.UiLuckyCodeSign;
import hbm.UiLuckyCodeSignId;
import hbm.UiLuckyCodeTask;
import hbm.UiLuckyCodeTaskId;
import hbm.UiMusicOrder;
import hbm.UiMusicOrderId;
import hbm.UiVacOrder;
import hbm.UiVacOrderId;
import hibernate.HibernateSessionFactory;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.codehaus.xfire.client.Client;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

public class ActivityAction extends ActionSupport
{
	private String phone;

	private String newissue;

	private String op;

	private String clickUrl;

	private String terminalType;

	private String skey;

	private String tag;

	private String random;

	private String resultCode;

	private String spid;

	private String sppid;

	private String pushSmsName;

	private String productPrice;

	private String productid;

	private String PLuckyNum;

	private String pId;

	private String pName;

	private String codeNumber;

	private String type;

	private static int newNumId;

	private String showType;

	private int page;

	private int endPage;

	private String userPhone;

	private String issue;

	private String phonenum;

	private String userkey;

	private String op_tag;

	private String anzhuoUrl;

	private String flowBagTag;

	public String portal()
	{
		RandomNumberAction randomNumberAction;
		Calendar rightNow;
		String value;
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		if ((this.op == "menhu") || ("menhu".equals(this.op)))
		{
			if (WoCheck.check(this.phonenum, this.userkey))
			{
				if ((this.phonenum != null)
						&& (this.phonenum.length() > 0)
						&& (this.phonenum
								.matches("^(130|131|132|155|156|185|186)\\d{8}$")))
				{
					this.phone = this.phonenum;
					this.op_tag = "1";
					if (this.phone.substring(0, 3).trim().equals("+86"))
					{
						this.phone = this.phone.substring(3, 14);
					}
					randomNumberAction = new RandomNumberAction();
					this.random = RandomNumberAction.getRandomNumber();
					rightNow = new GregorianCalendar();
					rightNow.add(11, 168);
					value = "";

					value = (String) Mem.getUserCodeMap().get(this.phone);
					if ((value == null) || (value.length() <= 0))
					{
						Mem.addUvList(this.phone);
					}
					Mem.getUserCodeMap().put(this.phone,
							this.random + "," + rightNow.getTimeInMillis());
				}
				return execute();
			}
			return getKey();
		}

		if ((this.op == "ygj") || ("ygj".equals(this.op)))
		{
			if (WoCheck.check(this.phonenum, this.userkey))
			{
				if ((this.phonenum != null)
						&& (this.phonenum.length() > 0)
						&& (this.phonenum
								.matches("^(130|131|132|155|156|185|186)\\d{8}$")))
				{
					this.phone = this.phonenum;
					if (this.phone.substring(0, 3).trim().equals("+86"))
					{
						this.phone = this.phone.substring(3, 14);
					}
					randomNumberAction = new RandomNumberAction();
					this.random = RandomNumberAction.getRandomNumber();
					rightNow = new GregorianCalendar();
					rightNow.add(11, 168);
					value = "";

					value = (String) Mem.getUserCodeMap().get(this.phone);
					if ((value == null) || (value.length() <= 0))
					{
						Mem.addUvList(this.phone);
					}
					Mem.getUserCodeMap().put(this.phone,
							this.random + "," + rightNow.getTimeInMillis());
				}
				return execute();
			}
			return getKey();
		}

		return "none";
	}

	public String getKey()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);
		try
		{
			HttpRequest httpRequest = new HttpRequest();
			String skey = httpRequest.getKey();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("terminalType", this.terminalType);
			request.setAttribute("userPhone", this.phone);
			request.setAttribute("phone", this.userPhone);
			request.setAttribute("op", this.op);
			if ((!("".equals(skey))) && (skey.length() > 0))
			{
				request.setAttribute("skey", skey);
				if ("3".equals(this.terminalType))
				{
					return "deliverSkey";
				}
				return "deliverSkey-2g";
			}

			return "noMiddleActivity-2g";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return "noMiddleActivity-2g";
	}

	public String getNetPhone()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		HttpRequest httpRequest = new HttpRequest();
		this.phone = httpRequest.getPhone(this.skey).trim();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("terminalType", this.terminalType);

		if ((!("".equals(this.phone))) && (this.phone.length() > 0)
				&& (!(this.phone.contains("null")))
				&& (!(this.phone.equals("00000000000"))))
		{
			if (this.phone.substring(0, 3).trim().equals("+86"))
			{
				this.phone = this.phone.substring(3, 14);
			}
			RandomNumberAction randomNumberAction = new RandomNumberAction();
			this.random = RandomNumberAction.getRandomNumber();
			Calendar rightNow = new GregorianCalendar();
			rightNow.add(11, 168);
			String value = "";

			value = (String) Mem.getUserCodeMap().get(this.phone);
			if ((value == null) || (value.length() <= 0))
			{
				Mem.addUvList(this.phone);
			}
			Mem.getUserCodeMap().put(this.phone,
					this.random + "," + rightNow.getTimeInMillis());
			if ((this.userPhone != null)
					&& (this.userPhone.length() > 0)
					&& (this.userPhone
							.matches("^(130|131|132|155|156|185|186)\\d{8}$")))
			{
				SeedSmsPhone seedSmsPhone = new SeedSmsPhone();
				seedSmsPhone.querySeedSms(this.phone, this.userPhone,
						this.random, this.terminalType);
			}
			this.op_tag = "2";
			return execute();
		}
		this.phone = "";
		request.setAttribute("userPhone", this.userPhone);
		request.setAttribute("phone", this.phone);
		request.setAttribute("op", this.op);
		return "noMiddleActivity-2g";
	}

	public String execute()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=UTF-8");
			if (("".equals(this.phone)) || (this.phone == null))
			{
				return "noMiddleActivity-2g";
			}

			RandomNumberAction randomNumberAction = new RandomNumberAction();
			if (!(randomNumberAction.logincheck(this.phone, this.random)))
			{
				return "noMiddleActivity-2g";
			}
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			int k = 0;
			try
			{
				if ((Mem.getRwdStateList() != null)
						&& (Mem.getRwdStateList().size() > 0))
				{
					this.tag = ((RwdState) Mem.getRwdStateList().get(0))
							.getTag();
					Date date1 = sdf.parse(this.tag);
					Date date2 = sdf.parse(df.format(new Date()).toString());
					k = date1.compareTo(date2);
				}
			}
			catch (ParseException e1)
			{
				e1.printStackTrace();
			}
			request.setAttribute("k", Integer.valueOf(k));
			request.setAttribute("phonenum", this.phone);
			request.setAttribute("userkey", WoCheck.make(this.phone));
			request.setAttribute("rwdStateList", Mem.getRwdStateList());
			if ("3".equals(this.terminalType))
				request.setAttribute("hList", Mem.getHList());
			else
			{
				request.setAttribute("h2gList", Mem.getH2gList());
			}
			if ((Mem.getRwdStateList() != null)
					&& (Mem.getRwdStateList().size() > 0))
			{
				request.setAttribute("tagState", ((RwdState) Mem
						.getRwdStateList().get(0)).getTagState());

				request.setAttribute("luckyCode", ((RwdState) Mem
						.getRwdStateList().get(0)).getLuckyCode());

				request.setAttribute("sse", ((RwdState) Mem.getRwdStateList()
						.get(0)).getSse());

				request.setAttribute("szse", ((RwdState) Mem.getRwdStateList()
						.get(0)).getSzse());

				request.setAttribute("issue", ((RwdState) Mem.getRwdStateList()
						.get(0)).getIssue());

				request.setAttribute("lotteryDate", ((RwdState) Mem
						.getRwdStateList().get(0)).getLotteryDate());

				request.setAttribute("startDate", ((RwdState) Mem
						.getRwdStateList().get(0)).getStartDate());

				request.setAttribute("endDate", ((RwdState) Mem
						.getRwdStateList().get(0)).getEndDate());

				this.newissue = String.valueOf(Integer
						.parseInt(new StringBuilder(String
								.valueOf(((RwdState) Mem.getRwdStateList().get(
										0)).getIssue())).toString()) + 1);
			}

			Mem.addFirstPage();
			request.setAttribute("phone", this.phone);
			List uiLuckyCodeRwdList = parseString((String) Mem
					.getCurrentIssueMap().get(this.phone));
			UiLuckyCodeRwd u = new UiLuckyCodeRwd();
			int thisnum = 0;
			int thiswinnum = 0;
			int nextnum = 0;
			if ((uiLuckyCodeRwdList != null) && (uiLuckyCodeRwdList.size() > 0))
			{
				for (int i = 0; i < uiLuckyCodeRwdList.size(); ++i)
				{
					u = (UiLuckyCodeRwd) uiLuckyCodeRwdList.get(i);

					if (((RwdState) Mem.getRwdStateList().get(0)).getIssue()
							.equals(u.getId().getIssue()))
					{
						++thisnum;
						if (("0".equals(u.getId().getWinTag()))
								|| (u.getId().getWinTag() == "0"))
							continue;
						++thiswinnum;
					}
					else
					{
						++nextnum;
					}
				}
			}
			request.setAttribute("thisnum", Integer.valueOf(thisnum));
			request.setAttribute("thiswinnum", Integer.valueOf(thiswinnum));
			request.setAttribute("nextnum", Integer.valueOf(nextnum));
			request.setAttribute("random", this.random);
			request.setAttribute("type", this.type);
			if (!("3".equals(this.terminalType)))
				// break label963;
				return "activity-2g";
			return "activity";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// label963: return "activity-2g";
		return "activity-2g";
	}

	public String showLuckyNumber()
	{
		int num;
		int j;
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String gourl = "noMiddleActivity-2g";
		HttpServletRequest request = ServletActionContext.getRequest();
		if ((Mem.getRwdStateList() != null)
				&& (Mem.getRwdStateList().size() > 0))
		{
			request.setAttribute("tagState", ((RwdState) Mem.getRwdStateList()
					.get(0)).getTagState());
		}

		List uiLuckyCodeRwdList = parseString((String) Mem.getCurrentIssueMap()
				.get(this.phone));
		UiLuckyCodeRwd u = new UiLuckyCodeRwd();
		int thisnum = 0;
		int thiswinnum = 0;
		int nextnum = 0;
		List thisnowinnumList = new ArrayList();
		List thiswinnumList = new ArrayList();
		List nextnumList = new ArrayList();
		if ((uiLuckyCodeRwdList != null) && (uiLuckyCodeRwdList.size() > 0))
		{
			for (int i = 0; i < uiLuckyCodeRwdList.size(); ++i)
			{
				u = (UiLuckyCodeRwd) uiLuckyCodeRwdList.get(i);

				if (((RwdState) Mem.getRwdStateList().get(0)).getIssue()
						.equals(u.getId().getIssue()))
				{
					++thisnum;
					if ((!("0".equals(u.getId().getWinTag())))
							&& (u.getId().getWinTag() != "0"))
					{
						++thiswinnum;

						thiswinnumList.add(u);
					}
					else
					{
						thisnowinnumList.add(u);
					}
				}
				else
				{
					++nextnum;

					nextnumList.add(u);
				}
			}
		}
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		request.setAttribute("type", this.type);

		int numPage = 10;
		List list = new ArrayList();
		if (("thisissue".equals(this.showType))
				|| (this.showType == "thisissue"))
		{
			request.setAttribute("thisnum", Integer.valueOf(thisnum));
			request.setAttribute("thiswinnum", Integer.valueOf(thiswinnum));
			request.setAttribute("thiswinnumList", thiswinnumList);
			request.setAttribute("thisnowinnumList", thisnowinnumList);
			num = thisnowinnumList.size();
			if ((thisnowinnumList != null) && (num >= 0))
			{
				if (this.page < 0)
				{
					this.page = 0;
				}
				this.endPage = (int) Math.floor(num / numPage);
				if (num % numPage == 0)
				{
					this.endPage -= 1;
				}
				if (this.page > this.endPage)
				{
					this.page = this.endPage;
				}
				for (j = 0; j < num; ++j)
				{
					if ((j >= this.page * 10) && (j < (this.page + 1) * 10))
					{
						list.add((UiLuckyCodeRwd) thisnowinnumList.get(j));
					}
				}
			}
			if ("3".equals(this.terminalType))
				gourl = "showThisIssue";
			else
			{
				gourl = "showThisIssue-2g";
			}
		}
		if (("nextissue".equals(this.showType))
				|| (this.showType == "nextissue"))
		{
			request.setAttribute("nextnum", Integer.valueOf(nextnum));
			request.setAttribute("nextnumList", nextnumList);
			num = nextnumList.size();
			if ((nextnumList != null) && (num > 0))
			{
				if (this.page < 0)
				{
					this.page = 0;
				}
				this.endPage = (int) Math.floor(num / numPage);
				if (num % numPage == 0)
				{
					this.endPage -= 1;
				}
				if (this.page > this.endPage)
				{
					this.page = this.endPage;
				}
				for (j = 0; j < num; ++j)
				{
					if ((j >= this.page * 10) && (j < (this.page + 1) * 10))
					{
						list.add((UiLuckyCodeRwd) nextnumList.get(j));
					}
				}
			}
			if ("3".equals(this.terminalType))
				gourl = "showNextIssue";
			else
			{
				gourl = "showNextIssue-2g";
			}
		}
		request.setAttribute("list", list);
		request.setAttribute("page", Integer.valueOf(this.page));
		request.setAttribute("endPage", Integer.valueOf(this.endPage));
		request.setAttribute("issue",
				((RwdState) Mem.getRwdStateList().get(0)).getIssue());
		request.setAttribute("newissue", Integer.parseInt(((RwdState) Mem
				.getRwdStateList().get(0)).getIssue()) + 1);

		list = null;
		return gourl;
	}

	public String queryPhoneToPage()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		int k = 0;
		try
		{
			if ((Mem.getRwdStateList() != null)
					&& (Mem.getRwdStateList().size() > 0))
			{
				this.tag = ((RwdState) Mem.getRwdStateList().get(0)).getTag();
				Date date1 = sdf.parse(this.tag);
				Date date2 = sdf.parse(df.format(new Date()).toString());
				k = date1.compareTo(date2);
			}
		}
		catch (ParseException e1)
		{
			e1.printStackTrace();
		}
		request.setAttribute("k", Integer.valueOf(k));
		try
		{
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=UTF-8");
			if (("".equals(this.phone)) || (this.phone == null))
			{
				return "noMiddleActivity-2g";
			}
			request.setAttribute("rwdStateList", Mem.getRwdStateList());
			if ((Mem.getRwdStateList() != null)
					&& (Mem.getRwdStateList().size() > 0))
			{
				request.setAttribute("tagState", ((RwdState) Mem
						.getRwdStateList().get(0)).getTagState());

				request.setAttribute("luckyCode", ((RwdState) Mem
						.getRwdStateList().get(0)).getLuckyCode());

				request.setAttribute("sse", ((RwdState) Mem.getRwdStateList()
						.get(0)).getSse());

				request.setAttribute("szse", ((RwdState) Mem.getRwdStateList()
						.get(0)).getSzse());

				request.setAttribute("issue", ((RwdState) Mem.getRwdStateList()
						.get(0)).getIssue());

				request.setAttribute("lotteryDate", ((RwdState) Mem
						.getRwdStateList().get(0)).getLotteryDate());

				request.setAttribute("startDate", ((RwdState) Mem
						.getRwdStateList().get(0)).getStartDate());

				request.setAttribute("endDate", ((RwdState) Mem
						.getRwdStateList().get(0)).getEndDate());

				this.newissue = String.valueOf(Integer
						.parseInt(new StringBuilder(String
								.valueOf(((RwdState) Mem.getRwdStateList().get(
										0)).getIssue())).toString()) + 1);
			}
			request.setAttribute("phone", this.phone);
			List uiRandom50RwdList = parseString((String) Mem
					.getCurrentIssueMap().get(this.phone));
			List uiList = new ArrayList();
			List uiWinList = new ArrayList();
			List uiDownPageList = new ArrayList();
			List uiDownPageWinList = new ArrayList();
			List uiDownPageNewList = new ArrayList();
			if ((uiRandom50RwdList != null) && (uiRandom50RwdList.size() > 0))
			{
				for (int i = 0; i < uiRandom50RwdList.size(); ++i)
				{
					UiLuckyCodeRwdId uir = new UiLuckyCodeRwdId();
					UiLuckyCodeRwd ui = new UiLuckyCodeRwd();
					UiLuckyCodeRwd u = (UiLuckyCodeRwd) uiRandom50RwdList
							.get(i);
					uir.setRandom(u.getId().getRandom());
					uir.setWinTag(u.getId().getWinTag());
					uir.setTag(u.getId().getTag());
					uir.setIssue(u.getId().getIssue());
					ui.setId(uir);

					if (((RwdState) Mem.getRwdStateList().get(0)).getIssue()
							.equals(uir.getIssue()))
					{
						if ("0".equals(uir.getTag()))
						{
							if ("0".equals(uir.getWinTag()))
							{
								uiList.add(ui);
							}
							else
							{
								uiWinList.add(ui);
							}
						}
						else if ("0".equals(uir.getWinTag()))
						{
							uiDownPageList.add(ui);
						}
						else
						{
							uiDownPageWinList.add(ui);
						}
					}
					else
					{
						uiDownPageNewList.add(ui);
					}
				}
				request.setAttribute("uiList", uiList);
				request.setAttribute("uiDownPageList", uiDownPageList);
				request.setAttribute("uiWinList", uiWinList);
				request.setAttribute("uiDownPageWinList", uiDownPageWinList);
				request.setAttribute("uiDownPageNewList", uiDownPageNewList);
			}
			if ((uiList != null) && (uiList.size() > 0) && (uiWinList != null)
					&& (uiWinList.size() > 0))
				request.setAttribute("num1",
						Integer.valueOf(uiList.size() + uiWinList.size()));
			else if ((uiList != null) && (uiList.size() > 0)
					&& (((uiWinList == null) || (uiWinList.size() <= 0))))
				request.setAttribute("num1", Integer.valueOf(uiList.size()));
			else if ((uiWinList != null) && (uiWinList.size() > 0)
					&& (((uiList == null) || (uiList.size() <= 0))))
				request.setAttribute("num1", Integer.valueOf(uiWinList.size()));
			else
			{
				request.setAttribute("num1", Integer.valueOf(0));
			}
			if ((uiDownPageList != null) && (uiDownPageList.size() > 0)
					&& (uiDownPageWinList != null)
					&& (uiDownPageWinList.size() > 0))
				request.setAttribute(
						"num2",
						Integer.valueOf(uiDownPageList.size()
								+ uiDownPageWinList.size()));
			else if ((uiDownPageList != null)
					&& (uiDownPageList.size() > 0)
					&& (((uiDownPageWinList == null) || (uiDownPageWinList
							.size() <= 0))))
				request.setAttribute("num2",
						Integer.valueOf(uiDownPageList.size()));
			else if ((uiDownPageWinList != null)
					&& (uiDownPageWinList.size() > 0)
					&& (((uiDownPageList == null) || (uiDownPageList.size() <= 0))))
				request.setAttribute("num2",
						Integer.valueOf(uiDownPageWinList.size()));
			else
			{
				request.setAttribute("num2", Integer.valueOf(0));
			}
			request.setAttribute("random", this.random);
			request.setAttribute("type", this.type);
			uiRandom50RwdList = null;
			uiList = null;
			uiWinList = null;
			uiDownPageList = null;
			uiDownPageWinList = null;
			uiDownPageNewList = null;
			if (!("3".equals(this.terminalType)))
				// break label1426;
				return "activity-2g";
			return "activity";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// label1426: return "activity-2g";
		return "activity-2g";
	}

	public List<UiLuckyCodeRwd> parseString(String mapValue)
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		List randomList = new ArrayList();

		if ((mapValue == null) || (mapValue.length() < 1))
		{
			return null;
		}
		String[] issues = decode(mapValue, ",");
		String[] randoms = (String[]) null;
		if ((issues != null) && (issues.length > 0))
		{
			for (int i = 0; i < issues.length; ++i)
			{
				UiLuckyCodeRwdId urdId = new UiLuckyCodeRwdId();
				UiLuckyCodeRwd urd = new UiLuckyCodeRwd();
				randoms = decode(issues[i], ":");
				if ((randoms == null) || (randoms.length <= 0))
				{
					continue;
				}

				urdId.setRandom(randoms[0]);
				urdId.setWinTag(randoms[1]);
				urdId.setIssue(randoms[2]);
				urdId.setTag(randoms[3]);
				urd.setId(urdId);
				randomList.add(urd);
			}
		}

		return randomList;
	}

	public List<UiLuckyCodeBefore> parseMyString(String mapValue)
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		List randomList = new ArrayList();

		if ((mapValue == null) || (mapValue.length() < 1))
		{
			return null;
		}
		String[] issues = decode(mapValue, ",");
		String[] randoms = (String[]) null;
		if ((issues != null) && (issues.length > 0))
		{
			for (int i = 0; i < issues.length; ++i)
			{
				UiLuckyCodeBeforeId urdId = new UiLuckyCodeBeforeId();
				UiLuckyCodeBefore urd = new UiLuckyCodeBefore();
				randoms = decode(issues[i], ":");
				if ((randoms == null) || (randoms.length <= 0))
				{
					continue;
				}

				urdId.setRandom(randoms[0]);
				urdId.setWinTag(randoms[1]);
				urdId.setIssue(randoms[2]);
				urd.setId(urdId);
				randomList.add(urd);
			}
		}

		return randomList;
	}

	public static String[] decode(String s_source, String decch)
	{
		String[] asTemp = (String[]) null;
		if (s_source.length() != 0)
		{
			StringTokenizer st = new StringTokenizer(s_source, decch);
			int iSize = st.countTokens();
			asTemp = new String[iSize];
			for (int i = 0; st.hasMoreTokens(); ++i)
			{
				asTemp[i] = st.nextToken();
			}
		}
		return asTemp;
	}

	public String error()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		return "error";
	}

	public String goDownPage()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String message = "";
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		Criteria c = session.createCriteria(UiDownPage.class);
		ProjectionList prolist = Projections.projectionList();
		prolist.add(Projections.rowCount());
		c.setProjection(prolist);
		c.add(Expression.eq("id.PId", this.pId));
		c.add(Expression.eq("id.phone", this.phone));
		List list = c.list();
		int a = -2;
		if ((list != null) && (list.size() > 0))
			a = Integer.parseInt(list.get(0).toString());
		try
		{
			if ((a <= 0) && (a != -2) && (this.codeNumber != null)
					&& (this.codeNumber.length() > 0))
			{
				Transaction trans = session.beginTransaction();
				UiDownPageId uiDownPageId = new UiDownPageId();
				uiDownPageId.setPId(this.pId);
				uiDownPageId.setPName(this.pName);
				uiDownPageId.setPhone(this.phone);
				uiDownPageId.setLuckyNumber(this.codeNumber);
				uiDownPageId.setIssue(((RwdState) Mem.getRwdStateList().get(0))
						.getIssue());
				UiDownPage uiDownPage = new UiDownPage();
				uiDownPage.setId(uiDownPageId);
				message = session.save(uiDownPage).toString();
				trans.commit();
				if ((message != null) && (message.length() > 0)
						&& (this.phone != null) && (this.phone.length() > 0))
				{
					String op_issue = "";
					int k = 0;
					DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					if ((Mem.getRwdStateList() != null)
							&& (Mem.getRwdStateList().size() > 0))
					{
						this.tag = ((RwdState) Mem.getRwdStateList().get(0))
								.getTag();
						this.newissue = String.valueOf(Integer
								.parseInt(new StringBuilder(String
										.valueOf(((RwdState) Mem
												.getRwdStateList().get(0))
												.getIssue())).toString()) + 1);
						try
						{
							Date date1 = sdf.parse(this.tag);
							Date date2 = sdf.parse(d.format(new Date())
									.toString());
							k = date1.compareTo(date2);
						}
						catch (ParseException e1)
						{
							e1.printStackTrace();
						}
					}

					String row_str_id = "";
					String row_str_number = "";
					int num = 0;
					String hql = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID = "
							+ Mem.getNumberId(num);

					Query query = session.createSQLQuery(hql);
					List uList = query.list();
					if ((uList != null) && (uList.size() > 0))
					{
						int i;
						Transaction trans2;
						Object[] row;
						UiLuckyCodeRwdId uiRandom50RwdId;
						UiLuckyCodeRwd uiRandom50Rwd;
						if (k > 0)
						{
							for (i = 0; i < uList.size(); ++i)
							{
								row = (Object[]) uList.get(i);
								row_str_id = row[0].toString();
								row_str_number = row[1].toString();
							}

							trans2 = session.beginTransaction();
							uiRandom50RwdId = new UiLuckyCodeRwdId();
							uiRandom50RwdId.setIssue(((RwdState) Mem
									.getRwdStateList().get(0)).getIssue());
							uiRandom50RwdId.setRandom(row_str_number);
							uiRandom50RwdId.setSvcId(this.phone);
							uiRandom50RwdId.setTag("1");
							uiRandom50RwdId.setWinTag("0");
							uiRandom50Rwd = new UiLuckyCodeRwd();
							uiRandom50Rwd.setId(uiRandom50RwdId);
							session.save(uiRandom50Rwd);
							trans2.commit();
							op_issue = ((RwdState) Mem.getRwdStateList().get(0))
									.getIssue();
						}
						else
						{
							for (int t = 0; t < uList.size(); ++t)
							{
								Object[] objs = (Object[]) uList.get(t);
								row_str_id = objs[0].toString();
								row_str_number = objs[1].toString();
							}

							trans2 = session.beginTransaction();
							uiRandom50RwdId = new UiLuckyCodeRwdId();
							uiRandom50RwdId.setIssue(this.newissue);
							uiRandom50RwdId.setRandom(row_str_number);
							uiRandom50RwdId.setSvcId(this.phone);
							uiRandom50RwdId.setTag("1");
							uiRandom50RwdId.setWinTag("0");
							uiRandom50Rwd = new UiLuckyCodeRwd();
							uiRandom50Rwd.setId(uiRandom50RwdId);
							session.save(uiRandom50Rwd);
							trans2.commit();
							op_issue = this.newissue;
						}
						String value = "";
						if ((row_str_number != null)
								&& (row_str_number.length() > 0))
						{
							if ((value != null) && (value.length() > 0))
								value = value + "," + row_str_number + ":"
										+ "0" + ":" + op_issue + ":" + "1";
							else
							{
								value = row_str_number + ":" + "0" + ":"
										+ op_issue + ":" + "1";
							}
							Mem.addRwd(this.phone, value);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		HibernateSessionFactory.closeSession();
		if ((this.pId.equals("10001")) || (this.pId == "10001"))
		{
			return "wangxian";
		}
		if ((this.pId.equals("10002")) || (this.pId == "10002"))
		{
			return "weishi";
		}
		if ((this.pId.equals("10003")) || (this.pId == "10003"))
		{
			return "souhu";
		}
		if ((this.pId.equals("10004")) || (this.pId == "10004"))
		{
			return "jinshan";
		}
		if ((this.pId.equals("10005")) || (this.pId == "10005"))
		{
			return "youyuanwang";
		}
		if ((this.pId.equals("10006")) || (this.pId == "10006"))
		{
			return "azsc";
		}
		if ((this.pId.equals("10007")) || (this.pId == "10007"))
		{
			return "wpjyz";
		}
		if ((this.pId.equals("10008")) || (this.pId == "10008"))
		{
			return "tojmj";
		}
		if ((this.pId.equals("10009")) || (this.pId == "10009"))
		{
			return "tdhx";
		}
		if ((this.pId.equals("10010")) || (this.pId == "10010"))
		{
			return "uc";
		}
		if ((this.pId.equals("10011")) || (this.pId == "10011"))
		{
			return "baidu";
		}
		if ((this.pId.equals("10012")) || (this.pId == "10012"))
		{
			return "toyidao";
		}
		if ((this.pId.equals("10013")) || (this.pId == "10013"))
		{
			return "dmyy";
		}
		if ((this.pId.equals("10014")) || (this.pId == "10014"))
		{
			return "feiyue";
		}
		if ((this.pId.equals("10015")) || (this.pId == "10015"))
		{
			return "xiaoao";
		}
		if ((this.pId.equals("10016")) || (this.pId == "10016"))
		{
			return "wyy";
		}
		if ((this.pId.equals("10027")) || (this.pId == "10027"))
		{
			return "goyingyeting";
		}
		if ((this.pId.equals("10028")) || (this.pId == "10028"))
		{
			return "golingxi";
		}
		return "goDownPage";
	}

	public String goDownPageNew()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String message = "";
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		Criteria c = session.createCriteria(UiDownPage.class);
		ProjectionList prolist = Projections.projectionList();
		prolist.add(Projections.rowCount());
		c.setProjection(prolist);
		c.add(Expression.eq("id.PId", this.pId));
		c.add(Expression.eq("id.phone", this.phone));
		List list = c.list();
		int a = -2;
		if ((list != null) && (list.size() > 0))
			a = Integer.parseInt(list.get(0).toString());
		try
		{
			if ((a <= 0) && (a != -2) && (this.codeNumber != null)
					&& (this.codeNumber.length() > 0))
			{
				Transaction trans = session.beginTransaction();
				UiDownPageId uiDownPageId = new UiDownPageId();
				uiDownPageId.setPId(this.pId);
				uiDownPageId.setPName(this.pName);
				uiDownPageId.setPhone(this.phone);
				uiDownPageId.setLuckyNumber(this.codeNumber);
				uiDownPageId.setIssue(((RwdState) Mem.getRwdStateList().get(0))
						.getIssue());
				UiDownPage uiDownPage = new UiDownPage();
				uiDownPage.setId(uiDownPageId);
				message = session.save(uiDownPage).toString();
				trans.commit();
				if ((message != null) && (message.length() > 0)
						&& (this.phone != null) && (this.phone.length() > 0))
				{
					if ((Mem.getRwdStateList() != null)
							&& (Mem.getRwdStateList().size() > 0))
					{
						this.tag = ((RwdState) Mem.getRwdStateList().get(0))
								.getTag();
						this.newissue = String.valueOf(Integer
								.parseInt(new StringBuilder(String
										.valueOf(((RwdState) Mem
												.getRwdStateList().get(0))
												.getIssue())).toString()) + 1);
					}

					String op_issue = "";
					DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					int k = 0;
					try
					{
						Date date1 = sdf.parse(this.tag);
						Date date2 = sdf.parse(d.format(new Date()).toString());
						k = date1.compareTo(date2);
					}
					catch (ParseException e1)
					{
						e1.printStackTrace();
					}

					String row_str_id = "";
					String row_str_number = "";
					int num = 0;
					String hql = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID = "
							+ Mem.getNumberId(num);
					Query query = session.createSQLQuery(hql);
					List uList = query.list();
					if ((uList != null) && (uList.size() > 0))
					{
						int i;
						Transaction trans2;
						Object[] row;
						UiLuckyCodeRwdId uiRandom50RwdId;
						UiLuckyCodeRwd uiRandom50Rwd;
						if (k > 0)
						{
							for (i = 0; i < uList.size(); ++i)
							{
								row = (Object[]) uList.get(i);
								row_str_id = row[0].toString();
								row_str_number = row[1].toString();
							}

							trans2 = session.beginTransaction();
							uiRandom50RwdId = new UiLuckyCodeRwdId();
							uiRandom50RwdId.setIssue(((RwdState) Mem
									.getRwdStateList().get(0)).getIssue());
							uiRandom50RwdId.setRandom(row_str_number);
							uiRandom50RwdId.setSvcId(this.phone);
							uiRandom50RwdId.setTag("1");
							uiRandom50RwdId.setWinTag("0");
							uiRandom50Rwd = new UiLuckyCodeRwd();
							uiRandom50Rwd.setId(uiRandom50RwdId);
							session.save(uiRandom50Rwd);
							trans2.commit();
							op_issue = ((RwdState) Mem.getRwdStateList().get(0))
									.getIssue();
						}
						else
						{
							for (int t = 0; t < uList.size(); ++t)
							{
								Object[] objs = (Object[]) uList.get(t);
								row_str_id = objs[0].toString();
								row_str_number = objs[1].toString();
							}

							trans2 = session.beginTransaction();
							uiRandom50RwdId = new UiLuckyCodeRwdId();
							uiRandom50RwdId.setIssue(this.newissue);
							uiRandom50RwdId.setRandom(row_str_number);
							uiRandom50RwdId.setSvcId(this.phone);
							uiRandom50RwdId.setTag("1");
							uiRandom50RwdId.setWinTag("0");
							uiRandom50Rwd = new UiLuckyCodeRwd();
							uiRandom50Rwd.setId(uiRandom50RwdId);
							session.save(uiRandom50Rwd);
							trans2.commit();
							op_issue = this.newissue;
						}
						String value = "";
						if ((row_str_number != null)
								&& (row_str_number.length() > 0))
						{
							if ((value != null) && (value.length() > 0))
								value = value + "," + row_str_number + ":"
										+ "0" + ":" + op_issue + ":" + "1";
							else
							{
								value = row_str_number + ":" + "0" + ":"
										+ op_issue + ":" + "1";
							}
							Mem.addRwd(this.phone, value);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		request.setAttribute("type", this.pId);
		HibernateSessionFactory.closeSession();
		return "goPage";
	}

	public String goDownPageNewwyy()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String message = "";
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		Criteria c = session.createCriteria(UiDownPage.class);
		ProjectionList prolist = Projections.projectionList();
		prolist.add(Projections.rowCount());
		c.setProjection(prolist);
		c.add(Expression.eq("id.PId", this.pId));
		c.add(Expression.eq("id.phone", this.phone));
		List list = c.list();
		int a = -2;
		if ((list != null) && (list.size() > 0))
			a = Integer.parseInt(list.get(0).toString());
		try
		{
			if ((a <= 0) && (a != -2) && (this.codeNumber != null)
					&& (this.codeNumber.length() > 0))
			{
				Transaction trans = session.beginTransaction();
				UiDownPageId uiDownPageId = new UiDownPageId();
				uiDownPageId.setPId(this.pId);
				uiDownPageId.setPName(this.pName);
				uiDownPageId.setPhone(this.phone);
				uiDownPageId.setLuckyNumber(this.codeNumber);
				uiDownPageId.setIssue(((RwdState) Mem.getRwdStateList().get(0))
						.getIssue());
				UiDownPage uiDownPage = new UiDownPage();
				uiDownPage.setId(uiDownPageId);
				message = session.save(uiDownPage).toString();
				trans.commit();
				if ((message != null) && (message.length() > 0)
						&& (this.phone != null) && (this.phone.length() > 0))
				{
					if ((Mem.getRwdStateList() != null)
							&& (Mem.getRwdStateList().size() > 0))
					{
						this.tag = ((RwdState) Mem.getRwdStateList().get(0))
								.getTag();
						this.newissue = String.valueOf(Integer
								.parseInt(new StringBuilder(String
										.valueOf(((RwdState) Mem
												.getRwdStateList().get(0))
												.getIssue())).toString()) + 1);
					}

					String op_issue = "";
					DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					int k = 0;
					try
					{
						Date date1 = sdf.parse(this.tag);
						Date date2 = sdf.parse(d.format(new Date()).toString());
						k = date1.compareTo(date2);
					}
					catch (ParseException e1)
					{
						e1.printStackTrace();
					}

					String row_str_id = "";
					String row_str_number = "";
					int num = 0;
					String hql = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID = "
							+ Mem.getNumberId(num);
					Query query = session.createSQLQuery(hql);
					List uList = query.list();
					if ((uList != null) && (uList.size() > 0))
					{
						int i;
						Transaction trans2;
						Object[] row;
						UiLuckyCodeRwdId uiRandom50RwdId;
						UiLuckyCodeRwd uiRandom50Rwd;
						if (k > 0)
						{
							for (i = 0; i < uList.size(); ++i)
							{
								row = (Object[]) uList.get(i);
								row_str_id = row[0].toString();
								row_str_number = row[1].toString();
							}

							trans2 = session.beginTransaction();
							uiRandom50RwdId = new UiLuckyCodeRwdId();
							uiRandom50RwdId.setIssue(((RwdState) Mem
									.getRwdStateList().get(0)).getIssue());
							uiRandom50RwdId.setRandom(row_str_number);
							uiRandom50RwdId.setSvcId(this.phone);
							uiRandom50RwdId.setTag("1");
							uiRandom50RwdId.setWinTag("0");
							uiRandom50Rwd = new UiLuckyCodeRwd();
							uiRandom50Rwd.setId(uiRandom50RwdId);
							session.save(uiRandom50Rwd);
							trans2.commit();
							op_issue = ((RwdState) Mem.getRwdStateList().get(0))
									.getIssue();
						}
						else
						{
							for (int t = 0; t < uList.size(); ++t)
							{
								Object[] objs = (Object[]) uList.get(t);
								row_str_id = objs[0].toString();
								row_str_number = objs[1].toString();
							}

							trans2 = session.beginTransaction();
							uiRandom50RwdId = new UiLuckyCodeRwdId();
							uiRandom50RwdId.setIssue(this.newissue);
							uiRandom50RwdId.setRandom(row_str_number);
							uiRandom50RwdId.setSvcId(this.phone);
							uiRandom50RwdId.setTag("1");
							uiRandom50RwdId.setWinTag("0");
							uiRandom50Rwd = new UiLuckyCodeRwd();
							uiRandom50Rwd.setId(uiRandom50RwdId);
							session.save(uiRandom50Rwd);
							trans2.commit();
							op_issue = this.newissue;
						}
						String value = "";
						if ((row_str_number != null)
								&& (row_str_number.length() > 0))
						{
							if ((value != null) && (value.length() > 0))
								value = value + "," + row_str_number + ":"
										+ "0" + ":" + op_issue + ":" + "1";
							else
							{
								value = row_str_number + ":" + "0" + ":"
										+ op_issue + ":" + "1";
							}
							Mem.addRwd(this.phone, value);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		request.setAttribute("type", this.pId);
		HibernateSessionFactory.closeSession();
		return "goWyyAnzhuo";
	}

	public String goDown()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String message = "";
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		Criteria c = session.createCriteria(UiDownPage.class);
		ProjectionList prolist = Projections.projectionList();
		prolist.add(Projections.rowCount());
		c.setProjection(prolist);
		c.add(Expression.eq("id.PId", this.pId));
		c.add(Expression.eq("id.phone", this.phone));
		List list = c.list();
		int a = -2;
		if ((list != null) && (list.size() > 0))
			a = Integer.parseInt(list.get(0).toString());
		try
		{
			if ((a <= 0) && (a != -2))
			{
				int w;
				String score_number = "0";

				if ("3".equals(this.terminalType))
				{
					for (w = 0; w < Mem.getTaskList().size(); ++w)
					{
						if ((((UiLuckyCodeTask) Mem.getTaskList().get(w))
								.getId().getPId() != this.pId)
								&& (!(this.pId
										.equals(((UiLuckyCodeTask) Mem
												.getTaskList().get(w)).getId()
												.getPId()))))
							continue;
						this.codeNumber = ((UiLuckyCodeTask) Mem.getTaskList()
								.get(w)).getId().getPLuckyNum();
						score_number = ((UiLuckyCodeTask) Mem.getTaskList()
								.get(w)).getId().getPIntegralNum();
						this.pName = ((UiLuckyCodeTask) Mem.getTaskList()
								.get(w)).getId().getPName();
					}
				}
				else
				{
					for (w = 0; w < Mem.getTask2gList().size(); ++w)
					{
						if ((((UiLuckyCodeTask) Mem.getTask2gList().get(w))
								.getId().getPId() != this.pId)
								&& (!(this.pId.equals(((UiLuckyCodeTask) Mem
										.getTask2gList().get(w)).getId()
										.getPId()))))
							continue;
						this.codeNumber = ((UiLuckyCodeTask) Mem
								.getTask2gList().get(w)).getId().getPLuckyNum();
						score_number = ((UiLuckyCodeTask) Mem.getTaskList()
								.get(w)).getId().getPIntegralNum();
						this.pName = ((UiLuckyCodeTask) Mem.getTask2gList()
								.get(w)).getId().getPName();
					}
				}

				if ((this.codeNumber != null) && (this.codeNumber.length() > 0))
				{
					Transaction trans = session.beginTransaction();
					UiDownPageId uiDownPageId = new UiDownPageId();
					uiDownPageId.setPId(this.pId);
					uiDownPageId.setPName(this.pName);
					uiDownPageId.setPhone(this.phone);
					uiDownPageId.setLuckyNumber(this.codeNumber);
					uiDownPageId.setIssue(((RwdState) Mem.getRwdStateList()
							.get(0)).getIssue());
					UiDownPage uiDownPage = new UiDownPage();
					uiDownPage.setId(uiDownPageId);
					message = session.save(uiDownPage).toString();
					trans.commit();
					if ((message != null) && (message.length() > 0)
							&& (this.phone != null)
							&& (this.phone.length() > 0))
					{
						if ((Mem.getRwdStateList() != null)
								&& (Mem.getRwdStateList().size() > 0))
						{
							this.tag = ((RwdState) Mem.getRwdStateList().get(0))
									.getTag();
							this.newissue = String
									.valueOf(Integer.parseInt(new StringBuilder(
											String.valueOf(((RwdState) Mem
													.getRwdStateList().get(0))
													.getIssue())).toString()) + 1);
						}

						String op_issue = "";
						DateFormat d = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");

						int k = 0;
						try
						{
							Date date1 = sdf.parse(this.tag);
							Date date2 = sdf.parse(d.format(new Date())
									.toString());
							k = date1.compareTo(date2);
						}
						catch (ParseException e1)
						{
							e1.printStackTrace();
						}

						String row_str_id = "";
						String row_str_number = "";
						int num = 0;
						String hql = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID = "
								+ Mem.getNumberId(num);
						Query query = session.createSQLQuery(hql);
						List uList = query.list();
						if ((uList != null) && (uList.size() > 0))
						{
							int i;
							Transaction trans2;
							Object[] row;
							UiLuckyCodeRwdId uiRandom50RwdId;
							UiLuckyCodeRwd uiRandom50Rwd;
							if (k > 0)
							{
								for (i = 0; i < uList.size(); ++i)
								{
									row = (Object[]) uList.get(i);
									row_str_id = row[0].toString();
									row_str_number = row[1].toString();
								}

								trans2 = session.beginTransaction();
								uiRandom50RwdId = new UiLuckyCodeRwdId();
								uiRandom50RwdId.setIssue(((RwdState) Mem
										.getRwdStateList().get(0)).getIssue());
								uiRandom50RwdId.setRandom(row_str_number);
								uiRandom50RwdId.setSvcId(this.phone);
								uiRandom50RwdId.setTag("1");
								uiRandom50RwdId.setWinTag("0");
								uiRandom50Rwd = new UiLuckyCodeRwd();
								uiRandom50Rwd.setId(uiRandom50RwdId);
								session.save(uiRandom50Rwd);
								trans2.commit();
								op_issue = ((RwdState) Mem.getRwdStateList()
										.get(0)).getIssue();
							}
							else
							{
								for (int t = 0; t < uList.size(); ++t)
								{
									Object[] objs = (Object[]) uList.get(t);
									row_str_id = objs[0].toString();
									row_str_number = objs[1].toString();
								}

								trans2 = session.beginTransaction();
								uiRandom50RwdId = new UiLuckyCodeRwdId();
								uiRandom50RwdId.setIssue(this.newissue);
								uiRandom50RwdId.setRandom(row_str_number);
								uiRandom50RwdId.setSvcId(this.phone);
								uiRandom50RwdId.setTag("1");
								uiRandom50RwdId.setWinTag("0");
								uiRandom50Rwd = new UiLuckyCodeRwd();
								uiRandom50Rwd.setId(uiRandom50RwdId);
								session.save(uiRandom50Rwd);
								trans2.commit();
								op_issue = this.newissue;
							}
							String value = "";
							if ((row_str_number != null)
									&& (row_str_number.length() > 0))
							{
								UiLuckyCodeRwdId uId = new UiLuckyCodeRwdId();
								uId.setSvcId(this.phone);
								uId.setRandom(row_str_number);
								uId.setWinTag("0");
								uId.setIssue(op_issue);
								UiLuckyCodeRwd u = new UiLuckyCodeRwd();
								u.setId(uId);
								if ((value != null) && (value.length() > 0))
									value = value + "," + row_str_number + ":"
											+ "0" + ":" + op_issue + ":" + "1";
								else
								{
									value = row_str_number + ":" + "0" + ":"
											+ op_issue + ":" + "1";
								}
								Mem.addRwd(this.phone, value);

								if (Integer.parseInt(score_number) > 0)
								{
									ScoreAction scoreAction = new ScoreAction();
									scoreAction.giveScore(score_number,
											this.phone, this.pId);
								}
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		request.setAttribute("type", this.pId);
		HibernateSessionFactory.closeSession();
		if ("3".equals(this.terminalType))
		{
			return "jmj";
		}
		return "jmj-2g";
	}

	public String goDownwyy()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String message = "";
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		Criteria c = session.createCriteria(UiDownPage.class);
		ProjectionList prolist = Projections.projectionList();
		prolist.add(Projections.rowCount());
		c.setProjection(prolist);
		c.add(Expression.eq("id.PId", this.pId));
		c.add(Expression.eq("id.phone", this.phone));
		List list = c.list();
		int a = -2;
		if ((list != null) && (list.size() > 0))
			a = Integer.parseInt(list.get(0).toString());
		try
		{
			if ((a <= 0) && (a != -2) && (this.codeNumber != null)
					&& (this.codeNumber.length() > 0))
			{
				Transaction trans = session.beginTransaction();
				UiDownPageId uiDownPageId = new UiDownPageId();
				uiDownPageId.setPId(this.pId);
				uiDownPageId.setPName(this.pName);
				uiDownPageId.setPhone(this.phone);
				uiDownPageId.setLuckyNumber(this.codeNumber);
				uiDownPageId.setIssue(((RwdState) Mem.getRwdStateList().get(0))
						.getIssue());
				UiDownPage uiDownPage = new UiDownPage();
				uiDownPage.setId(uiDownPageId);
				message = session.save(uiDownPage).toString();
				trans.commit();
				if ((message != null) && (message.length() > 0)
						&& (this.phone != null) && (this.phone.length() > 0))
				{
					if ((Mem.getRwdStateList() != null)
							&& (Mem.getRwdStateList().size() > 0))
					{
						this.tag = ((RwdState) Mem.getRwdStateList().get(0))
								.getTag();
						this.newissue = String.valueOf(Integer
								.parseInt(new StringBuilder(String
										.valueOf(((RwdState) Mem
												.getRwdStateList().get(0))
												.getIssue())).toString()) + 1);
					}

					String op_issue = "";
					DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					int k = 0;
					try
					{
						Date date1 = sdf.parse(this.tag);
						Date date2 = sdf.parse(d.format(new Date()).toString());
						k = date1.compareTo(date2);
					}
					catch (ParseException e1)
					{
						e1.printStackTrace();
					}

					String row_str_id = "";
					String row_str_number = "";
					int num = 0;
					String hql = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID = "
							+ Mem.getNumberId(num);
					Query query = session.createSQLQuery(hql);
					List uList = query.list();
					if ((uList != null) && (uList.size() > 0))
					{
						int i;
						Transaction trans2;
						Object[] row;
						UiLuckyCodeRwdId uiRandom50RwdId;
						UiLuckyCodeRwd uiRandom50Rwd;
						if (k > 0)
						{
							for (i = 0; i < uList.size(); ++i)
							{
								row = (Object[]) uList.get(i);
								row_str_id = row[0].toString();
								row_str_number = row[1].toString();
							}

							trans2 = session.beginTransaction();
							uiRandom50RwdId = new UiLuckyCodeRwdId();
							uiRandom50RwdId.setIssue(((RwdState) Mem
									.getRwdStateList().get(0)).getIssue());
							uiRandom50RwdId.setRandom(row_str_number);
							uiRandom50RwdId.setSvcId(this.phone);
							uiRandom50RwdId.setTag("1");
							uiRandom50RwdId.setWinTag("0");
							uiRandom50Rwd = new UiLuckyCodeRwd();
							uiRandom50Rwd.setId(uiRandom50RwdId);
							session.save(uiRandom50Rwd);
							trans2.commit();
							op_issue = ((RwdState) Mem.getRwdStateList().get(0))
									.getIssue();
						}
						else
						{
							for (int t = 0; t < uList.size(); ++t)
							{
								Object[] objs = (Object[]) uList.get(t);
								row_str_id = objs[0].toString();
								row_str_number = objs[1].toString();
							}

							trans2 = session.beginTransaction();
							uiRandom50RwdId = new UiLuckyCodeRwdId();
							uiRandom50RwdId.setIssue(this.newissue);
							uiRandom50RwdId.setRandom(row_str_number);
							uiRandom50RwdId.setSvcId(this.phone);
							uiRandom50RwdId.setTag("1");
							uiRandom50RwdId.setWinTag("0");
							uiRandom50Rwd = new UiLuckyCodeRwd();
							uiRandom50Rwd.setId(uiRandom50RwdId);
							session.save(uiRandom50Rwd);
							trans2.commit();
							op_issue = this.newissue;
						}
						String value = "";
						if ((row_str_number != null)
								&& (row_str_number.length() > 0))
						{
							UiLuckyCodeRwdId uId = new UiLuckyCodeRwdId();
							uId.setSvcId(this.phone);
							uId.setRandom(row_str_number);
							uId.setWinTag("0");
							uId.setIssue(op_issue);
							UiLuckyCodeRwd u = new UiLuckyCodeRwd();
							u.setId(uId);
							if ((value != null) && (value.length() > 0))
								value = value + "," + row_str_number + ":"
										+ "0" + ":" + op_issue + ":" + "1";
							else
							{
								value = row_str_number + ":" + "0" + ":"
										+ op_issue + ":" + "1";
							}
							Mem.addRwd(this.phone, value);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		request.setAttribute("terminalType", this.terminalType);
		HibernateSessionFactory.closeSession();
		return "goWyyIphone";
	}

	public String yidaoGoDown()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String message = "";
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		Criteria c = session.createCriteria(UiDownPage.class);
		ProjectionList prolist = Projections.projectionList();
		prolist.add(Projections.rowCount());
		c.setProjection(prolist);
		c.add(Expression.eq("id.PId", this.pId));
		c.add(Expression.eq("id.phone", this.phone));
		List list = c.list();
		int a = -2;
		if ((list != null) && (list.size() > 0))
			a = Integer.parseInt(list.get(0).toString());
		try
		{
			if ((a <= 0) && (a != -2))
			{
				int w;
				String score_number = "0";

				if ("3".equals(this.terminalType))
				{
					for (w = 0; w < Mem.getTaskList().size(); ++w)
					{
						if ((((UiLuckyCodeTask) Mem.getTaskList().get(w))
								.getId().getPId() != this.pId)
								&& (!(this.pId
										.equals(((UiLuckyCodeTask) Mem
												.getTaskList().get(w)).getId()
												.getPId()))))
							continue;
						this.codeNumber = ((UiLuckyCodeTask) Mem.getTaskList()
								.get(w)).getId().getPLuckyNum();
						score_number = ((UiLuckyCodeTask) Mem.getTaskList()
								.get(w)).getId().getPIntegralNum();
						this.pName = ((UiLuckyCodeTask) Mem.getTaskList()
								.get(w)).getId().getPName();
					}
				}
				else
				{
					for (w = 0; w < Mem.getTask2gList().size(); ++w)
					{
						if ((((UiLuckyCodeTask) Mem.getTask2gList().get(w))
								.getId().getPId() != this.pId)
								&& (!(this.pId.equals(((UiLuckyCodeTask) Mem
										.getTask2gList().get(w)).getId()
										.getPId()))))
							continue;
						this.codeNumber = ((UiLuckyCodeTask) Mem
								.getTask2gList().get(w)).getId().getPLuckyNum();
						score_number = ((UiLuckyCodeTask) Mem.getTaskList()
								.get(w)).getId().getPIntegralNum();
						this.pName = ((UiLuckyCodeTask) Mem.getTask2gList()
								.get(w)).getId().getPName();
					}

				}

				if ((this.codeNumber != null) && (this.codeNumber.length() > 0))
				{
					Transaction trans = session.beginTransaction();
					UiDownPageId uiDownPageId = new UiDownPageId();
					uiDownPageId.setPId(this.pId);
					uiDownPageId.setPName(this.pName);
					uiDownPageId.setPhone(this.phone);
					uiDownPageId.setLuckyNumber(this.codeNumber);
					uiDownPageId.setIssue(((RwdState) Mem.getRwdStateList()
							.get(0)).getIssue());
					UiDownPage uiDownPage = new UiDownPage();
					uiDownPage.setId(uiDownPageId);
					message = session.save(uiDownPage).toString();
					trans.commit();
					if ((message != null) && (message.length() > 0)
							&& (this.phone != null)
							&& (this.phone.length() > 0))
					{
						if ((Mem.getRwdStateList() != null)
								&& (Mem.getRwdStateList().size() > 0))
						{
							this.tag = ((RwdState) Mem.getRwdStateList().get(0))
									.getTag();
							this.newissue = String
									.valueOf(Integer.parseInt(new StringBuilder(
											String.valueOf(((RwdState) Mem
													.getRwdStateList().get(0))
													.getIssue())).toString()) + 1);
						}

						String op_issue = "";
						DateFormat d = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");

						int k = 0;
						try
						{
							Date date1 = sdf.parse(this.tag);
							Date date2 = sdf.parse(d.format(new Date())
									.toString());
							k = date1.compareTo(date2);
						}
						catch (ParseException e1)
						{
							e1.printStackTrace();
						}

						String row_str_id = "";
						String row_str_number = "";
						int num = 0;
						String hql = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID = "
								+ Mem.getNumberId(num);
						Query query = session.createSQLQuery(hql);
						List uList = query.list();
						if ((uList != null) && (uList.size() > 0))
						{
							int i;
							Transaction trans2;
							Object[] row;
							UiLuckyCodeRwdId uiRandom50RwdId;
							UiLuckyCodeRwd uiRandom50Rwd;
							if (k > 0)
							{
								for (i = 0; i < uList.size(); ++i)
								{
									row = (Object[]) uList.get(i);
									row_str_id = row[0].toString();
									row_str_number = row[1].toString();
								}

								trans2 = session.beginTransaction();
								uiRandom50RwdId = new UiLuckyCodeRwdId();
								uiRandom50RwdId.setIssue(((RwdState) Mem
										.getRwdStateList().get(0)).getIssue());
								uiRandom50RwdId.setRandom(row_str_number);
								uiRandom50RwdId.setSvcId(this.phone);
								uiRandom50RwdId.setTag("1");
								uiRandom50RwdId.setWinTag("0");
								uiRandom50Rwd = new UiLuckyCodeRwd();
								uiRandom50Rwd.setId(uiRandom50RwdId);
								session.save(uiRandom50Rwd);
								trans2.commit();
								op_issue = ((RwdState) Mem.getRwdStateList()
										.get(0)).getIssue();
							}
							else
							{
								for (int t = 0; t < uList.size(); ++t)
								{
									Object[] objs = (Object[]) uList.get(t);
									row_str_id = objs[0].toString();
									row_str_number = objs[1].toString();
								}

								trans2 = session.beginTransaction();
								uiRandom50RwdId = new UiLuckyCodeRwdId();
								uiRandom50RwdId.setIssue(this.newissue);
								uiRandom50RwdId.setRandom(row_str_number);
								uiRandom50RwdId.setSvcId(this.phone);
								uiRandom50RwdId.setTag("1");
								uiRandom50RwdId.setWinTag("0");
								uiRandom50Rwd = new UiLuckyCodeRwd();
								uiRandom50Rwd.setId(uiRandom50RwdId);
								session.save(uiRandom50Rwd);
								trans2.commit();
								op_issue = this.newissue;
							}
							String value = "";
							if ((row_str_number != null)
									&& (row_str_number.length() > 0))
							{
								if ((value != null) && (value.length() > 0))
									value = value + "," + row_str_number + ":"
											+ "0" + ":" + op_issue + ":" + "1";
								else
								{
									value = row_str_number + ":" + "0" + ":"
											+ op_issue + ":" + "1";
								}
								Mem.addRwd(this.phone, value);

								if (Integer.parseInt(score_number) > 0)
								{
									ScoreAction scoreAction = new ScoreAction();
									scoreAction.giveScore(score_number,
											this.phone, this.pId);
								}
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		request.setAttribute("type", this.pId);
		HibernateSessionFactory.closeSession();
		if ("3".equals(this.terminalType))
		{
			return "yidao";
		}
		return "yidao-2g";
	}

	public String yingyetingGoDown()
	{
		int w;
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String message = "";
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		String code_number = "";
		String score_number = "";

		if ("3".equals(this.terminalType))
		{
			for (w = 0; w < Mem.getTaskList().size(); ++w)
			{
				if ((((UiLuckyCodeTask) Mem.getTaskList().get(w)).getId()
						.getPId() != this.pId)
						&& (!(this.pId.equals(((UiLuckyCodeTask) Mem
								.getTaskList().get(w)).getId().getPId()))))
					continue;
				code_number = ((UiLuckyCodeTask) Mem.getTaskList().get(w))
						.getId().getPLuckyNum();
				score_number = ((UiLuckyCodeTask) Mem.getTaskList().get(w))
						.getId().getPIntegralNum();
				this.pName = ((UiLuckyCodeTask) Mem.getTaskList().get(w))
						.getId().getPName();
			}
		}
		else
		{
			for (w = 0; w < Mem.getTask2gList().size(); ++w)
			{
				if ((((UiLuckyCodeTask) Mem.getTask2gList().get(w)).getId()
						.getPId() != this.pId)
						&& (!(this.pId.equals(((UiLuckyCodeTask) Mem
								.getTask2gList().get(w)).getId().getPId()))))
					continue;
				code_number = ((UiLuckyCodeTask) Mem.getTask2gList().get(w))
						.getId().getPLuckyNum();
				score_number = ((UiLuckyCodeTask) Mem.getTaskList().get(w))
						.getId().getPIntegralNum();
				this.pName = ((UiLuckyCodeTask) Mem.getTask2gList().get(w))
						.getId().getPName();
			}
		}

		if ((code_number != null) && (code_number.length() > 0))
		{
			Criteria c = session.createCriteria(UiDownPage.class);
			ProjectionList prolist = Projections.projectionList();
			prolist.add(Projections.rowCount());
			c.setProjection(prolist);
			c.add(Expression.eq("id.PId", this.pId));
			c.add(Expression.eq("id.phone", this.phone));
			List list = c.list();

			int a = -2;
			if ((list != null) && (list.size() > 0))
				a = Integer.parseInt(list.get(0).toString());
			try
			{
				if ((a <= 0) && (a != -2))
				{
					message = insertDownPage(this.pId, this.pName, this.phone,
							code_number);
					if ((message != null) && (message.length() > 0)
							&& (this.phone != null)
							&& (this.phone.length() > 0))
					{
						if ((Mem.getRwdStateList() != null)
								&& (Mem.getRwdStateList().size() > 0))
						{
							this.tag = ((RwdState) Mem.getRwdStateList().get(0))
									.getTag();
							this.newissue = String
									.valueOf(Integer.parseInt(new StringBuilder(
											String.valueOf(((RwdState) Mem
													.getRwdStateList().get(0))
													.getIssue())).toString()) + 1);
						}

						String op_issue = "";
						DateFormat d = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");

						int k = 0;
						try
						{
							Date date1 = sdf.parse(this.tag);
							Date date2 = sdf.parse(d.format(new Date())
									.toString());
							k = date1.compareTo(date2);
						}
						catch (ParseException e1)
						{
							e1.printStackTrace();
						}

						String row_str_id = "";
						String row_str_number = "";
						int num = Integer.parseInt(code_number);
						int n = Mem.getNumberId(num);
						String hql = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID >= "
								+ (n - num + 1) + " and " + n + " >=T.ID";
						Query query = session.createSQLQuery(hql);
						List uList = query.list();
						if ((uList != null) && (uList.size() > 0))
						{
							if (k > 0)
							{
								op_issue = ((RwdState) Mem.getRwdStateList()
										.get(0)).getIssue();
							}
							else
							{
								op_issue = this.newissue;
							}
							String value = "";
							Transaction tx = session.beginTransaction();
							for (int i = 0; i < uList.size(); ++i)
							{
								Object[] row = (Object[]) uList.get(i);
								row_str_id = row[0].toString();
								row_str_number = row[1].toString();
								if ((row_str_number == null)
										|| (row_str_number.length() <= 0))
									continue;
								UiLuckyCodeRwdId uiRandom50RwdId = new UiLuckyCodeRwdId();
								uiRandom50RwdId.setIssue(op_issue);
								uiRandom50RwdId.setRandom(row_str_number);
								uiRandom50RwdId.setSvcId(this.phone);
								uiRandom50RwdId.setTag("1");
								uiRandom50RwdId.setWinTag("0");
								UiLuckyCodeRwd uiRandom50Rwd = new UiLuckyCodeRwd();
								uiRandom50Rwd.setId(uiRandom50RwdId);
								session.save(uiRandom50Rwd);
								if (i % 100 == 0)
								{
									session.flush();
									session.clear();
								}

								if ((value != null) && (value.length() > 0))
									value = value + "," + row_str_number + ":"
											+ "0" + ":" + op_issue + ":" + "1";
								else
								{
									value = row_str_number + ":" + "0" + ":"
											+ op_issue + ":" + "1";
								}

							}

							tx.commit();

							session.close();
							Mem.addRwd(this.phone, value);
						}
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		request.setAttribute("type", this.pId);
		HibernateSessionFactory.closeSession();
		if ("3".equals(this.terminalType))
		{
			return "yingyeting";
		}
		return "yingyeting-2g";
	}

	public String lingxiGoDown()
	{
		int w;
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String message = "";
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		String code_number = "";
		String score_number = "";

		if ("3".equals(this.terminalType))
		{
			for (w = 0; w < Mem.getTaskList().size(); ++w)
			{
				if ((((UiLuckyCodeTask) Mem.getTaskList().get(w)).getId()
						.getPId() != this.pId)
						&& (!(this.pId.equals(((UiLuckyCodeTask) Mem
								.getTaskList().get(w)).getId().getPId()))))
					continue;
				code_number = ((UiLuckyCodeTask) Mem.getTaskList().get(w))
						.getId().getPLuckyNum();
				score_number = ((UiLuckyCodeTask) Mem.getTaskList().get(w))
						.getId().getPIntegralNum();
				this.pName = ((UiLuckyCodeTask) Mem.getTaskList().get(w))
						.getId().getPName();
			}
		}
		else
		{
			for (w = 0; w < Mem.getTask2gList().size(); ++w)
			{
				if ((((UiLuckyCodeTask) Mem.getTask2gList().get(w)).getId()
						.getPId() != this.pId)
						&& (!(this.pId.equals(((UiLuckyCodeTask) Mem
								.getTask2gList().get(w)).getId().getPId()))))
					continue;
				code_number = ((UiLuckyCodeTask) Mem.getTask2gList().get(w))
						.getId().getPLuckyNum();
				score_number = ((UiLuckyCodeTask) Mem.getTaskList().get(w))
						.getId().getPIntegralNum();
				this.pName = ((UiLuckyCodeTask) Mem.getTask2gList().get(w))
						.getId().getPName();
			}
		}

		if ((code_number != null) && (code_number.length() > 0))
		{
			Criteria c = session.createCriteria(UiDownPage.class);
			ProjectionList prolist = Projections.projectionList();
			prolist.add(Projections.rowCount());
			c.setProjection(prolist);
			c.add(Expression.eq("id.PId", this.pId));
			c.add(Expression.eq("id.phone", this.phone));
			List list = c.list();

			int a = -2;
			if ((list != null) && (list.size() > 0))
				a = Integer.parseInt(list.get(0).toString());
			try
			{
				if ((a <= 0) && (a != -2))
				{
					message = insertDownPage(this.pId, this.pName, this.phone,
							code_number);
					if ((message != null) && (message.length() > 0)
							&& (this.phone != null)
							&& (this.phone.length() > 0))
					{
						if ((Mem.getRwdStateList() != null)
								&& (Mem.getRwdStateList().size() > 0))
						{
							this.tag = ((RwdState) Mem.getRwdStateList().get(0))
									.getTag();
							this.newissue = String
									.valueOf(Integer.parseInt(new StringBuilder(
											String.valueOf(((RwdState) Mem
													.getRwdStateList().get(0))
													.getIssue())).toString()) + 1);
						}

						String op_issue = "";
						DateFormat d = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");

						int k = 0;
						try
						{
							Date date1 = sdf.parse(this.tag);
							Date date2 = sdf.parse(d.format(new Date())
									.toString());
							k = date1.compareTo(date2);
						}
						catch (ParseException e1)
						{
							e1.printStackTrace();
						}

						String row_str_id = "";
						String row_str_number = "";
						int num = Integer.parseInt(code_number);
						int n = Mem.getNumberId(num);
						String hql = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID >= "
								+ (n - num + 1) + " and " + n + " >=T.ID";
						Query query = session.createSQLQuery(hql);
						List uList = query.list();
						if ((uList != null) && (uList.size() > 0))
						{
							if (k > 0)
							{
								op_issue = ((RwdState) Mem.getRwdStateList()
										.get(0)).getIssue();
							}
							else
							{
								op_issue = this.newissue;
							}
							String value = "";
							Transaction tx = session.beginTransaction();
							for (int i = 0; i < uList.size(); ++i)
							{
								Object[] row = (Object[]) uList.get(i);
								row_str_id = row[0].toString();
								row_str_number = row[1].toString();
								if ((row_str_number == null)
										|| (row_str_number.length() <= 0))
									continue;
								UiLuckyCodeRwdId uiRandom50RwdId = new UiLuckyCodeRwdId();
								uiRandom50RwdId.setIssue(op_issue);
								uiRandom50RwdId.setRandom(row_str_number);
								uiRandom50RwdId.setSvcId(this.phone);
								uiRandom50RwdId.setTag("1");
								uiRandom50RwdId.setWinTag("0");
								UiLuckyCodeRwd uiRandom50Rwd = new UiLuckyCodeRwd();
								uiRandom50Rwd.setId(uiRandom50RwdId);
								session.save(uiRandom50Rwd);
								if (i % 100 == 0)
								{
									session.flush();
									session.clear();
								}

								if ((value != null) && (value.length() > 0))
									value = value + "," + row_str_number + ":"
											+ "0" + ":" + op_issue + ":" + "1";
								else
								{
									value = row_str_number + ":" + "0" + ":"
											+ op_issue + ":" + "1";
								}

							}

							tx.commit();

							session.close();
							Mem.addRwd(this.phone, value);
						}
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		request.setAttribute("type", this.pId);
		HibernateSessionFactory.closeSession();
		if ("3".equals(this.terminalType))
		{
			return "lingxi";
		}
		return "lingxi-2g";
	}

	public String showDownPage()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

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
			return "showDownPage";
		}
		return "showDownPage-2g";
	}

	public String getMiddle()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		HttpServletRequest request = ServletActionContext.getRequest();
		this.phone = request.getHeader("x-up-calling-line-id");
		if ((!("".equals(this.phone))) && (this.phone != null)
				&& (this.phone.substring(0, 3).trim().equals("861")))
		{
			this.phone = this.phone.substring(2, 13);
		}

		return this.phone;
	}

	public String showMyActivity()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		Mem.addMyWinCode();

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		if ((!("".equals(this.phone))) && (this.phone != null))
		{
			request.setAttribute(
					"uiLuckyNumberUserSList",
					parseMyString((String) Mem.getShowMyActivityMap().get(
							this.phone)));
			request.setAttribute("phone", this.phone);
		}
		else
		{
			return "noMiddleActivity-2g";
		}
		request.setAttribute("random", this.random);
		if ("3".equals(this.terminalType))
		{
			return "showMyActivity";
		}
		return "showMyActivity-2g";
	}

	public String userSet()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

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
			return "userSet";
		}
		return "userSet-2g";
	}

	public String noPhoenUserSet()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		return "noPhoenUserSet";
	}

	public String showActivityWin()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		Mem.addAnCodeWin();

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		if ((Mem.getRwdStateList() != null)
				&& (Mem.getRwdStateList().size() > 0))
		{
			request.setAttribute("tagState", ((RwdState) Mem.getRwdStateList()
					.get(0)).getTagState());

			request.setAttribute("luckyCode", ((RwdState) Mem.getRwdStateList()
					.get(0)).getLuckyCode());

			if ((((RwdState) Mem.getRwdStateList().get(0)).getTagState()
					.equals("1"))
					|| (((RwdState) Mem.getRwdStateList().get(0)).getTagState() == "1"))
			{
				request.setAttribute("issue", ((RwdState) Mem.getRwdStateList()
						.get(0)).getIssue());
			}
			else
			{
				request.setAttribute("issue", Integer.valueOf(Integer
						.parseInt(((RwdState) Mem.getRwdStateList().get(0))
								.getIssue()) - 1));
			}
			request.setAttribute("lotteryDate", ((RwdState) Mem
					.getRwdStateList().get(0)).getLotteryDate());

			request.setAttribute("uiRandom50RwdListOneList",
					Mem.getUiRandom50RwdListOneList());
		}
		request.setAttribute("phone", this.phone);

		request.setAttribute("random", this.random);
		if ("3".equals(this.terminalType))
		{
			return "showActivityWin";
		}
		return "showActivityWin-2g";
	}

	public String showActivity()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		Mem.addCodeRule();

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
			return "showActivity";
		}
		return "showActivity-2g";
	}

	public String showActivitykeFu()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		Mem.addkefu();

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
			return "keFu";
		}
		return "keFu-2g";
	}

	public void setCookie(String phone)
	{
		phone = FilterHtml.Html2Text(phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		HttpServletResponse response = ServletActionContext.getResponse();

		Cookie cookie_phone = new Cookie("COOKIE_PHONE_NUMBER", this.phone);

		cookie_phone.setMaxAge(99999999);
		response.addCookie(cookie_phone);
	}

	public void isAutoLogin()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		HttpServletRequest request = ServletActionContext.getRequest();
		String phoneNumCookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if ("COOKIE_PHONE_NUMBER".equals(cookie.getName()))
				{
					phoneNumCookie = cookie.getValue();
					this.phone = phoneNumCookie;
				}
	}

	public String showIssueWin()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("issue",
				((RwdState) Mem.getRwdStateList().get(0)).getIssue());

		request.setAttribute("random", this.random);
		if ("3".equals(this.terminalType))
		{
			return "showIssueWin";
		}
		return "showIssueWin-2g";
	}

	public String addOpinion()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		String message = "";
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		Transaction trans = session.beginTransaction();
		OpinionId opinionId = new OpinionId();
		opinionId.setOpinion(this.op);
		opinionId.setPhone(this.phone);
		opinionId
				.setIssue(((RwdState) Mem.getRwdStateList().get(0)).getIssue());
		Opinion opinion = new Opinion();
		opinion.setId(opinionId);
		message = session.save(opinion).toString();
		trans.commit();
		HibernateSessionFactory.closeSession();
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

	public String addOpinion_2g()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		Transaction trans = session.beginTransaction();
		OpinionId opinionId = new OpinionId();
		opinionId.setOpinion(this.op);
		opinionId.setPhone(this.phone);
		opinionId
				.setIssue(((RwdState) Mem.getRwdStateList().get(0)).getIssue());
		Opinion opinion = new Opinion();
		opinion.setId(opinionId);
		session.save(opinion);
		trans.commit();
		request.setAttribute("phone", this.phone);
		request.setAttribute("mes", "true");
		HibernateSessionFactory.closeSession();
		return "userSet-2g";
	}

	public String randomContinue_2g()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		Transaction trans = session.beginTransaction();
		RandomContinueId randomContinueId = new RandomContinueId();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		randomContinueId.setCreatedate(df.format(new Date()));
		randomContinueId.setPhone(this.phone);
		randomContinueId.setIssue(((RwdState) Mem.getRwdStateList().get(0))
				.getIssue());
		RandomContinue randomContinue = new RandomContinue();
		randomContinue.setId(randomContinueId);
		session.save(randomContinue);
		trans.commit();
		request.setAttribute("phone", this.phone);
		request.setAttribute("mes", "true");
		HibernateSessionFactory.closeSession();
		return "userSet-2g";
	}

	public String randomStop_2g()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		Transaction trans = session.beginTransaction();
		RandomStopId randomStopId = new RandomStopId();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		randomStopId.setCreatedate(df.format(new Date()));
		randomStopId.setPhone(this.phone);
		randomStopId.setIssue(((RwdState) Mem.getRwdStateList().get(0))
				.getIssue());
		RandomStop randomStop = new RandomStop();
		randomStop.setId(randomStopId);
		session.save(randomStop);
		trans.commit();
		request.setAttribute("phone", this.phone);
		request.setAttribute("mes", "true");
		HibernateSessionFactory.closeSession();
		return "userSet-2g";
	}

	public String addClick()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		String message = "";
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Transaction trans = session.beginTransaction();
		ClicknumId clicknumId = new ClicknumId();
		clicknumId.setCreateDate(df.format(new Date()).toString());
		clicknumId.setPhone(this.phone);
		clicknumId.setClickUrl(this.clickUrl);
		Clicknum clicknum = new Clicknum();
		clicknum.setId(clicknumId);
		message = session.save(clicknum).toString();
		trans.commit();
		HibernateSessionFactory.closeSession();
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

	public String consume()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		return "consume";
	}

	public String accurate()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		return "accurate";
	}

	public String realTime()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		return "realTime";
	}

	public String toVacOrder()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("terminalType", this.terminalType);
		request.setAttribute("random", this.random);
		request.setAttribute("spid", this.spid);
		request.setAttribute("sppid", this.sppid);
		return "toVacOrder";
	}

	public String vacOrder()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String message = "";
		String mes = "";
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		try
		{
			if (vacOrderTag(this.phone, this.spid, this.sppid))
			{
				Random r = new Random();
				int a = r.nextInt(9999);
				String pn = "86" + this.phone;
				String nowDateStr = MyDateTime.getCurrentDateTimeStringNow();
				String seq = nowDateStr.substring(4) + "52001" + a;
				VacAction vac = new VacAction();
				String xml = vac.madeCheckPriceXML(seq, pn, "1", this.spid,
						this.sppid);
				String xml2 = vac.madeCheckPriceConfirmXml(seq, nowDateStr);

				String url = "http://192.168.37.52:8088/vac/services/SnatcherService?wsdl";
				List list = VacAction.getList(vac.CheckPrice(xml, url));
				this.resultCode = ((String) ((Map) list.get(0)).get("value"));

				if ("0".equals(this.resultCode))
				{
					String needConfirm = (String) ((Map) list.get(2))
							.get("value");

					if ("1".equals(needConfirm))
					{
						List list2 = VacAction.getList(vac.CheckPriceConfirm(
								xml2, url));
						this.resultCode = ((String) ((Map) list2.get(0))
								.get("value"));
						if ("0".equals(this.resultCode))
						{
							message = "true";
						}
						else
							message = "false";
					}
					else
					{
						message = "true";
					}
				}
				else if ("1200".equals(this.resultCode))
				{
					message = "1200";
				}
				else
				{
					message = "false";
				}
				if (("true".equals(message)) && (!("".equals(this.phone)))
						&& (this.phone != null))
				{
					int num = 50;
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Transaction trans = session.beginTransaction();
					UiVacOrderId uiVacOrderId = new UiVacOrderId();
					uiVacOrderId.setSpid("11312");
					uiVacOrderId.setSppid("3111004800");
					uiVacOrderId.setPhone(this.phone);
					uiVacOrderId.setLuckyNumber(String.valueOf(num));
					uiVacOrderId.setIssue(((RwdState) Mem.getRwdStateList()
							.get(0)).getIssue());
					uiVacOrderId.setComDate(getNextMonthFirst());
					uiVacOrderId.setCreateDate(sdf.format(new Date())
							.toString());
					UiVacOrder uiVacOrder = new UiVacOrder();
					uiVacOrder.setId(uiVacOrderId);
					mes = session.save(uiVacOrder).toString();
					trans.commit();
					if ((mes != null) && (mes.length() > 0)
							&& (this.phone != null)
							&& (this.phone.length() > 0))
					{
						if ((Mem.getRwdStateList() != null)
								&& (Mem.getRwdStateList().size() > 0))
						{
							this.tag = ((RwdState) Mem.getRwdStateList().get(0))
									.getTag();
							this.newissue = String
									.valueOf(Integer.parseInt(new StringBuilder(
											String.valueOf(((RwdState) Mem
													.getRwdStateList().get(0))
													.getIssue())).toString()) + 1);
						}

						String op_issue = "";
						DateFormat d = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");

						int k = 0;
						try
						{
							Date date1 = sdf.parse(this.tag);
							Date date2 = sdf.parse(d.format(new Date())
									.toString());
							k = date1.compareTo(date2);
						}
						catch (ParseException e1)
						{
							e1.printStackTrace();
						}

						String row_str_id = "";
						String row_str_number = "";
						int n = Mem.getNumberId(num);
						String hql = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID >= "
								+ (n - num + 1) + " and " + n + " >=T.ID";
						Query query = session.createSQLQuery(hql);
						List uList = query.list();
						if ((uList != null) && (uList.size() > 0))
						{
							if (k > 0)
							{
								op_issue = ((RwdState) Mem.getRwdStateList()
										.get(0)).getIssue();
							}
							else
							{
								op_issue = this.newissue;
							}
							String value = "";
							Transaction tx = session.beginTransaction();
							for (int i = 0; i < uList.size(); ++i)
							{
								Object[] row = (Object[]) uList.get(i);
								row_str_id = row[0].toString();
								row_str_number = row[1].toString();
								if ((row_str_number == null)
										|| (row_str_number.length() <= 0))
									continue;
								UiLuckyCodeRwdId uiRandom50RwdId = new UiLuckyCodeRwdId();
								uiRandom50RwdId.setIssue(op_issue);
								uiRandom50RwdId.setRandom(row_str_number);
								uiRandom50RwdId.setSvcId(this.phone);
								uiRandom50RwdId.setTag("2");
								uiRandom50RwdId.setWinTag("0");
								UiLuckyCodeRwd uiRandom50Rwd = new UiLuckyCodeRwd();
								uiRandom50Rwd.setId(uiRandom50RwdId);
								session.save(uiRandom50Rwd);
								if (i % 100 == 0)
								{
									session.flush();
									session.clear();
								}

								if ((value != null) && (value.length() > 0))
									value = value + "," + row_str_number + ":"
											+ "0" + ":" + op_issue + ":" + "2";
								else
								{
									value = row_str_number + ":" + "0" + ":"
											+ op_issue + ":" + "2";
								}

							}

							tx.commit();

							session.close();
							Mem.addRwd(this.phone, value);
						}
					}
				}
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("message", message);
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		return "vacOrder";
	}

	public String getNextMonthFirst()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(2, 1);
		lastDate.set(5, 1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	public boolean vacOrderTag(String phone, String spid, String sppid)
	{
		phone = FilterHtml.Html2Text(phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		boolean b = false;
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		String hql = "SELECT * FROM (SELECT t.com_date FROM UI_VAC_ORDER T where t.phone = "
				+ phone
				+ " AND T.SPID = "
				+ spid
				+ " AND T.SPPID = "
				+ sppid
				+ " ORDER BY T.com_date desc) WHERE ROWNUM = 1";
		Query query = session.createSQLQuery(hql);
		List list = query.list();
		if ((list != null) && (list.size() > 0))
		{
			String comDate = list.get(0).toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			int k = -2;
			try
			{
				Date date1 = sdf.parse(sdf.format(new Date()).toString());
				Date date2 = sdf.parse(comDate);
				k = date1.compareTo(date2);
			}
			catch (ParseException e1)
			{
				e1.printStackTrace();
			}
			if (k >= 0)
				b = true;
		}
		else
		{
			b = true;
		}
		HibernateSessionFactory.closeSession();
		return b;
	}

	public String jmj()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		request.setAttribute("pId", this.pId);
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
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

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

	public String toWyy()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		rom.bon.setAttribute("phone", this..open.wo_key.WoCheck;
import comrandomsymphonym.Clic.wo_keif ("3".equals(phonyterminalType))o_ke{o_ke	return "toWyy"wo_ke}o_key.nionId;
im-2gport }

	public String g
imI.open(mpor hbm.phony.xwor = FilterHtml.ando2Textort hb.xwork2.Actm;
import ort hbm.RandomStopId;
importmport hbm.Clrt hbm.Opinion;
ort hbm.RandomStopId;
importm.Opinion;
iageId;
imuserP
import hbm.RandomStopId;
importport hbm.foreId;
imoport hbm.RandomStopId;
importopforeo_keR.ClicNumberction; m.Clicport hbm.UiLu= new g;
import hbm.UiLc.wo_keickn!(ckyCodePhoneFlowBa.logincheckimport hbm.num;
import himport hbm.OpinionIdnoMiddlectiovit;
importhbm.RaHttpckage acom.bon y.WoChe package action;

import com.bonc.wo_key.WoCheck;
import com.opensymphony.xwork2.ActionSupport;
import hbm.Clicknum;
import hbm.ClndomContd;
import mport hbm.RandomContinueId;
iAnzhuohbm.RandomStop;
import hbm.RandomStopId;
import hbm.RwdState;
import hbm.UiDownPage;
import hbm.UiDownPageId;
import hbm.UiLuckyCodeBefore;
import hbm.UiLuckyCodeBeforeId;
import hbm.UiLuckyCodeFenxiang;
import hbm.UiLuckyCodeFenxiangId;
import hbm.UiLuckyCodePhoneFlowBag;
import hbm.UiLuckyCodePhoneFlowBagId;
import hbm.UiLuckyCodeRwd;
import hbm.UiLuckyCodeRwdId;
import hbm.UiLuckyCodeSign;
import hbm.UiLuckyCodeSignId;
import hbm.UiLuckyCodeTask;
import hbm.UiLuckyCodeTaskId;
import hbm.UiMusicOrder;
import hbm.UiMusicOrderId;
import hbm.UiVacOrder;
import hbm.UiVacOrderId;
import hibernate.HibernateSeriter;mport hbm.RandomContinueIinsertDownPage(ntinueIpId,ontinueIpNamentExceptio.UiLuhbm.OntinueIcode_nort hbm.Random
import hbm.RandomStopId;
i hbm.RwdState;
import hbm.UiDownPage;
import hbm.UiDownPageId;
import hbm.UiLuckyCodeBefore;
import hbm.UiLuckyCodeBeforeId;
import hbm.UiLuckyCodeFenxiang;
import hbm.UiLuckyCodeFenxiangId;
import hbm.UiLuckyCodePhoneFlowBaSessUiLusionList= HibernatetionLisFactoryort nate.criterionhbm.R			.opentionLisc.wo_kentinueImessagLuck"port hTransam.UiLut
	pr =t;
impo.begin
	private c.wo_keUirt org.dId u;

	privatId;
ie;

	privac.wo_kee String op;ck;PId(pIdclickUrl;

	private Sn;
(on;
clickUrl;

	private Sort h.xwork2.Actrl;

	private Luckyport h(lper;
imporprivate String randomIssue(((RwdState) Memort cid;

Lionc.ort (0import		ort String)ewissue;

	prite String o

	private Stri clickUrl;

	prate Sd(e String op;sultCo ActionSupone;

saved;

	priv).tontinuec.wo_ketring.comminc.wo_keorg.hibernate.criterion.closrnate.crc.wo_key.nionI Actionport hbm.RandomContinueId;Taskhbm.Randomint wageId;
im
import hbm.RandomStopId;
import hbm.RwdState;
import hbm.UiDownPage;
import hbm.UiDownPageId;
import hbm.UiLuckyCodeBefore;
import hbm.UiLuckyCodeBeforeId;
import hbm.UiLuckyCodeFenxiang;
import hbm.UiLuckyCodeFenxiangId;
import hbm.UiLuckyCodePhoneFlowBag;
import hbm.UiLuckyCodePhoneFlowBagId;
import hbm.UiLuckyCodeRwd;
import hbm.UiLuckyCodeRwdId;
import hbm.UiLuckyCodeSign;
import hbm.UiLuckyCodeSignId;
import hbm.UiLuckyextends ActionSupport
{tionList;
import org.hibernate.criterion.Projections;

public class ActivityAction extendslper;
impoom);
		thitinueIscorext(this.userPhom.ClicknumId;
import hbm.Opinion;
import hbm.Ofor (w = 0; w <private 

ppid;
siz	pri ++wvate S hbm.O;
im(((Ui;

Code

	private is.op)))
	
	pw)		if ng clic clStringStri) !=mphony.Idenum != n&&impo& (this.d;
impo.phonenum, this.userkenum != nString
			{
				if ((this.phoneull)
			8}$"num != continurivat				{Text(this.us.phonenum, this.userkey))
			{
				if ((thnum != null)185|186)\;

	prc.wo_ke		his.op = Filteronenum;
					this.op_tag = "1";
					if (this.phone.substring(0Integraltrim().equalphony.n;
				{
						this.phone = this.phone.substring(3, 14);
					}
				te St().equabm.Rabm.Raelse== "menhu") || ("menhu".equals(this.o2gp)))
		{
			if (WoCheck.check(this.phonenum, this.userkey))
			w.add(11,if ((this.phonenum != null)
						&& (this.phonenum.length() > 0)
						&& (this.phonenum
								.matches("^(13erCodeMap().get(this.pho186)\\d{8}$")))
				{
					this.phone = this.phonenum;
					this.op_tag = "1erCodeMap().get(his.phone.substring(0, 3).trim().equals("+86"))
					{
						this.phone = this.phone.substring(3, 14);
					}
					randomNumberAction = new RandomNumberAction();
					this.rand));
				}
				return execute();
		mber();
					rightNRwd;
imte String re			&null) lenghone = this.length() > rivate  hbm.OCriteria ckyNum;

	create85|186)\(e;

	pri.class().equaProjetion;ppid prolthis= 
					if s.p
					if (thi();
			.phone..add(string(0, 3)rowCountSmsName;	cate S
					if (.phone.phone.sub
		Exprne;

eq("id.PIdsymphony.Id.phone.subandomNumberAction = ne.opensymphony.xworkphone.s(thisone.subc.one.();
this.trina = -2hone.s					one.sonenum
									{	{
		31|132|155		rego	rander.parseInt.add(1
	priId;

	priphone.stryoCheck.check(thisa <= 0
					a				-2		returnk.checking PLuckyN
import org.doomNumberymphony.n;
imrt hbm.UiLu				.matce String resultCoalendar( ActionSonenum
					 Action("^(130|131|13				.matclengStop;
impoonenum
"," + rightNow.getTimeI("^(130|131|132|155ll) || (valendar(rivate String sppid;nMillis());
				}ghtNorivate String sppid;
1, 168);
					valreturn getKd;
impag				{pid;

	private String sppid;

	private Stmatches("^(private l2Text(thinewitrinckyCtinue(this.randomvalueOf( "";

					valu;
iml.HtmBuilder((this.randone);
		.terminale);
		this.ranml.Html2Text(String= FilterHtml.Html2Text(this.randoe String pushSmsg) Mem.getUse + 1his.termin > 0)
ext(this.u op_FilterHtport
{;
	DateFormat ;

	prSimple	HttpServltml.Html2Te"yyyy-MM-dd HH:mm:ss"his.termin request = Servl sdfRequest request = ServletActionContext.getRequest();
			reurn getKent k"menhy();
		ap().getHtml.Html2Tex	Htt date1kyNudf
					ort hbmaghis.terminaphone", t2is.userPhone)d.fServle;
i	Htt(xt(this.random Mem.getUserCodeM> 0).pho", thg pNpareTo(te("oest();
			St
					}atch (P				Excep.UiLue1FilterHtml.Html2Texe1.printStackTracr();
			
			String skey = httrow_str_i;
tKey();
			}
				return t(this.userPhonee", thisnut hb "";

					valu}
					Mem.getUserCvity-2 =private port hId(nu hbm.Cl skey = htthqlelivSELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID >= "(this.rand+ (n --2g";eque + " and " + nHtml2>=()
iddleActivQuery qm = F8}$")))
				{
SQLom = (hql		return "(thisu(this=Filter			rightNon getKey();lType onenum
					lTypehis.phone = FilterHtml.Html2Texicknkom + "," + rigl.Html2Tex	pRequest.gee);
		this.random = FilterHtml.H{
			HttpRe

	privring pushSmtml2Text(skey", skew = new Ge);
		this.op = FilterHtmllType = Filteest();
		this.phone);
		ttermieliverSkey-2g"{
	private Stx phone;

	private String newissulType) || thisi"menhui <alType		{
			if irPhone);
		this.op =Ob				[]		retml.&& (this));

	
	piuest();
				return "delirow[0]Id;

	private s.phone.contait(this.usull"1))
				&& (!(this.phon					e.equals("000000";
	}

	public		|| e.substring(0, ("^(130|13
				quest httpRe	{
					this.p		}honenum, thRwdvate g;
im50mberAc

	priv	RandomNumberAquest();
			tion randomNumbate StringpRequestberAction();
			this.random = g;
ime.substring(0, berAction();
			this.random = SvcIdow.getTimeIberAction();
			this.random = 
		t"1			request.);
			this.random = Winalue 0 (String) Memn = new Randomction randomNuerAction = new RandommberAction();
			this.ranoductid;on randomNumb(String) Memum;

	privateon randomNu			if (this.phoni % 1003).t+ "," + rig
		this.op =ap().put(flushmberAction();ap().put(clearmberAction();	String skes.phoneServleonenum
					termixecute();
			}
			re30|1ServletAServle+ ",(thie.equals("00000+ ":		this.phonhone "0(thi":(thipRequest.dSmsPhon"1tionContexte = httpRequesMillis());
	ServletA\\d{8}$")))
			{
	sPhoseedSmsP			SeedSmsPhonee = new SeedSmsPhone();
				sene.length() ne.length() txg pName;

s("^(130|ll)
					osequest();
		rivaadd		{rt hbm.UiLuctermi}
		this.phoickn "";

					valuhis.op = Fildom + "," + rig		this.op =Sis.obm.UiLuhis.oFlowBagId;
itribute("opmberAction();, this.op);.givetribu("phone", thist(this.phone);
					on = new .getUserCodeMkey", skekey", skkey", skey", key", y);
		("3".equalsWoCheck.checke				{
					return "deli		rightNowCodeTask;
import hbm.UiLuckyCodeTaskId;
import hbm.UiMusicOrder;
import hbm.UiMusicOrderId;
import hbm.UiVacOrder;
import hbm.UiVacOrderId;
import hibernate.WoCheck;
import comtypnsymphony..getUserrivate String codeNumber;

	private StrinicknumId;
import hbm.Opinion;
import hbm.OpinionIdd;

port hbm.Ra					phonyaiter;Url&& (this.userP
			response.s("^(130|131|132|155|156|1					"0Id;
import hbesponse.s)) "))
			response.se==			t.phone.k.checkndomContcloudrt hbm.UiilterHt request = ServletActiesponse.ssymphony || (this.ort hbm.RandomContd;


import hbm.RandomContinueId;Vachbm.RandomStop;
import hbm.RandomStopId;
import hbm.RwdState;
import hbm.UiDownPage;
import hbm.UiDownPageId;
import hbm.UiLuckyCodeBefore;
import hbm.UiLuckyCodeBeforeId;
import hbm.UiLuckyCodeFenxiang;
import hbm.UiLuckyCodeFenxiangId;
import hbm.UiLuckyCodePhoneFlowBag;
import hbm.UiLuckyCodePhoneFlowBagId;
import hbm.UiLuckyCodeRwd;
import hbm.UiLuckyCodeRwdId;
import hbm.UiLuckyCodeSign;
import hbm.UiLuckyCodeSignId;
import hbm.UiLuckyText(this.random);
		thixtends Acom);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		tvac			{.parse(df.formap_nandomNport
{, this.te("menhu".equals(this.op)))
		{
			if (WoCheif (("".equs.phonenum, this.userkey))
			{
				if ((this.phoneull)
						&& (thspid
					th() > 		reml2Text
						&& (this.phonenum
				tag = "1";
					if (this.phon
					}
					d{8}$")))
			"))s.phonenum, this.userkey))
			{
				if ((this.phonenum != nquestSpPi.phone));
				request.setAttribbute("rwdSdStateList", Mem.getRwdStateList());
			if ("3".equals(this.teterminalType))tribute8}$")))
			{
					this.hone = this.phonenum;
					this.op_tag = "1";
					if (tis.phonenum != ing(0, 3).trim().equa);
			}e", ((RwdState) Mem
						.getRwdStateList().get(0)).getTagStaUrl("+86"))Attribute", ((RwdState) Mem
						.getRwdStateList().get(0)).getTagStatmber();
		 > 0)
						&& (this.phonenum
								.matches("^(130|131|132|155Map().get(this.phonevacOrder
		trt hbm.UiLuckyCod		re0)).getSz	req= null) || (valg;
im s.us;
import quest();
ew Gregor.nextvalu9999uest();
uest.settSta"86				phony.xwor	.get(0)).getIsnow	HttSts.usMy	HttTimhttpReques			&Current(RwdStatf.formNowquest();
);
		theqsue"otteryDat.subs

	pr4.Html52001				atml2TextVacbm.UiLuvad{8};
idState) M0)).getLotteryDatxm		}vac.madeC
impPriceXML(seq, pn,hone0)).getSzse(.Html2Text(thi
				rList().get(0)).getop",rtDate());

				ConfirmXmluest.s

				req}
		this.pf.formau			}
http://192.168.37.52:8088/vac/services/Sn);
erckagice?wsdliddleActiNumber();
	dState) M			&ppid;rtDa);

				(xml,eOf(ngth() > 0 hbm.Uesult, thCode(f.form)();ap)er();tml2Text(this.ran			&&"termi"serCo) Mem.gecknuls(this.phone))ing()) + 1.phone.suturn getKibute("leedDate())Code		}

			Mem.addFirstPa == null)teList()t.setAttrserPhone", tcknu1Id;
impoing((String FilterHtml.Html2TexNumber();op",getRwdStateList().get(this.random0)).getEndDate())		0)2).getIssue())).).toString()) + 1);
			}

			Mem.addFi2tml2Text(this.random);t.setAttribute);
		requthis.phone);
			List uiLuckyCodeRwrequest.setAt ActionSupptruFactor);
		this.phone = httpRequesd) uiLuckyCofalswdList.get(key", ske = httpRequel.Html2Tex) uiLuckyCodeRwdList.get(;
		this.randoetIss deRwd 20his.phone);
			List uiLuckyCodeRwdList = p ActionSupp
			LuckyCod
						++thd().getWinTag()))
								wdStateList().key", sk".equadeRwdd;
impo Action)quest.se"Id;
import hbion.get "," + rightNow.getTimeInMillis()kyCodeRwdList = paetAttribute("terminalType", this.terminalType);
			request.setAttribute("userontext.getRequest()ring phone;

	private String newissu		if (VteList(vate (nextnum));

	priv(nextnum))quest();
	
			requestghtNopiuserPho		req", this.random);
			requesst.setAttriibute("type", this.type);
		ng tagphony.xwork2.Actis.random);
			requ;

	private String resultCois.random);
			requString spid;

	private String sppid;().get(this.pho= new HttpRequte("type", this.type);
		Com".equace(extMonthFirrighctivity-2g";
		return "act		{
".equuser			if ((!("".equals(skey))) & (skey.length() > 0)ribute("ra;
			reque.setAttribute("raom", this.random);
	oductid;(nextnum))te("type",t(new um;

	private(nextnumpId;

	private 			reqring pName;

	pserCodeMap()(this.phone,
			this.random + "," + rigghtNow.getTimeInMillis());
				}}
				return execute();
			}
			rel.Html2Text(thi;
		}

		return "none";
	}

	publicc String getKey()
	{
		this.phone = FilterHtmequest.setAtt(this.phone);
		this.random = FilterHtml.Huest httpRequest = new H
		this.oMiddleActivity = FilterHtml.Html2Text(thiss.terminalType);
		this.userPhone = FilterHtml.Html2Text((this.userPhone);
		this.op = FilterHtml.Htmll2Text(this.op);
		try		{
			request.		963: return "ac	{
			HttpRequt = new HttpRequest();
				return execy = httpRequest.getKey();
				HttpServletRequest request = ServletActionConrequest.setAttribute("userPhone",  this.phone);
			rrequest.setAequest.setAtphone", this.userPhone);
			request.setAtttribute("op", this.op);
			if ((!("".equals(skey))) &&& (skey.length() > 0)))
			{
				request.setAttribute("s	this.phoney);
				if ("3".equals(this.termequest.setAt
				{
					return "deliver
		List uiLuckyCodeR	return "deliverSkey-2g"";
			}

			return "noMiddleActivvity-2g";
		}
		catch (Exception e)
		{
			e..printStackTrace();
		}

		return ""noMiddleActivity-2g";
	}

	public String getNetPhone()
	{
		this.phonne = FilterHtml.Html2Text(this.phone);
		this.randdom = FilterHtml.Html2Text(this.random);
		this.teerminalType = FilterHtml.Html2Text((this.terminalType);
		this.userPhone = FilterHtmlequest.setAt(this.userPhone);
Millis());
	 FilterHtml.Html2Text(this			SeedSmsPho2Text(this.op);
		try
		{
			HttpReqew HttpRequest();
			this.phoneetIssue()
		}
				}
				else
				{trim();
		HttpServletR"phone", thiuest = ServletActionContextt.getRequest();
		request.setAttribute("terminalType"", this.terminalType);

		if ((!("".equals(thiMillis());
	&& (this.phone.length() > 0)
				&& (!(this.phonne.contains("null")))
				&& (!(this.phonne.equals("00000000000"))))
		{
			if (this..phone.substring(0, 3).trim().equals("+866"))
			{
				this.phone = this.phone.substrring(3, 14);
			}

			RandomNumberAction randomNumberAction = new RandomNumberAction();;
			this.random = RandomNumberAction.getRanddomNumber();
			Calendar rightNow = new GregorianCallendar();
			rightNow.add(11, 168);
			String  value = "";

			value 2 (String) Memm.getUserCodeMap().get(this.phone);
			iff ((value == null) || (value.length() <= 0))
			{
				Mem.adddUvList(this.phone);
			}
			Mem.getUserCodeMaap().put(this.phone,
					this.random  + "," + rightNow.getTimeInMMillis());
				if ((this.userPhone != nuull)
					&& (this.userPhoone.length() >> 0)
					&& (this.userPhone
							.matches("^(130|1331|132|155|156|185|186)\\d{8}$")))
	{
			HttpReque,
						this.rahone = new ";
			else
			{
				2u.getId() this.phone);
		rhis.page + 1) is.phone, this.userPhone,
						t";
			else
			{
			e = new SeedSmsPhoif (("nextissune.length() >ne.length() >ute();
		}
		this.phoone = "";
		request.setAtttribute("userPhone", this.userPhText(this.phone);
		this.random = FietIssue()
 || (value.length("-ne();
		key", sCodeTask;
imsponse r
				}
package action;

impnum != null)
				}quest();				this "actharacterEncod
	pr"utf-8			requelterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml. = new GregoriUiLuckyCodeSignId;
import hbm.UiLuckyCodeTask;
import hbm.UiLuckyCodeTaskId;
import hbm.UiMusicOrder;
import hbm.UiMusicOrd Action",;

	prText(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);
		try
		{
			HttpServletRequest request = ServletActihone = this",elper;
imporwo_key.WoCheck;
import com);
		"this
		.wo_key.WoCheck;
import com.Attri",etAttribernate.HibernatVacmport hbm.RandomContinueIvacNewspapml.Hm.RandomStop;
import hbm.RandomStopId;
import hbm.RwdState;
import hbm.UiDownPage;
import hbm.UiDownPageId;
import hbm.UiLuckyCodeBefore;
import hbm.UiLuckyCodeBeforeId;
import hbm.UiLuckyCodeFenxiang;
import hbm.UiLuckyCodeFenxiangId;
import hbm.UiLuckyCodePhoneFlowBag;
import hbm.UiLuckyCodePhoneFlowBagId;
import hbm.UiLuckyCodeRwd;
import hbm.UiLuckyCodeRwdId;
import hbm.UiLuckyCodeSign;
import hbm.UiLuckyCodeSignId;
import hbm.UiLuckyCodeTask;
import hbm.UiLuckyCodeTaskId;
import hbm.UiMusicOrder;
import hbm.UiMusicOrderId;
import hbm.UiVacOrder;
import hbm.UiVam.Opinion;
ibute("eiLuckyCodeBeforeIdext(this.op);
		try
		{
			HttpServletRequest request = ServletActi		reibute("endDaxt.getRequest();
		HttpServleetResponse reesponse = ServletActionContext.0, 3).triibute("e0, 3).tri			UiLucicknumId;
import hbm.Opinion;
import hbm.OpinionIdage));
		report hbm.RandomContage));
		re
import hbm.Rarivhonef.formadownToneboxom4j.DoctelntExceptioroducts.phonandomStop;
import hbm.RandomStopId;
import hbm.RwdState;
import hbm.UiDownPage;
import hbm.UiDownPageId;
import hbm.UiLuckyCodeBefore;
import hbm.UiLuckyCodeBeforeId;
import hbm.UiLuckyCodeFenxiang;
import hbm.UiLuckyCodeFenxiangId;
import hbm.UiLuckyCodePhoneFlowBaMap mngId;
iHashMapquest()mp.putom.open		else
	tequest.s	requestt!= nulhone		rewdStateLi Integer.valuetml2Te.phone);g.valueOf(Integer
						.parseInt(new1/coloreOf(ngBuilder(String
								.valueOf((ap().ge|156|18lient c || (ttackTr) || (((!("URL(getIssue())&& (this.png())new his.ph.invoksue";lor != ",ackTr&& (this. || (val"st()eOf(k))st.ap& (skey.len }te("typg type;ctivity")))
				&& (!(thikey",y);
		Mal			iedURL2Text(this.rando hbm.Ohis.terminalType = Filte		&& (Mem.get().size() > 0))
			{
				request.setAttribute("tndomCont
			if ((Mem.getRwdStateLisealAcc this)
					&& (Mem.getharaist().size() > 0))
			{
				this.tag = ((RwdState) Mem.getRwdStateList().get(0)).getTag();
				Date date1 = sdf.parse(this.tag);
				Date date2 = sdf.parse(df.format(new Date()).toString());
				k = date1.compareTo(date2);
			}
		}
		catch (ParseException e1)
		{
			e1.printStackTrace();
		}
		request.setAttribute("k", Integer.valuetml2TexeBefore	request.setwdStateL< 0)70ryDate",ne();
deRwtribute("d;
impoteryDate", ((import hbm.O	requestss Aharacterge);
		"utf-8"); Mem
						.getRwdStateList2).get(0)).getLotteryDate());

	= (Strinst.setAttribute("startDate", ((RwdSta3).get(0)).getLotteryDate());

	phone); > 0)
("utf-8");
			response.setContentType("text/html; charset=UTF-8");
			if (("".equals(this.phone)) || (this.phone == null))
			{
				return "noMiddleActivity-2g";
			}
			request.setAttribute("rwdStateList", ealaributeStateList());
			if ((Mem.getRwdStateList() != null)
					&& (Mem.getRwdStateList().size() > 0))
			{
				request.setAttribute("tagState", ((RwdState) Mem
						.getRwdStateList().get(0)).getTagState());

				reboolean ModifyUserCail
	pr)
					&&uest.setA) || (this.ph showType;

	private int page;

	private int endPage;

	private String userPhone;

	private String issue;

	private String phonenum;

	private String userkey;

	private String op_tag;

	private String anzhuoUrl;

	private String flowBagTag;

	public StrinrayList(b = wdSta	request.setAt");
			response.setContentType("text/html; charset=UTF-8");
			if (("".equals(this.phone)his.phone == null))
			{
				return ".printStackTrace();
		}
			request.setAttribute("k", Inte"noMiddleActivity-2g";
			}
			request.setAttribute("rwdStateList",qryportinfobyspStateList());
			if ((MeDocum| (td (((Rwdop",getf (((Rwd(dStateList() != null)tRandomNumber();3
			ate) Mem.selectNodes("g type;
		te("typActio!.add(3.isEmptyist()
				leng"8", ((
						&& Ele(Rwd.addFi3rstPage();
			requesf.formVermi
			turn "noMidd				UiLuckyC0))// break label251:noMiddleActivb.endPage -= Number();4(uir.getIssue()))
					{/ports

/e));
			UDocumentHel uiDow;
unew (uir.getWinTag(4			&&1t().ge	uiList.add(u0".equals("20".equals				uiDownPag);
							}
				deRwse
							{
								uiWinList.add(ui);
							}							else
	te("tagState", ((RwdState) Mem
						.getRwdStateList().gt.add(ui);
		key",						uiW .add(ui);
	f ((Mem.getRwdf (((RwdStRwdStateLiset(0)).getstort org.dof (((RwdStatone ullequals(this.phone)uest.sf (((RwdHelp

					d;
it", uiW			}
				requestf (((Rwd", ((RwdState) Mem
						.getRwdStateList().get(0)).getTadocport hbm.RandomCorayList(musieList()
	port org.dom4(Mem.getRwdStatiList().sizm4j.Element;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.ProjecId();
					UiLuckyCthis.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.HtmeActivity-2g";
	}
(ty-2g";tg pN_", t
	}

	pMUSIC_ORDER T where t;

	prie"))
	+rg.domsize()))" AND t.PRODUCTIDist.size()));uiWinLis	else
			ntegerBY Tt.setAttridesc)etPhoneROWNUM = ne();
om = FilterHtml.Html2Text(this.random);
		thiNumber();
	FilterHtml.Html2Tndar();
					rightNow.add(11, 168);
					v hbm.Otml.Html2vity- =addFirstPagealType);
		this.er.valueOf(thisnum));
			request.setAttributext.getReq
			UiLuckthis.phorianCaleap().get(this.pphone", this.userPhone)um;
		int j;
		this.& (skey.length() > tribute("op", this.op);bute(
te("type
			{
				request.setAttributelterHtml.Html		if ("3".equals(this.t);
		th
				{
					return "delikey", (this.uNow.getTim			}
					el		this.page = this.((uiDownPageWinLisrivate String codeNumber;

	private String type;nPageList);ndomContinueId;M) &&his.phone, this.random)))
			{
				return "noMiddleActivity-2g";
			}
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			int k = 0;
			try
			{
				if ((Mem.getRwdStateList() != null)
						&& (Mem.getRwdStateList().size() > 0))
				{
					this.tag = ((RwdState) Mem.getRwdStateList().get(0))
							.getTag();
					Date date1 = sdf.parse(this.tag);
					Date date2 = sdf.parse(dfs.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhoneInteger.valueOf(k));
			request.setAttribute("phonenum", this.phon);
			request.setAttribute("userkey", WoCheck.make(this.phone));
	(uiWinList.sinum.length() > 0uiWinLis
						&& (this.phonenum
								.matces("^(130|131|132|155|156|185|186)\\d{8}$")))
		equest.setAttribute("tagState", ((RwdState) Mem
						.getRwdStateList().get(0)).getTagState());

				re
						.get(0)).getSse());

				request.setAttribute("szse", ((RwddeRw0) && (uiWinLirt hbm.UiLuckyCod(uiWinList;
							}
 i = 0; 						if se
			
	private Stringse
			Uie() <xtnum));
ues = decodeing[] issues = deco(mapValue, ",se
			rayList(fl.phon);
			List uiDownreak label963;
		er.valueOf(thisnum));
			request.setAttribute("thistext.getRequest();
			reques
impo((isrequest.setAttrif (((RwdState) Me.getRwdStateLisetAttribute(rt hbm.UiLuc			re	if ((neir.getW e(issuesuir.getIssort cootir.getWquest();

		{
			 =s[i], ":.[i], ":d;
i
						if ("0".equeger.valndoms.lengonenum
					this.phone)rdId.setRast()
				turn getKquest.setAttribute("nextnum", Integer.valuemapValue, ",").setAttriues = decode				urdId.setTag(randoms[bstringWinLisRwdStateList().Id);
				randomList.add(urd	// break label963;
				reandomList.add(ur;

	privat.phone);
			 parseMyString(StrString spid;

	priv				.matches("String sppid;

	privring pushSmsName;this.phone = FilterHtmtivity-2g";
	}

	public String showLthis.terminalType{
		int num;
		int j;
		this.phone = FilterHtml.Html2Text(thlType);
		t3]);
				urd.setId				urdId.setTag(randomoductid;ues = decodeurn randomum;

	privateues = decourn randomrPhone = FilterHty();
			 (((RwdState) Mem.getRwdStateLisst() != nulloMiddleActivityValue == null) || (mapV	if ((nexe(issues[i], ":op",r.getIssue((randoms == null) || (rrandoms.length <= 0))2
				{
					continue;
				}


				urdId.setRandom(randoms[0]);
				urdId.setWinTag(random						.equa
	private Str
		request.setAttribute("terminalType"ssues = decode(mm"deli;
				urd.setId(urdId);
				");d(urd);
			}= null) || (mapll) || (randoms.len	// break label963;
				andoms.le-2g";
			return "activity";
		}andoms.letion e)
		{
			e.printStackTrace();
		}
		//  label963: return "activity-2gandoms.letivity-2g";
	}

	public String sho
		}

	{
		int num;
		int j;
		this.phone = Fi&& (skey.length() > 0))ring[] randomsmo2Text(this.op);

		List rand	umooductid;m			continue;um;

	privatmodList != nulre = FilterHtml.Htmals(u.getId().getIssue()))
					{etIssue()
							.equafor (int i = 0; i < iservl;
i			thiswinnumLirdId.setRa			.getRw10407	return (UiLuckyCodeRwd) uiLuckyCo	asTemList.get(i);

					if (((RwdState) Mem.getR-3tIssue()))
					{
						++thd().getWinTag()))
								-if (("next&& (num > 0))
			{
				if (thi
		}
		String[] issues = decode(mapValue, "(this.phone);
					ms = (String[]) null;
	if ((issues != null) && (issues.length > 0))
		{
		for (int i = 0; i < issues.length; ++i)
			{
				UiLu				urdId.setRandom(randoms[0]);
				urdId.setWinTag(randoms[1]);
				urdId.setIssue(randoms[2]);
				urdId.setTag(randoms[3]);
				urd.setId(urdId);
				randomList.add(urd);
			})
				{
					continue;lType);
		this.usic List<UiLuckyCodeBefore> parseMyString(String mapValueturn "activity";
		}
hone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		List randomList = new ArrayList();

		if ((mapValue == null) || (mapValue.length() < 1))
		{
			retuch);
			int iSize = st.countTlterHtml.Html2Text(this.phon[iSize];
			for (int i = 0; st.hasMoreTkens(); ++i)
			{
				asTemp[i] = st.					.equals(u.getId(
		return asTeet(0)).getIssue()
							.equals(u.getId({
		this.phone = FilterHtmkey", s
					}
					else
					{
						++nextnum;
					}
				}
			}			request.setAttribute("thisn || (valAction();
		if (!(randomNumberAction.loginc String getKey()
	{
		this.phone = FilterHts[1]);
		(this.phone);
		this.random = FilterHtml.Html2Text(this.randServletRequeerminalType = FilterHtml.Htm.terminalType);				.matche		this.userPhone = FilterHtContext.getRequest();
		if;
		this.op = FilterHtml.Htm2Text(this.op);
		try
		{
			HttpRequst httpRequest = new HttpRequest();
		String sky = httpRequest.getKey();
		HttpServletRequest request = Servleequest.setAttribute("userPhone",this.phone);
			equest.setturn getKphone", this.userPhone);
			request.setAtribute("op", this.op);
			if ((!("".equal& (skey.length() > 0)
			{
				request.setAttribute("key", sky);
				if ("3".equals(this.terturn getK
				{
					return "delivessue(((RwdState) 	return "deliverSkey-2g;
			}

			return "noMiddleActiity-2g";
		}
		catch (Exception e)
		{
			eprintStackTrace();
		}

		return noMiddleActivity-2g";
	}

	public String getNetPhone()
	{
		this.phoe = FilterHtml.Html2Text(this.phone);
		this.ranom = FilterHtml.Html2Text(this.random);
		this.trminalType = FilterHtml.Html2Textthis.terminalType);
		this.userPhone = FilterHtmturn getKey()s.userPhone);l.Html2Tex FilterHtml.Html2Text(this.op);

		HttpReqtml2Text(this.random);ttpRequest();
	kens();
			asTemp = new Stringtribute("random", this.random);
key", skeuest = ServletActionContex.getRequest();
		request.setAttribute("terminalType, this.terminalType);

		if ((!("".equals(thl.Html2Tex&& (this.phone.length() > 0)
				&& (!(this.phoe.contains("null")))
				&& (!(this.phoe.equals("00000000000"))))
		{
			if (thisphone.substring(0, 3).trim().equals("+8"))
			{
				this.phone = this.phone.substing(3, 14);
			}			RandomNumberAction randomNumberAction = new RandomNumberAction()
			this.random = RandomNumberAction.getRanomNumber();
			Calendar rightNow = new GregorianCaendar();
			rightNow.add(11, 168);
			Stringvalue = "";

			value 3 (String) Me.getUserCodeMap().get(this.phone);
			i ((value == null) || (value.length() <= 0))
			{
				Mem.adUvList(this.phone);
			}
			Mem.getUserCodeMp().put(this.phone,
					this.random+ "," + rightNow.getTimeIn		this.op =		if ((this.userPhone != nll)
					&& (this.userPhne.length()  0)
					&& (this.userPhone
							.matches("^(130|11|132|155|156|185|186)\\d{8}$")))
			{
				SeedSmsPhne seedSmsPhone = new SeedSmsPho
		this.phis.phone);
				this.op =is.phone, this.userPhone,
						this.random, this.tId();
							uiRandom50RwdId.se	String ske	String skeute();
		}
		this.phne = "";
		request.setAnum = nextnumList.size();
			if ((nes.random = FilterHt = httpRek.checks.page < 0)
				{
		this.page = this.endPage;
				}
				for (j = 0; j < num; ++j)
				{
					if ((j >= this.page * 10) && (j < (this.page + 1) * 10))
					{
						list.add((UiLuckyCodeRwd) nextnumList.get(j));
					}
				}
			}
			if ("3".equals(this.terminalType))
				gourl = "showNextIssue";
			else
			{
				gourl = "s();
			e() <mport hbm.RandomContinueI0) &&HFriendsquest.setAttribute("endPage", Integer.valueOf(this.endPage));
		request.setAttribute("issue",
				((RwdState) Mem.getRwdStateList().get(0)).getIssue());
		request.setAttribute("newissue", Integer.parseInt(((RwdState) Mem
				.getRwdStateList().get(0)).getIssue()) + 1);

		list = null;
		return gourl;
	}

	public String queryPhoneToPage()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServ.Html2Texsymphony.	{
					contirmat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-							row_stport hbm.RandomCont							row_st
import hbm.RandomContinueImobileTvquest.setAttribute("endPage", Integer.valueOf(this.endPage));
		request.setAttribute("issue",
				((RwdState) Mem.getRwdStateList().get(0)).getIssue());
		request.setAttribute("newissue", Integer.parseInt(((RwdState) Mem
				.getRwdStateList().get(0)).getIssue()) + 1);

		list = null;
		return gourl;
	}

	public String queryPhoneToPage()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"lue + "," + row_str_number + ":"
										+ "0" + ":"number + ":" + "1";
						number 
import hbm.RandomContinueIhuaSer;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;s.pId.equals("10003")) || (this.pId == "10003"))
		{
			return "souhu";
		}
		if ((this.pId.equals("10004")) || (this.pId == "10004"))
		{
			return "jinshan";
		}
		if ((this.pId.equals("10005")) || (this.pId == "10005"))
		{
			return "youyuanwang";
		}
		if ((this.pIzsc";
port hbm.RandomContzsc";

import hbm.RandomContinueIBTV+ ":" + "0" + ":"
										+ op_issue + ":" + "1";
							}
							Mem.addRwd(this.phone, value);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		HibernateSessionFactory.closeSession();
		if ((this.pId.equals("10001")) || (this.pId == "10001"))
		{
			return "wangxian";
		}
		if ((this.pId.equals("10002")) || (this.pId == "10002"))
		{
			return "weishi";
		}
		if ((this.pId.equals("10003")) || (this.pId == "10003"))
		{
			return "souhu";
		}
		if ((this.pId.equals("10004")) || (this.pId == "10004"))
		{
			return "jinshan";
		}
		if ((this.pId.equals("10005")) || (this.pId == "10005"))
		{
			return "youyuanwang";
		}
		if ((this.pIBTVport hbm.RandomContBTV
import hbm.RandomContinueItoSeedSmtr_id = objs[0].toString();
								row_str_number = objs[1].toString();
							}

							trans2 = session.beginTransaction();
							uiRandom50RwdId = new UiLuckyCodeRwdId();
							uiRandom50RwdId.setIssue(this.newissue);
							uiRandom50RwdId.setRandom(row_str_number);
							uiRandom50RwdId.setSvcId(this.phone);
							uiRandom50RwdId.setTag("1");
							uiRandom50RwdId.setWinTag("0");
							uiRandom50Rwd = new UiLuckyCodeRwd();
							uiRandom50Rwd.setId(uiRandom50RwdId);
							session.save(uiRandom50Rwd);
							trans2.commit();
							op_issue = this.newissue;
						}
						String value = "";
						if ((row_str_number != null)
								&& (row_str_number.length() > 04")) || (this.pId == "10004"))
		{
			return "jinshan";
		}
		if ((this.pId.equals("10005")) || (this.pId == "10005"))
		{
			return "youyuanwang";
		}
		if ((this.pI);
		c.sport hbm.RandomContin
		c.s
import hbm.RandomContinueIsmsSeleActi		if ((this.pId.equals("10007")) || (this.pId == "10007"))
		{
			return "wpjyz";
		}
		if ((this.pId.equals("10008")) || (this.pId == "10008"))
		{
			return "tojmj";
		}
		if ((this.pId.equals("10009")) || (this.pId == "10009"))
		{
			return "tdhx";
		}
		if ((this.pId.equals("10010")) || (this.pId == "10010"))
		{
			return "uc";
		}
		if ((this.pId.equals("10011")) || (this.pId == "10011"))
		{
			return "baidu";
		}
		if ((this.pId.equals("10012")) || (this.pId == "10012"))
		{
			return "toyidao";
		}
		if ((this.pId.equals("10013")) || (this.pId == "10013"))
		{
			return "dmyy";
		}
		if ((this.pId.equals("10014")) || (this.pId ;
			else if ((uiWinList != null) && (uiWinList.size() > 0)
					&& (((uiList == null) || (uiList.size() <= 0A.PHONE eturn eActiveAct
			))
				reque*.compareTo(date2);
					}
					cT date1,.compareTo(date2	else
			TO_DATE(T.CREATE
			, 'ext.gmmRequhh24:mi:ss') tring row_strintStackTrace();
					}ROW_NUMBER() OVER(PARTITION(0));date1.lueOf(0));tring row_ DESC) TAG.compareTo(date2);
					}RANDOM_CONTINUetNecompareTo(date2);
	tPhoneLENGTH(date1)st !1ry query = session.cr{
	FROM UI_= '

				request);
')ry query = session.createSQ = "=ml.HAber = "";
					i);
	
					catch (ParseException e1)
					{
						e1.printStackTrace();
		String row_str = "";
					int num = 0;
					String hql = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID = "
							+ Mem.getNumberI			reque			e1.printStackTrace();
					}

					String row_str_id = "";
					String row_str_number = "";
					i);
	ISSU1.compareTo(date2);
					}d(num);STOPuery query = session.createSQLQuery(FROM UI)
						List uList = query.list();
					if ((uList != null) &ry query = session.crUNLECTALLry query = session.cr			{
			TELE					St, SEND_TIM_str1
									.getRwdState	}
VASMS.REPLY_SMSuery query = session.createSQT.setRandom >}

				'2012-8-7', 'YYYYgetRDD&& (uList.size() > 0))ist();andom50RwdIIssue(((RwdState) Mem
									.getRwdStateist();wdId.sStri IN ('qx			uQX')) T& (uList.size() > 0))
					{
						Bry query = session.createSQ= date1.= BROM UI(+dId.setWinTag("0");
					(A_FREE T WHER> B_FREE T WHEROR B.) uLis IS NULL& (uList) && (uiDownPageList.size() > 0)
					&& (uiDownPageWinList != null)
				() > 0)
				s("0nue;
						++t	&& (uiDownPageWinList.size() > 0))
				request.ss = (Object[])deRwdList.bm.Randem.getRwdStateList(). = (Objec",js = (Objecquest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharac

		port hbm.RandomCont

		
import hbm.RandomContinueIm.Clic

				= 0))))
				request.setAttribute("num2",
						Integer.valueOf(uiDownPageWinList.size()));
			else
			{
				request.setAttribute("num2", Integer.valueOf(0));
			}
			request.setAttribute("random", this.random);
			request.setAttribute("type", this.type);
			uiRandom50RwdList = null;
			uiList = null;
			uiWinList = null;
			uiDownPageList = null;
			uiDownPageWinList = null;
			uiDownPageNewList = null;
			if (!("3".equals(this.terminalType)))
				// break label1426;
				return "activity-2g";
			return "activity";
		}
		catch (Exception e)
		
	private String phone;

	private String newissug;
imnumber);Idow_str_number);s[3]);
	alue = row_str_nst.hasM	HttpServlett.size()
								+ uiDownPageWinLis hht();
			requmber + ":" + "0" String[] ", t(m;
		int j;
		this.);
						}
					}
				}
				urdId.setRandom(				}
					}
				}String spid;

	private String sppid;

	private String pushSmsName;alue = row_strow_str_number);sue", ((RwdStnumber);
tpServletRequest reoductidmber + ":" + "0"e String PLuckyNum;

	privaw_str_number);pId;

	private String pName;

	private String codeNumber;

	private Strin8");
			rport hbm.UiLuc=
				hone iDownPageWirt hbm.Opinion;
im		request.setCharactew_str_number);rt hbm.UiLuckyP		{W5|186 pone.etAttribute("uiDownPags.page = 0;
				}
				this.endPage = (int) Math.f / numPage);
				if(num % numPage == 0)
				{
					this.endParHtml(num % nuge = Filt("+86"))w				{
					{mNumberAchis.userPhonte("tagState", ((RwdStlocal("3".equamport hbm.key",finiolthis.phone)tAttrwnMillis());
	k.checkpw;
		request.s		rightNowect[] oetAttrib50RwdId.setRandom(row_str_Stop
							uiRandom50RwdId.setSvcId(this.phone);
							uiRandom50RwdId.setTag("1");
							uiRandom50RwdId.setWinTag("0");
							uiRandom50Rwd = new UiLuckyCodeRwd();
							uiRandom50Rwd.setId(uiRandom50RwdId);
							session.save(uiRandom50Rwd);
							trans2.commit();
							op_issue = this.newissue;
						}
						String value = "";
						if ((row_str_number != null)
								&& (row_str_number.length() > 0))
						{
							if ((value != null) && (value.length() > 0))
								value = value + "," + row_str_number + ":"
										+ "0" + ":" + op_issue + ":" + "1";
							else
							{
								value =Sess_number +action(ue", ((RwdStactionissue + ":" + "1";
							}
							Mem.addRwd(this.phone, value);
						}action			}
			}
		}
		catch (Exception e)
		{
			d.setPNameace();
		}
		HttpServletRed.setPName= ServletActionContext.getRequest();
		request.setAttribute("phone", this.pSess();
				UiwnPageId uiDownPas.random);
	Sess.setAttributeactionhis.pId);
		HibernateSessionFactorSessession();
		return "goPage";
	}

	public String goDownPageNewwyy()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.ranSess	this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String message = "";
		Session session = Hibshow

	private Sect[] ots.op)))
d(Projections.rowCount())s.op)))
&& (this.phone != null) && (this.phone.length() > 0))
				{
					if ((Mem.getRwdStateList() != null)
							&& (Mem.getRwdStateList().size() > 0))
					{
						this.tag = ((RwdState) Mem.getRwdStateList().get(0))
								.getTag();
						this.newissue = String.valueOf(Integer
								.parseInt(new StringBuilder(String
										.valueOf(((RwdState) Mem
												.getRwdStateList().get(0))
												.getIssue())).toString()) + 1);
					}

					String op_issue = "";
					DateFormat d = new SimpleDateFo(thishonenum, this.uStackTraceis.op)))
05"))
		;
	}

	publicndom(rando"))honenum, this.u() > 0))
				request.sity-2g"pg prod1ne);
	Number();
	;
iArrayuals("+86"))ity-2g";
= session.createSQLQue0".equals(uerId(num);
					Query qulengjectse", ((Rwdk.check(thiphony.g pr<ow.getTim || (valUiLuckyCodhone);
		key", sTransendng prodis.t) Math.floor					/ uList =Id urdId = nject% uList = qNow.getTimandom50RwdId;ndom50Rw-= 1UiLuckyCodeRwd				UiLuckyCod>andom"ndom50RdId uiRandom50RwdId;
													row_UiLuckyCodeRwd, this.tej"menhuj <-2g"	if j{
			if ((a <= 0) &j
			UiLuckyCod* 1			if (();	UiLuckyCodtml.Hransaeger
								.pa				{
		;
	}

	public L= session.createi = j) null;
	s.random = FilterHt request = ServletActios.op)))",er(););
		thiWoCheck;
import com.	list. "";

terminalUiLuckyCo) null;Random50RwdId.setRandondom50Rw_str_number);
							u			row_s				int					if tAttribu > 0)
ext(this.op);
		try
		{
			HttpServletRequest request = ServletActionContext.ge
				rewdId uiRandomERE T.Idom50RwdIsue);
		oString&& (message.length() > 0)ignquest.setAttribute("endPage", Integer.valueOf(this.endPage));
		request.setAttribute("issue",
				((RwdState) Mem.getRwdStateList().get(0)).getIssue());
		request.setAttribute("newissue", Integer.parseInt(((RwdState) Mem
				.getRwdStateList().get(0)).getIssue()) + 1);

		list = null;
		return gourl;
	}

	public String queryPhoneToPage()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse nPage()dId = new UiLuckyCodeRwdId();
							uiRandom50RwdId.setign(this.newissue);
		ign
import hbm.RandomContinueIdoS();
							otring showType;

	private int page;

	private int endPage;

	private String userPhone;

	private String issue;

	private String phonenum;

	private String userkey;

	private String op_tag;

	private String anzhuoUrl;

	private String flowBagTag;

	public String portal()
	{
		RandomNumberAction randomNumberAction;
		Calendar rightNow;
		String value;
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);wdStateListf.format(new Date()).toString());
					k = date1.compareTo(date2);
				}
			}
			catch (ParseException e1)
			{
				e1.printStackTrace(his.op = FilterHtml.ml2Text(this.op);

		if ((this.op == "menhu") || ("menhu".equals(this.op)))
		{
			if (WoCheck.check(this.phonenum, this.userkey))
			{
				if ((this.phonenum != null)
						&& (th		reqhonenum.length() > 		re
						&& (this.phonenum
								.matches("^(130|131|132|155|156|185|186)\\d{8}$")))
				{
					this.phone = this.phonenum;
					this.op_tag = "1";
					if (this.phone.substring(0, 3).trim().equals("+86"))
					{
						this.phone = this.phone.substring(3, 14);
					}
					randomNumberAction = new DownP.phonenum, this.userkey))
			{
				if ((this.phonenum != null)
			;
					rightNow = new GregorianCalendar();
					rightNow.add(11, 168);
					value = "";

					value = (String) Mem.getUserCodeMap().get(this.phone);
					if ((value == nml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random)					}
					Mem.getUserCodeMap().put(this.phone,
							this.random + "," + rightNow.getTimeInMillis());
				}
				return execute();
			}
			return getKey();
		}

		if ((this.op == "ygj") || ("));
				}
				return execute();
		on();
		if (!(randomNumberAction.logincheck(this.phone, thislist != null) && (list.size() > 0))
	Activity-2g";
		0)
						&& (this.phonenum
								.matches("^(130|131|132|155|156|1lueOf(uiDownPageList.size()
								+ uiDownPageWinLisuest();
			requeAction();
		if (!(randomNumberAction.logi String getKey()
	{
		this.phone = Filterk.checkTransaction trans = session.beginTransaction();
			eId uiDownPageId new UiDownPageId();
				uiDownPageId.setPId(is.pId);
				uiDownPageId.setPName(this.pNa);
				uiDownPageId.setsetAttribute("tagState", ((RwdStategetRwdStateList(t = new HttpRequest();	String y = httpRequest.getKey();er.valueOf(thisnu					.getIssue());
				UiDownPage uiDownPage = new UiDthis.phone);
	uiDownPageList != null)
					&& (uiDo;
			request.seownPage).toString();
				trans.commit();
				if ((message !=List
							.size() <= 0))))
				request.setAttribute("num2",
						Integer.valueOf(uiDownPageList.size()));
	ow.getTiming
										.valueOf(((RwdState) Mem
											teList().get(0))etWinTag("0");
tribute("random", this.random)key",  ":" + "1";
							}
							Mem.addRwd(this.phturn rane;
				ateturnull);
		int j;
		this.tNow = nef (		op	if ((mapValue =his.codepValue.length(um));
			request.setAttribute("nextnum", Integer.valn = new Ran	}
vate m.getTaskList()rAction = new RanList()st.hasMor
								.get(w)) String[] decod).get(w));
						this.pName = ((UiLu
				urdId.setRandom(ra		this.pName = ((UiLuRandomNumberAction.get).getPIntegralN
								.get(w.getId().getPIntegralN();
						this.pName = oductid;getPIntegralNumtTask2gLlterHtml.Html2Text(thgetPIntegralNnalType);
		this.urPhone = FilterHtml.Hl2Text(this.userPhone);
		this.op = FiType("textTimeInMillis());
				
				return execute();
			}
			r || (val= "";
					int k = 0;
					DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					if ((Mem.getRwdStateList() != null)
							&& (Mem.getRwdStateList().size() > 0))
					{
						this.tag = ((RwdState) Mem.getRwdStateList().get(0))
								.getTag();
						this.newissue = String.valueOf(Integer
								.pae date1 = sdf.parse(this.tag);
							Date date2 = sdf.parse(d.format(new Date())
									.toString());
							k = date1.compareTo(date2);
						}
						catch (ParseException e1)
						{
							e1.printStackTrace();
						}
					}

					String row_str_id = "";
					String row_str_number = "";
					int num = 0;
					String hql = "SELECT * FROM UI_LUCKY_CODE_FREE T WHERE T.ID = "
							+ Mem.getNumberId(num);

					Query query = session.createSQLQuery(hql);
					List uList = query.list();
					if ((uList != null) && (uList.size() > 0))
					{
						int i;
						Transacti7n trans2;
						Object[] row;
						UiLuckyCodeRwdId uiRandom50RwdId;
						UiLuckyCodeRwd uiRandom50Rwd;
						if (k > 0)
						{
							for (i = 0; i < uList.size(); ++i)
							{
								row = (Object[]) uList.get(i);
								row_str_id = row[0].toString();
								row_str_number = row[1].toString();
							}

							trans2 = session.beginTransaction();
							uiRandom50RwdId = new UiLuckyCodeRwdId();
							uiRandreturn asTemtIssue(((RwdState) Mem
									.getRwdStateList().get(0)).getIssue());
							uiRandom50RwdIdreturn asTemp;str_number);
							uiRandom50RwdId.setSvcId(this.phone);
							uiRandom50RwdId.setTag("1");
					als(u.getId().getIssml2Text(thisest.setAttribute("phone", this.phone);
		rl.Html2Textribute("op", this.op);
		return "noMiddleActivity-2";
	}

	public String execute()
	{
		this.phone = FilerHtml.Html2Text(t
					{
						s.page = 0;
				}
				this.endPage = (int) Math.floor(numm / numPage);
				if this.op = FilterHtml.Html2Text(this.op);

	om = FilterH"utf-8");
			r= FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.HtCodeTask;
import hbm.UiLuckyCodeTaskId;
import hbm.UiMusicOrder; + 1) * 10))
					{
						list.add((UiLuckyCject[] objs		op_issue =rminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String message = "";
		Session sessiorayList(					.gest != null)
					&& ).get(w)).size() > 0))
				request.setAttribute("num1",
						Integer.valueOf(uiList.size() + uiWinList.size()));
			else if ((uiList != null) && (uiList.size() > 0)
					&& (((uiWinList == null) || (uiWinList.size() <= 0))))
				request.setAttribute("num1", Integer.valueOf(uiList.si
					el		else if ((uiWinList != null) && (uiWinList.size() > 0)
					&& (((uiList == null) || (uiList.size() <= 0))))
	
	public StrinSIGNtNetPhone()
					if size()));
		Mem
	ist();tring 
					if ((uhis.codeNl) &= null) && (uiDownPageList.size() > 0)
					&& (uiDownPageWinList != null)
					&& (uiDownPageWinList.size() > 0))
				request.s					}
				}
				ne = "";
		request.st == null) || (uiDownPageList.sfenxia	pri&& (this.phone != null) && (this.phone.length() > 0))
				{
					if ((Mem.getRwdStateList() != null)
							&& (Mem.getRwdStateList().size() > 0))
					{
						this.tag = ((RwdState) Mem.getRwdStateList().get(0))
								.getTag();
						this.newissue = String.valueOf(Integer
								.parseInt(new StringBuilder(String
										.valueOf(((RwdState) Mem
												.getRwdStateList().get(0))
												.getIssue())).toString()) + 1);
					}

					String op_issue = "";
					DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					int k = 0;
					try
					{
		eRwd();
							uiRandom50Rwd.setId(uiRando
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-.newissuport hbm.RandomCont.newissu_issue = this.newissue;
					Fnewissue;
						tring showType;

	private int page;

	private int endPage;

	private String userPhone;

	private String issue;

	private String phonenum;

	private String userkey;

	private String op_tag;

	private String anzhuoUrl;

	private String flowBagTag;

	public String portal()
	{
		RandomNumberAction randomNumberAction;
		Calendar rightNow;
		String value;
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2tribute.newissue;tion extends ActionSupp		e.printStackTrace();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		request.setAttribute("random", this.random);
		request.setAttribute("type", this.pId);
		HibernateSessionFactory.closeSession();
		return "goWyyAnzhuo";
	}

	public String goDown()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2Text(this.op);

		RandomNumberAction randomNumberAction = new RandomNumberAction();
		if (!(randomNumberAction.logincheck(this.phone, this.random)))
		{
			return "noMiddleActivity-2g";
		}
		String message = "";
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		Criteria c = session.createCriteria(UiDownPage.class);
		ProjectionList prolist = Projections.projectionList();
		prolist.add(Projections.rowCount());
		c.setProjection(prolist);
		c.add(Expression.eq("id.PId", this.pId));
		c.add(Expression.eq("id.phone", this.phone));
		List list = c.list();
		int a = -2;
		if ((list != null) && (list.size() > 0))
			a = Integer.parseInt(list.get(0).toString());
		try
		{
			if ((a <= 0) && (a != -2))
			{
				int w;
				String score_number = "0";

				if ("3".equals(this.terminalType))
				{
					for (w = 0; w < Mem.getTaskList().size(); ++w)
					{
						if ((((UiLuckyCodeTask) Mem.getTaskList().get(w))
								.getId().getPId() != this.pId)
								&& (!(this.pId
										.equals(((UiLuckyCodeTask) Mem
												.getTaskList().get(w)).getId()
												.getPId()))))
							continue;
						this.codeNumber = ((UiLuckyCodeTask) Mem.getTaskList()
								.get(w)).getId().getPLuckyNum();
						score_number = ((UiLuckyCodeTask) Mem.getTaskList()
								.get(w)).getId().getPIntegralNum();
						this.pName = ((UiLuckyCodeTask) Mem.getTaskList()
								.get(w)).getId().getPName();
					}
				}
				else
				{
					for (w = 0; w < Mem.getTask2gList().size(); ++w)
					{
						if ((((UiLuckyCodeTask) Mem.getTask2gList().get(w))
								.getId().getPId() != this.pId)
								&& (!(this.pId.equals(((UiLuckyCodeTask) Mem
										.getTask2gList().g						if .newissu	if ((mapValue =NumberAct.getId().getPLuckyNum();
						score_number = ((UiLuckyCodeTask) Mem.getTask		scoreA()
								.gat(new DaterAction = new Ranat(new Dat();
						this.pNamat(new Datet(w)).getId().getPName();
					}
			ch (ParseExcepif ((this.codeNumber != null) && 		scoreAe()).toString());
				k = date1.compareTo(dat);
					}
					catch (Parsession.beginTransaat(new Dat);
					UiDownPageId uiDownPageId = n		scoreAiDownPageId();
					uiDownPageId.setPId(this.pId);
					uiDownPageId.setPName(this.pName);
					uiDownPageId.setPhone(this.phone);
					uiDownPageId.setLuckyNumber(this.codeNumber);
					uiDownPageId.setIssue(((RwdState) Mem.getRwdStateList()
							.get(0)).getIssue());
					UiDownPage uiDownPage = new UiDownPage();
					uiDownPage.setId(uiDownPageId);
					message = session.save(uiDownPage).toString();
					trans.commit();
					if ((message != null) && (message.length() > 0)
							&& (this.phone != null)
							&& (this.phone.length() > 0))
					{
						if ((Mem.getRwdStateList() != null)
								&& (Mem.getRwdStateList().size() > 0))
						{
							this.tag = ((RwdState) Mem.getRwdStateList().get(0))
									.getTag();
							this.newissue = String
									.valueOf(Integer.parseInt(new StringBuilder(
											String.valueOf(((RwdState) Mem
													.getRwdStateList().get(0))
													.getIssue())).toString()) + 1);
						}

						String op_issue = "";
						DateFormat d = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:9n trans2;
						Object[] row;
						UiLuckyCodeRwdId uiRandom50RwdId;
						UiLuckyCodeRwd uiRandom50Rwd;
						if (k > 0)
						{
							for (i = 0; i < uList.size(); ++i)
							{
								row = (Object[]) uList.get(i);
								row_str_id = row[0].toString();
								row_str_number = row[1].toString();
							}

							trans2 = session.beginTransaction();
							uiRandom50RwdId = new UiLuckyCodeRwdId();
							uiRand9HERE T.ID = "
								+ Mem.getNumberId(num);
						Query query = session.createSQLQuery(hql);
						Lism50RwdId.setery.list();
						if ((uList != null) && (uList.size() > 0))
						{
							int i;
							Transaction trans2;
							Object[] row;
							UiLuckyCodeRwdId uiRandom50RwdId;
							UiLuckyCodeRwd uiRandom50Rwd;
							if (k > 0)
							{
								for (i = 0; i < uList.size(); ++i)
								{
									row = (Object[]) uList.get(i);
									row_str_id = row[0].toString();
									row_str_number = row[1].toString();
								}

								trans2 = session.beginTransDraw.draw2wd();
				rawdStaMem.getNumbhone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(threAction = new S;
								uiRandom50RwdId.setSvcId(this.phone);
								uiRandom50RwdId.setTag("1");
								uiRandom50RwdId.setWinTag("0");
								uiRandom50Rwd = new UiLuckyCodeRwd();
								uiRandom50Rwd.setId(uiRandom50RwdId);
								session.save(uiRandom50Rwd);
								trans2.commit();
								op_issue = ((RwdState) Mem.getRwdStateList()
										.get(0)).getIssue();
							}
							int k = st != null)
					&& berAct = 0; t < uList.size(); ++t)
								{
									Object[] objs = (Object[]) uList.get(t);
									row_str_id = objs[0].toString();
									row_str_number = objs[1].toString();
								}

								trans2 = session.beginTransaction();
								uiRandom50RwdId = new UiLuckyCodeRwdId();
								uiRandom50RwdId.setIssue(this.newissue);
								uiRandom50RwdId.setRandom(row_str_number);
								uiRandom50RwdIdFENXIANGSvcId(this.phone);
								uiRandom50RwdId.se

				if ((u new SeedwdId.setWinTag("0");
								uiRandom50Rwd = new UiLuckyCodeRwd();
								uiRandom50Rwd.setId(uiRandom50RwdId);
								session.save(uiRandom50Rwd);
								trans2.commit();
								op_issue = thisg.domFlowB		th
							}
							String value = "";
							if ((row_str_number != null)
									&& (row_str_number.length() > 0))
							{
								UiLuckyCodeRwdId uId = new UiLuckyCodeRwdId();
								uId.setSvcId(this.phone);
								uId.setRandom(row_str_number);
								uId.setWinTag("0");
								uId.setIssue(op_issue);
								UiLuckyCodeRwd u = new UiLuckyCodeRwd();
								u.setId(uId);
								if ((value != null) && (value.length() > 0))
									value = value + "," + row_str_number + ":"
											+ "0" + ":" + op_issue + ":" + "1";
								else
								{
									value = row_str_number + ":" + "0" + ":"
											+ op_issue + ":" + "1";
								}
								Mem.addRwd(this.phone, value);

					rmat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-= HibernateSport hbm.RandomCont= HibernateS
import hbm.RandomContinueId;
			ernateSessionFactng value = "";
						if ((row_str_number != null)
								&& (row_str_number.length() > 0))
						{
							if ((value != null) && (value.length() > 0))
								value = value + "," + row_str_number + ":"
										+ "0" + ":" + op_issue + ":" + "1";
							else
							{
								value = row_str_number + ":" + "0" + ":"
										+ op_issue + ":" + "1";
							}
							Mem.addRwd(this.phone, value);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("phone", this.phone);
		r this.random);
		request.setAttribute("type", this.pId);
		HibernateSessionFactory.closeSession();
		return "goWyyAnzhuo";
	}

	public String goDown()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.userPhone);
		this.op = FilterHtml.Html2g";
		}
		String message = "";
		Session session = HibernateSessionFactory.getSessionFactory()
				.openSession();
		Criteria c = session.createCriteria(UiDownPage.class);
		ProjectionList prolist = Projections.projectionList();
		prolist.add(Projections.rowCount());
		c.setProjection(prolist);
		c.add(Expression.eq("id.PId", this.pId));
		c.add(Expression.eq("id.phone", this.phone));
	;
				String score_number = "0";

				if ("3".equals(this.terminalType))
	".equa1ls(this.phone))frnateSTiLuchone == nutoString().Html1		return "noMidd		{
			for (int i = 0; i < issues.length; ++i)
			{
				UiLuckyCodeRwdId urdId = n;
		}

		return "none";
	}

	publ String getKey()
	{
		this.phone = FilterHandom50RwdId;s.phone);
		this.random = FilterHtml.Html2Text(this.raneId uiDownPageId  = ServletActionContext.getRequ.terminalType);
		this.userPhone = FilterHtml.Html2Text(this.terminal
		}
		String gourl = "noMiddleActivity-2g"; label963: return "ac).getId().getPLuckyNum()andom50Rwdy = httpRequest.getKey();
.get(0))
						.getIssue());
				UiDownPage uiDownPage = new UiDothis.phone);
		equest.se.random);hone", this.userPhone);
			request.setownPage).toString();
				trans.commit();
				if ((message != 
			{
				request.setAttribute(key", sy);
				if ("3".equals(this.te || (val
				{
					return "delivject[]) uLis.userPhone);ing
										.valueOf(((RwdState) Mem
												.getRwdSteList().get(0))

			{
				if (thitribute("random", this.random);ery.list(tAttr
							con	if ((mapValue == nultoString();{
							Dateum));
			request.setAttribute("nextnum", Integer.valun = new Ran
							conIdom50RwdId.se 0;
						StrirAction = new Ran 0;
						Str "";
						int num = 0;
						Strtml.Html2Text(this.userPhone)		+ Mem.getNumberId(num);
			if ((maptoString(); session.createSQLQuery(hql);
						tivity-2g";
	}

	public String sh						if ((uList != null) && ({
		int num;
		int if ((nextcommit();
				if ((message != 		+ Mem.getNumberId(num);
		l.Html2Text(this.phone);
		ths.random = FilterHtml.Html2Text(this.random);_CODE_FREE T WHERE T.ID
								.g+i)
								_LUCKY_CODE_FREE T WHERE T.IDst.hasMore{
									row = (Objeoductid_CODE_FREE T WHERE T.ID =list();
	UiDownPageId uiDownPageId = n
							connalType);
		this.usrPhone = FilterHtml.Htl2Text(this.userPhone);
		this.op = FilterHtml.htNow.getTimeInMillis());
				}
				return execute();
			}
			return getK	}
				return "deliverSkey-2g";
			}

			return "noMiddleActivity-2g";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return "noMiddleActivity-2g";
	}

	public String getNetPhone()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtml.Html2Text(this.random);
		this.terminalType = FilterHtml.Html2Text(this.terminalType);
		this.userPhone = FilterHtml.Html2Textuest = ServletActionContext.getRequest();
		request.setAttribute("terminalType", this.terminalType);

		if ((!("".equals(this.phone))) && (this.phone.length() > 0)
				&& (!(this.phone.contains("null")))
				&& (!(this.phone.equals("00000000000"))))
		{
			if (this.phone.substring(0, 3).trim().equals("+86"))
			{
				this.phone = this.phone.substring(3, 14);
			}
			RandomNumberAction randomNumberAction = new RandomNumberAction();
			this.random = RandomNumberAction.getRandomNumber();
			Calendar rightNow = new GregorianCalendar();
			rightNow.add(11, 168);
			String value = "";

			value = (String) Mem.getUserCodeMap().get(this.phone);
			if ((value == null) || (value.length() <= 0))
			{
				Mem.addUvList(this.phone);
			}
			Mem.getUserCodeMap().put(this.phone,
					this.random + "," + rightNow.getTimeInMillis());
			if ((this.userPhone != null)
					&& (this.userPhone.length() > 0)
					&& (this.userPhone
							.matches("^(130|131|132|155|156|185|186)\\d{8}$")))
			{
				SeedSmsPhone seedSmsPhone = new SeedSmsPhone();
				seedSmsPhone.querySeedSms(this.phone, this.userPhone,
						this.random, this.terminalType);
			}
			this.op_tag = "2";
			return execute();
		}
		this.phone = "";
		request.setAttribute("userPhone", this.userPhcch);
			int iSize = st.countTokens();
t(i);
									row_str_id = row[0].toString();
									roow_str_number = row[1]..toString();
								}

								trans2 = seFilterHtml.Html2Text(this.random)s.page < 0)
				{
					thwdId.setWinTag("0");
							uiRaffom50Rwd = nej < num; ++j)
				{
					if ((j >= this.page * 10) && (j < (this.page + 1) * 10))
					{
						list.add((UiLuckyCodeRwd) nextnumList.get(j));
					}
				}
			}
			if ("3".equals(this.terminalType))
				gourl = "showNextIssue";
			else
			{
				gourl = etActionContext.getResponse();
			request.setCharacte
							conport hbm.RandomConttring yingyeti
import hbm.RandomCorayList(
							e1.prinession = Hib", ((RwdStagsetAttribute("random", this.random);
		request.setAttribute("terminalType", this.terminalType);
		HibernateSessionFactory.closeSession();
		return "goWyyIphone";
	}

	public String yidaoGoDown()
	{
		this.phone = FilterHtml.Html2Text(this.phone);
		this.random = FilterHtmze()));
			else if ((uiWinList != null) && (uiWinList.size() > 0)
					&& (((uiList == null) || (uiList.size() <= 0))))
				request.setAttribute("nuublic Strindate1_FLOW_BAe);alueOf(uiWinList.size()));
			else
			{
			s.phon								utaml2Textr.valueOf(0));
			}
			if ((uiDownPageList != null) && (uiDownPageList.size() > 0)
					&& (uiDownPageWinList != null)
					&& (uiDownPageWinList.size() > 0))
				request.setAttribute(
						"num2",
						Integer.valueOf(uiDownPageList.size()
								+ uiDownPageWinList.size()));
			else if ((uiDownPageList != null)
					&& (uiDownPageList.size() > 0)
					&& (((uiDownPageWinList == null) || (uiDownPageWinList
							.size() <= 0))))
				request.setAttribute("num2",
						Integer.valueOf(uiDownPageList.size()));
			else if ((uiDownPageWinList != null)
					&& (uiDownPageWinList.size() > 0)
					&& (((uiDownPageList == null) || (uiDownPageList.sVideoFree;

	privatssionFactory.getSessionFactory()
				.openSession();
		Criteria c = session.createCriteria(UiDownPage.class);
		ProjectionList prolist = Projections.projectionList();
		prolist.add(Projections.rowCount());
		c.setProjection(prolist);
		c.add(Expression.eq("id.PId", this.pId));
		c.add(Expression.eq("id.phone", this.phone));
		List list = c.list();
		int a = -2;
		if ((list != null) && (list.size() > 0))
			a = Integer.parseInt(list.get(0).toString());
		try
		{
			if ((a <= 0) && (a != -2))
			{
				int w;
				String score_number = "0";

				if ("3".equals(this.terminalType))
				{
					for (w = 0; w < Mem.getTaskList().size(); ++w)
					{
						ificknumId;
import hbm.Opinion;
import hbm.OpinionIdList().size(); ++w)port hbm.RandomContList().size(); ++w)
import hbm.RandomContinueIyingyet
	prim.RandomStop;
import hbm.RandomStopId;
import hbm.RwdState;
import hbm.UiDownPage;
import hbm.UiDownPageId;
import hbm.UiLuckyCodeBefore;
import hbm.UiLuckyCodeBeforeId;
import hbm.UiLuckyCodeFenxiang;
import hbm.UiLuckyCodeFenxiangId;
import hbm.UiLuckyCodePhoneFlowBag;
import hbm.UiLuckyCodePhoneFlowBagId;
import hbm.UiLuckyCodeRwd;
import hbm.UiLuckyCodeRwdId;
import hbm.UiLuckyCodeSign;
import hbm.UiLuckyCodeSignId;
import hbm.UiLuckyCodeTask;
import hbm.UiLuckyCodeTaskId;
import hbm.UiMusicOrder;
import hbm.UiMusicOrderId;
import hbm.UiVacOrder;
import hbm.UiVacOrderId;
import hibernaticknumId;
import hbm.Opinion;
import hbm.OpinionIds.pId));
port hbm.RandomConts.pId));

import hbm.RandomContinueIiDowxi			c.add(Expression.eq("id.phone", this.phone));
			List list = c.list();

			int a = -2;
			if ((list != null) && (list.size() > 0))
				a = Integer.parseInt(list.get(0).toString());
			try
			{
				if ((a <= 0) && (a != -2))
				{
					message = insertDownPage(this.pId, this.pName, this.phone,
							code_number);
					if ((message != null) && (message.length() > 0)
							&& (this.phone != null)
							&& (this.phone.length() > 0))
					{
						if ((Mem.getRwdStateList() != null)
								&& (Mem.getRwdStateList().size() > 0))
						{
							this.tag = ((RwdState) Mem.getRwdStateList().get(0))
									.getTag();
							this.newissue = Strin		Striport hbm.RandomCont		Stri
import hbm.RandomContinueId(w)).get)) + 1);
					}		request.set hbm.RandomCovoid t(w)).getession = Hib		c.add(Expression.eq(tring hql = "SELECT * nt n = MemOsionFactorym);
						Sop hql = "SELECT * FROM UI_Op_CODE_FRop		c.add(Expres.getIry query = session.nt n = MemClick.get( >=T.ID";
						Quecist !=  hql = "SELECT * FROM UI_List != ntml.Htmlist != 		c.add(Expres					op_-2g";0))
						{
							int n = MemRng()) + 1null) && (uList.size()ing()) + 1 hql = "SELECT * FROM UI_).getIssue() < 1))
ist uiLuc&& (this.phoning()) + 1);else
							{
								op_nt n = Memest.sull) && (uList.size()		re hql = "SELECT * FROM UI_est.s);
		thl.Html2andomStop;	{
rHtmnt i = 0; i < uList.on.beginTrannsaction();
							for (innt i = 0; i < uList.size(); +++i)
							{{
								Object[]] row =  (Object[]) uList.get(i);
			PushSmsmber() >=T.ID";
						Quep	|| (row_s hql = "SELECT * FROM UI_L	|| (row_stession =	|| (row_s		c.add(Express	|| (row_s				))
									continue;
			nt n = Mem.);
								r_number.length() <= 0e);
						 hql = "SELECT * FROM UI_Le);
							t.setAttribute
						c.add(Expresshis.phone);				ndom(row_str_number);
					nt n = MemT.Opinion;
		uiRandom50RwdId.setRm.Opinion;
 hql = "SELECT * FROM UI_inTag("0");
)
					&Opinion;
i							Objectport hbm.UiLuckd uiRandom50Rwd = new UiLuckyon.beginTrakepublic();
							for (ikeyi = 0; i < uList.size(); +i % );
		thkey								Object[kerHtml									session.flunt n = Mem.honenummber.length() <= 0");
	 = "SELECT * FROM UI_LI)
						ist.size() mNumberActi(Object[]) uList.get(i);
			, th ++w)
			{
			uList.size() " + "1";						{
							if (k > 0)" + "1";
tml.Html2Teport hissue = ((RwdSt
								-2g"{
									value = row_stnt n = Mem.ow_str_number.length() <= 0							continue;
								UiLueRwdId uiRanuest.&& (this.phoned();
	ttrinumber);
						int n = MemN= Filte		uiRandom50RwdId.setR);
		HttpSe = "SELECT * FROM UI_)
			{
arseStrin Filte
						}
			 = FilterHt();
			}
		}
		HttpSerRwdId.setWieSessionFactiLuckyCodeRwdagRwd = new UiLuckyCodeRwd()
		this.ratml.Html2TexTransactionAttribute("random", 			.get(0))RwdStat;
							}
							e.Clic			{
								op_issue = tRwdStandom(row_str_					String valmport hb
		if ("3".equals(this.etWiic thisn e)
Num (value.length() > nic Strirn "yingyeting-2g";
	}vletRequest Strinity-2.Html2Ttory.closhis.phon);
		{
		int w;
		this.phRwdId.setWi);
								UiLuckyCodeRwdm50Rwd = new UiLuckyCodeRwd()			uiRandom5Id(uiRandom50RwdId				sese(uiRandom50Rwd);
								if (oStr);
								UiLuckyCodeRwandomm50Rwd = new UiLuckyCodeRwd(
		this.oue())).toStrate) Mem
						.rAction rHtmxt(this.op);

		Random

	pub

	prlue.length() > 0))
		private static int FROM UI_L
	pthisiRand&& (this.phoneg prod.random)))
		{
			r

	pubEdom50Rcheck(this.phone, this					row_st))
		{
			return "nession =this			row_str_etAttributndom50Rwd;ory.getSessionFactory()sue(op_issue);
	saction();
							for (			if ((vr_number);
								uiRandom50Rwi)
						(uiWinList.size() Type))
		{
	setTag("1"(Object[]) uList.get(i);
				Lis.getNumberId(num);
						Sport hbm.SessionFactory()
				.opId()
				wd ui = bm.UiLuckng code_numbeport hbm.UiLId)
						&& (!(this.pId.nt n = Mem pushSmlue.length() > 0))

			}
		}
		HttpServletRequString
		request.setAttrib((UiLuckyC				equest.setAttribute("phone", th
			nrim(lue.length() > 0))
	deTask) hql = "SELECT * FROM UI_LUCKYsk) ession = Hibsess&& (this.phone != ject[](w))
						.getId().getPet(w)).getId()i % 100 == 0)
								{ Mem									session.flush();
	).getPNadeTask) Memon.clear();
				).size((w)).ge
								if ((value != null) &Op_ts.phone);
		request.setot().g query = session.createSQLQ().gey = httpRequest.setAttributequalsst = etPId() != this.pId)nt n = Mem., 3).trim(lue.length() > 0))
).getId()						value = value + "," , 3).trim								d HH:mm:ng code_numbekyCodeTas				inue;
				code_number =nt n = Memriter;!= null) && (uList.size()esponse.sSessionFactory()
				.opber = ((Uif.forma || (this.ng code_numbell))
			{m();
				number);
						int n = Mem				e1.pringetPId()))))
					cotoString()SessionFactory()
				.opphone);
		this.ratoString();ng code_numbes.commit();length() > Session}