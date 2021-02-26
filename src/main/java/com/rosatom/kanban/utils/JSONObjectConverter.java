package com.rosatom.kanban.utils;

import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.json.JsonObject;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JSONObjectConverter implements AttributeConverter<JSONPObject, String> {
    @Override
    public String convertToDatabaseColumn(JSONPObject jsonData) {
        String json;
        try{
            json = jsonData.toString();
        }
        catch (NullPointerException ex)
        {
            json = "";
        }
        return json;
    }

    @Override
    public JSONPObject convertToEntityAttribute(String jsonDataAsJson) {
//        JsonObject jsonData;
//        try {
//            jsonData = new JSONPObject(jsonDataAsJson, );
//        } catch (JSONException ex) {
//            jsonData = null;
//        }
//        return jsonData;
//    }
        return null;}
}
