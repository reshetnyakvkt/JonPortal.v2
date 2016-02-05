package ua.com.jon.quiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Reshetnyak Viktor on 19.01.2016.
 * Package quiz.domain
 */
@Entity
@Table(name = "ANSWERS")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ANSWER_ID")
    private Long id;

    @Column(name = "NAME", length = 512)
    private String name;

    @Column(name = "CORRECT")
    private Boolean isCorrect;

    @Column(name = "CREATE_DATE", length = 15)
    private Date createDate;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    public Answer() {
        this.createDate = Calendar.getInstance().getTime();
    }

    public Answer(Question question, String name, Boolean isCorrect) {
        this();
        this.question = question;
        this.name = name;
        this.isCorrect = isCorrect;
    }

    public Answer(String name, Boolean isCorrect) {
        this();
        this.name = name;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
