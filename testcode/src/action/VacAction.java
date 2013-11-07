package action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.xfire.client.Client;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class VacAction
{
	public String madeCheckPriceXML(String seq, String pn,
			String operaTionType, String spid, String sppid)
	{
		String xml = "<message><IP value='10.12.71.161'/><KEY value='"
				+ MD5.MD5Encode(new StringBuilder("zsw152").append(pn)
						.append("        ").toString()) + "'/>"
				+ "<SequenceNumber value='" + seq + "'/>"
				+ "<OA_Type value='1'/>" + "<OANetwork_ID value='GSM'/>"
				+ "<OA value='" + pn + "'/>" + "<ServiceIDType value='3'/>"
				+ "<SP_ID value='" + spid + "'/>" + "<ServiceID value='"
				+ sppid + "'/>" + "<ProductID value='        '/>"
				+ "<Operation_Type value='" + operaTionType + "'/>"
				+ "<ServiceType value='31'/>" + "<LinkID value=''/>"
				+ "<SMSFormat value='0'/>" + "<SMSContentLen value='0'/>"
				+ "<SMSContent value=''/>" + "<CPID value=''/>"
				+ "<ContentID value=''/>" + "<OrderMethod value=''/>"
				+ "<PushId value=''/>" + "<FeeType value=''/>"
				+ "<fee value=''/>" + "<AccessNo value=''/>" + "</message>";
		return xml;
	}

	public String madeCheckPriceConfirmXml(String seq, String nowDateStr)
	{
		String xml2 = "<message><IP value='10.12.71.161'/><KEY value='"
				+ MD5.MD5Encode(new StringBuilder("zsw152").append(seq)
						.toString()) + "'/>" + "<SequenceNumber value='" + seq
				+ "'/>" + "<ErrCode value='0'/>" + "<End_Time value='"
				+ nowDateStr + "'/>" + "<ServiceType value='31'/>"
				+ "</message>";
		return xml2;
	}

	public String CheckPrice(String xml, String url) throws Exception
	{
		Client client = new Client(new URL(url));
		Object[] results = client.invoke("CheckPrice", new Object[] { xml });
		return results[0].toString();
	}

	public String CheckPriceConfirm(String xml2, String url) throws Exception
	{
		Client client = new Client(new URL(url));
		Object[] results = client.invoke("CheckPiriceConfirm",
				new Object[] { xml2 });
		return results[0].toString();
	}

	public static List<Map> getList(String xml) throws Exception
	{
		List result = new ArrayList();
		Document document = getDocumentForXmlString(xml);
		if (document != null)
		{
			Element root = document.getRootElement();
			List list = root.getChildren();
			for (int i = 0; i < list.size(); ++i)
			{
				Element e = (Element) list.get(i);
				result.add(fetchMap(e));
			}
		}
		return result;
	}

	public static Map fetchMap(Element e)
	{
		Map map = new HashMap();
		map.put("tagname", e.getName().toLowerCase());
		String value = e.getAttributeValue("value");
		if (value.length() == 0)
			map.put("value", null);
		else
		{
			map.put("value", value);
		}
		return map;
	}

	public static Document getDocumentForXmlString(String xml) throws Exception
	{
		List result = new ArrayList();
		Document document = null;
		ByteArrayInputStream bais = null;
		try
		{
			bais = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			SAXBuilder saxBuilder = new SAXBuilder();
			document = saxBuilder.build(bais);
		}
		catch (UnsupportedEncodingException e)
		{
			throw new Exception("获得xml文档的Document时出现异常,不支持此种编码");
		}
		catch (JDOMException e)
		{
			throw new Exception("获得xml文档的Document时出现异常,JDOME解析错误");
		}
		catch (IOException e)
		{
			throw new Exception("获得xml文档的Document时出现异常,文件读写错误");
		}
		return document;
	}
}