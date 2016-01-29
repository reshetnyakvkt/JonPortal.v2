package ua.com.jon.quiz.domain;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Reshetnyak Viktor on 19.01.2016.
 * Package quiz.domain
 */
@Entity
@Table(name = "USER_QUIZ_ANSWER")
public class UserQuizAnswer {
    @Id
    @SequenceGenerator(name = "sequence_user_quiz_answer", sequenceName = "SEQ_USER_QUIZ_ANSWER",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATE_DATE", length = 15)
    private Date createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserQuiz userQuiz;

    @ManyToOne(fetch = FetchType.EAGER)
    private Answer answer;

    public UserQuizAnswer() {
        this.createDate = Calendar.getInstance().getTime();
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

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public UserQuiz getUserQuiz() {
        return userQuiz;
    }

    public void setUserQuiz(UserQuiz userQuiz) {
        this.userQuiz = userQuiz;
    }
}
