package hospital.utilities;

import hospital.enity.Yuyue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class YyHelper {
    public static Boolean isSuitable(List<Yuyue> list,String date,String time) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date1 = dateFormat.parse(date);
        Date time1 = timeFormat.parse(time);
        for(Yuyue y:list){
            if (y.getYdate().equals(date)){
                //判断时间是否冲突
                Date date2 = timeFormat.parse(y.getYtime());
                Date date3 = new Date();
                date3.setTime(date2.getTime()+10*60*1000);
                if((time1.after(date2)&&time1.before(date3))){
                    return false;
                }else if(time1.getTime()==date2.getTime()){
                    return false;
                }
            }
        }
        return true;
    }

    //建议预约时间
    public static String YyAdvise(List<Yuyue> list,String date) throws ParseException {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String msg1 = "与当天以下时间已被预约发生冲突:";
        String msg2 = "当天建议预约时间:";
        for(Yuyue y:list) {
            if (y.getYdate().equals(date)) {
                Date d = timeFormat.parse(y.getYtime());
                d.setTime(d.getTime()+10*60*1000);
                msg1 += y.getYtime()+";";
                //判断建议是否冲突
                for(Yuyue yy:list) {
                    Date d1 = timeFormat.parse(yy.getYtime());
                    Date d2 = new Date();
                    d2.setTime(d1.getTime()+10*60*1000);
                    if(d.after(d1)&&d.before(d2)){
                        break;
                    }else if(d.getTime()==d1.getTime()){
                        break;
                    }
                }
                msg2 += timeFormat.format(d)+";";
            }
        }
        return msg1+"\n"+msg2;
    }
}
