package ci.yobak.leavemanagement.domain.port.out;

import ci.yobak.leavemanagement.domain.model.Profile;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;

public interface ProfileRepositoryPort {
    Profile save(Profile profile);

    @Nullable
    Optional<Profile> findById(Long id);

    List<Profile> findAll();

    boolean existsByCode(String code);

    void deleteById(Long id);
}
