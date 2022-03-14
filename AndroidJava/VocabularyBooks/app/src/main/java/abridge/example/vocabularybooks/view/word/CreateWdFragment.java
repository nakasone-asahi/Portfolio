package abridge.example.vocabularybooks.view.word;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import abridge.example.vocabularybooks.R;
import abridge.example.vocabularybooks.model.Common;
import abridge.example.vocabularybooks.viewmodel.word.WordViewModel;

public class CreateWdFragment extends Fragment implements View.OnClickListener {

    WordViewModel wordViewModel;
    TextView wd_errortext;
    EditText front;
    EditText rear;
    Common common;

    //必要な空のコンストラクタ
    public CreateWdFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ViewModel(LiveData)クラスとの連携
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        //Activityからアプリケーションクラスの情報(インスタンス)を受け取る
        Bundle bundle = getArguments();
        common = (Common) bundle.getSerializable("common");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //ワード作成画面をセットし、紐づくUIの項目を設定する
        View v = inflater.inflate(R.layout.fragment_createwd, container, false);
        front = (EditText) v.findViewById(R.id.wd_front_input);
        rear = (EditText) v.findViewById(R.id.wd_rear_input);
        wd_errortext = v.findViewById(R.id.wd_errorText);
        Button create_btn = (Button)v.findViewById(R.id.wd_create);
        create_btn.setOnClickListener(this);
        Button cancel_btn = (Button)v.findViewById(R.id.wd_cancel);
        cancel_btn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wd_create://作成ボタンが押された場合
                    //WDの作成処理 listener のメソッドを呼ぶ
                String ft = String.valueOf(front.getText());
                String rr = String.valueOf(rear.getText());
                if(ft.isEmpty() && rr.isEmpty()){
                    wd_errortext.setText("※前面も後面も必須です");
                }else {
                    //アカウントを作成
                    wordViewModel.insert(common,ft,rr);
                    //追加後はEditTextの中身を空にする
                    front.setText("");
                    rear.setText("");
                    getFragmentManager().popBackStack();
                }
            break;
            case R.id.wd_cancel://キャンセルボタンが押された場合
                getFragmentManager().popBackStack();
                break;
        }
    }
}