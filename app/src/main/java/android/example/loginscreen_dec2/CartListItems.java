package android.example.loginscreen_dec2;

public class CartListItems {

    private int row_id;
    private String Productname;
    private int Quantity;
    private int price;
    private int purchased;




    public CartListItems(int row_id ,String Productname, int Quantity , int price, int purchased ) {
        this.row_id = row_id;
        this.Productname=Productname;
        this.Quantity=Quantity;
        this.price=price;
        this.purchased=purchased;


    }

    public int getRow_id() {return row_id;}
    public String getProductname() {return  Productname;}
    public int getQuantity() {return Quantity;}
    public int getprice() {return  price;}
    public int getpurchased() {return purchased;}

}
