package hbm;

import java.io.Serializable;

public class UiLuckyCodeBeforeId implements Serializable
{
	private String svcId;

	private String random;

	private String winTag;

	private String issue;

	private String tag;

	public UiLuckyCodeBeforeId()
	{
	}

	public UiLuckyCodeBeforeId(String svcId, String random, String winTag,
			String issue, String tag)
	{
		this.svcId = svcId;
		this.random = random;
		this.winTag = winTag;
		this.issue = issue;
		this.tag = tag;
	}

	public String getSvcId()
	{
		return this.svcId;
	}

	public void setSvcId(String svcId)
	{
		this.svcId = svcId;
	}

	public String getRandom()
	{
		return this.random;
	}

	public void setRandom(String random)
	{
		this.random = random;
	}

	public String getWinTag()
	{
		return this.winTag;
	}

	public void setWinTag(String winTag)
	{
		this.winTag = winTag;
	}

	public String getIssue()
	{
		return this.issue;
	}

	public void setIssue(String issue)
	{
		this.issue = issue;
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
		if (!(other instanceof UiLuckyCodeBeforeId))
			return false;
		UiLuckyCodeBeforeId castOther = (UiLuckyCodeBeforeId) other;

		return (((getSvcId() != castOther.getSvcId()) && (((getSvcId() == null)
				|| (castOther.getSvcId() == null) || (!(getSvcId()
					.equals(castOther.getSvcId()))))))
				|| ((getRandom() != castOther.getRandom()) && (((getRandom() == null)
						|| (castOther.getRandom() == null) || (!(getRandom()
							.equals(castOther.getRandom()))))))
				|| ((getWinTag() != castOther.getWinTag()) && (((getWinTag() == null)
						|| (castOther.getWinTag() == null) || (!(getWinTag()
							.equals(castOther.getWinTag()))))))
				|| ((getIssue() != castOther.getIssue()) && (((getIssue() == null)
						|| (castOther.getIssue() == null) || (!(getIssue()
							.equals(castOther.getIssue())))))) || ((getTag() != castOther
				.getTag()) && (((getTag() == null)
				|| (castOther.getTag() == null) || (!(getTag().equals(castOther
				.getTag())))))));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result
				+ ((getSvcId() == null) ? 0 : getSvcId().hashCode());
		result = 37 * result
				+ ((getRandom() == null) ? 0 : getRandom().hashCode());
		result = 37 * result
				+ ((getWinTag() == null) ? 0 : getWinTag().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		result = 37 * result + ((getTag() == null) ? 0 : getTag().hashCode());
		return result;
	}
}