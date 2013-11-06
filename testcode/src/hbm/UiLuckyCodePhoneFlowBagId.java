package hbm;

import java.io.Serializable;

public class UiLuckyCodePhoneFlowBagId implements Serializable
{
	private String phone;

	private String issue;

	private String createDate;

	private String tag;

	private String comDate;

	public UiLuckyCodePhoneFlowBagId()
	{
	}

	public UiLuckyCodePhoneFlowBagId(String phone, String issue,
			String createDate, String tag, String comDate)
	{
		this.phone = phone;
		this.issue = issue;
		this.createDate = createDate;
		this.tag = tag;
		this.comDate = comDate;
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

	public String getTag()
	{
		return this.tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public String getComDate()
	{
		return this.comDate;
	}

	public void setComDate(String comDate)
	{
		this.comDate = comDate;
	}

	public boolean equals(Object other)
	{
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof UiLuckyCodePhoneFlowBagId))
			return false;
		UiLuckyCodePhoneFlowBagId castOther = (UiLuckyCodePhoneFlowBagId) other;

		return (((getPhone() != castOther.getPhone()) && (((getPhone() == null)
				|| (castOther.getPhone() == null) || (!(getPhone()
					.equals(castOther.getPhone()))))))
				|| ((getIssue() != castOther.getIssue()) && (((getIssue() == null)
						|| (castOther.getIssue() == null) || (!(getIssue()
							.equals(castOther.getIssue()))))))
				|| ((getCreateDate() != castOther.getCreateDate()) && (((getCreateDate() == null)
						|| (castOther.getCreateDate() == null) || (!(getCreateDate()
							.equals(castOther.getCreateDate()))))))
				|| ((getTag() != castOther.getTag()) && (((getTag() == null)
						|| (castOther.getTag() == null) || (!(getTag()
							.equals(castOther.getTag())))))) || ((getComDate() != castOther
				.getComDate()) && (((getComDate() == null)
				|| (castOther.getComDate() == null) || (!(getComDate()
					.equals(castOther.getComDate())))))));
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
		result = 37 * result + ((getTag() == null) ? 0 : getTag().hashCode());
		result = 37 * result
				+ ((getComDate() == null) ? 0 : getComDate().hashCode());
		return result;
	}
}