package android.example.loginscreen_dec2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name="Password")
    String Password;

    @ColumnInfo(name="Username")
    String Username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
