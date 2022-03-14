package abridge.example.vocabularybooks.viewmodel.user;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import abridge.example.vocabularybooks.db.user_table.User;
import abridge.example.vocabularybooks.model.Common;
import abridge.example.vocabularybooks.model.UserRepository;


public class UserDataViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    public UserDataViewModel (Application application) {
        super(application);
        mRepository = new UserRepository(application);
    }

    //リポジトリにユーザー追加の処理を依頼する。
    public void insert(String name,String pass) {
        User user = new User(name,pass);
        mRepository.insert(user);
    }

    public void logout(Common common) {
        common.setId(0);
        common.setName(null);
    }
}

