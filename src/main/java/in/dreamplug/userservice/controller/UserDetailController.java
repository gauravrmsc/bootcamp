package in.dreamplug.userservice.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.dreamplug.userservice.dto.UserRequest;
import in.dreamplug.userservice.entity.User;
import in.dreamplug.userservice.service.userdetails.IUserDetailService;
import in.dreamplug.userservice.service.userdetails.impl.UserDetailServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author gauravkumar
 * @since 17/01/23
 */
@Slf4j
@RestController
@RequestMapping ("/users")
@RequiredArgsConstructor
public class UserDetailController {
    private static final String USER_ID = "USER_ID";

   @Qualifier("UserDetailServiceImpl")
    private final IUserDetailService userDetailService;

    @GetMapping (path = "/{" + USER_ID + "}")
    public ResponseEntity<User> getUser(@NotEmpty @PathVariable (USER_ID) String userId) {
        return ResponseEntity.ok(userDetailService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userDetailService.create(userRequest));
    }

    //TODO Create Put Endpoint
}
