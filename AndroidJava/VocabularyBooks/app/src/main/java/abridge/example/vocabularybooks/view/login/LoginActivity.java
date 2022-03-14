package abridge.example.vocabularybooks.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import abridge.example.vocabularybooks.R;
import abridge.example.vocabularybooks.db.user_table.User;
import abridge.example.vocabularybooks.model.Common;
import abridge.example.vocabularybooks.view.flashcard.DeleteFcDialogFragment;
import abridge.example.vocabularybooks.view.main.MainActivity;
import abridge.example.vocabularybooks.viewmodel.user.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginViewModel loginViewModel;
    Observer<User> loginUserObserver;
    private ProgressBar progressBar;
    private Common common;
    Button loginButton;
    Button gustlogin;
    EditText username;
    EditText password;
    TextView errortext;

    //Activityの作成処理
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//親クラスの実行
        setTheme(R.style.AppTheme_NoTitleBar);//タイトルバーの非表示
        setContentView(R.layout.activity_login);//表示する画面の設定：ログイン画面
        common = (Common) this.getApplication();//APPLICATIONクラスの読み込み
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);//LiveDataクラスとの接続

        //UIの読み込み：ボタンやテキスト関連
        loginButton = (Button) findViewById(R.id.loginButton);
        gustlogin = (Button) findViewById(R.id.gustLogin);
        progressBar = findViewById(R.id.progressbar);
        username = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        errortext = (TextView) findViewById(R.id.errorText);
        progressBar.setVisibility(android.widget.ProgressBar.INVISIBLE);
        loginButton.setOnClickListener(this);
        gustlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton://ログインボタン押下時
                String input_name = String.valueOf(username.getText());
                String input_pass = String.valueOf(password.getText());
                //DB接続時にインジケーターの表示
                progressBar.setVisibility(android.widget.ProgressBar.VISIBLE);
                if (textcheck(input_name,input_pass)) {//空欄チェックが通ると下を実施
                    // getUserFlashCard()によって返されるLiveData
                    loginViewModel.getLoginUser(input_name,input_pass).observe(this, loginUser -> {
                        if(loginUser != null){
                            common.setId(loginUser.getUser_id());
                            common.setName(loginUser.getUser_name());
                            // MainActivityに遷移させる
                            Intent intent = new Intent(getApplication(), MainActivity.class);
                            intent.putExtra("Dialogflag",1);
                            startActivity(intent);
                            finish();
                        }else{
                            errortext.setText("※ユーザ名 または パスワードが間違っています");
                        }
                        progressBar.setVisibility(android.widget.ProgressBar.INVISIBLE);//インジケーターの非表示
                    });
                }
            break;
            case R.id.gustLogin://ゲストログインボタン押下時
                //DB接続時にインジケーターの表示
                progressBar.setVisibility(android.widget.ProgressBar.VISIBLE);
                //getUserFlashCard()によって返されるLiveData
                loginViewModel.getLoginUser("Sample","Sample").observe(this, loginUser -> {
                    if(loginUser != null){
                        common.setId(loginUser.getUser_id());
                        common.setName(loginUser.getUser_name());
                        // MainActivityに遷移させる
                        Intent intent = new Intent(getApplication(), MainActivity.class);
                        intent.putExtra("Dialogflag",1);
                        startActivity(intent);
                        finish();
                        progressBar.setVisibility(android.widget.ProgressBar.INVISIBLE);//インジケーターの非表示
                    }
                });
            break;
        }
    }

    public boolean textcheck(String username,String password){
        if (username.isEmpty() || password.isEmpty()) {
            errortext.setText("※ユーザ名 と パスワードは必須項目です");
            return false;
        } else {
            errortext.setText("");//エラーメッセージ欄のクリア。
            return true;
        }
    }
}
