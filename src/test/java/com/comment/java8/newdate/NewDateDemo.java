package com.comment.java8.newdate;


import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * Created by ning.wang on 2016/3/1.
 */
public class NewDateDemo {
    @Test
    public void clockTest() {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        Instant now = Instant.now(clock);
        java.util.Date from = Date.from(now);
        Date date = new Date(millis);
        int i = from.compareTo(date);
        System.out.println("i = " + i);
        String dateStr = date.toLocalDate().toString();
        System.out.println("dateStr = " + dateStr);
    }

    @Test
    public void TimezoneTest(){
        System.out.println(ZoneId.getAvailableZoneIds());
        System.out.println(ZoneId.systemDefault().getRules());
    }

    @Test
    public  void LocalTimeTest(){
        LocalTime now = LocalTime.now();
        LocalTime now1 = LocalTime.now(ZoneId.of("Europe/Berlin"));
        System.out.println("----"+now1.isBefore(now));
        long between = ChronoUnit.HOURS.between(now1, now);
        long between1 = ChronoUnit.MILLIS.between(now, now1);
        System.out.println(between);
        System.out.println(between1);
        LocalTime time = LocalTime.of(23, 59, 59);
        System.out.println("time = " + time);

        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(Locale.GERMAN);
        LocalTime parse = LocalTime.parse("13:37", formatter);
        System.out.println("parse = " + parse);
    }
    @Test
    public  void LocalDateTest(){
        LocalDate now = LocalDate.now();
        LocalDate tomorrow = now.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);
        LocalDate date = LocalDate.of(2016, 7, 4);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)
                .withLocale(Locale.GERMAN);
        LocalDate localDate = LocalDate.parse("24.12.2016", formatter);
        System.out.println("localDate = " + localDate);
        System.out.println("formatter = " + formatter);
    }
    @Test
    public void localDateTime(){
//        printlnJson(cityBiz.getCityIdList());
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println(dayOfWeek);
        Month month = now.getMonth();
        System.out.println("month = " + month);
        long aLong = now.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println("aLong = " + aLong);

        Instant instant = now.atZone(ZoneId.of("Europe/Berlin"))
                .toInstant();
        java.util.Date from = Date.from(instant);
        System.out.println("from = " + from);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd,yyyy - HH:mm");
        LocalDateTime parse = LocalDateTime.parse("03 03,2015 - 07:13", formatter);
        System.out.println(parse);
    }
    @Test
    public void testJS()throws Throwable{
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine script = manager.getEngineByName("JavaScript");

        System.out.println("script = " + script.getClass().getName());
        System.out.println("re:"+script.eval("function f() {return 1;}; f()+1;"));


    }
}
