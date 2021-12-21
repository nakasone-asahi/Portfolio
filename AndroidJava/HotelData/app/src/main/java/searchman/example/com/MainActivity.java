package searchman.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // インスタンス作成（まだDBはできない）
        OpenDatabase OpenDb = new OpenDatabase(this, "hotel.db", null,1);
        // DBにアクセス（と同時にDB作成）
        SQLiteDatabase db = OpenDb.getWritableDatabase();
        //DBを閉じる
        db.close();
    }

    //ホテル一覧ボタン
    public void hotelList(View view){
        Intent intent = new Intent(this,HotelList.class);
        startActivity(intent);
    }

    // ホテル追加画面を表示するボタン
    public void hotelDataEntry(View view) {
        Intent intent = new Intent(this, HotelDataEntry.class);
        intent.putExtra("mode", "insert");
        startActivity(intent);
    }
}