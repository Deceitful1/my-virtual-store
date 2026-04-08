package com.gablins.virtual_store.repositories;

import com.gablins.virtual_store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<UserDetails> findByUsername(String username);

}
