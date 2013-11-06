package hbm;

import java.io.Serializable;

public class UiLuckyCodeSign implements Serializable
{
	private UiLuckyCodeSignId id;

	public UiLuckyCodeSign()
	{
	}

	public UiLuckyCodeSign(UiLuckyCodeSignId id)
	{
		this.id = id;
	}

	public UiLuckyCodeSignId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodeSignId id)
	{
		this.id = id;
	}
}