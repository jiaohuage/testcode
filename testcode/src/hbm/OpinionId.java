package hbm;

import java.io.Serializable;

public class OpinionId implements Serializable
{
	private String opinion;

	private String phone;

	private String issue;

	public OpinionId()
	{
	}

	public OpinionId(String opinion, String phone, String issue)
	{
		this.opinion = opinion;
		this.phone = phone;
		this.issue = issue;
	}

	public String getOpinion()
	{
		return this.opinion;
	}

	public void setOpinion(String opinion)
	{
		this.opinion = opinion;
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
		if (!(other instanceof OpinionId))
			return false;
		OpinionId castOther = (OpinionId) other;

		return (((getOpinion() != castOther.getOpinion()) && (((getOpinion() == null)
				|| (castOther.getOpinion() == null) || (!(getOpinion()
					.equals(castOther.getOpinion()))))))
				|| ((getPhone() != castOther.getPhone()) && (((getPhone() == null)
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
				+ ((getOpinion() == null) ? 0 : getOpinion().hashCode());
		result = 37 * result
				+ ((getPhone() == null) ? 0 : getPhone().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		return result;
	}
}