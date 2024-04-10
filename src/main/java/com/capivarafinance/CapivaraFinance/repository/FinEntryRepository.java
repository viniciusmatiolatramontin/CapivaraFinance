package com.capivarafinance.CapivaraFinance.repository;

import com.capivarafinance.CapivaraFinance.enums.EntryType;
import com.capivarafinance.CapivaraFinance.model.FinEntry;
import com.capivarafinance.CapivaraFinance.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FinEntryRepository extends JpaRepository<FinEntry, Long> {

    @Query("SELECT f FROM FinEntry f WHERE f.owner = ?1")
    List<FinEntry> findAllByUser(UserAuth user);

    @Query("SELECT f FROM FinEntry f WHERE f.owner = ?1 AND f.entryType = ?2")
    List<FinEntry> findEntriesByEntryType(UserAuth user, EntryType entryType);

    @Query("SELECT f FROM FinEntry f WHERE f.owner = ?1 AND f.id = ?2")
    FinEntry findEntryById(UserAuth user, Long id);

    @Query("SELECT f FROM FinEntry f WHERE f.owner = ?1 AND f.date BETWEEN ?2 AND ?3")
    List<FinEntry> findEntriesByDate(UserAuth user, Date minDate, Date maxDate);
}
