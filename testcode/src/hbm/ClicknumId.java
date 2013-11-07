package hbm;

import java.io.Serializable;

public class ClicknumId implements Serializable
{
	private String createDate;

	private String phone;

	private String clickUrl;

	public ClicknumId()
	{
	}

	public ClicknumId(String createDate, String phone, String clickUrl)
	{
		this.createDate = createDate;
		this.phone = phone;
		this.clickUrl = clickUrl;
	}

	public String getCreateDate()
	{
		return this.createDate;
	}

	public void setCreateDate(String createDate)
	{
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

	public String getClickUrl()
	{
		return this.clickUrl;
	}

	public void setClickUrl(String clickUrl)
	{
		this.clickUrl = clickUrl;
	}

	public boolean equals(Object other)
	{
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof ClicknumId))
			return false;
		ClicknumId castOther = (ClicknumId) other;

		return (((getCreateDate() != castOther.getCreateDate()) && (((getCreateDate() == null)
				|| (castOther.getCreateDate() == null) || (!(getCreateDate()
					.equals(castOther.getCreateDate()))))))
				|| ((getPhone() != castOther.getPhone()) && (((getPhone() == null)
						|| (castOther.getPhone() == null) || (!(getPhone()
							.equals(castOther.getPhone())))))) || ((getClickUrl() != castOther
				.getClickUrl()) && (((getClickUrl() == null)
				|| (castOther.getClickUrl() == null) || (!(getClickUrl()
					.equals(castOther.getClickUrl())))))));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result
				+ ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
		result = 37 * result
				+ ((getPhone() == null) ? 0 : getPhone().hashCode());
		result = 37 * result
				+ ((getClickUrl() == null) ? 0 : getClickUrl().hashCode());
		return result;
	}
}