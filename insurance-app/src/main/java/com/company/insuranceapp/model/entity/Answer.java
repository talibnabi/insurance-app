package com.company.insuranceapp.model.entity;

import lombok.Data;

import javax.persistence.*;

import static com.company.insuranceapp.model.entity.Answer.TABLE_NAME;

@Table
@Entity(name = TABLE_NAME)
@Data
public class Answer {
    public static final String TABLE_NAME = "answers";

    @Id
    @Column(
            name = "id",
            insertable = false
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_answer"
    )
    @SequenceGenerator(
            name = "seq_answer",
            sequenceName = "seq_answer",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "answer", nullable = false)
    private String answer;

    @JoinColumn(referencedColumnName = "id", name = "id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Question question;
}
