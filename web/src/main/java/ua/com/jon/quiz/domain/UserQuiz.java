package ua.com.jon.quiz.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ua.com.jon.common.domain.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Олег on 16.01.2016.
 */
@Entity
@Table(name = "USERS_QUIZZES")
public class UserQuiz {
    @Id
    @GeneratedValue(generator = "userQuizGen", strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "userQuizGen", initialValue = 101, allocationSize = 1)
    @Column(name = "USER_QUIZ_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUIZ_ID")
    private Quiz quiz;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "SCORE")
    private Integer score;

    public UserQuiz() {
    }

    public UserQuiz(User user, Quiz quiz, Date date, Integer score) {
        this.user = user;
        this.quiz = quiz;
        this.date = date;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
