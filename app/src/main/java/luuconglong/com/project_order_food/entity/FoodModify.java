package luuconglong.com.project_order_food.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import luuconglong.com.project_order_food.db.DBHelper;
import luuconglong.com.project_order_food.models.Food;

public class FoodModify {
    public static final String QUERY_CREATE_TABLE = "create table food (\n" +
            "\t_id integer primary key autoincrement, \n" +
            "\ttitle varchar(50), \n" +
            "\tcontent text, \n"+
            "\tprice float\n"+
            ")";
    public static Cursor findAll(){
        String sql = "select * from food";
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        return cursor;
    }

    public static void insert(Food food){
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", food.getTitle());
        values.put("content", food.getContent());
        values.put("price", food.getPrice());

        sqLiteDatabase.insert("food", null,values);
    }

    public static void save (Food food){
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", food.getTitle());
        values.put("content", food.getContent());
        values.put("price", food.getPrice());

        sqLiteDatabase.update("food", values, "_id = " + food.get_id(),null);
    }

    public static void delete (int id){
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();
        sqLiteDatabase.delete("food", " _id = " + id,null);
    }
}
