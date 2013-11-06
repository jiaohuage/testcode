package hbm;

import java.io.Serializable;

public class UiLuckyCodeSignId implements Serializable
{
	private String phone;

	private String createDate;

	private String issue;

	public UiLuckyCodeSignId()
	{
	}

	public UiLuckyCodeSignId(String phone, String createDate, String issue)
	{
		this.phone = phone;
		this.createDate = createDate;
		this.issue = issue;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getCreateDate()
	{
		return this.createDate;
	}

	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
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
		if (!(other instanceof UiLuckyCodeSignId))
			return false;
		UiLuckyCodeSignId castOther = (UiLuckyCodeSignId) other;

		return (((getPhone() != castOther.getPhone()) && (((getPhone() == null)
				|| (castOther.getPhone() == null) || (!(getPhone()
					.equals(castOther.getPhone()))))))
				|| ((getCreateDate() != castOther.getCreateDate()) && (((getCreateDate() == null)
						|| (castOther.getCreateDate() == null) || (!(getCreateDate()
							.equals(castOther.getCreateDate())))))) || ((getIssue() != castOther
				.getIssue()) && (((getIssue() == null)
				|| (castOther.getIssue() == null) || (!(getIssue()
					.equals(castOther.getIssue())))))));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result
				+ ((getPhone() == null) ? 0 : getPhone().hashCode());
		result = 37 * result
				+ ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		return result;
	}
}