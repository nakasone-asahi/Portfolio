package abridge.example.vocabularybooks.db.flashcard_table;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface FlashCardDao {

    @Query("SELECT * FROM flashcards WHERE user_id IN (:u_id)")
    LiveData<List<FlashCard>> getLinkingFlashCards(int u_id);

    @Query("SELECT * FROM flashcards WHERE fc_id IN (:s_id)")
    LiveData<FlashCard> getSelectFlashCard(int s_id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFlashCard(FlashCard flashCard);

    @Query("UPDATE flashcards SET title=(:title),remarks=(:remarks) WHERE fc_id = (:fc_id)")
    void update(String title,String remarks,int fc_id);

    @Query("DELETE FROM flashcards WHERE fc_id = (:fc_id)")
    void delete(int fc_id);
}
