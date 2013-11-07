package hbm;

import java.io.Serializable;

public class UiLuckyCodeFenxiang implements Serializable
{
	private UiLuckyCodeFenxiangId id;

	public UiLuckyCodeFenxiang()
	{
	}

	public UiLuckyCodeFenxiang(UiLuckyCodeFenxiangId id)
	{
		this.id = id;
	}

	public UiLuckyCodeFenxiangId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodeFenxiangId id)
	{
		this.id = id;
	}
}