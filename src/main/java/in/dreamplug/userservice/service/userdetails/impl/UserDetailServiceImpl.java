package in.dreamplug.userservice.service.userdetails.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import in.dreamplug.userservice.dto.UserRequest;
import in.dreamplug.userservice.entity.User;
import in.dreamplug.userservice.mapper.UserMapper;
import in.dreamplug.userservice.repository.UserDetailRepository;
import in.dreamplug.userservice.service.userdetails.IUserDetailService;
import lombok.RequiredArgsConstructor;

/**
 * @author gauravkumar
 * @since 17/01/23
 */
@Service ("UserDetailServiceImpl")
@RequiredArgsConstructor
public class UserDetailServiceImpl implements IUserDetailService {
    @Autowired
    private final UserDetailRepository userDetailRepository;

    private final UserMapper userMapper;

    @Override
    public User create(UserRequest userRequest) {
        if (userDetailRepository.findByMobileNumber(userRequest.getMobileNumber()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        User user = userMapper.buildUser(userRequest);
        userDetailRepository.save(user);
        return user;
    }

    @Override
    public User findByUserId(String userId) {
        return userDetailRepository.findByExternalId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //TODO Create Put Endpoint
}
