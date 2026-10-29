package ctm.mptc.kh.ecommerce.user.repository;

import ctm.mptc.kh.ecommerce.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByUsername(String username);
}
