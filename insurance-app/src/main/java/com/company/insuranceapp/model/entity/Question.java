package com.company.insuranceapp.model.entity;

import lombok.*;

import javax.persistence.*;

import static com.company.insuranceapp.model.entity.User.TABLE_NAME;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = TABLE_NAME)
public class Question {
    public static final String TABLE_NAME = "questions";

    @Id
    @Column(
            name = "id",
            insertable = false
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_question"
    )
    @SequenceGenerator(
            name = "seq_question",
            sequenceName = "seq_question",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "question", nullable = false)
    private String question;

    @JoinColumn(referencedColumnName = "id", name = "id")
    @OneToOne
    private Answer rightAnswer;
}
