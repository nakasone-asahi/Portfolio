package abridge.example.vocabularybooks.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import abridge.example.vocabularybooks.db.AppDatabase;
import abridge.example.vocabularybooks.db.user_table.User;
import abridge.example.vocabularybooks.db.user_table.UserDao;

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<User> mLoginUser;
    private int flag = 0;

    //コンストラクタ
    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.userDao();
    }

    //ログインしているユーザーを取得する
    public LiveData<User> getLoginUser(String name, String pass){
        mLoginUser = mUserDao.getLoginUser(name,pass);
        return mLoginUser;
    }

    //ユーザーを追加する
    public void insert(User user){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insertAcount(user);
        });
    }

}
