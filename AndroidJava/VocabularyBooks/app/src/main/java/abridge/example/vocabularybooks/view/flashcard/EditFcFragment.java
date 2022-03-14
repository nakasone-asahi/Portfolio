package abridge.example.vocabularybooks.view.flashcard;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import abridge.example.vocabularybooks.R;
import abridge.example.vocabularybooks.db.flashcard_table.FlashCard;
import abridge.example.vocabularybooks.viewmodel.flashcard.FlashCardViewModel;

public class EditFcFragment extends Fragment implements View.OnClickListener {

    FlashCardViewModel flashCardViewModel;
    Bundle args = new Bundle();
    FlashCard editItem;
    EditText remarks;
    EditText title;
    int itemId;

    //必要な空のコンストラクタ
    public EditFcFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActivityからItemIDの値を受け取る
        itemId = getArguments().getInt("ItemID");
        flashCardViewModel = new ViewModelProvider(this).get(FlashCardViewModel.class);
        //getUserFlashCard()によって返されるLiveData
        flashCardViewModel.getSelectFlashCard(itemId).observe(this, flashCards -> {
            //ItemIDに紐づく単語帳カードの情報を表示する。
            editItem = flashCards;
            updateTextView();
        });
    }

    private void updateTextView(){//ItemIDに紐づく単語帳カードの情報を表示する。
        if (editItem != null){
            //表示先が準備出来ていると値をセットする
            if (title != null){title.setText(editItem.getTitle());}
            if (remarks != null){ remarks.setText(editItem.getRemarks()); }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //単語帳カードの編集画面の表示とそれに基づくUIの設定
        View v = inflater.inflate(R.layout.fragment_editfc, container, false);
        title = (EditText) v.findViewById(R.id.fc_title_edit);
        remarks = (EditText) v.findViewById(R.id.fc_sub_remarks_edit);
        Button update_btn = (Button)v.findViewById(R.id.fc_update);
        update_btn.setOnClickListener(this);
        Button delete_btn = (Button)v.findViewById(R.id.fc_delete);
        delete_btn.setOnClickListener(this);
        Button cancel_btn = (Button)v.findViewById(R.id.fc_cancel);
        cancel_btn.setOnClickListener(this);
        updateTextView();
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fc_update://更新ボタンが押下された場合
                //入力された情報に更新してCardActivityに戻る
                String tt = String.valueOf(title.getText());
                String rm = String.valueOf(remarks.getText());
                flashCardViewModel.update(tt,rm,itemId);
                getFragmentManager().popBackStack();
                break;
            case R.id.fc_delete://削除ボタンが押下された場合
                //削除確認のダイアログを表示する
                DialogFragment dialogFragment = new DeleteFcDialogFragment();
                args.putInt("ItemID", itemId);//フラグメントに選択したカードのIDを送る
                dialogFragment.setArguments(args);
                dialogFragment.show(getActivity().getSupportFragmentManager(),"my_dialog");
                break;
            case R.id.fc_cancel://キャンセルボタンが押下された場合
                //CardActivityに遷移する
                getFragmentManager().popBackStack();
                break;
        }
    }
}