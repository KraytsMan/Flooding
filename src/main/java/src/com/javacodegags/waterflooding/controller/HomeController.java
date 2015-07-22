package com.javacodegags.waterflooding.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

import com.javacodegags.waterflooding.handler.*;
import com.javacodegags.waterflooding.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private CaptionInterface captionInterface;
    @Autowired
    private CriteriaInterface criteriaInterface;
    @Autowired
    private ParameterInterface parameterInterface;
    @Autowired
    private FloodingInterface floodingInterface;
    @Autowired
    private ResultInterface resultInterface;
    @Autowired
    private ThermInterface thermInterface;

    private static final Logger LOG = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = "/")
    public ModelAndView test(HttpServletResponse response) throws IOException {
        List<Caption> listCaption = captionInterface.list();
        List<Flooding> listFlooding = floodingInterface.getList();
        LOG.info(listFlooding.toString());
        RowBuilderCriteria builder = new RowBuilderCriteria();
        for (int i = 0; i < listCaption.size(); i++) {
            builder.addList(criteriaInterface.getListById(listCaption.get(i).getId()));
        }
        List<Result> listResults = resultInterface.getAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("listCaption", listCaption);
        mav.setViewName("home");
        return mav;
    }

    @RequestMapping(value = "/computewaterflooding", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getWaterflooding(@RequestBody String postBody) throws JSONException {
        JSONArray jsono = new JSONArray(postBody);
        for (int i = 0; i < jsono.length(); i++) {
            JSONObject item = jsono.getJSONObject(i);
            captionInterface.updateArgument(item.getInt("id"), item.getDouble("value"));
        }
        List<Criteria> listCriteria = criteriaInterface.getAll();
        for (int i = 0; i < listCriteria.size(); i++) {
            Criteria criteria = listCriteria.get(i);
            double func = new FormulaHendler(criteria, parameterInterface.getListById(criteria.getId())).toCalculate();
            LOG.info("" + func);
            criteriaInterface.updateFunction(criteria.getId(), func);
        }
        List<Flooding> floodings = floodingInterface.getList();
        for (Flooding f : floodings) {
            resultInterface.updateResult(f.getId());
        }
        ArrayList<String> floodnames = new ArrayList<String>();
        for (Flooding f : floodings) {
            floodnames.add(f.getName());
        }
        JSONObject wtjs = new JSONObject();
        wtjs.put("category", floodnames);
        ArrayList<Series> seria = new ArrayList<Series>();
        List<Result> listResults = resultInterface.getAll();
        Series min = new Series("'Kmin'");
        Series avg = new Series("'Kav'");
        for (int i = 0; i < listResults.size(); i++) {
            min.getData().add(listResults.get(i).getMinimum());
            avg.getData().add(listResults.get(i).getAverage());
        }
        seria.add(min);
        seria.add(avg);
        wtjs.put("series", new CustomJSONParser().seriesToJsonArray(seria));
        return wtjs.toString();
    }

    @RequestMapping(value = "/criteria", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getCriteria(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject(postBody);
        int id = jsono.getInt("id");
        Criteria criteria = criteriaInterface.get(id);
        jsono = new CustomJSONParser().objectToJSON(criteria);
        List<Parameters> list = parameterInterface.getListById(id);
        jsono.put("parameters", new CustomJSONParser().listParamsToJSONArray(list));
        return jsono.toString();
    }

    @RequestMapping(value = "/allCriterias", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getAllCriterias(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject();
        List<Flooding> listFlooding = floodingInterface.getList();
        List<Result> listResults = resultInterface.getAll();
        List<Caption> listCaption = captionInterface.list();
        RowBuilderCriteria builder = new RowBuilderCriteria();
        for (int i = 0; i < listCaption.size(); i++) {
            builder.addList(criteriaInterface.getListById(listCaption.get(i).getId()));
        }
        jsono.put("floodings", new CustomJSONParser().floodingsToJsonArray(listFlooding));
        jsono.put("results", new CustomJSONParser().resultsToJsonArray(listResults));
        jsono.put("criterias", new CustomJSONParser().criteriastringsToJsonArray(builder.buildRowCriteries()));
        jsono.put("captions", new CustomJSONParser().captionsToJsonArray(listCaption));
        LOG.info(jsono.toString());
        return jsono.toString();
    }

    @RequestMapping(value = "/allTherms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getAllTherms(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject();
        List<Flooding> listFlooding = floodingInterface.getList();
        List<Caption> listCaption = captionInterface.list();
        RowBuilderTherm builder = new RowBuilderTherm();
        for (int i = 0; i < listCaption.size(); i++) {
            builder.addList(thermInterface.getStackByCaptureId(listCaption.get(i).getId()));
        }
        jsono.put("floodings", new CustomJSONParser().floodingsToJsonArray(listFlooding));
        jsono.put("captions", new CustomJSONParser().captionsToJsonArray(listCaption));
        jsono.put("therms", new CustomJSONParser().criteriastringsToJsonArray(builder.buildRowCriteries()));
        LOG.info(jsono.toString());
        return jsono.toString();
    }
}
