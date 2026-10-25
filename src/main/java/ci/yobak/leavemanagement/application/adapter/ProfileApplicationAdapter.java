package ci.yobak.leavemanagement.application.adapter;

import ci.yobak.leavemanagement.application.dto.PostProfileDto;
import ci.yobak.leavemanagement.application.dto.ProfileResponseDto;
import ci.yobak.leavemanagement.application.mapper.ProfileResponseMapper;
import ci.yobak.leavemanagement.domain.in.ProfileUseCase;
import ci.yobak.leavemanagement.domain.model.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileApplicationAdapter {
    private final ProfileUseCase profileUseCase;
    private final ProfileResponseMapper profileResponseMapper;

    public ProfileApplicationAdapter(ProfileUseCase profileUseCase, ProfileResponseMapper profileResponseMapper) {
        this.profileUseCase = profileUseCase;
        this.profileResponseMapper = profileResponseMapper;
    }

    public ProfileResponseDto create(PostProfileDto request) {
        Profile profile = new Profile();
        profile.setCode(request.getCode());
        profile.setName(request.getName());
        return profileResponseMapper.toProfileDto(profileUseCase.create(profile));
    }

    public List<ProfileResponseDto> getAll() {
        return profileResponseMapper.toProfileDto(profileUseCase.getAll());
    }

    public ProfileResponseDto getById(Long id) {
        return profileResponseMapper.toProfileDto(profileUseCase.getById(id));
    }

    public ProfileResponseDto update(Long id, PostProfileDto request) {
        Profile profile = new Profile();
        profile.setCode(request.getCode());
        profile.setName(request.getName());
        return profileResponseMapper.toProfileDto(profileUseCase.update(id, profile));
    }

    public void delete(Long id) {
        profileUseCase.delete(id);
    }
}
