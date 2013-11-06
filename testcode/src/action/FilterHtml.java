package action;

public class FilterHtml
{
	public static String Html2Text(String inputString)
	{
		if ((inputString != null) && (inputString.length() > 0))
		{
			inputString = inputString.replaceAll(".*([';<>/]+|(--)+).*", " ");
			if ((inputString.contains("script"))
					|| (inputString.contains("iframe"))
					|| (inputString.contains("onload")))
			{
				inputString = "";
			}
		}
		return inputString;
	}
}