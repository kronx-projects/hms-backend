package ru.kronx.hmsbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kronx.hmsbackend.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUsersByIdAndEnabled (Long id, boolean enabled);
    List<User> findAllByEnabled (boolean enabled);
    User findUsersById (Long id);

}
