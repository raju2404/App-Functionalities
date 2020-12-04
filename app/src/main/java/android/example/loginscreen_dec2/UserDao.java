package android.example.loginscreen_dec2;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);
}
