package abridge.example.vocabularybooks.db.flashcard_table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import abridge.example.vocabularybooks.db.user_table.User;

@Entity(tableName = "flashcards",indices = {@Index("user_id"),@Index(value = {"fc_id","user_id","title"}, unique = true)},foreignKeys = {@ForeignKey(entity = User.class,parentColumns = "user_id",childColumns = "user_id",onDelete = ForeignKey.CASCADE)})
public class FlashCard {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "fc_id")
    private int fc_id;

    @NonNull
    @ColumnInfo(name = "user_id")
    private int user_id;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "remarks")
    private String remarks;

    public FlashCard(int user_id, String title, String remarks){
        this.user_id = user_id;
        this.title = title;
        this.remarks = remarks;
    }

    public void setFc_id(int id){this.fc_id = id;}
    public int getFc_id(){return this.fc_id;}

    public int getUser_id(){return this.user_id;}

    public void setTitle(String name){this.title = name;}
    public String getTitle(){return this.title;}

    public void setRemarks(String pass){this.remarks = pass;}
    public String getRemarks(){return this.remarks;}

}
