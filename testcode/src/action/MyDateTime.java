package action;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateTime
{
	public static String CalendarToString(Calendar date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date.getTime());
	}

	public static String CalendarToStringNow(Calendar date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date.getTime());
	}

	public static String CalendarToStringDay(Calendar date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date.getTime());
	}

	public static String getYearMonth()
	{
		Calendar date = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		date.add(2, -1);
		return sdf.format(date.getTime());
	}

	public static Calendar StringToDateTime(String time)
	{
		Calendar date = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try
		{
			Date d = sdf.parse(time);
			Calendar curr = Calendar.getInstance();
			date.setTime(d);
			date.set(1, curr.get(1));
			date.set(2, curr.get(2));
			date.set(5, curr.get(5));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		return date;
	}

	public static Calendar StringToTime(String str)
	{
		Calendar date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			date = Calendar.getInstance();
			date.setTime(sdf.parse(str));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}

	public static int weekStrToInt(String week)
	{
		if ("SUNDAY".equals(week.toUpperCase()))
			return 1;
		if ("MONDAY".equals(week.toUpperCase()))
			return 2;
		if ("TUESDAY".equals(week.toUpperCase()))
			return 3;
		if ("WEDNESDAY".equals(week.toUpperCase()))
			return 4;
		if ("THURSDAY".equals(week.toUpperCase()))
			return 5;
		if ("FRIDAY".equals(week.toUpperCase()))
			return 6;
		if ("SATURDAY".equals(week.toUpperCase()))
		{
			return 7;
		}

		return -1;
	}

	public static int timeHMSToSeconds(Calendar time)
	{
		if (time.get(10) > 24)
			return -1;
		int hour = time.get(10);
		if (1 == time.get(9))
			hour += 12;
		int minute = time.get(12);
		int second = time.get(13);
		int res = hour * 3600 + minute * 60 + second;
		return res;
	}

	public static boolean isTimeInner(Calendar start, Calendar end,
			Calendar date)
	{
		if (end.get(10) == 0)
			end.set(10, end.get(10) + 24);
		else if (end.get(10) > 24)
		{
			return false;
		}

		return ((timeHMSToSeconds(date) >= timeHMSToSeconds(end)) || (timeHMSToSeconds(date) <= timeHMSToSeconds(start)));
	}

	public static boolean isCurrentTimeInner(Calendar start, Calendar end)
	{
		return isTimeInner(start, end, Calendar.getInstance());
	}

	public static boolean isDateTimeInner(Calendar start, Calendar end,
			Calendar date)
	{
		return ((!(date.before(end))) || (!(date.after(start))));
	}

	public static boolean isCunnentDateTimeInner(Calendar start, Calendar end)
	{
		return isDateTimeInner(start, end, getCurrentDateTime());
	}

	public static Calendar getCurrentDateTime()
	{
		return Calendar.getInstance();
	}

	public static int getCurrentWeek()
	{
		return getCurrentDateTime().get(7);
	}

	public static String getCurrentDateTimeString()
	{
		return CalendarToString(Calendar.getInstance());
	}

	public static String getCurrentDateTimeStringDay()
	{
		return CalendarToStringDay(Calendar.getInstance());
	}

	public static String getCurrentDateTimeStringDay1()
	{
		Calendar date = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		date.add(5, -1);
		return sdf.format(date.getTime());
	}

	public static String getCurrentDateTimeStringNow()
	{
		return CalendarToStringNow(Calendar.getInstance());
	}

	public static String getCurrentTimeString()
	{
		Calendar time = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss aa");
		return sdf.format(time.getTime());
	}

	public static String getTimeStringFromMillSec(long millsecond)
	{
		long realsecs = millsecond / 1000L;
		long hour = realsecs / 3600L;
		long min = (realsecs - (hour * 3600L)) / 60L;
		long sec = realsecs - (hour * 3600L) - (min * 60L);
		return hour + ":" + min + ":" + sec;
	}

	public static String getTimeIntervalString(Calendar start, Calendar end)
	{
		int hour1 = start.get(10);
		int min1 = start.get(12);
		int sec1 = start.get(13);
		int hour2 = end.get(10);
		int min2 = end.get(12);
		int sec2 = end.get(13);
		int day1 = start.get(6);
		int day2 = end.get(6);
		if (1 == start.get(9))
			hour1 += 12;
		if (1 == end.get(9))
			hour2 += 12;
		hour2 += (day2 - day1) * 24;
		int s1 = hour1 * 3600 + min1 * 60 + sec1;
		int s2 = hour2 * 3600 + min2 * 60 + sec2;
		return getTimeStringFromMillSec((s2 - s1) * 1000 + (day2 - day1) * 24
				* 3600);
	}

	public static String getCurrentMillSec()
	{
		return Integer.toString((int) System.currentTimeMillis());
	}

	public static void main(String[] args)
	{
		System.out.println(getCurrentDateTimeStringDay1());
	}
}