package org.springcloud.msvc.courses.model.clients;

import org.springcloud.msvc.courses.model.dto.DTOUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "msvc-users", url = "localhost:8001/msvc-users")
public interface UserClientRest {

    @GetMapping("/{id}")
    DTOUser userById(@PathVariable Long id);

    @PostMapping("/save")
    Map<String, String> save(@RequestBody DTOUser user);

    @GetMapping("/users-by-courses")
    List<DTOUser> findAllUsersByIdsInCourse(@RequestParam Iterable<Long> idsUsers);
}
