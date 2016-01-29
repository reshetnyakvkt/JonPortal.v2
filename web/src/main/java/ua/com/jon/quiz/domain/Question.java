package ua.com.jon.quiz.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Reshetnyak Viktor on 19.01.2016.
 * Package quiz.domain
 */
@Entity
@Table(name = "QUESTIONS")
public class Question {
    @Id
 //   @TableGenerator(name = "questGen", initialValue = 101, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO/*, generator = "questGen"*/)
    @Column(name = "QUESTION_ID")
    private Long id;

    @Column (name = "NAME", length = 512)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column (name = "QUESTION_TYPE")
    private Type questionType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "QUIZ_ID")
    private Quiz quiz;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "question")
    private Set<Answer> answerList;

    public static enum Type {
        SINGLE_CHOICE, MULIT_CHOICE
    }

    public Question() {
    }

    public Question(Type questionType, String name, Set<Answer> answerList) {
        this.name = name;
        this.questionType = questionType;
        this.answerList = answerList;
    }

    public Question(Quiz quiz, Type questionType, String name) {
        this.quiz = quiz;
        this.questionType = questionType;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getForm() {
        return quiz;
    }

    public void setForm(Quiz quiz) {
        this.quiz = quiz;
    }

    public Set<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(Set<Answer> answerList) {
        this.answerList = answerList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getQuiestionType() {
        return questionType;
    }

    public void setQuiestionType(Type quiestionType) {
        this.questionType = quiestionType;
    }
}
