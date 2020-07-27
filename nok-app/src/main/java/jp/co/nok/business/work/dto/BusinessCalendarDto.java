package jp.co.nok.business.work.dto;

import java.math.BigDecimal;

/**
 * カレンダーのbeanクラス
 *
 * @version 1.0.0
 */
public class BusinessCalendarDto {

    /** 日にち */
    private BigDecimal day;
    /** 曜日 */
    private String weekDay;

    public BigDecimal getDay() {
        return day;
    }

    public void setDay(BigDecimal day) {
        this.day = day;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

}
