package luuconglong.com.project_order_food.models;

import java.io.Serializable;

public class Food implements Serializable {
    int _id;
    int resID;
    String title, content;
    float price;

    public Food(int resID) {
        this.resID = resID;
    }

    public Food(int resID, String title, String content, float price) {
        this.resID = resID;
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public Food(String title, String content, float price) {
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
