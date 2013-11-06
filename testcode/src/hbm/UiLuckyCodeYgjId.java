package hbm;

import java.io.Serializable;

public class UiLuckyCodeYgjId implements Serializable
{
	private String phone;

	private String issue;

	private String comDate;

	private String createDate;

	public UiLuckyCodeYgjId()
	{
	}

	public UiLuckyCodeYgjId(String phone, String issue, String comDate,
			String createDate)
	{
		this.phone = phone;
		this.issue = issue;
		this.comDate = comDate;
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

	public String getComDate()
	{
		return this.comDate;
	}

	public void setComDate(String comDate)
	{
		this.comDate = comDate;
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
		if (!(other instanceof UiLuckyCodeYgjId))
			return false;
		UiLuckyCodeYgjId castOther = (UiLuckyCodeYgjId) other;

		return (((getPhone() != castOther.getPhone()) && (((getPhone() == null)
				|| (castOther.getPhone() == null) || (!(getPhone()
					.equals(castOther.getPhone()))))))
				|| ((getIssue() != castOther.getIssue()) && (((getIssue() == null)
						|| (castOther.getIssue() == null) || (!(getIssue()
							.equals(castOther.getIssue()))))))
				|| ((getComDate() != castOther.getComDate()) && (((getComDate() == null)
						|| (castOther.getComDate() == null) || (!(getComDate()
							.equals(castOther.getComDate())))))) || ((getCreateDate() != castOther
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
				+ ((getComDate() == null) ? 0 : getComDate().hashCode());
		result = 37 * result
				+ ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
		return result;
	}
}