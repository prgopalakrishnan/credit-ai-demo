package com.creditai.demo;

import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CreditAdviceController {

    private final OpenAiService openAiService = new OpenAiService();

    @PostMapping("/advice")
    public String getAdvice(@RequestBody UserFinancialProfile profile) throws IOException {
        String prompt = "You are a financial advisor AI. Based on the following data, suggest 3 actions to improve credit score and financial health:\n"
                + "Credit Score: " + profile.getCreditScore()
                + "\nDebt: $" + profile.getDebt()
                + "\nIncome: $" + profile.getIncome()
                + "\nGoal: " + profile.getGoal();

        return openAiService.getAdvice(prompt);
    }
}

