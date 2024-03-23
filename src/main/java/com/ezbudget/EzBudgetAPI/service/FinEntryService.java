package com.ezbudget.EzBudgetAPI.service;

import com.ezbudget.EzBudgetAPI.model.FinEntry;
import com.ezbudget.EzBudgetAPI.model.UserAuth;
import com.ezbudget.EzBudgetAPI.repository.FinEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinEntryService {

    @Autowired
    private FinEntryRepository repo;

    public List<FinEntry> findAllEntries(UserAuth user) {
        return repo.findAllByUser(user);
    }

    public FinEntry addEntry(UserAuth user, FinEntry entry) {
        FinEntry entry_saved = new FinEntry(entry.getName(), entry.getEntryValue(), entry.getEntryType(), entry.getDate());
        entry_saved.setOwner(user);
        return repo.save(entry_saved);
    }

}
