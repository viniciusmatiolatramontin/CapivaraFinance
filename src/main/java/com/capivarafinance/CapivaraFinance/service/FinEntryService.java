package com.capivarafinance.CapivaraFinance.service;

import com.capivarafinance.CapivaraFinance.enums.EntryType;
import com.capivarafinance.CapivaraFinance.exception.EntryNotFoundException;
import com.capivarafinance.CapivaraFinance.model.FinEntry;
import com.capivarafinance.CapivaraFinance.model.UserAuth;
import com.capivarafinance.CapivaraFinance.repository.FinEntryRepository;
import com.capivarafinance.CapivaraFinance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<FinEntry> findEntriesByDate(UserAuth user, Date minDate, Date maxDate) {
        return repo.findEntriesByDate(user, minDate, maxDate);
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

    public FinEntry updateEntry(UserAuth user, FinEntry entry, Long id) throws EntryNotFoundException {
        FinEntry data = this.findEntryById(user, id);
        Double dataValueBefore = data.getEntryValue();

        data.setDate(entry.getDate());
        data.setEntryValue(entry.getEntryValue());
        data.setName(entry.getName());

        double balance = 0.0;

        if (data.getEntryType().toString().equalsIgnoreCase("expense")) {
            balance = user.getBalance() - (data.getEntryValue() - dataValueBefore);
        } else {
            balance = user.getBalance() + (data.getEntryValue() - dataValueBefore);
        }

        user.setBalance(balance);

        userRepo.save(user);
        data.setOwner(user);

        return repo.save(data);
    }

    public FinEntry removeEntry(UserAuth user, Long id) throws EntryNotFoundException {
        FinEntry result = repo.findEntryById(user, id);

        if (result == null) {
            throw new EntryNotFoundException("Erro: Dado não encontrado");
        }

        repo.delete(result);

        if (result.getEntryType().toString().equalsIgnoreCase("expense")) {
            user.setBalance(user.getBalance() + result.getEntryValue());
        } else {
            user.setBalance(user.getBalance() - result.getEntryValue());
        }

        userRepo.save(user);
        result.setOwner(user);

        return result;
    }
}
