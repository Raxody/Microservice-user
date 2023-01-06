package org.springcloud.msvc.courses.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {
    private static final String THE_NAME_MUST_NOT_BE_EMPTY = "the name mustn't be empty";

    public Course(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = THE_NAME_MUST_NOT_BE_EMPTY)
    private String name;
}
