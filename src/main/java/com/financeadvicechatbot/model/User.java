package com.financeadvicechatbot.model;

import com.financeadvicechatbot.dto.ChatbotInfoDto;
import jakarta.persistence.*;

@Entity
@Table
public class User {
    // Attributes
    @Id
    private String email;
    private String password;
    //Inputs received from the user, on the chatbot page
    private String financialAim;
    private float monthlyIncome;
    private float monthlySavings;
    private float spendingNeccesities;
    private float spendingLeisure;
    private float spendingSubscriptions;
    private float spendingOther;
    //Stores the output of the ChatbotInfoDto that was made
    @Lob
    private String previousInputs;
    //Saves the output from the AI
    @Lob
    private String savedResponse;

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFinancialAim() {
        return financialAim;
    }

    public void setFinancialAim(String financialAim) {
        this.financialAim = financialAim;
    }

    public float getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public float getMonthlySavings() {
        return monthlySavings;
    }

    public void setMonthlySavings(float monthlySavings) {
        this.monthlySavings = monthlySavings;
    }

    public float getSpendingNeccesities() {
        return spendingNeccesities;
    }

    public void setSpendingNeccesities(float spendingNeccesities) {
        this.spendingNeccesities = spendingNeccesities;
    }

    public float getSpendingLeisure() {
        return spendingLeisure;
    }

    public void setSpendingLeisure(float spendingLeisure) {
        this.spendingLeisure = spendingLeisure;
    }

    public float getSpendingSubscriptions() {
        return spendingSubscriptions;
    }

    public void setSpendingSubscriptions(float spendingSubscriptions) {
        this.spendingSubscriptions = spendingSubscriptions;
    }

    public float getSpendingOther() {
        return spendingOther;
    }

    public void setSpendingOther(float spendingOther) {
        this.spendingOther = spendingOther;
    }

    public String getPreviousInputs() {
        return previousInputs;
    }

    public void setPreviousInputs(String previousInputs) {
        this.previousInputs = previousInputs;
    }

    public String getSavedResponse() {
        return savedResponse;
    }

    public void setSavedResponse(String savedResponse) {
        this.savedResponse = savedResponse;
    }
}
