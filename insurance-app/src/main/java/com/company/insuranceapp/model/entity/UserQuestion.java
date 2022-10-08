package com.company.insuranceapp.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "user_questions")
public class UserQuestion {
    @Id
    @Column(name = "user_question_id",
            insertable = false
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_user_question"
    )
    @SequenceGenerator(
            name = "seq_user_question",
            sequenceName = "seq_user_question",
            allocationSize = 1
    )
    private Long id;

    @NonNull
    @JoinColumn(referencedColumnName = "id", name = "id_user")
    @ManyToOne
    private User user;

    @NonNull
    @JoinColumn(referencedColumnName = "question_id", name = "id_question")
    @ManyToOne
    private Question question;

    @NonNull
    private Boolean point;
}
