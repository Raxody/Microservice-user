package org.springcloud.msvc.courses.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springcloud.msvc.courses.model.dto.DTOUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {
    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course() {
        this.courseUser = new ArrayList<>();
        this.users = new ArrayList<>();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "course_id")
    private List<CourseUser> courseUser;

    @Transient
    private List<DTOUser> users;

    public void addCourseUser(CourseUser courseUser){
        this.courseUser.add(courseUser);
    }
    public void deleteCourseUser(CourseUser courseUser){
        this.courseUser.remove(courseUser);
    }
}
