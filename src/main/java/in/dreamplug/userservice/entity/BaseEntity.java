package in.dreamplug.userservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gauravkumar
 * @since 17/01/23
 */
@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "external_id", nullable = false)
    private String externalId;

    @Column (name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column (name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column (name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column (name = "updated_by", nullable = false)
    private String updatedBy;

    @Version
    @Column (nullable = false)
    private Long version;

    @PrePersist
    public void prePersist() {
        LocalDateTime date = LocalDateTime.now();
        this.updatedAt = date;
        this.createdAt = date;
        //TODO take user details from Header
        if (StringUtils.isBlank(this.createdBy)) {
            this.createdBy = "system";
            this.updatedBy = "system";
        }
    }

    @PreUpdate
    public void preUpdate() {
        LocalDateTime date = LocalDateTime.now();
        this.updatedAt = date;
        //TODO take user details from Header
        if (StringUtils.isBlank(this.createdBy)) {
            this.updatedBy = "system";
        }
    }
}
