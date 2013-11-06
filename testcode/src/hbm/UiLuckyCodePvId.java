package hbm;

import java.io.Serializable;

public class UiLuckyCodePvId implements Serializable
{
	private String firstPage;

	private String myWinCode;

	private String codeRule;

	private String anCodeWin;

	private String createTime;

	private String issue;

	private String kefu;

	private String fenxiang;

	public UiLuckyCodePvId()
	{
	}

	public UiLuckyCodePvId(String firstPage, String myWinCode, String codeRule,
			String anCodeWin, String createTime, String issue, String kefu,
			String fenxiang)
	{
		this.firstPage = firstPage;
		this.myWinCode = myWinCode;
		this.codeRule = codeRule;
		this.anCodeWin = anCodeWin;
		this.createTime = createTime;
		this.issue = issue;
		this.kefu = kefu;
		this.fenxiang = fenxiang;
	}

	public String getFirstPage()
	{
		return this.firstPage;
	}

	public void setFirstPage(String firstPage)
	{
		this.firstPage = firstPage;
	}

	public String getMyWinCode()
	{
		return this.myWinCode;
	}

	public void setMyWinCode(String myWinCode)
	{
		this.myWinCode = myWinCode;
	}

	public String getCodeRule()
	{
		return this.codeRule;
	}

	public void setCodeRule(String codeRule)
	{
		this.codeRule = codeRule;
	}

	public String getAnCodeWin()
	{
		return this.anCodeWin;
	}

	public void setAnCodeWin(String anCodeWin)
	{
		this.anCodeWin = anCodeWin;
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

	public String getKefu()
	{
		return this.kefu;
	}

	public void setKefu(String kefu)
	{
		this.kefu = kefu;
	}

	public String getFenxiang()
	{
		return this.fenxiang;
	}

	public void setFenxiang(String fenxiang)
	{
		this.fenxiang = fenxiang;
	}

	public boolean equals(Object other)
	{
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof UiLuckyCodePvId))
			return false;
		UiLuckyCodePvId castOther = (UiLuckyCodePvId) other;

		return (((getFirstPage() != castOther.getFirstPage()) && (((getFirstPage() == null)
				|| (castOther.getFirstPage() == null) || (!(getFirstPage()
					.equals(castOther.getFirstPage()))))))
				|| ((getMyWinCode() != castOther.getMyWinCode()) && (((getMyWinCode() == null)
						|| (castOther.getMyWinCode() == null) || (!(getMyWinCode()
							.equals(castOther.getMyWinCode()))))))
				|| ((getCodeRule() != castOther.getCodeRule()) && (((getCodeRule() == null)
						|| (castOther.getCodeRule() == null) || (!(getCodeRule()
							.equals(castOther.getCodeRule()))))))
				|| ((getAnCodeWin() != castOther.getAnCodeWin()) && (((getAnCodeWin() == null)
						|| (castOther.getAnCodeWin() == null) || (!(getAnCodeWin()
							.equals(castOther.getAnCodeWin()))))))
				|| ((getCreateTime() != castOther.getCreateTime()) && (((getCreateTime() == null)
						|| (castOther.getCreateTime() == null) || (!(getCreateTime()
							.equals(castOther.getCreateTime()))))))
				|| ((getIssue() != castOther.getIssue()) && (((getIssue() == null)
						|| (castOther.getIssue() == null) || (!(getIssue()
							.equals(castOther.getIssue()))))))
				|| ((getKefu() != castOther.getKefu()) && (((getKefu() == null)
						|| (castOther.getKefu() == null) || (!(getKefu()
							.equals(castOther.getKefu())))))) || ((getFenxiang() != castOther
				.getFenxiang()) && (((getFenxiang() == null)
				|| (castOther.getFenxiang() == null) || (!(getFenxiang()
					.equals(castOther.getFenxiang())))))));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result
				+ ((getFirstPage() == null) ? 0 : getFirstPage().hashCode());
		result = 37 * result
				+ ((getMyWinCode() == null) ? 0 : getMyWinCode().hashCode());
		result = 37 * result
				+ ((getCodeRule() == null) ? 0 : getCodeRule().hashCode());
		result = 37 * result
				+ ((getAnCodeWin() == null) ? 0 : getAnCodeWin().hashCode());
		result = 37 * result
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		result = 37 * result + ((getKefu() == null) ? 0 : getKefu().hashCode());
		result = 37 * result
				+ ((getFenxiang() == null) ? 0 : getFenxiang().hashCode());
		return result;
	}
}