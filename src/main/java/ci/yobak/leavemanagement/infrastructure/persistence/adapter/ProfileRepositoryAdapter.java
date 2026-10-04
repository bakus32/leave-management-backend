package ci.yobak.leavemanagement.infrastructure.persistence.adapter;

import ci.yobak.leavemanagement.domain.model.Profile;
import ci.yobak.leavemanagement.domain.port.out.ProfileRepositoryPort;
import ci.yobak.leavemanagement.infrastructure.persistence.entity.ProfileEntity;
import ci.yobak.leavemanagement.infrastructure.persistence.entity.ProfileRepository;
import ci.yobak.leavemanagement.infrastructure.persistence.mapper.ProfileMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProfileRepositoryAdapter implements ProfileRepositoryPort {
    private final ProfileRepository profileRepository;
    private final ProfileMapper mapper;

    public ProfileRepositoryAdapter(ProfileRepository profileRepository, ProfileMapper mapper) {
        this.profileRepository = profileRepository;
        this.mapper = mapper;
    }

    @Override
    public Profile save(Profile profile) {
        ProfileEntity entity = mapper.toProfileEntity(profile);
        ProfileEntity saved = profileRepository.save(entity);
        return mapper.toProfile(saved);
    }

    @Override
    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id).map(mapper::toProfile);
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll().stream().map(mapper::toProfile).toList();
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
