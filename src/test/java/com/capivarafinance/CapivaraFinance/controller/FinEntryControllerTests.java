package com.capivarafinance.CapivaraFinance.controller;

import com.capivarafinance.CapivaraFinance.enums.EntryType;
import com.capivarafinance.CapivaraFinance.service.FinEntryService;
import com.capivarafinance.CapivaraFinance.model.FinEntry;
import com.capivarafinance.CapivaraFinance.model.UserAuth;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FinEntryController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = FinEntryController.class)
public class FinEntryControllerTests {

    @MockBean
    private FinEntryService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addEntryTest() throws Exception {
        FinEntry entry = new FinEntry("Entry1", 600.00, EntryType.EXPENSE, new Date(System.currentTimeMillis()));
        entry.setId(1L);
        entry.setOwner(new UserAuth("user", "12345", "test@gmail.com", 5000.00));

        when(service.addEntry(any(), any())).thenReturn(entry);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        mockMvc.perform(post("/financial_entry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(entry)))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(entry.getName()))
                .andExpect(jsonPath("$.entryValue").value(entry.getEntryValue()))
                .andExpect(jsonPath("$.entryType").value(entry.getEntryType().toString()))
                .andExpect(jsonPath("$.date").value(format.format(entry.getDate())));
    }

    @Test
    void findAllEntriesTest() throws Exception {
        List<FinEntry> entries = new ArrayList<>();
        entries.add(new FinEntry("Entry1", 600.00, EntryType.EXPENSE, new Date(System.currentTimeMillis())));
        entries.add(new FinEntry("Entry2", 300.00, EntryType.GAIN, new Date(System.currentTimeMillis())));
        entries.add(new FinEntry("Entry3", 150.00, EntryType.EXPENSE, new Date(System.currentTimeMillis())));

        when(service.findAllEntries(any())).thenReturn(entries);

        mockMvc.perform(get("/financial_entry")).andDo(print())
                .andExpect(content().string(containsString("Entry1")))
                .andExpect(content().string(containsString("Entry2")))
                .andExpect(content().string(containsString("Entry3")));
    }
}
