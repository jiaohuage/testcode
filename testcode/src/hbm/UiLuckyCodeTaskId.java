package hbm;

import java.io.Serializable;

public class UiLuckyCodeTaskId implements Serializable
{
	private String PId;

	private String spPid;

	private String PName;

	private String PDesc;

	private String PLuckyNum;

	private String PImg;

	private String issue;

	private String tag;

	private Long ord;

	private String buttonDesc;

	private String iphoneUrl;

	private String anzhuoUrl;

	private String urlTag;

	private String type;

	private String PIntegralNum;

	private String url;

	private String descTag;

	private String tag2g;

	public UiLuckyCodeTaskId()
	{
	}

	public UiLuckyCodeTaskId(String PId, String spPid, String PName,
			String PDesc, String PLuckyNum, String PImg, String issue,
			String tag, Long ord, String buttonDesc, String iphoneUrl,
			String anzhuoUrl, String urlTag, String type, String PIntegralNum,
			String url, String descTag, String tag2g)
	{
		this.PId = PId;
		this.spPid = spPid;
		this.PName = PName;
		this.PDesc = PDesc;
		this.PLuckyNum = PLuckyNum;
		this.PImg = PImg;
		this.issue = issue;
		this.tag = tag;
		this.ord = ord;
		this.buttonDesc = buttonDesc;
		this.iphoneUrl = iphoneUrl;
		this.anzhuoUrl = anzhuoUrl;
		this.urlTag = urlTag;
		this.type = type;
		this.PIntegralNum = PIntegralNum;
		this.url = url;
		this.descTag = descTag;
		this.tag2g = tag2g;
	}

	public String getPId()
	{
		return this.PId;
	}

	public void setPId(String PId)
	{
		this.PId = PId;
	}

	public String getSpPid()
	{
		return this.spPid;
	}

	public void setSpPid(String spPid)
	{
		this.spPid = spPid;
	}

	public String getPName()
	{
		return this.PName;
	}

	public void setPName(String PName)
	{
		this.PName = PName;
	}

	public String getPDesc()
	{
		return this.PDesc;
	}

	public void setPDesc(String PDesc)
	{
		this.PDesc = PDesc;
	}

	public String getPLuckyNum()
	{
		return this.PLuckyNum;
	}

	public void setPLuckyNum(String PLuckyNum)
	{
		this.PLuckyNum = PLuckyNum;
	}

	public String getPImg()
	{
		return this.PImg;
	}

	public void setPImg(String PImg)
	{
		this.PImg = PImg;
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

	public Long getOrd()
	{
		return this.ord;
	}

	public void setOrd(Long ord)
	{
		this.ord = ord;
	}

	public String getButtonDesc()
	{
		return this.buttonDesc;
	}

	public void setButtonDesc(String buttonDesc)
	{
		this.buttonDesc = buttonDesc;
	}

	public String getIphoneUrl()
	{
		return this.iphoneUrl;
	}

	public void setIphoneUrl(String iphoneUrl)
	{
		this.iphoneUrl = iphoneUrl;
	}

	public String getAnzhuoUrl()
	{
		return this.anzhuoUrl;
	}

	public void setAnzhuoUrl(String anzhuoUrl)
	{
		this.anzhuoUrl = anzhuoUrl;
	}

	public String getUrlTag()
	{
		return this.urlTag;
	}

	public void setUrlTag(String urlTag)
	{
		this.urlTag = urlTag;
	}

	public String getType()
	{
		return this.type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getPIntegralNum()
	{
		return this.PIntegralNum;
	}

	public void setPIntegralNum(String PIntegralNum)
	{
		this.PIntegralNum = PIntegralNum;
	}

	public String getUrl()
	{
		return this.url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getDescTag()
	{
		return this.descTag;
	}

	public void setDescTag(String descTag)
	{
		this.descTag = descTag;
	}

	public String getTag2g()
	{
		return this.tag2g;
	}

	public void setTag2g(String tag2g)
	{
		this.tag2g = tag2g;
	}

	public boolean equals(Object other)
	{
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof UiLuckyCodeTaskId))
			return false;
		UiLuckyCodeTaskId castOther = (UiLuckyCodeTaskId) other;

		return (((getPId() != castOther.getPId()) && (((getPId() == null)
				|| (castOther.getPId() == null) || (!(getPId().equals(castOther
				.getPId()))))))
				|| ((getSpPid() != castOther.getSpPid()) && (((getSpPid() == null)
						|| (castOther.getSpPid() == null) || (!(getSpPid()
							.equals(castOther.getSpPid()))))))
				|| ((getPName() != castOther.getPName()) && (((getPName() == null)
						|| (castOther.getPName() == null) || (!(getPName()
							.equals(castOther.getPName()))))))
				|| ((getPDesc() != castOther.getPDesc()) && (((getPDesc() == null)
						|| (castOther.getPDesc() == null) || (!(getPDesc()
							.equals(castOther.getPDesc()))))))
				|| ((getPLuckyNum() != castOther.getPLuckyNum()) && (((getPLuckyNum() == null)
						|| (castOther.getPLuckyNum() == null) || (!(getPLuckyNum()
							.equals(castOther.getPLuckyNum()))))))
				|| ((getPImg() != castOther.getPImg()) && (((getPImg() == null)
						|| (castOther.getPImg() == null) || (!(getPImg()
							.equals(castOther.getPImg()))))))
				|| ((getIssue() != castOther.getIssue()) && (((getIssue() == null)
						|| (castOther.getIssue() == null) || (!(getIssue()
							.equals(castOther.getIssue()))))))
				|| ((getTag() != castOther.getTag()) && (((getTag() == null)
						|| (castOther.getTag() == null) || (!(getTag()
							.equals(castOther.getTag()))))))
				|| ((getOrd() != castOther.getOrd()) && (((getOrd() == null)
						|| (castOther.getOrd() == null) || (!(getOrd()
							.equals(castOther.getOrd()))))))
				|| ((getButtonDesc() != castOther.getButtonDesc()) && (((getButtonDesc() == null)
						|| (castOther.getButtonDesc() == null) || (!(getButtonDesc()
							.equals(castOther.getButtonDesc()))))))
				|| ((getIphoneUrl() != castOther.getIphoneUrl()) && (((getIphoneUrl() == null)
						|| (castOther.getIphoneUrl() == null) || (!(getIphoneUrl()
							.equals(castOther.getIphoneUrl()))))))
				|| ((getAnzhuoUrl() != castOther.getAnzhuoUrl()) && (((getAnzhuoUrl() == null)
						|| (castOther.getAnzhuoUrl() == null) || (!(getAnzhuoUrl()
							.equals(castOther.getAnzhuoUrl()))))))
				|| ((getUrlTag() != castOther.getUrlTag()) && (((getUrlTag() == null)
						|| (castOther.getUrlTag() == null) || (!(getUrlTag()
							.equals(castOther.getUrlTag()))))))
				|| ((getType() != castOther.getType()) && (((getType() == null)
						|| (castOther.getType() == null) || (!(getType()
							.equals(castOther.getType()))))))
				|| ((getPIntegralNum() != castOther.getPIntegralNum()) && (((getPIntegralNum() == null)
						|| (castOther.getPIntegralNum() == null) || (!(getPIntegralNum()
							.equals(castOther.getPIntegralNum()))))))
				|| ((getUrl() != castOther.getUrl()) && (((getUrl() == null)
						|| (castOther.getUrl() == null) || (!(getUrl()
							.equals(castOther.getUrl()))))))
				|| ((getDescTag() != castOther.getDescTag()) && (((getDescTag() == null)
						|| (castOther.getDescTag() == null) || (!(getDescTag()
							.equals(castOther.getDescTag())))))) || ((getTag2g() != castOther
				.getTag2g()) && (((getTag2g() == null)
				|| (castOther.getTag2g() == null) || (!(getTag2g()
					.equals(castOther.getTag2g())))))));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result + ((getPId() == null) ? 0 : getPId().hashCode());
		result = 37 * result
				+ ((getSpPid() == null) ? 0 : getSpPid().hashCode());
		result = 37 * result
				+ ((getPName() == null) ? 0 : getPName().hashCode());
		result = 37 * result
				+ ((getPDesc() == null) ? 0 : getPDesc().hashCode());
		result = 37 * result
				+ ((getPLuckyNum() == null) ? 0 : getPLuckyNum().hashCode());
		result = 37 * result + ((getPImg() == null) ? 0 : getPImg().hashCode());
		result = 37 * result
				+ ((getIssue() == null) ? 0 : getIssue().hashCode());
		result = 37 * result + ((getTag() == null) ? 0 : getTag().hashCode());
		result = 37 * result + ((getOrd() == null) ? 0 : getOrd().hashCode());
		result = 37 * result
				+ ((getButtonDesc() == null) ? 0 : getButtonDesc().hashCode());
		result = 37 * result
				+ ((getIphoneUrl() == null) ? 0 : getIphoneUrl().hashCode());
		result = 37 * result
				+ ((getAnzhuoUrl() == null) ? 0 : getAnzhuoUrl().hashCode());
		result = 37 * result
				+ ((getUrlTag() == null) ? 0 : getUrlTag().hashCode());
		result = 37 * result + ((getType() == null) ? 0 : getType().hashCode());
		result = 37
				* result
				+ ((getPIntegralNum() == null) ? 0 : getPIntegralNum()
						.hashCode());
		result = 37 * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
		result = 37 * result
				+ ((getDescTag() == null) ? 0 : getDescTag().hashCode());
		result = 37 * result
				+ ((getTag2g() == null) ? 0 : getTag2g().hashCode());
		return result;
	}
}