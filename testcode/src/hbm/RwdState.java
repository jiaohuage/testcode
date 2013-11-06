package hbm;

import java.io.Serializable;

public class RwdState implements Serializable
{
	private String tagState;

	private String luckyCode;

	private String sse;

	private String szse;

	private String issue;

	private String lotteryDate;

	private String startDate;

	private String endDate;

	private String tag;

	public RwdState()
	{
	}

	public RwdState(String luckyCode, String sse, String szse, String issue,
			String lotteryDate, String startDate, String endDate, String tag)
	{
		this.luckyCode = luckyCode;
		this.sse = sse;
		this.szse = szse;
		this.issue = issue;
		this.lotteryDate = lotteryDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.tag = tag;
	}

	public String getTagState()
	{
		return this.tagState;
	}

	public void setTagState(String tagState)
	{
		this.tagState = tagState;
	}

	public String getLuckyCode()
	{
		return this.luckyCode;
	}

	public void setLuckyCode(String luckyCode)
	{
		this.luckyCode = luckyCode;
	}

	public String getSse()
	{
		return this.sse;
	}

	public void setSse(String sse)
	{
		this.sse = sse;
	}

	public String getSzse()
	{
		return this.szse;
	}

	public void setSzse(String szse)
	{
		this.szse = szse;
	}

	public String getIssue()
	{
		return this.issue;
	}

	public void setIssue(String issue)
	{
		this.issue = issue;
	}

	public String getLotteryDate()
	{
		return this.lotteryDate;
	}

	public void setLotteryDate(String lotteryDate)
	{
		this.lotteryDate = lotteryDate;
	}

	public String getStartDate()
	{
		return this.startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return this.endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public String getTag()
	{
		return this.tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}
}