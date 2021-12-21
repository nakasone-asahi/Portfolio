package searchman.example.com;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class HotelList extends ListActivity {

    //カーソル宣言
    Cursor cursor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotellist); //画面レイアウト

    //DBのオープン
        OpenDatabase OpenDb = new OpenDatabase(this, "hotel.db", null,1);
        final SQLiteDatabase db = OpenDb.getReadableDatabase();

        //データ取得（カーソル）
        cursor = db.query("hotel_list",
                new String[] { "_id","noo","name", "address", "tel", "comment","photo" },
                null, null, null, null,null,null);

    //アダプタ作成
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,//レイアウト指定
                cursor,//カーソル
                new String[] {"name"},//表示するカラム名
                new int[] {android.R.id.text1}//データを適用するTextViewのID
                ,0);
    //アダプタのセット
    setListAdapter(adapter);

    //DBを閉じる
    db.close();

        // リストビューがタップされた時の処理（内部クラスを使う）
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() { //内部クラス
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

            //Intentの作成
                Intent intent = new Intent(HotelList.this, HotelDetail.class);

            //Intentにデータ設定
                cursor.moveToPosition(position);
                intent.putExtra("noo",cursor.getString(1));
                intent.putExtra("name",cursor.getString(2));
                intent.putExtra("address",cursor.getString(3));
                intent.putExtra("tel",cursor.getString(4));
                intent.putExtra("comment",cursor.getString(5));
                intent.putExtra("photo", cursor.getBlob(6));

            //画面遷移
                startActivity(intent);
            }
        });
    }
}
