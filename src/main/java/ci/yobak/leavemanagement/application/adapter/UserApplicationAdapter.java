package ci.yobak.leavemanagement.application.adapter;

import ci.yobak.leavemanagement.application.dto.PostUserDto;
import ci.yobak.leavemanagement.application.dto.UserResponseDto;
import ci.yobak.leavemanagement.application.mapper.UserResponseMapper;
import ci.yobak.leavemanagement.domain.in.UserUseCase;
import ci.yobak.leavemanagement.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserApplicationAdapter {
    private final UserUseCase userUseCase;
    private final UserResponseMapper userResponseMapper;

    public UserApplicationAdapter(UserUseCase userUseCase, UserResponseMapper userResponseMapper) {
        this.userUseCase = userUseCase;
        this.userResponseMapper = userResponseMapper;
    }

    public UserResponseDto create(PostUserDto request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setProfileId(request.getProfileId());
        return userResponseMapper.toResponse(userUseCase.create(user));
    }

    public List<UserResponseDto> getAll() {
        return userResponseMapper.toResponse(userUseCase.getAll());
    }

    public UserResponseDto getById(Long id) {
        return userResponseMapper.toResponse(userUseCase.getById(id));
    }

    public UserResponseDto update(Long id, PostUserDto request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setProfileId(request.getProfileId());
        return userResponseMapper.toResponse(userUseCase.update(id, user));
    }

    public void delete(Long id) {
        userUseCase.delete(id);
    }
}
