package hbm;

import java.io.Serializable;

public class UiLuckyCodeFenxiangId implements Serializable
{
	private String phone;

	private String issue;

	public UiLuckyCodeFenxiangId()
	{
	}

	public UiLuckyCodeFenxiangId(String phone, String issue)
	{
		this.phone = phone;
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
		if (!(other instanceof UiLuckyCodeFenxiangId))
			return false;
		UiLuckyCodeFenxiangId castOther = (UiLuckyCodeFenxiangId) other;

		return (((getPhone() != castOther.getPhone()) && (((getPhone() == null)
				|| (castOther.getPhone() == null) || (!(getPhone()
					.equals(castOther.getPhone())))))) || ((getIssue() != castOther
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
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		return result;
	}
}