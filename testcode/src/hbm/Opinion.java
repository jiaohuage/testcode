package hbm;

import java.io.Serializable;

public class Opinion implements Serializable
{
	private OpinionId id;

	public Opinion()
	{
	}

	public Opinion(OpinionId id)
	{
		this.id = id;
	}

	public OpinionId getId()
	{
		return this.id;
	}

	public void setId(OpinionId id)
	{
		this.id = id;
	}
}