package abridge.example.vocabularybooks.view.word;

import android.content.Intent;
import android.os.Bundle;
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
import abridge.example.vocabularybooks.view.flashcard.CardActivity;
import abridge.example.vocabularybooks.view.flashcard.CreateFcFragment;
import abridge.example.vocabularybooks.viewmodel.word.WordAdapter;
import abridge.example.vocabularybooks.viewmodel.word.WordViewModel;

public class WordActivity extends AppCompatActivity implements View.OnClickListener {

    CreateWdFragment cwdfragment = new CreateWdFragment();
    EditWdFragment ewdfragment = new EditWdFragment();
    WordViewModel wordViewModel;
    Bundle args = new Bundle();
    Common common;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        setTheme(R.style.AppTheme_NoTitleBar);
        common = (Common) this.getApplication();

        //リサイクルビューのセット
        RecyclerView recyclerView = findViewById(R.id.listView_wd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //ViewModel(LiveData)クラスとの連携
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        //getLinkingWords()によって返されるLiveData
        wordViewModel.getLinkingWords(common).observe(this, words -> {
            final WordAdapter adapter = new WordAdapter(new WordAdapter.WordDiff());
            adapter.submitList(words);
            recyclerView.setAdapter(adapter);
            //リサイクルビューのレイアウトの中でクリック処理があると以下を実施
            adapter.setOnItemClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int selectItem = ((WordAdapter) adapter).getItemId();//選択されたWordのIdを取得する
                    if (view.getId() == R.id.wd_conf_btn) {
                        //単語帳ワード編集画面の表示
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        args.putString("ItemID", String.valueOf(selectItem));
                        ewdfragment.setArguments(args);
                        transaction.add(R.id.fragmentWdcreateview, ewdfragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                }
            });
        });

        //タイトルバーのボタンの設定(追加と戻る)
        ImageButton back = findViewById(R.id.backMain_Button);
        back.setOnClickListener(this);
        ImageButton add = findViewById(R.id.add_wd);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backMain_Button:
                //単語帳カード一覧画面へ戻る
                Intent cardIntent = new Intent(getApplication(), CardActivity.class);
                startActivity(cardIntent);
                finish();
                break;
            case R.id.add_wd:
                //単語帳カード作成画面の表示
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                args.putSerializable("common", common);//フラグメントにアプリケーションクラスの情報を送る
                cwdfragment.setArguments(args);
                transaction.add(R.id.fragmentWdcreateview, cwdfragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }
}
