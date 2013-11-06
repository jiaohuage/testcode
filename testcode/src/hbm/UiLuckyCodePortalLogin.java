package hbm;

import java.io.Serializable;

public class UiLuckyCodePortalLogin implements Serializable
{
	private UiLuckyCodePortalLoginId id;

	public UiLuckyCodePortalLogin()
	{
	}

	public UiLuckyCodePortalLogin(UiLuckyCodePortalLoginId id)
	{
		this.id = id;
	}

	public UiLuckyCodePortalLoginId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodePortalLoginId id)
	{
		this.id = id;
	}
}