package in.dreamplug.userservice.controllers;

import in.dreamplug.userservice.entry.UserEntry;
import in.dreamplug.userservice.services.manager.IUserDetailManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user/details")
public class UserDetailController {

    @Autowired
    private IUserDetailManager userDetailManager;

    private static final String USER_ID= "USER_ID";

    @GetMapping(path = "/{"+USER_ID+"}",
            produces =  {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody ResponseEntity get(@NotEmpty @PathVariable(USER_ID) Long userId){
        Optional<UserEntry> userEntry = userDetailManager.findByUserId(userId);
        if(userEntry.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userEntry.get());
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }

    }


    @PostMapping(path = "/",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody ResponseEntity create(@Valid @RequestBody UserEntry userEntry) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDetailManager.create(userEntry));
    }


}

