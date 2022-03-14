package abridge.example.vocabularybooks.view.flashcard;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import abridge.example.vocabularybooks.R;
import abridge.example.vocabularybooks.model.Common;
import abridge.example.vocabularybooks.view.main.MainActivity;
import abridge.example.vocabularybooks.view.word.WordActivity;
import abridge.example.vocabularybooks.viewmodel.flashcard.FlashCardAdapter;
import abridge.example.vocabularybooks.viewmodel.flashcard.FlashCardViewModel;

public class CardActivity extends AppCompatActivity implements View.OnClickListener  {

    CreateFcFragment cfcfragment = new CreateFcFragment();
    EditFcFragment efcfragment = new EditFcFragment();
    FlashCardViewModel flashCardViewModel;
    Bundle args = new Bundle();
    Common common;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //単語帳カード一覧のレイアウトをセットする
        setContentView(R.layout.activity_flashcards);
        //タイトルバーの非表示を設定する
        setTheme(R.style.AppTheme_NoTitleBar);
        //アプリケーションクラスを取得する
        common = (Common) this.getApplication();

        //リサイクルビューのセット
        RecyclerView recyclerView = findViewById(R.id.listView_fc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //ViewModel(LiveData)クラスとの接続
        flashCardViewModel = new ViewModelProvider(this).get(FlashCardViewModel.class);
        //getLinkingFlashCards()によって返されるLiveData
        flashCardViewModel.getLinkingFlashCards(common).observe(this, flashCards -> {
            final FlashCardAdapter adapter = new FlashCardAdapter(new FlashCardAdapter.FlashCardDiff());
            adapter.submitList(flashCards);
            recyclerView.setAdapter(adapter);
            //リサイクルビューのレイアウトの中でクリック処理があると以下を実施
            adapter.setOnItemClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int selectItem = ((FlashCardAdapter) adapter).getItemId();//選択した単語帳カードのIDを取得する
                    common.setSelectFcId(selectItem);//アプリケーションクラスにIDをセットする
                    switch (view.getId()) {
                        case R.id.fc_play_btn://playボタンが押された場合
                            //word画面へ遷移する
                            Intent wordIntent = new Intent(getApplication(), WordActivity.class);
                            startActivity(wordIntent);
                            finish();
                            break;
                        case R.id.fc_conf_btn://confボタンが押された場合
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            args.putInt("ItemID", selectItem);//フラグメントに選択したカードのIDを送る
                            args.putSerializable("common",common);
                            efcfragment.setArguments(args);
                            transaction.add(R.id.FcEditFragmentView, efcfragment);//単語カードの編集画面に遷移する
                            transaction.addToBackStack(null);
                            transaction.commit();
                            break;
                    }
                }
            });
        });

        //各ボタンの設定(タイトルバー)
        ImageButton back = findViewById(R.id.backMain_Button);
        back.setOnClickListener(this);
        ImageButton add = findViewById(R.id.add_fc);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backMain_Button:
                //ホーム画面へ戻る
                Intent mainIntent = new Intent(getApplication(), MainActivity.class);
                startActivity(mainIntent);
                finish();
            break;
            case R.id.add_fc:
                //単語帳カード作成画面の表示
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                args.putSerializable("common", common);//フラグメントにアプリケーションクラスの情報を送る
                cfcfragment.setArguments(args);
                transaction.add(R.id.fragmentFCcreateview, cfcfragment);
                transaction.addToBackStack(null);
                transaction.commit();
            break;
        }
    }
}