package hbm;

import java.io.Serializable;

public class UiLuckyCodeRwd implements Serializable
{
	private UiLuckyCodeRwdId id;

	public UiLuckyCodeRwd()
	{
	}

	public UiLuckyCodeRwd(UiLuckyCodeRwdId id)
	{
		this.id = id;
	}

	public UiLuckyCodeRwdId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodeRwdId id)
	{
		this.id = id;
	}
}