package hbm;

import java.io.Serializable;

public class UiLuckyCodeFlow implements Serializable
{
	private UiLuckyCodeFlowId id;

	public UiLuckyCodeFlow()
	{
	}

	public UiLuckyCodeFlow(UiLuckyCodeFlowId id)
	{
		this.id = id;
	}

	public UiLuckyCodeFlowId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodeFlowId id)
	{
		this.id = id;
	}
}