package android.example.loginscreen_dec2;

import android.app.Application;

public class GlobalClass extends Application {

    private String UserID;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
