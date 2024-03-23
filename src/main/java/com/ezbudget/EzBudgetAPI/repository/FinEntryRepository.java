package com.ezbudget.EzBudgetAPI.repository;

import com.ezbudget.EzBudgetAPI.model.FinEntry;
import com.ezbudget.EzBudgetAPI.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinEntryRepository extends JpaRepository<FinEntry, Long> {

    @Query("SELECT f FROM FinEntry f WHERE f.owner = ?1")
    List<FinEntry> findAllByUser(UserAuth user);

}
