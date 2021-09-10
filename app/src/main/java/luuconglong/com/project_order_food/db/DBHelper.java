package luuconglong.com.project_order_food.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import luuconglong.com.project_order_food.entity.FoodModify;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "gokisoft";
    private static final int DB_VERSION = 1;
    private static DBHelper instance = null;

    public synchronized static DBHelper getInstance(Context context){
        if(instance == null){
            instance = new DBHelper(context);
        }
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //TH nào gọi tới function này
        //Chạy tới câu truy vấn
        String sql = FoodModify.QUERY_CREATE_TABLE;

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TH nào gọi tới function này
    }
}
