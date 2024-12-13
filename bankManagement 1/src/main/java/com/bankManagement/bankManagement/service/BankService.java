package com.bankManagement.bankManagement.service;

import com.bankManagement.bankManagement.model.Bank;
import com.bankManagement.bankManagement.repository.BankRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public void depositMoney(Bank bank) {
        // Set default status if not already set
        if (bank.getStatus() == null) {
            bank.setStatus("Pending");
        }

        // Save the Bank entity to the database
        bankRepository.save(bank);
    }
    public Double getTotalAcceptedAmountByUser(Long userId) {
        // Query the repository to sum the amounts with status 'Accepted' for the given user
        return bankRepository.sumAcceptedAmountByUserId(userId).orElse(0.0);
    }


    public List<Bank> getAllBankRecords() {
        return bankRepository.findAll(); // Fetch all records
    }

    public void acceptTransaction(Long id) {
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Transaction ID"));
        bank.setStatus("Accepted");
        bankRepository.save(bank); // Update the record
    }

    public void deleteTransaction(Long id) {
        bankRepository.deleteById(id); // Delete the record
    }

    public Bank getBankDetailsByUser(Long userId) {
        return bankRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Bank details not found for user"));
    }

    public void saveBankDetails(Bank bank) {
        bankRepository.save(bank);
    }

    
}

