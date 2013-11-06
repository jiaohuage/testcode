package hbm;

import java.io.Serializable;

public class UiVacOrder implements Serializable
{
	private UiVacOrderId id;

	public UiVacOrder()
	{
	}

	public UiVacOrder(UiVacOrderId id)
	{
		this.id = id;
	}

	public UiVacOrderId getId()
	{
		return this.id;
	}

	public void setId(UiVacOrderId id)
	{
		this.id = id;
	}
}