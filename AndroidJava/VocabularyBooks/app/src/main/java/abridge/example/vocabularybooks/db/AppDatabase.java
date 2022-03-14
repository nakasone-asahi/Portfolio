package abridge.example.vocabularybooks.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import abridge.example.vocabularybooks.db.flashcard_table.FlashCard;
import abridge.example.vocabularybooks.db.flashcard_table.FlashCardDao;
import abridge.example.vocabularybooks.db.user_table.User;
import abridge.example.vocabularybooks.db.user_table.UserDao;
import abridge.example.vocabularybooks.db.word_table.Word;
import abridge.example.vocabularybooks.db.word_table.WordDao;

@Database(entities = {User.class,FlashCard.class,Word.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract WordDao wordDao();
    public abstract FlashCardDao flashcardDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;//スレッド数(４)
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized(AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"vocabularybooks.db").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    //コールバック(本当の初期、最初のDB作成の時だけ以下の処理をする)
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                UserDao udao = INSTANCE.userDao();

                User userInfo = new User("Sample","Sample");
                udao.insertAcount(userInfo);

                FlashCardDao fdao = INSTANCE.flashcardDao();
                FlashCard FcInfo = new FlashCard(1,"English","英語の単語帳");
                fdao.insertFlashCard(FcInfo);
                FcInfo = new FlashCard(1,"IT Parlance","IT用語(略語)");
                fdao.insertFlashCard(FcInfo);
                FcInfo = new FlashCard(1,"Business Parlance","ビジネス用語");
                fdao.insertFlashCard(FcInfo);

                WordDao wdao = INSTANCE.wordDao();
                Word wordInfo = new Word(1,"Repository","倉庫");
                wdao.insertWord(wordInfo);

                wordInfo = new Word(1,"English","英語");
                wdao.insertWord(wordInfo);

                wordInfo = new Word(1,"Info","情報");
                wdao.insertWord(wordInfo);

                wordInfo = new Word(1,"informal","非公式");
                wdao.insertWord(wordInfo);

                wordInfo = new Word(1,"Sample","見本");
                wdao.insertWord(wordInfo);

                wordInfo = new Word(2,"API","アプリケーション・プログラミング・インタフェース");
                wdao.insertWord(wordInfo);

                wordInfo = new Word(2,"MVC","モデル・ビュー・コントローラー");
                wdao.insertWord(wordInfo);

                wordInfo = new Word(2,"MVVM","モデル・ビュー・ビューモデル");
                wdao.insertWord(wordInfo);

                wordInfo = new Word(3,"eコマース(EC)","商品やサービスをインターネット上で売買するビジネスモデル");
                wdao.insertWord(wordInfo);

                wordInfo = new Word(3,"SDGs","持続可能な開発目標");
                wdao.insertWord(wordInfo);
            });
        }
    };
}
