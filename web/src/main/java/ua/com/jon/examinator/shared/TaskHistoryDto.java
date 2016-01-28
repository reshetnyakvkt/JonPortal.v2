package ua.com.jon.examinator.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 13.06.14
 */
public class TaskHistoryDto implements Serializable {
    private Long id;
    private String code;
    private String templateName;
    private String userName;
    private Date date;
    private String result;
    private String hash;

    public TaskHistoryDto() {
    }

    public TaskHistoryDto(Long id, String code, String templateName, String userName, Date date, String result, String hash) {
        this.id = id;
        this.code = code;
        this.templateName = templateName;
        this.userName = userName;
        this.date = date;
        this.result = result;
        this.hash = hash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "TaskHistoryDto{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", templateName='" + templateName + '\'' +
                ", userName='" + userName + '\'' +
                ", date=" + date +
                ", result='" + result + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
