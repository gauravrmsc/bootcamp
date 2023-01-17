package in.dreamplug.userservice.controller;

import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import in.dreamplug.userservice.dto.UserRequest;
import in.dreamplug.userservice.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;

/**
 * @author gauravkumar
 * @since 17/01/23
 */
@Slf4j
@RestController
@RequestMapping ("/users")
public class UserDetailController {
    private static final String USER_ID = "USER_ID";

    @GetMapping (path = "/{" + USER_ID + "}")
    public ResponseEntity<User> getUser(@NotEmpty @PathVariable (USER_ID) String userId) {
        return ResponseEntity.ok(User.builder().id((long) new Random().nextInt(0, Integer.MAX_VALUE)).userName("CRED User").build());
    }

    @PostMapping (path = "/")
    public @ResponseBody ResponseEntity<User> createUser(@Valid @RequestBody UserRequest user) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(User.builder().id((long) new Random().nextInt(0, Integer.MAX_VALUE)).userName(user.getUserName())
                                       .address(user.getAddress()).build());
    }

    //TODO Create Put Endpoint
}
