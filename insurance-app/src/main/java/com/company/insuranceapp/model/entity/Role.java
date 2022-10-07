package com.company.insuranceapp.model.entity;

import com.company.insuranceapp.model.enumeration.RoleType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

import java.util.Objects;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
public class Role {
    @Id
    @Column(
            name = "id",
            insertable = false
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_role"
    )
    @SequenceGenerator(
            name = "seq_role",
            sequenceName = "seq_role",
            allocationSize = 1
    )
    private Long id;

    @Enumerated(STRING)
    @Column(name = "role_type", unique = true, nullable = false)
    private RoleType roleType;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return id != null && Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
