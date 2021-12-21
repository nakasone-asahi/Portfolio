package searchman.example.com;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class OpenDatabase extends SQLiteOpenHelper {

    private String[][] hotelData= {
            {"001","プリンスホテル","東京都港区芝公園3-3-1","03-3432-2222","東京タワー近辺"},
            {"002","ハーベストイン","鳥取県米子市弥生町8-27","0859-31-3333","米子駅徒歩５分"},
            {"003","ホテル阪急","大阪府大阪市北区茶屋町19-19","06-6377-4444","梅田駅近く"},
            {"004","三井ガーデン","宮城県仙台市青葉区本町2-4-6","022-214-5555","仙台駅徒歩１０分"},
    };

    public OpenDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
//テーブル作成文
        String sql="CREATE TABLE [hotel_list] ("+
                "[_id] INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "[noo] VARCHAR(10) NOT NULL UNIQUE,"+
                "[name] VARCHAR(100) NOT NULL UNIQUE,"+
                "[address] VARCHAR(200) NOT NULL,"+
                "[tel] VARCHAR(100) NOT NULL,"+
                "[comment] VARCHAR(500) NOT NULL"+
                ",[photo] blob"+
                ");";

        db.beginTransaction();
        try {
            db.execSQL(sql);//テーブル生成
            SQLiteStatement stmt = db.compileStatement("INSERT INTO hotel_list" +
                    "(noo, name, address, tel,comment) values (?, ?, ?, ?, ?);");
            for (String[] data : hotelData) {//繰り返しでデータ挿入
                stmt.bindString(1, data[0]);
                stmt.bindString(2, data[1]);
                stmt.bindString(3, data[2]);
                stmt.bindString(4, data[3]);
                stmt.bindString(5, data[4]);
                stmt.executeInsert();
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.v("hotel.db","DB error:"+e.toString());
        } finally {
            db.endTransaction();
        }
    }
    public void onUpgrade(SQLiteDatabase arg0, int arg1,int arg2 ){}
}
