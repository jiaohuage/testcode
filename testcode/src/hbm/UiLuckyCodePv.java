package hbm;

import java.io.Serializable;

public class UiLuckyCodePv implements Serializable
{
	private UiLuckyCodePvId id;

	public UiLuckyCodePv()
	{
	}

	public UiLuckyCodePv(UiLuckyCodePvId id)
	{
		this.id = id;
	}

	public UiLuckyCodePvId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodePvId id)
	{
		this.id = id;
	}
}