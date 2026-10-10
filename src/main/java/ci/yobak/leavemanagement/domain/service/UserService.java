package ci.yobak.leavemanagement.domain.service;

import ci.yobak.leavemanagement.common.exception.DuplicateResourceException;
import ci.yobak.leavemanagement.common.exception.ResourceNotFoundException;
import ci.yobak.leavemanagement.domain.in.UserUseCase;
import ci.yobak.leavemanagement.domain.model.User;
import ci.yobak.leavemanagement.domain.port.out.ProfileRepositoryPort;
import ci.yobak.leavemanagement.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final ProfileRepositoryPort profileRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort, ProfileRepositoryPort profileRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.profileRepositoryPort = profileRepositoryPort;
    }

    @Override
    @Transactional
    public User create(User user) {
        if (userRepositoryPort.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("User email already exists: " + user.getEmail());
        }
        if (user.getProfileId() == null || Objects.requireNonNull(profileRepositoryPort.findById(user.getProfileId())).isEmpty()) {
            throw new ResourceNotFoundException("Profile not found with id: " + user.getProfileId());
        }
        return userRepositoryPort.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepositoryPort.findAll();
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User existing = getById(id);
        if (!existing.getEmail().equals(user.getEmail()) && userRepositoryPort.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("User email already exists: " + user.getEmail());
        }
        if (user.getProfileId() == null || Objects.requireNonNull(profileRepositoryPort.findById(user.getProfileId())).isEmpty()) {
            throw new ResourceNotFoundException("Profile not found with id: " + user.getProfileId());
        }
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPhone(user.getPhone());
        existing.setProfileId(user.getProfileId());
        return userRepositoryPort.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getById(id);
        userRepositoryPort.deleteById(id);
    }

}
