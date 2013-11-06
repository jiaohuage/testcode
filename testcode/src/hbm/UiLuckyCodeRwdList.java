package hbm;

import java.io.Serializable;

public class UiLuckyCodeRwdList implements Serializable
{
	private UiLuckyCodeRwdListId id;

	public UiLuckyCodeRwdList()
	{
	}

	public UiLuckyCodeRwdList(UiLuckyCodeRwdListId id)
	{
		this.id = id;
	}

	public UiLuckyCodeRwdListId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodeRwdListId id)
	{
		this.id = id;
	}
}