package hbm;

import java.io.Serializable;

public class UiLuckyCodeBefore implements Serializable
{
	private UiLuckyCodeBeforeId id;

	public UiLuckyCodeBefore()
	{
	}

	public UiLuckyCodeBefore(UiLuckyCodeBeforeId id)
	{
		this.id = id;
	}

	public UiLuckyCodeBeforeId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodeBeforeId id)
	{
		this.id = id;
	}
}