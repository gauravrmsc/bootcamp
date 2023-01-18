package in.dreamplug.userservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.dreamplug.userservice.entity.User;

/**
 * @author gauravkumar
 * @since 18/01/23
 */
@Repository
public interface UserDetailRepository extends JpaRepository<User, Long> {
    Optional<User> findByExternalId(String externalId);

    Optional<User> findByMobileNumber(String mobileNumber);
}
