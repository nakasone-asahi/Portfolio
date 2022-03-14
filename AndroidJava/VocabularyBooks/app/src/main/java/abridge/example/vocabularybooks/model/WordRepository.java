package abridge.example.vocabularybooks.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import abridge.example.vocabularybooks.db.AppDatabase;
import abridge.example.vocabularybooks.db.word_table.Word;
import abridge.example.vocabularybooks.db.word_table.WordDao;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mFlashCardWords;
    private LiveData<Word> mSelectWord;

    //コンストラクタ
    public WordRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mWordDao = db.wordDao();
    }

    //選択された単語帳カードに紐づくワードを表示する
    public LiveData<List<Word>> getLinkingWords(int fc_id){
        mFlashCardWords = mWordDao.getLinkingWords(fc_id);
        return mFlashCardWords;
    }

    //リストから選択したワードを表示する
    public LiveData<Word> getSelectWord(int select_id) {
        mSelectWord = mWordDao.getSelectWord(select_id);
        return mSelectWord;
    }

    //ワードを追加する
    public void insert(Word word){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insertWord(word);
        });
    }
    //ワードを削除する
    public void delete(int s_fc_id){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.delete(s_fc_id);
        });
    }
    //ワードを編集する
    public void update(String front,String rear,int s_fc_id){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.update(front,rear,s_fc_id);
        });
    }
}
