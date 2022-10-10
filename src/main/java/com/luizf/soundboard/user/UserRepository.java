package com.luizf.soundboard.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user_ where user_.username = :username", nativeQuery = true)
    Optional<User> findUserByUsername(@Param("username") String username);
}
