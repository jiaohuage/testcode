package hbm;

import java.io.Serializable;

public class UiLuckyCodeFreeId implements Serializable
{
	private Long id;

	private String randomNumber;

	public UiLuckyCodeFreeId()
	{
	}

	public UiLuckyCodeFreeId(Long id, String randomNumber)
	{
		this.id = id;
		this.randomNumber = randomNumber;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getRandomNumber()
	{
		return this.randomNumber;
	}

	public void setRandomNumber(String randomNumber)
	{
		this.randomNumber = randomNumber;
	}

	public boolean equals(Object other)
	{
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof UiLuckyCodeFreeId))
			return false;
		UiLuckyCodeFreeId castOther = (UiLuckyCodeFreeId) other;

		return (((getId() != castOther.getId()) && (((getId() == null)
				|| (castOther.getId() == null) || (!(getId().equals(castOther
				.getId())))))) || ((getRandomNumber() != castOther
				.getRandomNumber()) && (((getRandomNumber() == null)
				|| (castOther.getRandomNumber() == null) || (!(getRandomNumber()
					.equals(castOther.getRandomNumber())))))));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result + ((getId() == null) ? 0 : getId().hashCode());
		result = 37
				* result
				+ ((getRandomNumber() == null) ? 0 : getRandomNumber()
						.hashCode());
		return result;
	}
}