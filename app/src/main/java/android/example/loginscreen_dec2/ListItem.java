package android.example.loginscreen_dec2;

public class ListItem {

    private String productname;
    private String Location;

    public ListItem(String productname, String Location) {
        this.productname=productname;
        this.Location=Location;
    }

    public String getProductname() {return  productname;}
    public String getLocation() {return Location;}


}
