package com.example.playful.tasklostchilds.Child;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ListChild {
    private static ListChild instance;
    private ArrayList<Child> children;

    private ListChild() {
        children = new ArrayList<>();
    }

    public static ListChild getInstance() {
        if (instance==null){
            instance = new ListChild();
        }
        return instance;
    }

    public void addChild(Child child){
        children.add(child);
    }

    public void addChild(List<Child> child){

        children.addAll(child);
        Log.d(getClass().getName(), "add " + child.size() + "\n" + children.size());
    }

    public void refreshChild(List<Child> child){
        Log.d(getClass().getName(), "redresh " + child.size());
        children.clear();
        children.addAll(child);
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public Child getChild(int index){
        return children.get(index);
    }

    public int getIndex(Child child){
        return children.indexOf(child);
    }
}