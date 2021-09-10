package luuconglong.com.project_order_food.adapters;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import javax.xml.parsers.FactoryConfigurationError;

import luuconglong.com.project_order_food.R;

public class FoodCursorAdapter extends CursorAdapter{
    Activity activity;
    public FoodCursorAdapter(Activity activity, Cursor c) {
        super(activity, c);
        this.activity = activity;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_food, null);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView thumbnailimg = view.findViewById(R.id.ifo_thumbnail);
        TextView titletxt = view.findViewById(R.id.ifo_title);
        TextView contenttxt = view.findViewById(R.id.ifo_content);
        TextView pricetxt = view.findViewById(R.id.ifo_price);

        String title = cursor.getString(cursor.getColumnIndex("title"));
        String content = cursor.getString(cursor.getColumnIndex("content"));
        float price = cursor.getFloat(cursor.getColumnIndex("price"));

        titletxt.setText(title);
        contenttxt.setText(content);
        pricetxt.setText(String.valueOf(price));
    }
}
