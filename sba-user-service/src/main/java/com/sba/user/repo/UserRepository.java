package com.sba.user.repo;

import java.util.Date;
import java.util.Optional;

import com.sba.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "{ 'role':{$ne:'ADMIN'} }")
	Page<User> findAll(Pageable pageable);

	@Query(value = "{ '_id':?0 }")
	Optional<User> findById(Long userId);

	@Query(value = "{ 'userName':?0 }")
	Optional<User> findByName(String userName);

	@Query(value = "{ 'userName':?0, 'role':?1 }")
	User findByName(String userName, String userType);

	@Query(value = "{ 'userName':?0, 'confirmedSignUp':true, 'active':true, 'resetPassword':false }")
	public User findByActiveNonResetPasswdUser(String userName);

	@Query(value = "{ 'userName':?0, 'confirmedSignUp':true, 'active':true}")
	public User findByActiveUser(String userName);
	
	@Query(value = "{ 'userName':?0, 'regCode':?1 }")
	public User findUserByNameRegCodeForSignUp(String userName, String token);

	@Query(value = "{'userName':?0, 'regCode':?1, 'confirmedSignUp':true, 'resetPasswordDate':{ $lte:?2 } }")
	public User findUserByNameRegCodeForPwdReset(String userName, String token, Date expiryDate);

}
