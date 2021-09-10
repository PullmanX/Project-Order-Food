package luuconglong.com.project_order_food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.util.ArrayList;
import java.util.List;

import luuconglong.com.project_order_food.adapters.FoodAdapter;
import luuconglong.com.project_order_food.adapters.FoodCursorAdapter;
import luuconglong.com.project_order_food.db.DBHelper;
import luuconglong.com.project_order_food.entity.FoodModify;
import luuconglong.com.project_order_food.models.Food;

public class FoodActivity extends AppCompatActivity {
    ListView listView;
    List<Food> datalist = new ArrayList<>();
//    FoodAdapter foodAdapter;
    FoodCursorAdapter foodAdapter;
    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        //kich hoat database, khoi tao
        DBHelper.getInstance(this);

        listView = findViewById(R.id.afo_listview);
//        fake
//        datalist.add(new Food(R.drawable.beef, "Hamburger 01", "Ok Ngon", 15000));
//        datalist.add(new Food(R.drawable.beef, "Hamburger 02", "Ok Ngon", 20000));
//        datalist.add(new Food(R.drawable.beef, "Chicken 01", "Ok Ngon", 300000));
//        readFile();
        readSharedPreferences();

        //create adapter
//        foodAdapter = new FoodAdapter(this, datalist);
        Cursor cursor = FoodModify.findAll();
        foodAdapter = new FoodCursorAdapter(this, cursor);

        listView.setAdapter(foodAdapter);

        //Dangky Context menu
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_content, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        switch (item.getItemId()) {
            case R.id.menu_edit_item:
                //Thêm item mới
                this.currentIndex = index;
                showDialogAdd();
                return true;
            case R.id.menu_delete_item:
                //Xóa item
//                datalist.remove(index);
//                foodAdapter.notifyDataSetChanged();
                //Hàm xóa
                Cursor cursor = foodAdapter.getCursor();
                cursor.moveToPosition(index);
                int id = cursor.getInt(cursor.getColumnIndex("_id"));

                FoodModify.delete(id);

                //Update lại dữ liệu
                cursor = FoodModify.findAll();
                foodAdapter.changeCursor(cursor);

                foodAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_add_new_item:
                //Thêm Item mới
                showDialogAdd();
                return true;
            case R.id.menu_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void readFile(){
        //code save File
        ObjectInputStream ois = null;
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(getFileStreamPath("food.dat"));
            ois = new ObjectInputStream(fis);

            //save file
            datalist = (List<Food>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveFile(){
        //code save File
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(getFileStreamPath("food.dat"));
            oos = new ObjectOutputStream(fos);

            //save file
            oos.writeObject(datalist);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void saveSharedPreferences(){
        //Save dataList >> Object Array >> Convert Object Array >> String (json string)
        //Su dung thu vien gson >> convert object/array >>json string va nguoc lai
        String json = new Gson().toJson(datalist);
        //Save json to Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences("D18HT02", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //save
        editor.putString("dataList",json);

        editor.commit();
    }

    private void readSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("D18HT02", MODE_PRIVATE);
        String json = sharedPreferences.getString("dataList","[]");

        //convert json string >> object array
        Gson gson = new Gson();
        datalist=gson.fromJson(json, new TypeToken<List<Food>>(){}.getType());

        if(datalist == null){
            datalist = new ArrayList<>();
        }
    }

    private void showDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_food, null);

        final EditText edTitle = view.findViewById(R.id.df_title);
        final EditText edContent = view.findViewById(R.id.df_content);
        final EditText edPrice = view.findViewById(R.id.df_price);
        if(currentIndex >= 1){
            Cursor cursor = foodAdapter.getCursor();
            cursor.moveToPosition(currentIndex);

            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            float price = cursor.getFloat(cursor.getColumnIndex("price"));

            edTitle.setText(title);
            edContent.setText(content);
            edPrice.setText(String.valueOf(price));

//            edTitle.setText(datalist.get(currentIndex).getTitle());
//            edContent.setText(datalist.get(currentIndex).getContent());
//            edPrice.setText(String.valueOf(datalist.get(currentIndex).getPrice()));
        }

        builder.setView(view);
        builder.setTitle("Add/Update Item")
               .setPositiveButton("Save Item", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = edTitle.getText().toString();
                String content = edContent.getText().toString();
                float price = Float.parseFloat(edPrice.getText().toString());

                if(currentIndex >= 0){
                    //Cập nhật dữ liệu
//                    datalist.get(currentIndex).setTitle(title);
//                    datalist.get(currentIndex).setContent(content);
//                    datalist.get(currentIndex).setPrice(price);

                    Cursor cursor = foodAdapter.getCursor();
                    cursor.moveToPosition(currentIndex);
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));

                    Food food = new Food(title, content, price);
                    food.set_id(id);
                    FoodModify.save(food);

                    currentIndex = -1;
                }
                else {
                    Food food = new Food(R.drawable.beef, title, content, price);
                    datalist.add(food);
                    //save database
                    FoodModify.insert(food);
                }
                //Update lại dữ liệu
                Cursor cursor = FoodModify.findAll();
                foodAdapter.changeCursor(cursor);

                foodAdapter.notifyDataSetChanged();
//                saveFile();
//                saveSharedPreferences();


            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();
    }
}