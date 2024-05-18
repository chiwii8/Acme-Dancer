/*
 * UserAccountRepository.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	@Query("select ua from UserAccount ua where ua.username = :username")
	UserAccount findByUsername(@Param("username") String username);

	@Query("select a.userAccount from Actor a where a.id = :id")
	UserAccount findByActorId(@Param("id") int actorId);
}
