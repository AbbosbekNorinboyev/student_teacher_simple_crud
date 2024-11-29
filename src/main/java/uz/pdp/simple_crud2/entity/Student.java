package uz.pdp.simple_crud2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String phoneNumber;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Teacher teacher;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
