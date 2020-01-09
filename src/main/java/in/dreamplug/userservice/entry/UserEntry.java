package in.dreamplug.userservice.entry;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "user_detail")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@EqualsAndHashCode(callSuper = true)
public class UserEntry extends BaseEntry{
    private String userName;
    private LocalDate dateOfBirth;
    private String mobileNumber;
    private String mailId;
    private String address;
}
