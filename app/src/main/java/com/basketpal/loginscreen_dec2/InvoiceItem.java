package com.basketpal.loginscreen_dec2;

import android.app.DownloadManager;

public class InvoiceItem {

    private int row_id;
    private String Productname;
    private int Quantity;
    private int price;
    private int InvoiceNumber;


    public InvoiceItem(int row_id ,String Productname , int Quantity , int price , int InvoiceNumber ) {
        this.row_id = row_id;
        this.Productname = Productname;
        this.Quantity = Quantity;
        this.price = price;
        this.InvoiceNumber = InvoiceNumber;
    }

    public int getrowid() {return row_id;}
    public String getProductname() {return  Productname;}
    public int getQuantity() {return Quantity;}
    public int getprice() {return price;}
    public int getInvoiceNumber() {return InvoiceNumber;}


}
