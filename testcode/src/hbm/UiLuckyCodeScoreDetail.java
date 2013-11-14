package hbm;

import java.io.Serializable;
import java.util.Date;

public class UiLuckyCodeScoreDetail implements Serializable
{
	private String id;

	private String svcId;

	private String scoreNum;

	private String taskId;

	private Date validTime;

	private Date buildTime;

	private String scoreType;

	private String exchangeStatus;

	private String exchangeTime;

	private String exchangeType;

	private String exchangeCount;

	private String scoreIssue;

	public UiLuckyCodeScoreDetail()
	{
	}

	public UiLuckyCodeScoreDetail(String svcId, String scoreNum, String taskId,
			Date validTime, Date buildTime, String scoreType,
			String exchangeStatus, String exchangeTime, String exchangeType,
			String exchangeCount, String scoreIssue)
	{
		this.svcId = svcId;
		this.scoreNum = scoreNum;
		this.taskId = taskId;
		this.validTime = validTime;
		this.buildTime = buildTime;
		this.scoreType = scoreType;
		this.exchangeStatus = exchangeStatus;
		this.exchangeTime = exchangeTime;
		this.exchangeType = exchangeType;
		this.exchangeCount = exchangeCount;
		this.scoreIssue = scoreIssue;
	}

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getSvcId()
	{
		return this.svcId;
	}

	public void setSvcId(String svcId)
	{
		this.svcId = svcId;
	}

	public String getScoreNum()
	{
		return this.scoreNum;
	}

	public void setScoreNum(String scoreNum)
	{
		this.scoreNum = scoreNum;
	}

	public String getTaskId()
	{
		return this.taskId;
	}

	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}

	public Date getValidTime()
	{
		return this.validTime;
	}

	public void setValidTime(Date validTime)
	{
		this.validTime = validTime;
	}

	public Date getBuildTime()
	{
		return this.buildTime;
	}

	public void setBuildTime(Date buildTime)
	{
		this.buildTime = buildTime;
	}

	public String getScoreType()
	{
		return this.scoreType;
	}

	public void setScoreType(String scoreType)
	{
		this.scoreType = scoreType;
	}

	public String getExchangeStatus()
	{
		return this.exchangeStatus;
	}

	public void setExchangeStatus(String exchangeStatus)
	{
		this.exchangeStatus = exchangeStatus;
	}

	public String getExchangeTime()
	{
		return this.exchangeTime;
	}

	public void setExchangeTime(String exchangeTime)
	{
		this.exchangeTime = exchangeTime;
	}

	public String getExchangeType()
	{
		return this.exchangeType;
	}

	public void setExchangeType(String exchangeType)
	{
		this.exchangeType = exchangeType;
	}

	public String getExchangeCount()
	{
		return this.exchangeCount;
	}

	public void setExchangeCount(String exchangeCount)
	{
		this.exchangeCount = exchangeCount;
	}

	public String getScoreIssue()
	{
		return this.scoreIssue;
	}

	public void setScoreIssue(String scoreIssue)
	{
		this.scoreIssue = scoreIssue;
	}
}