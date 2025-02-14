package com.financeadvicechatbot.dto;

public class ChatbotInfoDto {
    private String financialAim;
    private float monthlyIncome;
    private float monthlySavings;
    private float spendingNeccesities;
    private float spendingLeisure;
    private float spendingSubscriptions;
    private float spendingOther;

    @Override
    public String toString() {
        return "My Financial Aim is : " + financialAim +
                ", My Monthly Income is : " + monthlyIncome +
                ", My Monthly Savings are : " + monthlySavings +
                ", I spend this much on neccesities each month : " + spendingNeccesities +
                ", I spend this much on Leisure each month : " + spendingLeisure +
                ", I spend this much on Subscriptions each month : " + spendingSubscriptions +
                ", I spend this much on Other each month : " + spendingOther + ". Give me Advice.";
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
}
