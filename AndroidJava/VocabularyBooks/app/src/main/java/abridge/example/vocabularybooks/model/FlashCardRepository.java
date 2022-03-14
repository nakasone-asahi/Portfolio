package abridge.example.vocabularybooks.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import abridge.example.vocabularybooks.db.AppDatabase;
import abridge.example.vocabularybooks.db.flashcard_table.FlashCard;
import abridge.example.vocabularybooks.db.flashcard_table.FlashCardDao;

public class FlashCardRepository{

    private FlashCardDao mFlashCardDao;
    private LiveData<List<FlashCard>> mUserFlashCards;
    private LiveData<FlashCard> mSelectFlashCard;

    //コンストラクタ
    public FlashCardRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mFlashCardDao = db.flashcardDao();
    }

    //ログインしたユーザーに紐づく単語カードを取得する
    public LiveData<List<FlashCard>> getLinkingFlashCards(int user_id){
        mUserFlashCards = mFlashCardDao.getLinkingFlashCards(user_id);
        return mUserFlashCards;
    }

    //リストから選択した単語カードを取得します
    public LiveData<FlashCard> getSelectFlashCard(int select_id){
        mSelectFlashCard = mFlashCardDao.getSelectFlashCard(select_id);
        return mSelectFlashCard;
    }

    //単語帳カードを追加する
    public void insert(FlashCard flashCard){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mFlashCardDao.insertFlashCard(flashCard);
        });
    }
    //単語帳カードを削除する
    public void delete(int s_fc_id){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mFlashCardDao.delete(s_fc_id);
        });
    }
    //単語帳カードを更新する
    public void update(String title,String remarks,int s_fc_id){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mFlashCardDao.update(title,remarks,s_fc_id);
        });
    }
}
