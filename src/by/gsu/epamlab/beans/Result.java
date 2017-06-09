package by.gsu.epamlab.beans;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Home on 07.06.2017.
 */
public class Result {
    private String login;
    private String test;
    private Date date;
    private Mark mark;
    private final static SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public Result() {
    }

    public Result(String login, String test, String date, Mark mark){
        this.login = login;
        this.test = test;
        setDate(date);
        this.mark = mark;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public void setDate(String date) {
        try {
            this.date = new Date(INPUT_DATE_FORMAT.parse(date).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public void setMark(String mark) {
        this.mark = new Mark(mark);
    }

    public void setMark(int mark){
        this.mark = new Mark(mark);
    }

    public String getStringDate() {
        return OUTPUT_DATE_FORMAT.format(date);
    }


    @Override
    public String toString() {
        return login + ";" + test + ";" + getStringDate() + ";" + mark.getStringMark() ;
    }
}
