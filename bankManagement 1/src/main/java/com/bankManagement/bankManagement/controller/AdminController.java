package com.bankManagement.bankManagement.controller;

import com.bankManagement.bankManagement.model.Bank;
import com.bankManagement.bankManagement.service.BankService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final BankService bankService;

    public AdminController(BankService bankService) {
        this.bankService = bankService;
    }


    @GetMapping("/admin/bank-data")
    public String showBankData(Model model) {
        // Fetch all bank records
        model.addAttribute("bankRecords", bankService.getAllBankRecords());
        return "admin/bank-data"; // View to display bank data
    }
    
    @GetMapping("/admin/withdraw")
    public String showWithDraw(Model model) {
        // Fetch all bank records
        model.addAttribute("bankRecords", bankService.getAllBankRecords());
        return "admin/withdraw"; // View to display bank data
    }
    

    @PostMapping("/admin/accept")
    public String acceptTransaction(@RequestParam("id") Long id) {
        bankService.acceptTransaction(id); // Update the status to Accepted
        return "redirect:/admin/bank-data"; // Redirect back to the data page
    }

    @PostMapping("/admin/delete")
    public String deleteTransaction(@RequestParam("id") Long id) {
        bankService.deleteTransaction(id); // Delete the record
        return "redirect:/admin/bank-data"; // Redirect back to the data page
    }
}
