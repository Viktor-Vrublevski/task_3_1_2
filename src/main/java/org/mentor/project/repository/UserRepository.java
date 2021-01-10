package org.mentor.project.repository;

import org.mentor.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllBy();
    User findByUsername(String username);

    @Override
    Optional<User> findById(Long id);
}
