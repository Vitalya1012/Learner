package com.vitaliy.learner;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddNewJSON {
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference databaseReference = database.getReference("words_general_de");
//
//    List<Word> words = new ArrayList<>();
//        try {
//        JSONObject jsonObject = new JSONObject(loadJsonObjectFromAssets(this));
//        JSONArray array = jsonObject.getJSONArray("words");
//        for (int i = 0; i < array.length(); i++) {
//            String russian = array.getJSONObject(i).getString("russian").toString();
//            String english = array.getJSONObject(i).getString("foreign").toString();
//            String examples = array.getJSONObject(i).getString("examples").toString();
//            Word word = new Word(russian,english, examples);
//            words.add(word);
//        }
//        for (Word word : words){
//            databaseReference.push().setValue(word);
//            Log.d("MainActivity2" , word.toString());
//
//        }
//
//    } catch (
//    JSONException e) {
//        e.printStackTrace();
//    }

//    private static String loadJsonObjectFromAssets(Context context) {
//        String json = null;
//        try {
//            InputStream is = context.getAssets().open("words.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return json;
//    }
}
