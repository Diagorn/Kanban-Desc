package com.rosatom.kanban.utils;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class ServiceUtils {
    public static String getCalendarAsString(GregorianCalendar calendar) {
        String result = "";
        switch (calendar.get(GregorianCalendar.DAY_OF_WEEK)) {
            case 1:
                result += "Воскресенье, ";
                break;
            case 2:
                result += "Понедельник, ";
                break;
            case 3:
                result += "Вторник, ";
                break;
            case 4:
                result += "Среда, ";
                break;
            case 5:
                result += "Четверг, ";
                break;
            case 6:
                result += "Пятница, ";
                break;
            case 7:
                result += "Суббота, ";
                break;
            default:
                result += "ДЕНЬ_НЕДЕЛИ, ";
        }
        result += calendar.get(GregorianCalendar.DAY_OF_MONTH);
        switch (calendar.get(GregorianCalendar.MONTH)) {
            case 0:
                result += " января, ";
                break;
            case 1:
                result += " февраля, ";
                break;
            case 2:
                result += " марта, ";
                break;
            case 3:
                result += " апреля, ";
                break;
            case 4:
                result += " мая, ";
                break;
            case 5:
                result += " июня, ";
                break;
            case 6:
                result += " июля, ";
                break;
            case 7:
                result += " августа, ";
                break;
            case 8:
                result += " сентября, ";
                break;
            case 9:
                result += " октября, ";
                break;
            case 10:
                result += " ноября, ";
                break;
            case 11:
                result += " декабря, ";
                break;
            default:
                result += " МЕСЯЦ, ";
        }
        result += calendar.get(GregorianCalendar.HOUR_OF_DAY);
        result += ":";
        if (calendar.get(GregorianCalendar.MINUTE) < 10) {
            result += "0";
        }
        result += calendar.get(GregorianCalendar.MINUTE);
        return result;
    }

    public static GregorianCalendar parseStringToCalendar(String date) {
        GregorianCalendar result = new GregorianCalendar();
        String[] resultStrings = date.split("-");
        result.set(Calendar.YEAR, Integer.parseInt(resultStrings[0]));
        result.set(Calendar.MONTH, Integer.parseInt(resultStrings[1])-1);
        result.set(Calendar.DAY_OF_MONTH, Integer.parseInt(resultStrings[2]));
        return result;
    }
}
