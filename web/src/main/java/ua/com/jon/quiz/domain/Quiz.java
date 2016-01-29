package ua.com.jon.quiz.domain;

import ua.com.jon.common.domain.Sprint;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Олег on 15.01.2016.
 */
@Entity
@Table(name = "QUIZ")
public class Quiz {

    @Id
    @GeneratedValue(generator = "quizGen", strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "quizGen", initialValue = 101, allocationSize = 1)
    @Column(name = "QUIZ_ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TIME_LIMIT")
    private Long timeLimit;

    @OneToOne(fetch = FetchType.EAGER)
    private Sprint sprint;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "quiz", orphanRemoval = true)
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

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
