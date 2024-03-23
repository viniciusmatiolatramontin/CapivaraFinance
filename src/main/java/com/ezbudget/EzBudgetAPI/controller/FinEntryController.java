package com.ezbudget.EzBudgetAPI.controller;

import com.ezbudget.EzBudgetAPI.model.FinEntry;
import com.ezbudget.EzBudgetAPI.model.UserAuth;
import com.ezbudget.EzBudgetAPI.service.FinEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<FinEntry> addEntry(@AuthenticationPrincipal UserAuth user, @RequestBody FinEntry entry) {
        return new ResponseEntity<FinEntry>(service.addEntry(user, entry), HttpStatus.CREATED);
    }
}
