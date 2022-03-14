package abridge.example.vocabularybooks.view.word;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import abridge.example.vocabularybooks.R;
import abridge.example.vocabularybooks.db.word_table.Word;
import abridge.example.vocabularybooks.viewmodel.word.WordViewModel;

public class EditWdFragment extends Fragment implements View.OnClickListener {

    WordViewModel wordViewModel;
    Bundle args = new Bundle();
    EditText front;
    EditText rear;
    Word editItem;
    int itemId;

    //必要な空のコンストラクタ
    public EditWdFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActivityからItemIDの値を受け取る
        itemId = Integer.parseInt(getArguments().getString("ItemID"));
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        //getUserFlashCard()によって返されるLiveData
        wordViewModel.getSelectWord(itemId).observe(this, words -> {
            //ItemIDに紐づくワードの情報を表示する
            editItem = words;
            updateTextView();
        });
    }

    private void updateTextView(){//取得元とセット先がnullじゃなければ値をセットする
        if (editItem != null){
            //値のセット
            if (front != null){front.setText(editItem.getFront());}
            if (rear != null){rear.setText(editItem.getRear());}
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //ワードの編集画面を読み込み、関連する部品をセットする
        View v = inflater.inflate(R.layout.fragment_editwd, container, false);
        front = (EditText) v.findViewById(R.id.wd_front_edit);
        rear = (EditText) v.findViewById(R.id.wd_rear_edit);
        Button update_btn = (Button)v.findViewById(R.id.wd_update);
        update_btn.setOnClickListener(this);
        Button delete_btn = (Button)v.findViewById(R.id.wd_delete);
        delete_btn.setOnClickListener(this);
        Button cancel_btn = (Button)v.findViewById(R.id.wd_cancel);
        cancel_btn.setOnClickListener(this);
        updateTextView();
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wd_update://更新ボタンが押下された場合
                //入力された情報に更新してCardActivityに戻る
                String tt = String.valueOf(front.getText());
                String rm = String.valueOf(rear.getText());
                wordViewModel.update(tt,rm,itemId);
                getFragmentManager().popBackStack();
                break;
            case R.id.wd_delete://削除ボタンが押下された場合
                //削除確認のダイアログを表示する
                DialogFragment dialogFragment = new DeleteWdDialogFragment();
                args.putInt("ItemID", itemId);//フラグメントに選択したカードのIDを送る
                dialogFragment.setArguments(args);
                dialogFragment.show(getActivity().getSupportFragmentManager(),"my_dialog");
                break;
            case R.id.wd_cancel://キャンセルボタンが押下された場合
                //WardActivityに遷移する
                getFragmentManager().popBackStack();
                break;
        }
    }
}