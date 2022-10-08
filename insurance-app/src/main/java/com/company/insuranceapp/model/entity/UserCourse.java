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
@Table(name = "user_courses")
public class UserCourse {
    @Id
    @Column(name = "user_course_id",
            insertable = false
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_user_course"
    )
    @SequenceGenerator(
            name = "seq_user_course",
            sequenceName = "seq_user_course",
            allocationSize = 1
    )
    private Long id;

    @NonNull
    @JoinColumn(referencedColumnName = "id", name = "id_user")
    @ManyToOne
    private User user;

    @NonNull
    @JoinColumn(referencedColumnName = "course_id", name = "id_course")
    @ManyToOne
    private Course course;

}
