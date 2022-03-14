package abridge.example.vocabularybooks.model;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.Serializable;

public class Common extends Application implements Serializable {
    private int userId;
    private String userName;
    private int selectFcId;
    private int selectWordId;

    //ユーザーのIDの管理
    public void setId(int id){
        userId = id;
    }
    public int getId(){
        return userId;
    }

    //ログインしているユーザーの管理
    public void setName(String name){
        userName = name;
    }
    public String getName(){ return userName; }

    //選択した単語カードの管理
    public void setSelectFcId(int id){ selectFcId = id;}
    public int getSelectFcId(){return selectFcId;}

    //選択したワードの管理
    public void setSelectWordId(int id){ selectWordId = id;}
    public int getSelectWordId(){return selectWordId;}

}
