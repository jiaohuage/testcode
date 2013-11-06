package hbm;

import java.io.Serializable;

public class UiLuckyCodePaihang implements Serializable
{
	private UiLuckyCodePaihangId id;

	public UiLuckyCodePaihang()
	{
	}

	public UiLuckyCodePaihang(UiLuckyCodePaihangId id)
	{
		this.id = id;
	}

	public UiLuckyCodePaihangId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodePaihangId id)
	{
		this.id = id;
	}
}