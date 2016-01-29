package ua.com.jon.quiz.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Reshetnyak Viktor on 19.01.2016.
 * Package quiz.domain
 */
@Entity
@Table(name = "QUESTION")
public class Question {
    @Id
    @SequenceGenerator(name = "sequence_question", sequenceName = "SEQ_QUESTION",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequence_question")
    @Column(name = "ID")
    private Long id;

    @Column (name = "NAME", length = 512)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column (name = "QUESTION_TYPE")
    private QuestionType questionType;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "question")
    private Set<Answer> answerList;

    public static enum Type {
        SINGLE_CHOICE, MULIT_CHOICE
    }

    public Question() {
    }

    public Question(Quiz quiz, Type quiestionType, String name) {
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

    public QuestionType getQuiestionType() {
        return questionType;
    }

    public void setQuiestionType(QuestionType quiestionType) {
        this.questionType = quiestionType;
    }
}
