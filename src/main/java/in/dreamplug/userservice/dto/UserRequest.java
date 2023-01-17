package in.dreamplug.userservice.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gauravkumar
 * @since 17/01/23
 */
@Getter
@Setter
public class UserRequest {
    @NotBlank
    private String userName;

    @NotNull
    private LocalDate dateOfBirth;

    @NotBlank
    private String mobileNumber;

    @NotBlank
    private String mailId;

    @NotBlank
    private String address;
}
