package android.example.loginscreen_dec2;

public class ProductListItem {


    private String productname;
    private int price;



    public ProductListItem(String productname, int price) {
        this.productname=productname;
        this.price=price;

    }

    public String getProductname() {return  productname;}
    public int getprice() {return price;}

}
