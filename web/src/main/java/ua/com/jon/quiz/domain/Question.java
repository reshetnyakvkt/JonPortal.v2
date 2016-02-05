package ua.com.jon.quiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Set<Answer> answerList = new HashSet<>();

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

    public Question(String name, Type questionType, Quiz quiz, Set<Answer> answerList) {
        this.name = name;
        this.questionType = questionType;
        this.quiz = quiz;
        this.answerList = answerList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
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

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
