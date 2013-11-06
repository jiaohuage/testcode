package hbm;

import java.io.Serializable;

public class UiLuckyCodeSeedSmsId implements Serializable
{
	private String phone;

	private String userphone;

	private String issue;

	public UiLuckyCodeSeedSmsId()
	{
	}

	public UiLuckyCodeSeedSmsId(String phone, String userphone, String issue)
	{
		this.phone = phone;
		this.userphone = userphone;
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

	public String getUserphone()
	{
		return this.userphone;
	}

	public void setUserphone(String userphone)
	{
		this.userphone = userphone;
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
		if (!(other instanceof UiLuckyCodeSeedSmsId))
			return false;
		UiLuckyCodeSeedSmsId castOther = (UiLuckyCodeSeedSmsId) other;

		return (((getPhone() != castOther.getPhone()) && (((getPhone() == null)
				|| (castOther.getPhone() == null) || (!(getPhone()
					.equals(castOther.getPhone()))))))
				|| ((getUserphone() != castOther.getUserphone()) && (((getUserphone() == null)
						|| (castOther.getUserphone() == null) || (!(getUserphone()
							.equals(castOther.getUserphone())))))) || ((getIssue() != castOther
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
				+ ((getUserphone() == null) ? 0 : getUserphone().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		return result;
	}
}