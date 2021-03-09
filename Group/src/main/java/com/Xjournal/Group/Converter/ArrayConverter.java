package com.Xjournal.Group.Converter;

import com.Xjournal.Group.Entity.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class ArrayConverter implements Converter<String, ArrayList<Question>>{
    @Override
    public ArrayList<Question> convert(String jsonString) {
        ArrayList<Question> result = new Gson().fromJson(jsonString, new TypeToken<List<Question>>(){}.getType());
        return result;
    }
}
