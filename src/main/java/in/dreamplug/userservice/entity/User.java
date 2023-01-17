package in.dreamplug.userservice.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
    private String userName;

    private LocalDate dateOfBirth;

    private String mobileNumber;

    private String mailId;

    private String address;

    @Builder
    public User(Long id, String externalId, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String lastModifiedBy, Long version,
            String userName, LocalDate dateOfBirth, String mobileNumber, String mailId, String address) {
        super(id, externalId, createdAt, createdBy, updatedAt, lastModifiedBy, version);
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.mailId = mailId;
        this.address = address;
    }
}
