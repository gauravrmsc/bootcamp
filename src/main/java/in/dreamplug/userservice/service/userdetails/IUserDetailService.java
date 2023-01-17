package in.dreamplug.userservice.service.userdetails;

import in.dreamplug.userservice.dto.UserRequest;
import in.dreamplug.userservice.entity.User;

/**
 * @author gauravkumar
 * @since 17/01/23
 */
public interface IUserDetailService {
    User create(UserRequest userEntry);

    User findByUserId(String userId);
}
