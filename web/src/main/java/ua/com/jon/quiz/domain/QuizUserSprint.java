package ua.com.jon.quiz.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Юыху on 15.01.2016.
 */
@Entity
@Table(name = "QUIZ_USER_SPRINT")
public class QuizUserSprint {

    @Id
    @GeneratedValue(generator = "quizGen", strategy = GenerationType.TABLE)
    @TableGenerator(name = "quizGen", initialValue = 101, allocationSize = 1)
    @Column(name = "QUIZ_ID")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "QUIZES_QUESTIONS", joinColumns = @JoinColumn(name = "QUIZ_ID"),
            inverseJoinColumns = @JoinColumn(name = "QUESTION_ID"))
    private Set<Question> questions = new LinkedHashSet<>();

    public QuizUserSprint() {
    }
}
