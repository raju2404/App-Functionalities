package android.example.loginscreen_dec2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name="Username")
    String Username;

    @ColumnInfo(name="Password")
    String Password;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public Integer getId() {
        return id;
    }
    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }


}
