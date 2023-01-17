package in.dreamplug.userservice.mapper;

import org.mapstruct.Mapper;
import in.dreamplug.userservice.dto.UserRequest;
import in.dreamplug.userservice.entity.User;

/**
 * @author gauravkumar
 * @since 17/01/23
 */
@Mapper (componentModel = "spring")
public interface UserMapper {
    User buildUser(UserRequest userRequest);
}
