package abridge.example.vocabularybooks.db.word_table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import abridge.example.vocabularybooks.db.flashcard_table.FlashCard;
import abridge.example.vocabularybooks.db.user_table.User;

@Entity(tableName = "words", indices = {@Index("fc_id"),@Index(value = {"word_id","front"}, unique = true)},foreignKeys = {@ForeignKey(entity = FlashCard.class,parentColumns = "fc_id",childColumns = "fc_id",onDelete = ForeignKey.CASCADE)})
public class Word {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "word_id")
    private int word_id;

    @NonNull
    @ColumnInfo(name = "fc_id")
    private int fc_id;

    @ColumnInfo(name = "front")
    private String front;

    @ColumnInfo(name = "rear")
    private String rear;

    public Word(int fc_id,String front,String rear){
        this.fc_id = fc_id;
        this.front = front;
        this.rear = rear;
    }

    public void setWord_id(int id){this.word_id = id;}
    public int getWord_id(){return this.word_id;}

    public int getFc_id(){return this.fc_id;}

    public void setFront(String front){this.front = front;}
    public String getFront(){return this.front;}

    public void setRear(String rear){this.rear = rear;}
    public String getRear(){return this.rear;}

}
