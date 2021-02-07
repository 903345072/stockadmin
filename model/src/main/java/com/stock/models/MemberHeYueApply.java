package com.stock.models;

import com.stock.models.frontend.Member;

import java.util.Map;

public class MemberHeYueApply {
    private int id;
    private int member_id;
    private double total_capital;
    private double deposit;
    private double leverage_money;
    private double loss_warning_line;
    private double loss_sell_line;
    private double interest_rate;
    private double interest;
    private int capital_used_time;
    private double repare_capital;
    private int apply_state;
    private String apply_time;
    private Map member;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public double getTotal_capital() {
        return total_capital;
    }

    public void setTotal_capital(double total_capital) {
        this.total_capital = total_capital;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getLeverage_money() {
        return leverage_money;
    }

    public void setLeverage_money(double leverage_money) {
        this.leverage_money = leverage_money;
    }

    public double getLoss_warning_line() {
        return loss_warning_line;
    }

    public void setLoss_warning_line(double loss_warning_line) {
        this.loss_warning_line = loss_warning_line;
    }

    public double getLoss_sell_line() {
        return loss_sell_line;
    }

    public void setLoss_sell_line(double loss_sell_line) {
        this.loss_sell_line = loss_sell_line;
    }

    public double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getCapital_used_time() {
        return capital_used_time;
    }

    public void setCapital_used_time(int capital_used_time) {
        this.capital_used_time = capital_used_time;
    }

    public double getRepare_capital() {
        return repare_capital;
    }

    public void setRepare_capital(double repare_capital) {
        this.repare_capital = repare_capital;
    }

    public int getApply_state() {
        return apply_state;
    }

    public void setApply_state(int apply_state) {
        this.apply_state = apply_state;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public Map getMember() {
        return member;
    }

    public void setMember(Map member) {
        this.member = member;
    }
}
