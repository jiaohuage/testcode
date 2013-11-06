package hbm;

import java.io.Serializable;

public class UiLuckyCodePortalLoginId implements Serializable
{
	private String phone;

	private String sources;

	private String issue;

	private String createTime;

	private String tag;

	public UiLuckyCodePortalLoginId()
	{
	}

	public UiLuckyCodePortalLoginId(String phone, String sources, String issue,
			String createTime, String tag)
	{
		this.phone = phone;
		this.sources = sources;
		this.issue = issue;
		this.createTime = createTime;
		this.tag = tag;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getSources()
	{
		return this.sources;
	}

	public void setSources(String sources)
	{
		this.sources = sources;
	}

	public String getIssue()
	{
		return this.issue;
	}

	public void setIssue(String issue)
	{
		this.issue = issue;
	}

	public String getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getTag()
	{
		return this.tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public boolean equals(Object other)
	{
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof UiLuckyCodePortalLoginId))
			return false;
		UiLuckyCodePortalLoginId castOther = (UiLuckyCodePortalLoginId) other;

		return (((getPhone() != castOther.getPhone()) && (((getPhone() == null)
				|| (castOther.getPhone() == null) || (!(getPhone()
					.equals(castOther.getPhone()))))))
				|| ((getSources() != castOther.getSources()) && (((getSources() == null)
						|| (castOther.getSources() == null) || (!(getSources()
							.equals(castOther.getSources()))))))
				|| ((getIssue() != castOther.getIssue()) && (((getIssue() == null)
						|| (castOther.getIssue() == null) || (!(getIssue()
							.equals(castOther.getIssue()))))))
				|| ((getCreateTime() != castOther.getCreateTime()) && (((getCreateTime() == null)
						|| (castOther.getCreateTime() == null) || (!(getCreateTime()
							.equals(castOther.getCreateTime())))))) || ((getTag() != castOther
				.getTag()) && (((getTag() == null)
				|| (castOther.getTag() == null) || (!(getTag().equals(castOther
				.getTag())))))));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result
				+ ((getPhone() == null) ? 0 : getPhone().hashCode());
		result = 37 * result
				+ ((getSources() == null) ? 0 : getSources().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		result = 37 * result
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = 37 * result + ((getTag() == null) ? 0 : getTag().hashCode());
		return result;
	}
}