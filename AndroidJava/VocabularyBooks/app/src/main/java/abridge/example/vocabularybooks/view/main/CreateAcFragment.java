package abridge.example.vocabularybooks.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import abridge.example.vocabularybooks.R;
import abridge.example.vocabularybooks.view.login.LoginActivity;
import abridge.example.vocabularybooks.viewmodel.user.UserDataViewModel;

public class CreateAcFragment extends Fragment implements View.OnClickListener {

    EditText username;
    EditText password;
    TextView ac_errortext;
    UserDataViewModel userViewModel;

    public CreateAcFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //ViewModel(LiveData)クラスとの接続
        userViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);



        //アカウント作成画面をセットし、それに関連する部品を設定する
        View v = inflater.inflate(R.layout.fragment_createac, container, false);
        username = (EditText) v.findViewById(R.id.ac_username_input);
        password = (EditText) v.findViewById(R.id.ac_pass_input);
        ac_errortext = v.findViewById(R.id.ac_errorText);
        Button crbtn = (Button)v.findViewById(R.id.ac_create);
        crbtn.setOnClickListener(this);
        Button cabtn = (Button)v.findViewById(R.id.ac_cancel);
        cabtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_create:
                //listener のメソッドを呼ぶ
                String name = String.valueOf(username.getText());
                String pass = String.valueOf(password.getText());
                if(name.isEmpty() || pass.isEmpty()){
                    ac_errortext.setText("※ユーザー名とパスワードは必須です");
                }else {
                    if(name.equals("false")){//falseは使用できない
                        ac_errortext.setText(" 'false' は使用できません。");
                    }else{
                        //アカウントを作成
                        userViewModel.insert(name,pass);
                        Toast.makeText(getActivity() , "アカウントを作成しました。", Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(loginIntent);
                    }
                }
                break;
            case R.id.ac_cancel:
                getFragmentManager().popBackStack();
                break;
        }
    }
}