package ci.yobak.leavemanagement.infrastructure.persistence.mapper;

import ci.yobak.leavemanagement.domain.model.Profile;
import ci.yobak.leavemanagement.infrastructure.persistence.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {
    Profile toProfile(ProfileEntity entity);

    ProfileEntity toProfileEntity(Profile profile);
}
