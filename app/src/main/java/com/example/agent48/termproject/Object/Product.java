package com.example.agent48.termproject.Object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Agent48 on 28.04.2018.
 */

public class Product {
    @SerializedName("ID")
    private String id;
    @SerializedName("URUN_KODU")
    private String productCode;
    @SerializedName("URUN_ADI")
    private String productName;
    @SerializedName("URETIM_TARIHI")
    private String productionDate;
    @SerializedName("SON_TUKETIM_TARIHI")
    private String consumeDate;
    @SerializedName("ADET")
    private String quantity;
    @SerializedName("BIRIM_FIYAT")
    private String price;
    @SerializedName("DURUM")
    private String state;
    @SerializedName("KAYIT")
    private String record;
    @SerializedName("TARIH")
    private String date;

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    //private String logType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductCode(){
        return productCode;
    }

    public void setProductCode(String productCode){
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getConsumeDate() {
        return consumeDate;
    }

    public void setConsumeDate(String consumeDate) {
        this.consumeDate = consumeDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getState() {return state;}

    public void setState(String state) {this.state = state;}

    public void setPrice(String price) {
        this.price = price;
    }


}
