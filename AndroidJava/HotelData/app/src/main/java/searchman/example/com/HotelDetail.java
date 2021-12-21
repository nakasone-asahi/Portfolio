package searchman.example.com;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HotelDetail extends AppCompatActivity {

    private TextView hotelView;
    private String noo;
    private String name;
    private String address;
    private String tel;
    private String comment;
    private ImageView imgView;
    private byte[] photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//呼び出し元からの値を取得
        Intent intent = getIntent();
        noo = intent.getStringExtra("noo");
        name = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        tel = intent.getStringExtra("tel");
        comment = intent.getStringExtra("comment");
        photo = intent.getByteArrayExtra("photo");

//画面レイアウトの設定
        setContentView(R.layout.hoteldetail);
        if (photo != null) {
            imgView = (ImageView)findViewById(R.id.photo);
            Bitmap bmp = BitmapFactory.decodeByteArray(photo, 0, photo.length);
            imgView.setImageBitmap(bmp);
        }

//画面への表示設定
        hotelView=(TextView)findViewById(R.id.hotelInfo);
        hotelView.setText(noo+":"+ name+"\n"+
                address+"\n"+ tel+"\n"+ comment+"\n"
        );

    }
    //地図連携メソッド
    public void map(View view){
//地図インテント
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q="+address));
        startActivity(intent);
    }

    //電話連携メソッド
    public void call(View view){
//電話インテント
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+tel));
        startActivity(intent);
    }

    // ホテル削除ボタン
    public void hotelDelete(View view) {
// DBのオープン
        OpenDatabase OpenDb = new OpenDatabase(this, "hotel.db", null, 1);
        final SQLiteDatabase db = OpenDb.getWritableDatabase();

        try { //削除実行
            db.delete("hotel_list", "Noo = ?" , new String[] {noo});
            setContentView(R.layout.success); //成功画面
        } catch(Exception e){
            setContentView(R.layout.fail); //失敗画面
        } finally {
            db.close();
        }
    }
    // トップに戻るボタン
    public void top(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    // ホテル変更ボタン
    public void hotelUpdate(View view) {

// インテントの作成（データを引数で渡す）
        Intent intent = new Intent(this, HotelDataEntry.class);
        intent.putExtra("mode", "update");
        intent.putExtra("noo", noo);
        intent.putExtra("name", name);
        intent.putExtra("address", address);
        intent.putExtra("tel", tel);
        intent.putExtra("comment", comment);
        intent.putExtra("comment", comment);intent.putExtra("photo",photo);


        startActivity(intent);

    }
}
