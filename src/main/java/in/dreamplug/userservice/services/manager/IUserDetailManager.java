package in.dreamplug.userservice.services.manager;

import in.dreamplug.userservice.entry.UserEntry;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Validated
public interface IUserDetailManager {
    UserEntry create(@Valid UserEntry userEntry);
    Optional<UserEntry> findByUserId(@NotNull @Min(1) Long userId);
}
