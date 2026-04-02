package com.amit;

import com.amit.common.LeetCodeUtils;
import java.util.*;

public class Driver {

    // Real Enums used in Financial Systems
    enum RiskLevel { LOW, MEDIUM, HIGH, CRITICAL }
    enum Currency { INR, GBP, USD, EUR }

    public static void main(String[] args) {
        System.setProperty("full", "true");

        // 1. Nested Financial Data (Map of Maps/Lists)
        Map<String, Object> history = new HashMap<>();
        history.put("credit_score", 820);
        history.put("annual_income", 4500000);
        history.put("previous_loans", List.of(
                Map.of("id", "L-99", "amount", 50000, "status", "PAID"),
                Map.of("id", "L-101", "amount", 120000, "status", "ACTIVE")
        ));

        // 2. The Heavyweight DTO
        LoanApplication request = new LoanApplication(
                UUID.randomUUID().toString(),
                new User("Amit", "Barclays", new User("Arunita", "Google", null)),
                Currency.GBP,
                75000.00,
                RiskLevel.LOW,
                history,
                new int[][] { {2026, 3, 29}, {2026, 4, 15} } // Schedule dates
        );

        // 3. System Context (Simulating Headers/Trace IDs)
        Map<String, Object> sysContext = new HashMap<>();
        sysContext.put("X-Trace-ID", "BCLC-99210-XP");
        sysContext.getOrDefault("retry_count", 0);
        sysContext.put("environment", "PROD-UAT");

        // Execute the Test
        LeetCodeUtils.runTest(1, () ->
                validateAndProcessLoan(request, sysContext)
        );
    }

    public static String validateAndProcessLoan(LoanApplication app, Map<String, Object> ctx) {
        // Complex logic would go here
        return "APPROVED";
    }
}

/**
 * High-Complexity Data Transfer Object
 */
class LoanApplication {
    String applicationId;
    User applicant;
    Driver.Currency currency;
    double amount;
    Driver.RiskLevel risk;
    Map<String, Object> financialHistory;
    int[][] keyDates;

    public LoanApplication(String id, User user, Driver.Currency cur, double amt,
                           Driver.RiskLevel risk, Map<String, Object> hist, int[][] dates) {
        this.applicationId = id;
        this.applicant = user;
        this.currency = cur;
        this.amount = amt;
        this.risk = risk;
        this.financialHistory = hist;
        this.keyDates = dates;
    }
}