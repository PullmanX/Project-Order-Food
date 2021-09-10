package luuconglong.com.project_order_food.adapters;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import luuconglong.com.project_order_food.R;
import luuconglong.com.project_order_food.db.DBHelper;
import luuconglong.com.project_order_food.models.Food;

public class FoodAdapter extends BaseAdapter {
    Activity activity;
    List<Food> dataList;
    public FoodAdapter(Activity activity, List<Food> dataList){
        this.activity = activity;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.item_food, null);

        ImageView thumbnail = view.findViewById(R.id.ifo_thumbnail);
        TextView title = view.findViewById(R.id.ifo_title);
        TextView content = view.findViewById(R.id.ifo_content);
        TextView price = view.findViewById(R.id.ifo_price);

        Food food = dataList.get(position);

        thumbnail.setImageResource(food.getResID());
        title.setText(food.getTitle());
        content.setText(food.getContent());
        price.setText(String.valueOf(food.getPrice()));

        return view;
    }


}
