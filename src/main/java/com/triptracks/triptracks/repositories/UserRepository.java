package com.triptracks.triptracks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.triptracks.triptracks.models.User;

public interface UserRepository extends JpaRepository<User,Long>{
	User findByEmail(String email);
}
