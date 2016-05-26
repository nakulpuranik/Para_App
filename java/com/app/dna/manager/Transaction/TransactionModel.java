package com.app.dna.manager.Transaction;

/**
 * Created by dhawal on 31/3/16.
 */
public class TransactionModel {

    private int transId, transEmpId, transOwnerId, amount, transStatus;
    private String transDescription, date;;

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public int getTransEmpId() {
        return transEmpId;
    }

    public void setTransEmpId(int transEmpId) {
        this.transEmpId = transEmpId;
    }

    public int getTransOwnerId() {
        return transOwnerId;
    }

    public void setTransOwnerId(int transOwnerId) {
        this.transOwnerId = transOwnerId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(int transStatus) {
        this.transStatus = transStatus;
    }

    public String getTransDescription() {
        return transDescription;
    }

    public void setTransDescription(String transDescription) {
        this.transDescription = transDescription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String  showAsString()
    {
        String obj;
            obj = "Date:"+this.date+"Amount:"+this.amount;
        return obj;
    }

}
