package com.example.soafirst.storage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "studio", schema = "s272736")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;
}
