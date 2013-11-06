package hbm;

import java.io.Serializable;

public class UiLuckyCodeFree implements Serializable
{
	private UiLuckyCodeFreeId id;

	public UiLuckyCodeFree()
	{
	}

	public UiLuckyCodeFree(UiLuckyCodeFreeId id)
	{
		this.id = id;
	}

	public UiLuckyCodeFreeId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodeFreeId id)
	{
		this.id = id;
	}
}