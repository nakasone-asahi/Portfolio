package abridge.example.vocabularybooks.view.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import abridge.example.vocabularybooks.R;
import abridge.example.vocabularybooks.model.Common;
import abridge.example.vocabularybooks.view.login.LoginActivity;
import abridge.example.vocabularybooks.view.flashcard.CardActivity;
import abridge.example.vocabularybooks.view.login.LoginSuccessDialogFragment;
import abridge.example.vocabularybooks.viewmodel.user.UserDataViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CreateAcFragment fragment = new CreateAcFragment();
    UserDataViewModel userViewModel;
    Bundle args = new Bundle();
    TextView username_edit;
    Common common;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppTheme_NoTitleBar);

        common = (Common) this.getApplication();
        username_edit = findViewById(R.id.userName);
        username_edit.setText(common.getName()+"でログイン中");

        //ログイン時のダイアログ設定
        int dialogFlag = getIntent().getIntExtra("Dialogflag",0);
        if (dialogFlag == 1){
            //ログインの通知ダイアログを表示する
            LoginSuccessDialogFragment dialogFragment = new LoginSuccessDialogFragment();
            args.putString("LoginName", common.getName());//フラグメントに選択したカードのIDを送る
            dialogFragment.setArguments(args);
            dialogFragment.show(getSupportFragmentManager(),"my_dialog");
        }

        //ViewModel(LiveData)クラスとの接続
        userViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);

        //各ボタンの設定
        Button myword = findViewById(R.id.myWords);
        myword.setOnClickListener(this);
        Button accreate = findViewById(R.id.ac_create);
        accreate.setOnClickListener(this);
        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myWords:
                //単語帳一覧画面へ遷移
                Intent mywordIntent = new Intent(getApplication(), CardActivity.class);
                startActivity(mywordIntent);
                finish();
                break;
            case R.id.ac_create:
                //アカウント画面の表示
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentACcreateview, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.logout:
                //ログアウト(ログイン画面に遷移)
                userViewModel.logout(common);
                Intent loginIntent = new Intent(getApplication(), LoginActivity.class);
                startActivity(loginIntent);
                finish();
                break;
        }
    }
}