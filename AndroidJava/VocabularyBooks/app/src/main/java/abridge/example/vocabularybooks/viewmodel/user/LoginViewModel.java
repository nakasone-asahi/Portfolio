package abridge.example.vocabularybooks.viewmodel.user;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import abridge.example.vocabularybooks.db.user_table.User;
import abridge.example.vocabularybooks.model.Common;
import abridge.example.vocabularybooks.model.UserRepository;

public class LoginViewModel extends AndroidViewModel {

    private UserRepository mRepository;//『WordRepository』クラスの読み込み//クラスto変数の宣言
    private LiveData<User> mLoginUser = null;


    public LoginViewModel (Application application) {
        super(application);//親クラスに値を渡す。
        mRepository = new UserRepository(application);//リポジトリのインスタンス化(リポジトリにてデータベースとの接続を行う)
    }

    public LiveData<User> getLoginUser(String lname,String lpass) {
        mLoginUser = mRepository.getLoginUser(lname,lpass);
        return mLoginUser;
    }

}
