package abridge.example.vocabularybooks.viewmodel.flashcard;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import abridge.example.vocabularybooks.db.flashcard_table.FlashCard;
import abridge.example.vocabularybooks.model.Common;
import abridge.example.vocabularybooks.model.FlashCardRepository;

public class FlashCardViewModel extends AndroidViewModel {

    private FlashCardRepository mRepository;
    private LiveData<List<FlashCard>> mSpecifiedFlashCard;
    private LiveData<FlashCard> mSelectFc;

    public FlashCardViewModel (Application application) {
        super(application);//親クラスに値を渡す。
        mRepository = new FlashCardRepository(application);//リポジトリのインスタンス化(リポジトリにてデータベースとの接続を行う)
    }

    //ログインユーザーに紐づく単語カードを取得する
    public LiveData<List<FlashCard>> getLinkingFlashCards(Common common){
        mSpecifiedFlashCard = mRepository.getLinkingFlashCards(common.getId());
        return mSpecifiedFlashCard;
    }

    //選択した単語カードを取得する
    public LiveData<FlashCard> getSelectFlashCard(int selectId){
        mSelectFc = mRepository.getSelectFlashCard(selectId);
        return mSelectFc;
    }

    //リポジトリにカード追加の処理を依頼する。
    public void insert(Common common, String title, String remarks) {
        int userid = common.getId();
        FlashCard flashCard = new FlashCard(userid,title,remarks);
        mRepository.insert(flashCard);
    }

    //リポジトリに更新の処理を依頼する。
    public void update(String title, String remarks,int s_fc_id) {
        mRepository.update(title,remarks,s_fc_id);
    }

    //リポジトリに削除の処理を依頼する。
    public void delete(int s_fc_id) {
        mRepository.delete(s_fc_id);
    }


}
