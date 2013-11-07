package hbm;

import java.io.Serializable;

public class Clicknum implements Serializable
{
	private ClicknumId id;

	public Clicknum()
	{
	}

	public Clicknum(ClicknumId id)
	{
		this.id = id;
	}

	public ClicknumId getId()
	{
		return this.id;
	}

	public void setId(ClicknumId id)
	{
		this.id = id;
	}
}