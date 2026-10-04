package ci.yobak.leavemanagement.domain.service;

import ci.yobak.leavemanagement.common.exception.DuplicateResourceException;
import ci.yobak.leavemanagement.common.exception.ResourceNotFoundException;
import ci.yobak.leavemanagement.domain.in.ProfileUseCase;
import ci.yobak.leavemanagement.domain.model.Profile;
import ci.yobak.leavemanagement.domain.port.out.ProfileRepositoryPort;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@NullMarked
@Service
public class ProfileService implements ProfileUseCase {
    private final ProfileRepositoryPort profileRepositoryPort;

    public ProfileService(ProfileRepositoryPort profileRepositoryPort) {
        this.profileRepositoryPort = profileRepositoryPort;
    }

    @Override
    @Transactional
    public Profile create(Profile profile) {
        validateProfile(profile);
        if (profileRepositoryPort.existsByCode(profile.getCode())) {
            throw new DuplicateResourceException("Profile code already exists: " + profile.getCode());
        }
        return profileRepositoryPort.save(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public @Nullable Profile getById(Long id) {
        return Objects.requireNonNull(profileRepositoryPort.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Profile> getAll() {
        return profileRepositoryPort.findAll();
    }

    @Override
    @Transactional
    public Profile update(Long id, Profile profile) {
        Profile existing = getById(id);
        validateProfile(profile);
        if (!existing.getCode().equals(profile.getCode()) && profileRepositoryPort.existsByCode(profile.getCode())) {
            throw new DuplicateResourceException("Profile code already exists: " + profile.getCode());
        }
        existing.setCode(profile.getCode());
        existing.setName(profile.getName());
        return profileRepositoryPort.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getById(id);
        profileRepositoryPort.deleteById(id);
    }

    private void validateProfile(Profile profile) {
        if (profile.getCode() == null || profile.getCode().isBlank()) {
            throw new IllegalArgumentException("Profile code is required");
        }
        if (profile.getName() == null || profile.getName().isBlank()) {
            throw new IllegalArgumentException("Profile name is required");
        }
    }
}
