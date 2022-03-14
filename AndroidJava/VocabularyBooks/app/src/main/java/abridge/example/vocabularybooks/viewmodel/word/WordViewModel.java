package abridge.example.vocabularybooks.viewmodel.word;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import abridge.example.vocabularybooks.db.word_table.Word;
import abridge.example.vocabularybooks.model.Common;
import abridge.example.vocabularybooks.model.WordRepository;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mSpecifiedWord;
    private LiveData<Word> mSelectWord;

    public WordViewModel (Application application) {
        super(application);//親クラスに値を渡す。
        mRepository = new WordRepository(application);//リポジトリのインスタンス化(リポジトリにてデータベースとの接続を行う)
    }

    //選択した単語カードに紐づくワードリストを取得する
    public LiveData<List<Word>> getLinkingWords(Common common){
        mSpecifiedWord = mRepository.getLinkingWords(common.getSelectFcId());
        return mSpecifiedWord;
    }

    //選択したワードを取得する
    public LiveData<Word> getSelectWord(int selectId){
        mSelectWord = mRepository.getSelectWord(selectId);
        return mSelectWord;
    }

    //リポジトリに追加の処理を依頼する。
    public void insert(Common common, String front, String rear) {
        int fcid = common.getSelectFcId();
        Word word = new Word(fcid,front,rear);
        mRepository.insert(word);
    }

    //リポジトリに更新の処理を依頼する。
    public void update(String front, String rear,int s_wd_id) {
        mRepository.update(front,rear,s_wd_id);
    }

    //リポジトリに削除の処理を依頼する。
    public void delete(int s_wd_id) {
        mRepository.delete(s_wd_id);
    }
}
