/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.handler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.javacodegags.waterflooding.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ������
 */
public class CustomJSONParser {

    private static final Logger LOG = Logger.getLogger(CustomJSONParser.class.getName());

    public JSONObject objectToJSON(Object object) {
        JSONObject jsonObject = new JSONObject(object);
        return jsonObject;
    }

    public JSONArray listParamsToJSONArray(List<Parameters> list) {
        JSONArray jArray = new JSONArray();
        try {

            for (Parameters parameters : list) {
                JSONObject parametersJSON = new JSONObject();
                parametersJSON.put("id", parameters.getId());
                parametersJSON.put("name", parameters.getName());
                parametersJSON.put("value", parameters.getValue());
                jArray.put(parametersJSON);
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        return jArray;
    }

    public JSONArray seriesToJsonArray(ArrayList<Series> list) {
        JSONArray jArray = new JSONArray();
        try {

            for (Series s : list) {
                JSONObject SeriesJSON = new JSONObject();
                SeriesJSON.put("name", s.getName());
                SeriesJSON.put("data", s.getData());
                jArray.put(SeriesJSON);
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        return jArray;
    }

    public JSONArray floodingsToJsonArray(List<Flooding> list) {
        JSONArray jArray = new JSONArray();
        try {
            for (Flooding f : list) {
                JSONObject floodingsJSON = new JSONObject();
                floodingsJSON.put("id", f.getId());
                floodingsJSON.put("name", f.getName());
                floodingsJSON.put("name", f.getName());
                floodingsJSON.put("image", f.getImage());
                floodingsJSON.put("description", f.getDescription());
                jArray.put(floodingsJSON);
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        return jArray;
    }

    public JSONArray resultsToJsonArray(List<Result> list) {
        JSONArray jArray = new JSONArray();
        try {
            for (Result r : list) {
                JSONObject resultJSON = new JSONObject();
                resultJSON.put("id", r.getId());
                resultJSON.put("minimum", r.getMinimum());
                resultJSON.put("average", r.getAverage());
                jArray.put(resultJSON);
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        return jArray;
    }

    public JSONArray criteriastringsToJsonArray(LinkedList<String> list) {
        JSONArray jArray = new JSONArray();
        try {
            for (String s : list) {
                JSONObject stringsJSON = new JSONObject();
                LOG.info(s);
                stringsJSON.put("criteria", s);
                jArray.put(stringsJSON);
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        return jArray;
    }

    public JSONArray captionsToJsonArray(List<Caption> list) {
        JSONArray jArray = new JSONArray();
        try {
            for (Caption c : list) {
                JSONObject captionJSON = new JSONObject();
                captionJSON.put("id", c.getId());
                captionJSON.put("name", c.getName());
                jArray.put(captionJSON);
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        return jArray;
    }

    public JSONArray thermsToJsonArray(List<Therm> list) {
        JSONArray jArray = new JSONArray();
        try {
            for (Therm t : list) {
                JSONObject thermJSON = new JSONObject();
                thermJSON.put("id", t.getId());
                thermJSON.put("name", t.getName());
                jArray.put(thermJSON);
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        return jArray;
    }

    public ArrayList<Parameters> jsonToListParams(JSONArray array, int froreignId) throws JSONException {
        ArrayList<Parameters> list = new ArrayList<Parameters>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            Parameters p = new Parameters();
            p.setName(object.getString("name"));
            p.setValue(object.getDouble("value"));
            p.setForeignId(froreignId);
            list.add(p);
        }
        return list;
    }
    
    public Criteria jsonToCriteria(JSONObject jsono) throws JSONException {
        Criteria c= new Criteria();
        c.setId(jsono.getInt("id"));
        c.setFormula(jsono.getString("formula"));
        c.setWeighFactor(jsono.getDouble("weight_factor"));
        c.setTherms(jsono.getInt("therm"));
        return c;
    }

}
