package in.dreamplug.userservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gauravkumar
 * @since 17/01/23
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {
    private Long id;

    private String externalId;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String lastModifiedBy;

    private Long version;
}
