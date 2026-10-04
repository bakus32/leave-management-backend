package ci.yobak.leavemanagement.application.mapper;

import ci.yobak.leavemanagement.application.dto.UserResponseDto;
import ci.yobak.leavemanagement.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserResponseMapper {
    UserResponseDto toResponse(User user);

    List<UserResponseDto> toResponse(List<User> users);
}
