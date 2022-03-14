package abridge.example.vocabularybooks.db.word_table;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import abridge.example.vocabularybooks.db.flashcard_table.FlashCard;


@Dao
public interface WordDao {
    @Query("SELECT * FROM words WHERE fc_id IN (:s_fc_id)")
    LiveData<List<Word>> getLinkingWords(int s_fc_id);

    @Query("SELECT * FROM words WHERE word_id IN (:s_id)")
    LiveData<Word> getSelectWord(int s_id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWord(Word wordInfo);

    @Query("UPDATE words SET front=(:front),rear=(:rear) WHERE word_id = (:word_id)")
    void update(String front,String rear,int word_id);

    @Query("DELETE FROM words WHERE word_id = (:word_id)")
    void delete(int word_id);

}
