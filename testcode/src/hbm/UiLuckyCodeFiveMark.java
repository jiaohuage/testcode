package hbm;

import java.io.Serializable;

public class UiLuckyCodeFiveMark implements Serializable
{
	private UiLuckyCodeFiveMarkId id;

	public UiLuckyCodeFiveMark()
	{
	}

	public UiLuckyCodeFiveMark(UiLuckyCodeFiveMarkId id)
	{
		this.id = id;
	}

	public UiLuckyCodeFiveMarkId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodeFiveMarkId id)
	{
		this.id = id;
	}
}