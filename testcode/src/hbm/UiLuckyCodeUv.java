package hbm;

import java.io.Serializable;

public class UiLuckyCodeUv implements Serializable
{
	private UiLuckyCodeUvId id;

	public UiLuckyCodeUv()
	{
	}

	public UiLuckyCodeUv(UiLuckyCodeUvId id)
	{
		this.id = id;
	}

	public UiLuckyCodeUvId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodeUvId id)
	{
		this.id = id;
	}
}