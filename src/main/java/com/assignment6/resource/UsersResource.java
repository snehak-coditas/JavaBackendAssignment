package com.assignment6.resource;
import com.assignment6.service.UserService;
import com.assignment6.service.dto.UserDetailsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UsersResource {

    private final Logger log = LoggerFactory.getLogger(UsersResource.class);

    @Autowired
    private UserService userService;

    @GetMapping("/search-users")
    public  UserDetailsDTO searchUsers(@RequestParam String search) {

        UserDetailsDTO userDetailsDTO = userService.searchUsers(search);
        return userDetailsDTO;
    }
}
