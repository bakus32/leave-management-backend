package ci.yobak.leavemanagement.infrastructure.persistence.adapter;

import ci.yobak.leavemanagement.domain.model.User;
import ci.yobak.leavemanagement.domain.port.out.UserRepositoryPort;
import ci.yobak.leavemanagement.infrastructure.persistence.entity.ProfileEntity;
import ci.yobak.leavemanagement.infrastructure.persistence.entity.ProfileRepository;
import ci.yobak.leavemanagement.infrastructure.persistence.entity.UserEntity;
import ci.yobak.leavemanagement.infrastructure.persistence.entity.UserRepository;
import ci.yobak.leavemanagement.infrastructure.persistence.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {
    private final UserRepository userJpaRepository;
    private final ProfileRepository profileRepository;
    private final UserMapper mapper;

    public UserRepositoryAdapter(UserRepository userJpaRepository,
                                ProfileRepository profileRepository,
                                UserMapper mapper) {
        this.userJpaRepository = userJpaRepository;
        this.profileRepository = profileRepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        ProfileEntity profileEntity = profileRepository.findById(user.getProfileId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found for user"));
        UserEntity entity = mapper.toUserEntity(user, profileEntity);
        UserEntity saved = userJpaRepository.save(entity);
        return mapper.toUser(saved);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(mapper::toUser);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream().map(mapper::toUser).toList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(mapper::toUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }
}
