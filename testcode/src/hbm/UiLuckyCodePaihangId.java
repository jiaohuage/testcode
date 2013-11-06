package hbm;

import java.io.Serializable;

public class UiLuckyCodePaihangId implements Serializable
{
	private String phone;

	private String phname;

	private String issue;

	public UiLuckyCodePaihangId()
	{
	}

	public UiLuckyCodePaihangId(String phone, String phname, String issue)
	{
		this.phone = phone;
		this.phname = phname;
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

	public String getPhname()
	{
		return this.phname;
	}

	public void setPhname(String phname)
	{
		this.phname = phname;
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
		if (!(other instanceof UiLuckyCodePaihangId))
			return false;
		UiLuckyCodePaihangId castOther = (UiLuckyCodePaihangId) other;

		return (((getPhone() != castOther.getPhone()) && (((getPhone() == null)
				|| (castOther.getPhone() == null) || (!(getPhone()
					.equals(castOther.getPhone()))))))
				|| ((getPhname() != castOther.getPhname()) && (((getPhname() == null)
						|| (castOther.getPhname() == null) || (!(getPhname()
							.equals(castOther.getPhname())))))) || ((getIssue() != castOther
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
				+ ((getPhname() == null) ? 0 : getPhname().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		return result;
	}
}