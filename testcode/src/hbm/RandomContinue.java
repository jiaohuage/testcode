package hbm;

import java.io.Serializable;

public class RandomContinue implements Serializable
{
	private RandomContinueId id;

	public RandomContinue()
	{
	}

	public RandomContinue(RandomContinueId id)
	{
		this.id = id;
	}

	public RandomContinueId getId()
	{
		return this.id;
	}

	public void setId(RandomContinueId id)
	{
		this.id = id;
	}
}