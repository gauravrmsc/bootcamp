package in.dreamplug.userservice.repository;

import in.dreamplug.userservice.entry.UserEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailDAO extends JpaRepository<UserEntry, Long> {
}
