package ru.darkslayer.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.darkslayer.userservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
