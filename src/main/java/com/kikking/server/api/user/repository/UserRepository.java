package com.kikking.server.api.user.repository;

import com.kikking.server.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    // Object 타입으로 반환되는 메서드이며, 이는 User 엔티티만을 반환할 것이므로 @SuppressWarnings("unchecked") 을 사용해도 안전하다. (무시)
    User saveUser(User user);
}
