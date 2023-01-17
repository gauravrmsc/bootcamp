package in.dreamplug.userservice.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gauravkumar
 * @since 17/01/23
 */
@Getter
@Setter
@JsonNaming (PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude (JsonInclude.Include.NON_NULL)
public class User extends BaseEntity {
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

    @Builder
    private User(Long id, String externalId, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String lastModifiedBy, Long version,
            String userName, LocalDate dateOfBirth, String mobileNumber, String mailId, String address) {
        super(id, externalId, createdAt, createdBy, updatedAt, lastModifiedBy, version);
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.mailId = mailId;
        this.address = address;
    }
}
