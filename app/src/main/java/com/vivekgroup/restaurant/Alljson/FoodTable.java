
package com.vivekgroup.restaurant.Alljson;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FoodTable {

    @SerializedName("Table_id")
    @Expose
    private String tableId;
    @SerializedName("User_id")
    @Expose
    private String userId;
    @SerializedName("TBook_date")
    @Expose
    private String tBookDate;
    @SerializedName("TBook_time")
    @Expose
    private String tBookTime;
    @SerializedName("TPayment_type")
    @Expose
    private String tPaymentType;
    @SerializedName("T_Amount")
    @Expose
    private String tAmount;
    @SerializedName("User_name")
    @Expose
    private String userName;
    @SerializedName("Count_table")
    @Expose
    private String countTable;
    @SerializedName("Person")
    @Expose
    private String person;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTBookDate() {
        return tBookDate;
    }

    public void setTBookDate(String tBookDate) {
        this.tBookDate = tBookDate;
    }

    public String getTBookTime() {
        return tBookTime;
    }

    public void setTBookTime(String tBookTime) {
        this.tBookTime = tBookTime;
    }

    public String getTPaymentType() {
        return tPaymentType;
    }

    public void setTPaymentType(String tPaymentType) {
        this.tPaymentType = tPaymentType;
    }

    public String getTAmount() {
        return tAmount;
    }

    public void setTAmount(String tAmount) {
        this.tAmount = tAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCountTable() {
        return countTable;
    }

    public void setCountTable(String countTable) {
        this.countTable = countTable;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

}
