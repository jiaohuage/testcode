package hbm;

import java.io.Serializable;

public class UiLuckyCodeSeedSms implements Serializable
{
	private UiLuckyCodeSeedSmsId id;

	public UiLuckyCodeSeedSms()
	{
	}

	public UiLuckyCodeSeedSms(UiLuckyCodeSeedSmsId id)
	{
		this.id = id;
	}

	public UiLuckyCodeSeedSmsId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodeSeedSmsId id)
	{
		this.id = id;
	}
}