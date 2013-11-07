package hbm;

import java.io.Serializable;

public class UiDownPageId implements Serializable
{
	private String PId;

	private String PName;

	private String phone;

	private String luckyNumber;

	private String issue;

	public UiDownPageId()
	{
	}

	public UiDownPageId(String PId, String PName, String phone,
			String luckyNumber, String issue)
	{
		this.PId = PId;
		this.PName = PName;
		this.phone = phone;
		this.luckyNumber = luckyNumber;
		this.issue = issue;
	}

	public String getPId()
	{
		return this.PId;
	}

	public void setPId(String PId)
	{
		this.PId = PId;
	}

	public String getPName()
	{
		return this.PName;
	}

	public void setPName(String PName)
	{
		this.PName = PName;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getLuckyNumber()
	{
		return this.luckyNumber;
	}

	public void setLuckyNumber(String luckyNumber)
	{
		this.luckyNumber = luckyNumber;
	}

	public String getIssue()
	{
		return this.issue;
	}

	public void setIssue(String issue)
	{
		this.issue = issue;
	}

	public boolean equals(Object other)
	{
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof UiDownPageId))
			return false;
		UiDownPageId castOther = (UiDownPageId) other;

		return (((getPId() != castOther.getPId()) && (((getPId() == null)
				|| (castOther.getPId() == null) || (!(getPId().equals(castOther
				.getPId()))))))
				|| ((getPName() != castOther.getPName()) && (((getPName() == null)
						|| (castOther.getPName() == null) || (!(getPName()
							.equals(castOther.getPName()))))))
				|| ((getPhone() != castOther.getPhone()) && (((getPhone() == null)
						|| (castOther.getPhone() == null) || (!(getPhone()
							.equals(castOther.getPhone()))))))
				|| ((getLuckyNumber() != castOther.getLuckyNumber()) && (((getLuckyNumber() == null)
						|| (castOther.getLuckyNumber() == null) || (!(getLuckyNumber()
							.equals(castOther.getLuckyNumber())))))) || ((getIssue() != castOther
				.getIssue()) && (((getIssue() == null)
				|| (castOther.getIssue() == null) || (!(getIssue()
					.equals(castOther.getIssue())))))));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result + ((getPId() == null) ? 0 : getPId().hashCode());
		result = 37 * result
				+ ((getPName() == null) ? 0 : getPName().hashCode());
		result = 37 * result
				+ ((getPhone() == null) ? 0 : getPhone().hashCode());
		result = 37
				* result
				+ ((getLuckyNumber() == null) ? 0 : getLuckyNumber().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		return result;
	}
}