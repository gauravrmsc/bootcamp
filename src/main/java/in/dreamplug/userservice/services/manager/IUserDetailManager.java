package in.dreamplug.userservice.services.manager;

import datadog.trace.api.Trace;
import in.dreamplug.userservice.entry.UserEntry;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Validated
public interface IUserDetailManager {
    @Trace
    UserEntry create(@Valid UserEntry userEntry);

    @Trace
    Optional<UserEntry> findByUserId(@NotNull @Min(1) Long userId);
}
