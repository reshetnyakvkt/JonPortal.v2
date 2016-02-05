package ua.com.jon.quiz.domain;

import org.hibernate.annotations.GeneratorType;
import ua.com.jon.common.domain.Sprint;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Олег on 15.01.2016.
 */
@Entity
@Table(name = "QUIZZES")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "QUIZ_ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TIME_LIMIT")
    private Long timeLimit;

    @OneToOne(fetch = FetchType.EAGER)
    private Sprint sprint;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "quiz", orphanRemoval = true)
    private Set<Question> questions = new HashSet<>();

    public Quiz() {
    }

    public Quiz(String description, Long timeLimit) {
        this.description = description;
        this.timeLimit = timeLimit;
    }

    public Quiz(String description, Long timeLimit, Set<Question> questions) {
        this.description = description;
        this.timeLimit = timeLimit;
        this.questions = questions;
    }

    public Quiz(String description, Long timeLimit, Set<Question> questions, Sprint sprint) {
        this.description = description;
        this.timeLimit = timeLimit;
        this.questions = questions;
        this.sprint = sprint;
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

    @Override
    public String toString() {
        return "Quiz{" +
                "timeLimit=" + timeLimit +
                ", id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
