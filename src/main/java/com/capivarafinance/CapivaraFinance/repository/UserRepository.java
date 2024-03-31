package com.capivarafinance.CapivaraFinance.repository;

import com.capivarafinance.CapivaraFinance.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAuth, Long> {

    @Query(value = "SELECT u FROM UserAuth u WHERE u.username = ?1")
    UserAuth findByUsername(String username);
}
