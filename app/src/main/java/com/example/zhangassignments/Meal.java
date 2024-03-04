package com.example.zhangassignments;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    public String mealName;
    public String category;

    public String instructions;

    public String imgUrl;
    public Meal(String mealName, String category, String instructions, String imgUrl) {
        this.mealName = mealName;
        this.category = category;
        this.instructions = instructions;
        this.imgUrl = imgUrl;
    }

    // toString method
    @Override
    public String toString() {
        String info = "";
        info+="NAME: " + mealName;
        info+="\nCATEGORY: " + category;
        info+="\nINSTRUCTION: " + instructions;


        return info.toString();
    }
}
