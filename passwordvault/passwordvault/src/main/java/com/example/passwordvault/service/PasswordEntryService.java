package com.example.passwordvault.service;

import com.example.passwordvault.model.PasswordEntry;
import com.example.passwordvault.repository.PasswordEntryRepository;
import com.example.passwordvault.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PasswordEntryService {

    @Autowired
    private PasswordEntryRepository repository;

    public List<PasswordEntry> getAllEntries() {
        return repository.findAll().stream().map(this::decryptPassword).collect(Collectors.toList());
    }

    public PasswordEntry getEntryById(Long id) {
        PasswordEntry entry = repository.findById(id).orElse(null);
        return entry != null ? decryptPassword(entry) : null;
    }

    public PasswordEntry saveEntry(PasswordEntry entry) {
        entry.setPassword(AESUtil.encrypt(entry.getPassword()));
        return repository.save(entry);
    }

    public void deleteEntry(Long id) {
        repository.deleteById(id);
    }

    // Helper to decrypt password before sending it back
    private PasswordEntry decryptPassword(PasswordEntry entry) {
        entry.setPassword(AESUtil.decrypt(entry.getPassword()));
        return entry;
    }
}
