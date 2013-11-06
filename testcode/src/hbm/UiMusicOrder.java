package hbm;

import java.io.Serializable;

public class UiMusicOrder implements Serializable
{
	private UiMusicOrderId id;

	public UiMusicOrder()
	{
	}

	public UiMusicOrder(UiMusicOrderId id)
	{
		this.id = id;
	}

	public UiMusicOrderId getId()
	{
		return this.id;
	}

	public void setId(UiMusicOrderId id)
	{
		this.id = id;
	}
}