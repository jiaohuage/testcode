package hbm;

import java.io.Serializable;

public class RandomStop implements Serializable
{
	private RandomStopId id;

	public RandomStop()
	{
	}

	public RandomStop(RandomStopId id)
	{
		this.id = id;
	}

	public RandomStopId getId()
	{
		return this.id;
	}

	public void setId(RandomStopId id)
	{
		this.id = id;
	}
}