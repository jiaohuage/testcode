package hbm;

import java.io.Serializable;

public class UiLuckyCodePhoneFlowBag implements Serializable
{
	private UiLuckyCodePhoneFlowBagId id;

	public UiLuckyCodePhoneFlowBag()
	{
	}

	public UiLuckyCodePhoneFlowBag(UiLuckyCodePhoneFlowBagId id)
	{
		this.id = id;
	}

	public UiLuckyCodePhoneFlowBagId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodePhoneFlowBagId id)
	{
		this.id = id;
	}
}