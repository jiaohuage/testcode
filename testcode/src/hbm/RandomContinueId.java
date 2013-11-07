package hbm;

import java.io.Serializable;

public class RandomContinueId implements Serializable
{
	private String phone;

	private String createdate;

	private String issue;

	public RandomContinueId()
	{
	}

	public RandomContinueId(String phone, String createdate, String issue)
	{
		this.phone = phone;
		this.createdate = createdate;
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

	public String getCreatedate()
	{
		return this.createdate;
	}

	public void setCreatedate(String createdate)
	{
		this.createdate = createdate;
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
		if (!(other instanceof RandomContinueId))
			return false;
		RandomContinueId castOther = (RandomContinueId) other;

		return (((getPhone() != castOther.getPhone()) && (((getPhone() == null)
				|| (castOther.getPhone() == null) || (!(getPhone()
					.equals(castOther.getPhone()))))))
				|| ((getCreatedate() != castOther.getCreatedate()) && (((getCreatedate() == null)
						|| (castOther.getCreatedate() == null) || (!(getCreatedate()
							.equals(castOther.getCreatedate())))))) || ((getIssue() != castOther
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
				+ ((getCreatedate() == null) ? 0 : getCreatedate().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		return result;
	}
}