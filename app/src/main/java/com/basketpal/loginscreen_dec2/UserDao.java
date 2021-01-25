package com.basketpal.loginscreen_dec2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Query(" SELECT * from Users where Username=(:Username) and Password= (:Password) ")
    UserEntity login(String Username, String Password);

}
