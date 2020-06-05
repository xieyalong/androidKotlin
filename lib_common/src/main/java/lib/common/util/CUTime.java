package lib.common.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * 时间管理 日期管理
 */

public class CUTime {
    private  static CUTime instance;
    public static CUTime i(){
        if (null==instance){
            instance=new CUTime();
        }
        return  instance;
    }
    /**
     * time1==time1=0
     * time1<time2=-1
     * time1>time2=1
     * @param time1
     * @param time2
     * @return
     */
    public   int contrastTime(String time1,String time2){
        int i=0;
        String s1=time1;
        String s2=time2;
        java.text.DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1= Calendar.getInstance();
        Calendar c2= Calendar.getInstance();
        try {
            c1.setTime(df.parse(s1));
            c2.setTime(df.parse(s2));
        }catch(ParseException e){
            System.err.println("格式不正确");
        }
        int result=c1.compareTo(c2);
        if(result==0){
            System.out.println("c1相等c2");
            i=0;
        }else if(result<0){
            System.out.println("c1小于c2");
            i=-1;
        }else{
            i=1;
            System.out.println("c1大于c2");
        }
        return  i;
    }
    /**
     *20180911104718
     * @return
     */
    public   String  getTime_yyyyMMddHHmmss(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String nowDate = df.format(new Date());// new Date()为获取当前系统时间
        return  nowDate;
    }
    /**
     *20180911
     * @return
     */
    public   String  getTime_yyyyMMdd(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String nowDate = df.format(new Date());// new Date()为获取当前系统时间
        return  nowDate;
    }
    public   String  getTime_yyyy_MM_dd(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String nowDate = df.format(new Date());// new Date()为获取当前系统时间
        return  nowDate;
    }
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     *20180911104718
     * @return
     */
    public   String  getyyyyMMddHHmmss(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String nowDate = df.format(new Date());// new Date()为获取当前系统时间
        return  nowDate;
    }
    public   String  getyyyyMMdd_HHmmss(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String nowDate = df.format(new Date());// new Date()为获取当前系统时间
        return  nowDate;
    }
    public static String getyyyyMMdd_HHmm(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String dateString = formatter.format(date);
//        LogUtils.showe("===格式化时间=="+dateString);
        return dateString;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s)  {
        if (!TextUtils.isEmpty(s)){
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = simpleDateFormat.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long ts = date.getTime();
            res = String.valueOf(ts);
            return res;
        }
        return "";
    }

    public static String dateToStamp2(String s)  {
        if (!TextUtils.isEmpty(s)){
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            Date date = null;
            try {
                date = simpleDateFormat.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long ts = date.getTime();
            res = String.valueOf(ts);
            return res;
        }
        return "";
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDateMonthDayHourMinute(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDateYearMonthDayHourMinute(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    /*
     * 将时间戳转换为年
     */
    public static String stampToDateYear(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    public static String stampToDateMinite(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    public static String getTimeMinute(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    public static String getTimeYear(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }


    public static String stampToMonthDay(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    //获得当前周的周一的日期的时间戳，精确到秒
    public static String getCurrentMonday(){
        Calendar cal = getCalendarInit();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime().getTime()+"";
    }
    //获得当前周的周日的日期的时间戳，精确到秒
    public static String getCurrentSunday(){
        Calendar cal = getCalendarInit();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime().getTime()+"";
    }

    //获得上周的周一的日期的时间戳，精确到秒
    public static String getLastMonday(){
        Calendar cal = getCalendarInit();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return (cal.getTime().getTime()-3600*24*7*1000)/1000+"";
    }

    public static String getLastMonday1000(){
        Calendar cal = getCalendarInit();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return (cal.getTime().getTime()-3600*24*7*1000)+"";
    }


    //获得上周的周日的日期的时间戳，精确到秒
    public static String getLastSunday(){
        Calendar cal = getCalendarInit();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return (cal.getTime().getTime()-3600*24*7*1000)/1000+"";
    }
    public static String getLastSunday1000(){
        Calendar cal = getCalendarInit();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return (cal.getTime().getTime()-3600*24*7*1000)+"";
    }

    //获得上周的周一的日期的时间戳，精确到秒
    public static String getNextMonday(){
        Calendar cal = getCalendarInit();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return (cal.getTime().getTime()+3600*24*7*1000)/1000+"";
    }
    public static String getNextMonday1000(){
        Calendar cal = getCalendarInit();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return (cal.getTime().getTime()+3600*24*7*1000)+"";
    }

    //获得上周的周日的日期的时间戳，精确到秒
    public static String getNextSunday(){
        Calendar cal = getCalendarInit();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return (cal.getTime().getTime()+3600*24*7*1000)/1000+"";
    }
    public static String getNextSunday1000(){
        Calendar cal = getCalendarInit();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return (cal.getTime().getTime()+3600*24*7*1000)+"";
    }

    private static Calendar getCalendarInit() {
        Calendar cal=Calendar.getInstance(Locale.CHINA);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTimeInMillis(System.currentTimeMillis());
        return cal;
    }
    private static final int MIN_DELAY_TIME= 1000;  // 两次点击间隔不能少于1000ms
    private static long lastClickTime;

    public static boolean isFastClick(){
        boolean flag=true;
        long currentClickTime=System.currentTimeMillis();
        if ((currentClickTime-lastClickTime)>= MIN_DELAY_TIME){
            flag=false;
        }
        lastClickTime=currentClickTime;
        return flag;
    }
    /**
     * 获取年份间隔，匹配到日，可以进一步扩展到时分秒
     *
     * @param startDate 开始时间
     * @param endDate   结束事件
     * @return 间隔年份
     */
    public static int getYearSpace(String startDate, String endDate) {
        String dateType = "yyyy-MM-dd";
        Calendar calendar1 =getCalendarFromString(startDate, dateType);
        Calendar calendar2 = getCalendarFromString(endDate, dateType);
        if (calendar1 == null || calendar2 == null) {
            return 0;
        }
        if (calendar1.getTimeInMillis() > calendar2.getTimeInMillis()) {
            Calendar calendar = calendar1;//修正开始时间大于结束时间
            calendar1 = calendar2;
            calendar2 = calendar;
        }
        int year1 = calendar1.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH) + 1;
        int date1 = calendar1.get(Calendar.DATE);
        int year2 = calendar2.get(Calendar.YEAR);
        int month2 = calendar2.get(Calendar.MONTH) + 1;
        int date2 = calendar2.get(Calendar.DATE);
        int years = year2 - year1;
        if (year1 != year2) {
            if (month1 != month2) {
                if (month1 > month2) {
                    years = years - 1;//修正如：2016-8到2017-4    未满一年
                }
            } else {
                if (date1 != date2) {
                    if (date1 > date2) {
                        years = years - 1; //修正如：2016-8-18到2017-8-10  未满一年
                    }
                }
            }
        }
        return years;
    }
    public static Calendar getCalendarFromString(String dateStr, String dateFormatType) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatType);
        Calendar calendar = null;
        try {
            Date date = sdf.parse(dateStr);
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}
