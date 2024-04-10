package com.capivarafinance.CapivaraFinance.controller;

import com.capivarafinance.CapivaraFinance.enums.EntryType;
import com.capivarafinance.CapivaraFinance.exception.EntryNotFoundException;
import com.capivarafinance.CapivaraFinance.model.FinEntry;
import com.capivarafinance.CapivaraFinance.model.UserAuth;
import com.capivarafinance.CapivaraFinance.service.FinEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/financial_entry")
public class FinEntryController {

    @Autowired
    private FinEntryService service;

    @GetMapping
    public ResponseEntity<List<FinEntry>> findAllEntries(@AuthenticationPrincipal UserAuth user) {
        return new ResponseEntity<List<FinEntry>>(service.findAllEntries(user), HttpStatus.OK);
    }

    @GetMapping("/byEntryType/{entryType}")
    public ResponseEntity<List<FinEntry>> findEntriesByEntryType(@AuthenticationPrincipal UserAuth user,
                                                                 @PathVariable("entryType") EntryType entryType) {
        return new ResponseEntity<List<FinEntry>>(service.findEntriesByEntryType(user, entryType), HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<FinEntry> findEntryById(@AuthenticationPrincipal UserAuth user,
                                                  @PathVariable("id") Long id) throws EntryNotFoundException {
        return new ResponseEntity<FinEntry>(service.findEntryById(user, id), HttpStatus.OK);
    }

    @GetMapping("/byDate/{minDate}/{maxDate}")
    public ResponseEntity<List<FinEntry>> findEntriesByDate(@AuthenticationPrincipal UserAuth user,
                                                            @PathVariable("minDate") Date minDate,
                                                            @PathVariable("maxDate") Date maxDate) {
        return new ResponseEntity<List<FinEntry>>(service.findEntriesByDate(user, minDate, maxDate), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FinEntry> addEntry(@AuthenticationPrincipal UserAuth user,
                                             @RequestBody FinEntry entry) {
        return new ResponseEntity<FinEntry>(service.addEntry(user, entry), HttpStatus.CREATED);
    }
}
