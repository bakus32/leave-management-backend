package ci.yobak.leavemanagement.infrastructure.persistence.adapter;

import ci.yobak.leavemanagement.domain.model.Profile;
import ci.yobak.leavemanagement.domain.port.out.ProfileRepositoryPort;
import ci.yobak.leavemanagement.infrastructure.persistence.entity.ProfileEntity;
import ci.yobak.leavemanagement.infrastructure.persistence.entity.ProfileRepository;
import ci.yobak.leavemanagement.infrastructure.persistence.mapper.ProfileMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProfileRepositoryAdapter implements ProfileRepositoryPort {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileRepositoryAdapter(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public Profile save(Profile profile) {
        ProfileEntity entity = profileMapper.toProfileEntity(profile);
        ProfileEntity saved = profileRepository.save(entity);
        return profileMapper.toProfile(saved);
    }

    @Override
    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id).map(profileMapper::toProfile);
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll().stream().map(profileMapper::toProfile).toList();
    }

    @Override
    public boolean existsByCode(String code) {
        return profileRepository.existsByCode(code);
    }

    @Override
    public void deleteById(Long id) {
        profileRepository.deleteById(id);
    }
}
