package abridge.example.vocabularybooks.view.flashcard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import abridge.example.vocabularybooks.R;
import abridge.example.vocabularybooks.model.Common;
import abridge.example.vocabularybooks.viewmodel.flashcard.FlashCardViewModel;

public class CreateFcFragment extends Fragment implements View.OnClickListener {

    FlashCardViewModel flashCardViewModel;
    TextView fc_errortext;
    EditText remarks;
    EditText title;
    Common common;

    //必要な空のコンストラクタ
    public CreateFcFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Activityからアプリケーションクラスの情報(インスタンス)を受け取る
        Bundle bundle = getArguments();
        common = (Common) bundle.getSerializable("common");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //単語帳カード作成画面の表示とそれに関連するUIの設定
        View v = inflater.inflate(R.layout.fragment_createfc, container, false);
        title = (EditText) v.findViewById(R.id.fc_title_input);
        remarks = (EditText) v.findViewById(R.id.fc_sub_remarks_input);
        fc_errortext = v.findViewById(R.id.fc_errorText);
        Button create_btn = (Button)v.findViewById(R.id.fc_create);
        create_btn.setOnClickListener(this);
        Button cancel_btn = (Button)v.findViewById(R.id.fc_cancel);
        cancel_btn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fc_create://作成ボタンが押された場合
                //単語帳カードを作成する
                String tt = String.valueOf(title.getText());
                String rm = String.valueOf(remarks.getText());
                if(tt.isEmpty()){//タイトルが空だったら
                    //エラー文言をセットする
                    fc_errortext.setText("※タイトルは必須です");
                }else {
                    //単語カードを追加する
                    flashCardViewModel = new ViewModelProvider(this).get(FlashCardViewModel.class);
                    flashCardViewModel.insert(common, tt, rm);
                    //追加後はEditTextの中身を空にする
                    title.setText("");
                    remarks.setText("");
                    getFragmentManager().popBackStack();
                }
            break;
            case R.id.fc_cancel://キャンセルボタンが押された場合
                //CardActivityへ戻る
                getFragmentManager().popBackStack();
            break;
        }
    }
}