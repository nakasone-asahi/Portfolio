package abridge.example.vocabularybooks.db.user_table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index("user_id"),@Index(value = {"user_id","user_name"}, unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "user_id")
    private int user_id;

    @ColumnInfo(name = "user_name")
    private String user_name;

    @ColumnInfo(name = "user_pass")
    private String user_pass;

    public User(String user_name, String user_pass) {
        this.user_name = user_name;
        this.user_pass =user_pass;
    }

    public int getUser_id() { return user_id; }
    public void setUser_id(int user_id){this.user_id = user_id;}

    public String getUser_name() { return user_name; }
    public void setUser_name(String name){this.user_name = name;}

    public String getUser_pass() {
        return user_pass;
    }
    public void setUser_pass(String pass){this.user_pass = pass;}

}
