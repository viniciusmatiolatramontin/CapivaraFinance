package com.capivarafinance.CapivaraFinance.service;

import com.capivarafinance.CapivaraFinance.enums.EntryType;
import com.capivarafinance.CapivaraFinance.exception.EntryNotFoundException;
import com.capivarafinance.CapivaraFinance.model.FinEntry;
import com.capivarafinance.CapivaraFinance.model.UserAuth;
import com.capivarafinance.CapivaraFinance.repository.FinEntryRepository;
import com.capivarafinance.CapivaraFinance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinEntryService {

    @Autowired
    private FinEntryRepository repo;

    @Autowired
    private UserRepository userRepo;

    public List<FinEntry> findAllEntries(UserAuth user) {
        return repo.findAllByUser(user);
    }

    public List<FinEntry> findEntriesByEntryType(UserAuth user, EntryType entryType) {
        return repo.findEntriesByEntryType(user, entryType);
    }

    public FinEntry findEntryById(UserAuth user, Long id) throws EntryNotFoundException {
        FinEntry result = repo.findEntryById(user, id);

        if (result == null) {
            throw new EntryNotFoundException("Erro: Dado não encontrado");
        }

        return result;
    }

    public FinEntry addEntry(UserAuth user, FinEntry entry) {
        FinEntry entry_saved = new FinEntry(entry.getName(), entry.getEntryValue(), entry.getEntryType(), entry.getDate());

        if (entry.getEntryType().toString().equalsIgnoreCase("expense")) {
            user.setBalance(user.getBalance() - entry.getEntryValue());
        } else {
            user.setBalance(user.getBalance() + entry.getEntryValue());
        }

        userRepo.save(user);
        entry_saved.setOwner(user);

        return repo.save(entry_saved);
    }
}
