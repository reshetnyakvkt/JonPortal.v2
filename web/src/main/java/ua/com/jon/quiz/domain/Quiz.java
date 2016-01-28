package ua.com.jon.quiz.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Олег on 15.01.2016.
 */
@Entity
@Table(name = "QUIZES")
public class Quiz {

    @Id
    @GeneratedValue(generator = "quizGen", strategy = GenerationType.TABLE)
    @TableGenerator(name = "quizGen", initialValue = 101, allocationSize = 1)
    @Column(name = "QUIZ_ID")
    private Long id;

    @Column(name = "DESCTIPTION")
    private String description;

    @Column(name = "TIME_LIMIT")
    private Long timeLimit;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "QUIZES_QUESTIONS", joinColumns = @JoinColumn(name = "QUIZ_ID"),
            inverseJoinColumns = @JoinColumn(name = "QUESTION_ID"))
    private Set<Question> questions = new LinkedHashSet<>();


    public Quiz() {
    }

    public Quiz(String description, Long timeLimit, Set<Question> questions) {
        this.description = description;
        this.timeLimit = timeLimit;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }



}
