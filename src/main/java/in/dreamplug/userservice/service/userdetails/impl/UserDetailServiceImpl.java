package in.dreamplug.userservice.service.userdetails.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import in.dreamplug.userservice.dto.UserRequest;
import in.dreamplug.userservice.entity.User;
import in.dreamplug.userservice.mapper.UserMapper;
import in.dreamplug.userservice.service.userdetails.IUserDetailService;

/**
 * @author gauravkumar
 * @since 17/01/23
 */
@Service
public class UserDetailServiceImpl implements IUserDetailService {
    private final Map<String, User> users = new HashMap<>();

    private final Map<String, User> userContactMap = new HashMap<>();

    private final UserMapper userMapper;

    public UserDetailServiceImpl(UserMapper userMapper) {
        initUsers();
        this.userMapper = userMapper;
    }

    @Override
    public User create(UserRequest userRequest) {
    }

    @Override
    public User findByUserId(String userId) {
    }

    //TODO Create Put Endpoint

    private void initUsers() {
        users.put("user_id1", User.builder().externalId("user_id1").userName("user1").mobileNumber("8340312345").build());
        users.put("user_id2", User.builder().externalId("user_id2").userName("user2").mobileNumber("8340312345").build());
        users.put("user_id3", User.builder().externalId("user_id3").userName("user3").mobileNumber("8340312345").build());
        users.values().stream().forEach(user -> userContactMap.put(user.getMobileNumber(), user));
    }
}
