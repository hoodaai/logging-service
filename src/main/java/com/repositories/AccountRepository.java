package com.repositories;

import com.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 ** Contains database operations for Account entity.
 */

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAppSecret(@Param("appSecret") String appSecret);

}
