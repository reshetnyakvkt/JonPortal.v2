package ua.com.jon.common.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 10.04.15
 */
public class GraduateResultDTO implements Serializable {
    private String mark;
    private String details;

    public GraduateResultDTO() {
    }

    public GraduateResultDTO(String mark, String details) {
        this.mark = mark;
        this.details = details;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "GraduateResultDTO{" +
                "mark='" + mark + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}