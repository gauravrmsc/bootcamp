package in.dreamplug.userservice.entity;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
}
