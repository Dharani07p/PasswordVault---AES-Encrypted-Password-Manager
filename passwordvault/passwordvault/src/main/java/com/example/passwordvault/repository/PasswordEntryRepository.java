package com.example.passwordvault.repository;

import com.example.passwordvault.model.PasswordEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordEntryRepository extends JpaRepository<PasswordEntry, Long> {
}
