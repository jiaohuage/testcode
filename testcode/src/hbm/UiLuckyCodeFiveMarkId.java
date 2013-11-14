package hbm;

import java.io.Serializable;

public class UiLuckyCodeFiveMarkId implements Serializable
{
	private String phone;

	private String issue;

	private String createDate;

	public UiLuckyCodeFiveMarkId()
	{
	}

	public UiLuckyCodeFiveMarkId(String phone, String issue, String createDate)
	{
		this.phone = phone;
		this.issue = issue;
		this.createDate = createDate;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getIssue()
	{
		return this.issue;
	}

	public void setIssue(String issue)
	{
		this.issue = issue;
	}

	public String getCreateDate()
	{
		return this.createDate;
	}

	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public boolean equals(Object other)
	{
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof UiLuckyCodeFiveMarkId))
			return false;
		UiLuckyCodeFiveMarkId castOther = (UiLuckyCodeFiveMarkId) other;

		return (((getPhone() != castOther.getPhone()) && (((getPhone() == null)
				|| (castOther.getPhone() == null) || (!(getPhone()
					.equals(castOther.getPhone()))))))
				|| ((getIssue() != castOther.getIssue()) && (((getIssue() == null)
						|| (castOther.getIssue() == null) || (!(getIssue()
							.equals(castOther.getIssue())))))) || ((getCreateDate() != castOther
				.getCreateDate()) && (((getCreateDate() == null)
				|| (castOther.getCreateDate() == null) || (!(getCreateDate()
					.equals(castOther.getCreateDate())))))));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result
				+ ((getPhone() == null) ? 0 : getPhone().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		result = 37 * result
				+ ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
		return result;
	}
}