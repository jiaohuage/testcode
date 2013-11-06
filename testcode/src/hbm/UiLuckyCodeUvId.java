package hbm;

import java.io.Serializable;

public class UiLuckyCodeUvId implements Serializable
{
	private String phone;

	private String createTime;

	private String issue;

	public UiLuckyCodeUvId()
	{
	}

	public UiLuckyCodeUvId(String phone, String createTime, String issue)
	{
		this.phone = phone;
		this.createTime = createTime;
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

	public String getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
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
		if (!(other instanceof UiLuckyCodeUvId))
			return false;
		UiLuckyCodeUvId castOther = (UiLuckyCodeUvId) other;

		return (((getPhone() != castOther.getPhone()) && (((getPhone() == null)
				|| (castOther.getPhone() == null) || (!(getPhone()
					.equals(castOther.getPhone()))))))
				|| ((getCreateTime() != castOther.getCreateTime()) && (((getCreateTime() == null)
						|| (castOther.getCreateTime() == null) || (!(getCreateTime()
							.equals(castOther.getCreateTime())))))) || ((getIssue() != castOther
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
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		return result;
	}
}