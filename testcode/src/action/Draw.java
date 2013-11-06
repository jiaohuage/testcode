package action;

import hbm.RwdState;
import hbm.UiLuckyCodeRwd;
import hbm.UiLuckyCodeRwdId;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Draw
{
	private static Font mFont = new Font("Microsoft YaHei", 0, 14);

	public static void draw2(String phone, boolean b)
	{
		double h;
		File outPictrue = new File("/opt/app/code/img/fxImg/" + phone + ".png");
		List uiLuckyCodeRwdList = parseString((String) Mem.getCurrentIssueMap()
				.get(phone));
		int lineLength = 24;
		int lineNum = 1;
		BufferedImage image = null;
		int k = 0;
		List list = new ArrayList();
		List list2 = new ArrayList();
		UiLuckyCodeRwd u = new UiLuckyCodeRwd();
		String str = "";
		String str1 = "";
		String str2 = "";

		String st1 = ((RwdState) Mem.getRwdStateList().get(0)).getIssue()
				+ "期幸运密码";
		String st2 = (Integer
				.parseInt(((RwdState) Mem.getRwdStateList().get(0)).getIssue()) + 1)
				+ "期幸运密码";
		list.add(st1);
		list2.add(st2);
		int m = 0;
		int n = 0;
		if ((uiLuckyCodeRwdList != null) && (uiLuckyCodeRwdList.size() > 0))
		{
			for (int i = 0; i < uiLuckyCodeRwdList.size(); ++i)
			{
				u = (UiLuckyCodeRwd) uiLuckyCodeRwdList.get(i);

				if ((u.getId().getTag().equals("0"))
						|| (u.getId().getTag() == "0"))
					str1 = "流量赠送";
				else if ((u.getId().getTag().equals("1"))
						|| (u.getId().getTag() == "1"))
					str1 = "下载任务";
				else if ((u.getId().getTag().equals("2"))
						|| (u.getId().getTag() == "2"))
					str1 = "vac订购";
				else if ((u.getId().getTag().equals("3"))
						|| (u.getId().getTag() == "3"))
					str1 = "炫铃订购";
				else if ((u.getId().getTag().equals("4"))
						|| (u.getId().getTag() == "4"))
					str1 = "种子短信";
				else if ((u.getId().getTag().equals("5"))
						|| (u.getId().getTag() == "5"))
					str1 = "积分赠送";
				else if ((u.getId().getTag().equals("6"))
						|| (u.getId().getTag() == "6"))
					str1 = "接受短信";
				else if ((u.getId().getTag().equals("7"))
						|| (u.getId().getTag() == "7"))
					str1 = "每期签到";
				else if ((u.getId().getTag().equals("8"))
						|| (u.getId().getTag() == "8"))
					str1 = "达量分配";
				else if ((u.getId().getTag().equals("9"))
						|| (u.getId().getTag() == "9"))
					str1 = "微博分享";
				else if ((u.getId().getTag().equals("10"))
						|| (u.getId().getTag() == "10"))
				{
					str1 = "流量有奖";
				}

				if ((((RwdState) Mem.getRwdStateList().get(0)).getTagState()
						.equals("0"))
						|| (((RwdState) Mem.getRwdStateList().get(0))
								.getTagState() == "0"))
				{
					str2 = "未开奖";
				}
				else if ((u.getId().getWinTag().equals("0"))
						|| (u.getId().getWinTag() == "0"))
					str2 = "未中奖";
				else if ((u.getId().getWinTag().equals("1"))
						|| (u.getId().getWinTag() == "1"))
					str2 = "一等奖";
				else if ((u.getId().getWinTag().equals("2"))
						|| (u.getId().getWinTag() == "2"))
					str2 = "二等奖";
				else if ((u.getId().getWinTag().equals("3"))
						|| (u.getId().getWinTag() == "3"))
				{
					str2 = "三等奖";
				}

				if (((RwdState) Mem.getRwdStateList().get(0)).getIssue()
						.equals(u.getId().getIssue()))
				{
					str = u.getId().getRandom() + "    " + str2 + "    " + str1;

					list.add(str);
					++m;
				}
				else
				{
					str = u.getId().getRandom() + "    " + "未开奖" + "    "
							+ str1;

					list2.add(str);
					++n;
				}
			}
			h = uiLuckyCodeRwdList.size() * 240 / 12 + 250;
		}
		else
		{
			h = 250.0D;
		}
		double w = 9.0D;
		int width = (int) (w * lineLength) + 120;
		int height = (int) (h * lineNum);
		image = new BufferedImage(width, height, 1);
		Graphics g = image.getGraphics();
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, width - 1, height - 1);
		if (!(b))
		{
			g.setColor(new Color(102, 102, 102));
		}
		g.setFont(mFont);
		for (int i = 0; i <= 4; ++i)
		{
			g.drawString("", 30, 15 + 20 * k);
			++k;
		}
		g.drawString("活动地址 http://33.vrmedia.com.cn", 30, 15 + 20 * k);
		++k;
		g.drawString("", 30, 15 + 20 * k);
		++k;
		Iterator it = list.iterator();
		while (it.hasNext())
		{
			g.drawString((String) it.next(), 30, 15 + 20 * k);
			++k;
		}
		if (m == 0)
		{
			g.drawString("还没有幸运密码", 30, 15 + 20 * k);
			++k;
		}

		Iterator it2 = list2.iterator();
		while (it2.hasNext())
		{
			g.drawString((String) it2.next(), 30, 15 + 20 * k);
			++k;
		}
		if (n == 0)
		{
			g.drawString("还没有幸运密码", 30, 15 + 20 * k);
			++k;
		}
		try
		{
			ImageIO.write(image, "png", outPictrue);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		String srcImgPath = "/opt/app/code/img/fxImg/" + phone + ".png";
		String iconPath = "/opt/app/code/img/logo.png";
		String targerPath = "/opt/app/code/img/fxImg/" + phone + ".png";
		markImageByIcon(iconPath, srcImgPath, targerPath, null);
	}

	public static void markImageByIcon(String iconPath, String srcImgPath,
			String targerPath, Integer degree)
	{
		OutputStream os = null;
		try
		{
			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), 1);

			Graphics2D g = buffImg.createGraphics();

			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(
					srcImg.getScaledInstance(srcImg.getWidth(null),
							srcImg.getHeight(null), 4), 0, 0, null);

			ImageIcon imgIcon = new ImageIcon(iconPath);

			Image img = imgIcon.getImage();
			float alpha = 1.0F;
			g.setComposite(AlphaComposite.getInstance(10, alpha));

			g.drawImage(img, 0, 0, null);
			g.setComposite(AlphaComposite.getInstance(3));
			g.dispose();
			os = new FileOutputStream(targerPath);

			ImageIO.write(buffImg, "JPG", os);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (os != null)
					os.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public static int format1(String s)
	{
		int length = 0;
		for (int t = 0; t < s.length(); ++t)
		{
			if (s.charAt(t) > 255)
				length += 2;
			else
			{
				++length;
			}
		}
		return length;
	}

	public static List<UiLuckyCodeRwd> parseString(String mapValue)
	{
		List randomList = new ArrayList();

		if ((mapValue == null) || (mapValue.length() < 1))
		{
			return null;
		}
		String[] issues = decode(mapValue, ",");
		String[] randoms = (String[]) null;
		if ((issues != null) && (issues.length > 0))
		{
			for (int i = 0; i < issues.length; ++i)
			{
				UiLuckyCodeRwdId urdId = new UiLuckyCodeRwdId();
				UiLuckyCodeRwd urd = new UiLuckyCodeRwd();
				randoms = decode(issues[i], ":");
				if ((randoms == null) || (randoms.length <= 0))
					continue;
				urdId.setRandom(randoms[0]);
				urdId.setWinTag(randoms[1]);
				urdId.setIssue(randoms[2]);
				urdId.setTag(randoms[3]);
				urd.setId(urdId);
				randomList.add(urd);
			}
		}

		return randomList;
	}

	public static String[] decode(String s_source, String decch)
	{
		String[] asTemp = (String[]) null;
		if (s_source.length() != 0)
		{
			StringTokenizer st = new StringTokenizer(s_source, decch);
			int iSize = st.countTokens();
			asTemp = new String[iSize];
			for (int i = 0; st.hasMoreTokens(); ++i)
			{
				asTemp[i] = st.nextToken();
			}
		}
		return asTemp;
	}
}