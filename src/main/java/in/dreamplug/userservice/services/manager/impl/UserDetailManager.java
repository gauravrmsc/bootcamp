package in.dreamplug.userservice.services.manager.impl;

import in.dreamplug.userservice.entry.UserEntry;
import in.dreamplug.userservice.repository.UserDetailDAO;
import in.dreamplug.userservice.services.manager.IUserDetailManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserDetailManager implements IUserDetailManager {

    @Autowired
    private UserDetailDAO userDetailDAO;

    @Override
    public UserEntry create(UserEntry workflowConfigEntry)  {
        return userDetailDAO.save(workflowConfigEntry);
    }

    public Optional<UserEntry> findByUserId(Long userId) {
        return userDetailDAO.findById(userId);
    }


}
