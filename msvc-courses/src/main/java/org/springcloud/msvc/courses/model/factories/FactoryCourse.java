package org.springcloud.msvc.courses.model.factories;

import org.springcloud.msvc.courses.model.dto.DTOCourse;
import org.springcloud.msvc.courses.model.entity.Course;

public class FactoryCourse {
    private FactoryCourse() {
    }

    public static Course toAddCourse(DTOCourse dtoCourse){
        return new Course(dtoCourse.getId(), dtoCourse.getName());
    }

    public static Course toUpdateCourse(DTOCourse dtoCourse){
        return new Course(dtoCourse.getId(),
                dtoCourse.getName());
    }

}
