package searchman.example.com;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class HotelDataEntry extends AppCompatActivity {

    private String mode; // 実行モード（insert or update)

    private ContentValues values; // データを入れる箱
    private TextView no;
    private TextView name;
    private TextView address;
    private TextView tel;
    private TextView comment;
    private ImageView imgView;
    private int heightMax = 480;
    private byte[] photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotelinput);
        imgView = (ImageView)findViewById(R.id.input_photo);

        Intent intent = getIntent(); // 引数を受け取るインテント
        mode = intent.getStringExtra("mode"); // 実行モード（insert or update)

        if (mode.equals("update")) {
            getData(); // データの取得

//取得したデータを画面に入れる
            no.setText(intent.getStringExtra("noo"));
            no.setEnabled(false); //変更不可にする
            name.setText(intent.getStringExtra("name"));
            address.setText(intent.getStringExtra("address"));
            tel.setText(intent.getStringExtra("tel"));
            comment.setText(intent.getStringExtra("comment"));
            photo = intent.getByteArrayExtra("photo");
            if (photo != null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                imgView.setImageBitmap(bmp);
            }

        }
    }

    // ギャラリー呼び出し
    public void photo(View view){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override//呼び出し後の処理
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 0 && resultCode == RESULT_OK && null != intent) {
            try {
                InputStream input = getContentResolver().openInputStream(intent.getData());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                Bitmap bitmap = BitmapFactory.decodeStream(input, null, options);
                input.close();

                int inSampleSize = Math.round((float) options.outHeight / (float) heightMax);
                input = getContentResolver().openInputStream(intent.getData());
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inSampleSize = inSampleSize;
                bitmap = BitmapFactory.decodeStream(input, null, options2);
                input.close();

                imgView.setImageBitmap(bitmap);

                //Bitmapをbyteに変換
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                photo = baos.toByteArray();

            } catch (Exception e) {
            }
        }
    }


    //データの追加・更新ボタン
    public void hotelInput(View view) {

        getData(); // データの取得
        if(makeValues()) { // DB用にデータ生成

// DBのオープン
            OpenDatabase OpenDb = new OpenDatabase(this, "hotel.db", null, 1);
            final SQLiteDatabase db = OpenDb.getWritableDatabase();

            long ret = -1; // データ挿入判定値

            try {
                if (mode.equals("insert")) {
                    ret = db.insert("hotel_list", null, values);
                }
                if (mode.equals("update")) { //update処理
                    ret = db.update("hotel_list", values, "Noo = ?",
                            new String[] { no.getText().toString() });
                }
            } finally {
                db.close();
            }
            if (ret == -1) { // データ挿入失敗
                setContentView(R.layout.fail);
            } else { // データ挿入成功
                setContentView(R.layout.success);
            }
        }
    }

    public void getData() { // データの取得
        no = (TextView) this.findViewById(R.id.input_no);
        name = (TextView) this.findViewById(R.id.input_name);
        address = (TextView) this.findViewById(R.id.input_address);
        tel = (TextView) this.findViewById(R.id.input_tel);
        comment = (TextView) this.findViewById(R.id.input_comment);
    }

    public boolean makeValues() { // DB用にデータ生成
        values = new ContentValues(); // データを入れる箱
        values.put("noo", no.getText().toString());
        values.put("name", name.getText().toString());
        values.put("address", address.getText().toString());
        values.put("tel", tel.getText().toString());
        values.put("comment", comment.getText().toString());
        values.put("photo",photo);


        //入力チェック
        if(no.getText().toString().equals("") || name.getText().toString().equals("")){
            setContentView(R.layout.fail);

            Resources res = getResources(); //リソース取得
            String err_msg = res.getString(R.string.err_msg); //エラーメッセージ取得

            Toast.makeText(this, err_msg, Toast.LENGTH_LONG).show();
            return false;
        } else{
            return true;
        }
    }

    // トップに戻るボタン
    public void top(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
