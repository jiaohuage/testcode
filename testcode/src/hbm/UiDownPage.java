package hbm;

import java.io.Serializable;

public class UiDownPage implements Serializable
{
	private UiDownPageId id;

	public UiDownPage()
	{
	}

	public UiDownPage(UiDownPageId id)
	{
		this.id = id;
	}

	public UiDownPageId getId()
	{
		return this.id;
	}

	public void setId(UiDownPageId id)
	{
		this.id = id;
	}
}