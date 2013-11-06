package action;

import hbm.RwdState;
import hbm.UiLuckyCodeRwd;
import hbm.UiLuckyCodeRwdId;
import hbm.UiLuckyCodeRwdList;
import hbm.UiLuckyCodeRwdListId;
import hibernate.HibernateSessionFactory;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

public class RwdStatic
{
	private String message;

	public String execute()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("message", this.message);
		return "rwdStatic";
	}

	public String updateRwdStatic()
	{
		this.message = "false";
		try
		{
			Session session = HibernateSessionFactory.getSessionFactory()
					.openSession();

			Criteria ccc = session.createCriteria(UiLuckyCodeRwdList.class);
			ccc.addOrder(Order.asc("id.winTag"));
			ccc.addOrder(Order.asc("id.svcNo"));
			List list = ccc.list();
			if ((list != null) && (list.size() > 0))
			{
				ActivityAction ac = new ActivityAction();
				Mem.setUiRandom50RwdListOneList(list);

				for (int i = 0; i < list.size(); ++i)
				{
					String random_list = ((UiLuckyCodeRwdList) list.get(i))
							.getId().getRandom();
					String winTag_list = ((UiLuckyCodeRwdList) list.get(i))
							.getId().getWinTag();
					String tel = ((UiLuckyCodeRwdList) list.get(i)).getId()
							.getSvcNo();
					List uiRandom50RwdList = ac.parseString((String) Mem
							.getCurrentIssueMap().get(tel));
					String random = "";
					String status = "";
					String tag = "";
					String iss = "";
					if ((uiRandom50RwdList == null)
							|| (uiRandom50RwdList.size() <= 0))
						continue;
					String value = "";
					for (int k = 0; k < uiRandom50RwdList.size(); ++k)
					{
						UiLuckyCodeRwdId uir = new UiLuckyCodeRwdId();
						uir = ((UiLuckyCodeRwd) uiRandom50RwdList.get(k))
								.getId();
						random = uir.getRandom();
						status = uir.getWinTag();
						if ((random_list == random)
								|| (random_list.equals(random)))
						{
							status = winTag_list;
						}
						tag = uir.getTag();
						iss = uir.getIssue();
						if ((value != null) && (value.length() > 0))
							value = value + "," + random + ":" + status + ":"
									+ iss + ":" + tag;
						else
						{
							value = random + ":" + status + ":" + iss + ":"
									+ tag;
						}
					}
					Mem.getCurrentIssueMap().put(tel, value);
				}

			}

			Criteria cc1 = session.createCriteria(RwdState.class);
			Mem.setRwdStateList(cc1.list());
			cc1 = null;
			session.clear();
			HibernateSessionFactory.closeSession();
			this.message = "true";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return execute();
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}