package android.example.loginscreen_dec2;

public class RatingItem {
    private int id;
    private String Productname;

    public RatingItem(int row_id ,String Productname ) {
        this.id = row_id;
        this.Productname = Productname;
    }

    public int getid() {return id;}
    public String getProductname() {return  Productname;}

}
