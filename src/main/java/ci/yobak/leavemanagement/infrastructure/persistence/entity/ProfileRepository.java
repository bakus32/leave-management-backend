package ci.yobak.leavemanagement.infrastructure.persistence.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    boolean existsByCode(String code);
}
