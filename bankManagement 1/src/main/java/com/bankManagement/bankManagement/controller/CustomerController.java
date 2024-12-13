package com.bankManagement.bankManagement.controller;

import com.bankManagement.bankManagement.model.Bank;
import com.bankManagement.bankManagement.model.User;
import com.bankManagement.bankManagement.service.BankService;
import com.bankManagement.bankManagement.service.UserService;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {

    private final BankService bankService;
    private final UserService userService; // To fetch accountNo from User

    public CustomerController(BankService bankService, UserService userService) {
        this.bankService = bankService;
        this.userService = userService;
    }

    @PostMapping("/customer/deposite")
    public String depositMoney(@ModelAttribute("amount") Double amount, HttpSession session) {
        // Retrieve the user from the session
        User user = (User) session.getAttribute("user");

        if (user == null) {
            throw new IllegalStateException("User is not logged in");
        }

        // Fetch accountNo based on the userId
        String accountNo = user.getAccountNo();
        if (accountNo == null || accountNo.isEmpty()) {
            throw new IllegalStateException("Account number not found for user");
        }

        // Prepare the Bank entity
        Bank bank = new Bank();
        bank.setAccountNo(accountNo);
        bank.setAmount(amount);
        bank.setStatus("Pending");

        // Format LocalDateTime to String and set deposit time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        bank.setDepositeAt(LocalDateTime.now().format(formatter));

        bank.setUser(user);

        // Save the Bank entity using the service
        bankService.depositMoney(bank);

        // Redirect to the customer dashboard after successful deposit
        return "redirect:/customer/dashboard";
    }

    @GetMapping("/customer/deposite")
    public String showDepositPage() {
        return "customer/deposite"; // Returns the deposit.html view
    }

    @PostMapping("/customer/withdraw")
    public String withdrawMoney(@RequestParam("withdrawAmount") Double withdrawAmount, HttpSession session) {
        // Retrieve user from session
        User user = (User) session.getAttribute("user");
    
        if (user == null) {
            throw new IllegalStateException("User is not logged in");
        }
    
        // Ensure the user has an accountNo
        String accountNo = user.getAccountNo();
        if (accountNo == null || accountNo.isEmpty()) {
            throw new IllegalStateException("Account number not found for user");
        }
    
        // Fetch the user's total accepted amount
        Double currentBalance = bankService.getTotalAcceptedAmountByUser(user.getId());
        if (currentBalance == null || currentBalance < withdrawAmount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
    
        // Create a new Bank entity for the withdrawal
        Bank bank = new Bank();
        bank.setAccountNo(accountNo);
        bank.setAmount(-withdrawAmount); // Negative value for withdrawal
        bank.setStatus("Accepted"); // Mark as pending until approval
        bank.setUser(user);
        bank.setDepositeAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    
        // Save the Bank entity
        bankService.depositMoney(bank);
    
        return "redirect:/customer/dashboard";
    }
    
    @GetMapping("/customer/withdraw")
    public String showWithdrawPage() {
        return "customer/withdraw"; // Returns the deposit.html view
    }

    @PostMapping("/customer/transfer")
    public String transferMoney(@RequestParam("accountNumber") String accountNumber,
                                @RequestParam("amount") Double amount,
                                HttpSession session) {
        // Retrieve the current user from the session
        User currentUser = (User) session.getAttribute("user");
    
        if (currentUser == null) {
            throw new IllegalStateException("User is not logged in");
        }
    
        
     
         // Create a new Bank entity for the withdrawal
         Bank bank = new Bank();
         bank.setAccountNo(accountNumber);
         bank.setAmount(-amount); // Negative value for withdrawal
         bank.setStatus("Accepted"); // Mark as pending until approval
         bank.setUser(currentUser);
         bank.setDepositeAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
     
         // Save the Bank entity
         bankService.depositMoney(bank);
      

        // Retrieve the recipient user by account number
        User recipientUser = userService.findByAccountNo(accountNumber);
        if (recipientUser == null) {
            throw new IllegalArgumentException("Recipient account number is invalid");
        }
    
        
    
        // Add amount to recipient and record the transaction in the Bank table
        Bank recipientBank = new Bank();
        recipientBank.setAccountNo(accountNumber);
        recipientBank.setAmount(amount);
        recipientBank.setStatus("Accepted");
        recipientBank.setDepositeAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        recipientBank.setUser(recipientUser);
        bankService.depositMoney(recipientBank);
    
        return "redirect:/customer/dashboard";
    }
    

    @GetMapping("/customer/transfer")
    public String showTransferPage() {
        return "customer/transfer";
    }

    
}
