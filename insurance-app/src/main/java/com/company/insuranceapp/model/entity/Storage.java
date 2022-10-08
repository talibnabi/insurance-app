package com.company.insuranceapp.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "storages")
public class Storage {
    @Id
    @Column(name = "storage_id",
            insertable = false
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_storage"
    )
    @SequenceGenerator(
            name = "seq_storage",
            sequenceName = "seq_storage",
            allocationSize = 1
    )
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String type;
    @NonNull
    private double size;
}
