package in.dreamplug.userservice.entry;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntry implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, updatable = false)
        @CreatedDate
        private LocalDateTime createdAt;

        @Value("system")
        @CreatedBy
        @Column(nullable = false, updatable = false)
        private String createdBy;

        @Column(nullable = false)
        @LastModifiedDate
        private LocalDateTime updatedAt;

        @LastModifiedBy
        @Value("system")
        @Column(nullable = false)
        private String lastModifiedBy;

        @Version
        @Column(nullable = false)
        private Long version;

        @PrePersist
        public void setAuditable() {
                LocalDateTime date = LocalDateTime.now();
                this.updatedAt = date;
                this.createdAt = date;
                if(StringUtils.isBlank(this.createdBy)) {
                        this.createdBy = "system";
                        this.lastModifiedBy = "system";
                }
        }
}
