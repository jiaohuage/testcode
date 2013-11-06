package hbm;

import java.io.Serializable;

public class UiVacOrderId implements Serializable
{
	private String spid;

	private String sppid;

	private String phone;

	private String luckyNumber;

	private String issue;

	private String comDate;

	private String createDate;

	public UiVacOrderId()
	{
	}

	public UiVacOrderId(String spid, String sppid, String phone,
			String luckyNumber, String issue, String comDate, String createDate)
	{
		this.spid = spid;
		this.sppid = sppid;
		this.phone = phone;
		this.luckyNumber = luckyNumber;
		this.issue = issue;
		this.comDate = comDate;
		this.createDate = createDate;
	}

	public String getSpid()
	{
		return this.spid;
	}

	public void setSpid(String spid)
	{
		this.spid = spid;
	}

	public String getSppid()
	{
		return this.sppid;
	}

	public void setSppid(String sppid)
	{
		this.sppid = sppid;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getLuckyNumber()
	{
		return this.luckyNumber;
	}

	public void setLuckyNumber(String luckyNumber)
	{
		this.luckyNumber = luckyNumber;
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
		if (!(other instanceof UiVacOrderId))
			return false;
		UiVacOrderId castOther = (UiVacOrderId) other;

		return (((getSpid() != castOther.getSpid()) && (((getSpid() == null)
				|| (castOther.getSpid() == null) || (!(getSpid()
					.equals(castOther.getSpid()))))))
				|| ((getSppid() != castOther.getSppid()) && (((getSppid() == null)
						|| (castOther.getSppid() == null) || (!(getSppid()
							.equals(castOther.getSppid()))))))
				|| ((getPhone() != castOther.getPhone()) && (((getPhone() == null)
						|| (castOther.getPhone() == null) || (!(getPhone()
							.equals(castOther.getPhone()))))))
				|| ((getLuckyNumber() != castOther.getLuckyNumber()) && (((getLuckyNumber() == null)
						|| (castOther.getLuckyNumber() == null) || (!(getLuckyNumber()
							.equals(castOther.getLuckyNumber()))))))
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

		result = 37 * result + ((getSpid() == null) ? 0 : getSpid().hashCode());
		result = 37 * result
				+ ((getSppid() == null) ? 0 : getSppid().hashCode());
		result = 37 * result
				+ ((getPhone() == null) ? 0 : getPhone().hashCode());
		result = 37
				* result
				+ ((getLuckyNumber() == null) ? 0 : getLuckyNumber().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		result = 37 * result
				+ ((getComDate() == null) ? 0 : getComDate().hashCode());
		result = 37 * result
				+ ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
		return result;
	}
}