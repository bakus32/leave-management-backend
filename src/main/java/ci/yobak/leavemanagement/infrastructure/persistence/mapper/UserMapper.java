package ci.yobak.leavemanagement.infrastructure.persistence.mapper;

import ci.yobak.leavemanagement.domain.model.User;
import ci.yobak.leavemanagement.infrastructure.persistence.entity.ProfileEntity;
import ci.yobak.leavemanagement.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "profileId", expression = "java(entity.getProfile() != null ? entity.getProfile().getId() : null)")
    User toUser(UserEntity entity);

    @Mapping(target = "profile", ignore = true)
    UserEntity toEntity(User user);

    default UserEntity toUserEntity(User user, @Context ProfileEntity profileEntity) {
        UserEntity entity = toEntity(user);
        if (profileEntity != null) {
            entity.setProfile(profileEntity);
        }
        return entity;
    }
}
