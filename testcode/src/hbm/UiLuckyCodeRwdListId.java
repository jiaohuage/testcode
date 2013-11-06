package hbm;

import java.io.Serializable;

public class UiLuckyCodeRwdListId implements Serializable
{
	private String svcId;

	private String winTag;

	private String svcNo;

	private String random;

	private String issue;

	public UiLuckyCodeRwdListId()
	{
	}

	public UiLuckyCodeRwdListId(String svcId, String winTag, String svcNo,
			String random, String issue)
	{
		this.svcId = svcId;
		this.winTag = winTag;
		this.svcNo = svcNo;
		this.random = random;
		this.issue = issue;
	}

	public String getSvcId()
	{
		return this.svcId;
	}

	public void setSvcId(String svcId)
	{
		this.svcId = svcId;
	}

	public String getWinTag()
	{
		return this.winTag;
	}

	public void setWinTag(String winTag)
	{
		this.winTag = winTag;
	}

	public String getSvcNo()
	{
		return this.svcNo;
	}

	public void setSvcNo(String svcNo)
	{
		this.svcNo = svcNo;
	}

	public String getRandom()
	{
		return this.random;
	}

	public void setRandom(String random)
	{
		this.random = random;
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
		if (!(other instanceof UiLuckyCodeRwdListId))
			return false;
		UiLuckyCodeRwdListId castOther = (UiLuckyCodeRwdListId) other;

		return (((getSvcId() != castOther.getSvcId()) && (((getSvcId() == null)
				|| (castOther.getSvcId() == null) || (!(getSvcId()
					.equals(castOther.getSvcId()))))))
				|| ((getWinTag() != castOther.getWinTag()) && (((getWinTag() == null)
						|| (castOther.getWinTag() == null) || (!(getWinTag()
							.equals(castOther.getWinTag()))))))
				|| ((getSvcNo() != castOther.getSvcNo()) && (((getSvcNo() == null)
						|| (castOther.getSvcNo() == null) || (!(getSvcNo()
							.equals(castOther.getSvcNo()))))))
				|| ((getRandom() != castOther.getRandom()) && (((getRandom() == null)
						|| (castOther.getRandom() == null) || (!(getRandom()
							.equals(castOther.getRandom())))))) || ((getIssue() != castOther
				.getIssue()) && (((getIssue() == null)
				|| (castOther.getIssue() == null) || (!(getIssue()
					.equals(castOther.getIssue())))))));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result
				+ ((getSvcId() == null) ? 0 : getSvcId().hashCode());
		result = 37 * result
				+ ((getWinTag() == null) ? 0 : getWinTag().hashCode());
		result = 37 * result
				+ ((getSvcNo() == null) ? 0 : getSvcNo().hashCode());
		result = 37 * result
				+ ((getRandom() == null) ? 0 : getRandom().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		return result;
	}
}