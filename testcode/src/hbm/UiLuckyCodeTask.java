package hbm;

import java.io.Serializable;

public class UiLuckyCodeTask implements Serializable
{
	private UiLuckyCodeTaskId id;

	public UiLuckyCodeTask()
	{
	}

	public UiLuckyCodeTask(UiLuckyCodeTaskId id)
	{
		this.id = id;
	}

	public UiLuckyCodeTaskId getId()
	{
		return this.id;
	}

	public void setId(UiLuckyCodeTaskId id)
	{
		this.id = id;
	}
}