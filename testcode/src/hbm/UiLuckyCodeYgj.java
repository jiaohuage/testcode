package hbm;

import java.io.Serializable;

public class UiLuckyCodeYgj implements Serializable
{
	private UiLuckyCodeYgjId id;

	public UiLuckyCodeYgj()
	{
	}

	public UiLuckyCodeYgj(UiLuckyCodeYgjId id)
	{
		this.id = id;
	}

	public UiLuckyCodeYgjId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodeYgjId id)
	{
		this.id = id;
	}
}