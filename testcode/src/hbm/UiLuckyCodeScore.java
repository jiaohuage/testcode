package hbm;

import java.io.Serializable;

public class UiLuckyCodeScore implements Serializable
{
	private String svcId;

	private String freeNum;

	private String frozenNum;

	public UiLuckyCodeScore()
	{
	}

	public UiLuckyCodeScore(String freeNum, String frozenNum)
	{
		this.freeNum = freeNum;
		this.frozenNum = frozenNum;
	}

	public String getSvcId()
	{
		return this.svcId;
	}

	public void setSvcId(String svcId)
	{
		this.svcId = svcId;
	}

	public String getFreeNum()
	{
		return this.freeNum;
	}

	public void setFreeNum(String freeNum)
	{
		this.freeNum = freeNum;
	}

	public String getFrozenNum()
	{
		return this.frozenNum;
	}

	public void setFrozenNum(String frozenNum)
	{
		this.frozenNum = frozenNum;
	}
}