package ci.yobak.leavemanagement.domain.in;

import ci.yobak.leavemanagement.domain.model.Profile;

import java.util.List;

public interface ProfileUseCase {
    Profile create(Profile profile);

    Profile getById(Long id);

    List<Profile> getAll();

    Profile update(Long id, Profile profile);

    void delete(Long id);
}
