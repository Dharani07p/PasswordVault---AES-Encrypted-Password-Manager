package com.example.passwordvault.controller;

import com.example.passwordvault.model.PasswordEntry;
import com.example.passwordvault.service.PasswordEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passwords")
public class PasswordEntryController {

    @Autowired
    private PasswordEntryService service;

    @GetMapping
    public List<PasswordEntry> getAll() {
        return service.getAllEntries();
    }

    @GetMapping("/{id}")
    public PasswordEntry getById(@PathVariable Long id) {
        return service.getEntryById(id);
    }

    @PostMapping
    public PasswordEntry create(@RequestBody PasswordEntry entry) {
        return service.saveEntry(entry);
    }

    @PutMapping("/{id}")
    public PasswordEntry update(@PathVariable Long id, @RequestBody PasswordEntry entry) {
        entry.setId(id);
        return service.saveEntry(entry);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEntry(id);
    }
}
