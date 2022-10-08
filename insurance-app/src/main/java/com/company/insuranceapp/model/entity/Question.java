package com.company.insuranceapp.model.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "questions")
public class Question {
    @Id
    @Column(
            name = "question_id",
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

    @JoinColumn(referencedColumnName = "answer_id", name = "id_answer")
    @OneToOne
    private Answer rightAnswer;

    @JoinColumn(referencedColumnName = "course_id", name = "id_course")
    @ManyToOne
    private Course course;
}
