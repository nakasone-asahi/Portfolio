package abridge.example.vocabularybooks.db.user_table;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface UserDao {

    @Query("SELECT * FROM users WHERE user_name IN (:name) AND user_pass IN(:pass)")
    LiveData<User> getLoginUser(String name, String pass);

    @Query("SELECT user_id FROM users WHERE user_name IN (:name)")
    int getUserId(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAcount(User user);

}
