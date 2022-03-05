package de.androidcrypto.recyclerviewstoreloadobjects;

import java.io.Serializable;

public class StockMovementsModal implements Serializable {
    //below line is use for date (yyyy-mm-dd).
    private String date;
    //below line is use for date as unix timestamp.
    private String dateUnix;
    //below line is a variable for stock name.
    private String stockName;
    //below line is use for stock isin.
    private String stockIsin;
    //below line is use for direction (+ or -).
    private String direction;
    //below line is use for amountEuro.
    private String amountEuro;
    //below line is use for number of shares (positive when bying, negative when selling).
    private String numberShares;
    //below line is use for the bank of the account.
    private String bank;
    // wertpapierdepot, e.g. Maxblue 01
    private String securitiesAccount;
    // summe der anteile im bestand
    private String totalNumberShares;
    // summe der kaufkosten
    private String totalPurchaseCosts;
    //below line is use for a note.
    private String note;
    // gültigkeit der daten für monat bzw jahr
    private String dataYear, dataMonth;
    // ist der datensatz aktiv
    private String active; // true or false

    //below line we are creating constructor class.
    //inside constructor class we are not passing our id because it is incrementing automatically
    public StockMovementsModal(String date, String dateUnix,
                               String stockName, String stockIsin,
                               String direction, String amountEuro,
                               String numberShares, String bank,
                               String securitiesAccount,
                               String note, String totalNumberShares,
                               String totalPurchaseCosts,
                               String dataYear, String dataMonth,
                               String active) {
        this.date = date;
        this.dateUnix = dateUnix;
        this.stockName = stockName;
        this.stockIsin = stockIsin;
        this.direction = direction;
        this.amountEuro = amountEuro;
        this.numberShares = numberShares;
        this.bank = bank;
        this.securitiesAccount = securitiesAccount;
        this.note = note;
        this.totalNumberShares = totalNumberShares;
        this.totalPurchaseCosts = totalPurchaseCosts;
        this.dataYear = dataYear;
        this.dataMonth = dataMonth;
        this.active = active;
    }

    //on below line we are creating getter and setter methods.

    public String getSecuritiesAccount() {
        return securitiesAccount;
    }

    public void setSecuritiesAccount(String securitiesAccount) {
        this.securitiesAccount = securitiesAccount;
    }

    public String getTotalNumberShares() {
        return totalNumberShares;
    }

    public void setTotalNumberShares(String totalNumberShares) {
        this.totalNumberShares = totalNumberShares;
    }

    public String getTotalPurchaseCosts() {
        return totalPurchaseCosts;
    }

    public void setTotalPurchaseCosts(String totalPurchaseCosts) {
        this.totalPurchaseCosts = totalPurchaseCosts;
    }

    public String getDataYear() {
        return dataYear;
    }

    public void setDataYear(String dataYear) {
        this.dataYear = dataYear;
    }

    public String getDataMonth() {
        return dataMonth;
    }

    public void setDataMonth(String dataMonth) {
        this.dataMonth = dataMonth;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateUnix() {
        return dateUnix;
    }

    public void setDateUnix(String dateUnix) {
        this.dateUnix = dateUnix;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockIsin() {
        return stockIsin;
    }

    public void setStockIsin(String stockIsin) {
        this.stockIsin = stockIsin;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAmountEuro() {
        return amountEuro;
    }

    public void setAmountEuro(String amountEuro) {
        this.amountEuro = amountEuro;
    }

    public String getNumberShares() {
        return numberShares;
    }

    public void setNumberShares(String numberShares) {
        this.numberShares = numberShares;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
