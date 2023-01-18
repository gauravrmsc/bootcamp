package in.dreamplug.userservice.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gauravkumar
 * @since 17/01/23
 */
@Getter
@Setter
@Entity
@Table (name = "users")
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
