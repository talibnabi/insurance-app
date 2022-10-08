package com.company.insuranceapp.model.entity;

import lombok.*;

import javax.persistence.*;

@Table
@Entity(name = "answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {
    @Id
    @Column(
            name = "answer_id",
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

    @JoinColumn(referencedColumnName = "question_id", name = "id_question")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Question question;


}
