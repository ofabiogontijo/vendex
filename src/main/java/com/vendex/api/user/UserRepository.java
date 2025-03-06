package com.vendex.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

interface UserRepository extends JpaRepository<User, UUID> {

	@Query("FROM User where email = :email")
	Optional<User> findByEmail(String email);

}