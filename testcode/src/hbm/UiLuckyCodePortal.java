package hbm;

import java.io.Serializable;

public class UiLuckyCodePortal implements Serializable
{
	private UiLuckyCodePortalId id;

	public UiLuckyCodePortal()
	{
	}

	public UiLuckyCodePortal(UiLuckyCodePortalId id)
	{
		this.id = id;
	}

	public UiLuckyCodePortalId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodePortalId id)
	{
		this.id = id;
	}
}