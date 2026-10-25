package ci.yobak.leavemanagement.application.mapper;

import ci.yobak.leavemanagement.application.dto.ProfileResponseDto;
import ci.yobak.leavemanagement.domain.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileResponseMapper {
    ProfileResponseDto toProfileDto(Profile profile);

    List<ProfileResponseDto> toProfileDto(List<Profile> profiles);
}
