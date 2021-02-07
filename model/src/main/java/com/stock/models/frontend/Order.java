package com.stock.models.frontend;

import annotation.checkTradeTime;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Order {

   // @checkTradeTime
    private String time;

    private int stock_status;

    public int getStock_status() {
        return stock_status;
    }

    public void setStock_status(int stock_status) {
        this.stock_status = stock_status;
    }

    private int id;
    @NotNull(message = "合约id不能为空")
    private int member_heyue_id;
    private int member_id;

    private String contract_no;



    public String getContract_no() {
        return contract_no;
    }

    public void setContract_no(String contract_no) {
        this.contract_no = contract_no;
    }

    private String stock_code;
    private double profit;
    private String stock_name;
    @NotNull(message = "买入价不能为空")
    private double buy_price;
    private double sell_price;
    @NotNull(message = "交易方向不能为空")
    private int trade_direction;
    private int buy_hand;
    private String make_order_date;
    @NotNull(message = "委托方式不能为空")
    private int entrust_way;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMember_heyue_id() {
        return member_heyue_id;
    }

    public void setMember_heyue_id(int member_heyue_id) {
        this.member_heyue_id = member_heyue_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public double getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(double buy_price) {
        this.buy_price = buy_price;
    }

    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }

    public int getTrade_direction() {
        return trade_direction;
    }

    public void setTrade_direction(int trade_direction) {
        this.trade_direction = trade_direction;
    }

    public int getBuy_hand() {
        return buy_hand;
    }

    public void setBuy_hand(int buy_hand) {
        this.buy_hand = buy_hand;
    }

    public String getMake_order_date() {
        return make_order_date;
    }

    public void setMake_order_date(String make_order_date) {
        this.make_order_date = make_order_date;
    }

    public int getEntrust_way() {
        return entrust_way;
    }

    public void setEntrust_way(int entrust_way) {
        this.entrust_way = entrust_way;
    }
}
