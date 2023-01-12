package org.springcloud.msvc.courses.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "course_user")
@Getter
@Setter
@AllArgsConstructor
public class CourseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", unique = true)
    private Long userId;

    public CourseUser() {

    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof CourseUser)){
            return false;
        }
        CourseUser courseUser = (CourseUser) obj;
        return this.userId != null && this.userId.equals(courseUser.getUserId());
    }
}
