/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

import com.javacodegags.waterflooding.handler.CustomJSONParser;
import com.javacodegags.waterflooding.handler.RowBuilderCriteria;
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
import org.springframework.web.multipart.commons.CommonsFileUploadSupport;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ������
 */
@Controller
public class AdminController {

    private static final Logger LOG = Logger.getLogger(AdminController.class.getName());

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

    @RequestMapping(value = "/admin")
    public ModelAndView admin(HttpServletResponse response) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin");
        return mav;
    }

    @RequestMapping(value = "/admin/criterias", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getCriteriaParams(@RequestBody String postBody) throws IOException, JSONException {
        JSONObject jsono = new JSONObject();
        List<Flooding> listFlooding = floodingInterface.getList();
        List<Caption> listCaption = captionInterface.list();
        RowBuilderCriteria builder = new RowBuilderCriteria();
        for (int i = 0; i < listCaption.size(); i++) {
            builder.addList(criteriaInterface.getListById(listCaption.get(i).getId()));
        }
        jsono.put("floodings", new CustomJSONParser().floodingsToJsonArray(listFlooding));
        jsono.put("criterias", new CustomJSONParser().criteriastringsToJsonArray(builder.buildRowCriteriesParams()));
        jsono.put("captions", new CustomJSONParser().captionsToJsonArray(listCaption));
        LOG.info(jsono.toString());
        return jsono.toString();
    }

    @RequestMapping(value = "/admin/criteria", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getCriteria(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject(postBody);
        int id = jsono.getInt("id");
        Criteria criteria = criteriaInterface.get(id);
        jsono = new CustomJSONParser().objectToJSON(criteria);
        List<Parameters> list = parameterInterface.getListById(id);
        jsono.put("parameters", new CustomJSONParser().listParamsToJSONArray(list));
        List<Therm> therms = thermInterface.getList();
        jsono.put("thermlist", new CustomJSONParser().thermsToJsonArray(therms));
        LOG.info(jsono.toString());
        return jsono.toString();
    }

    @RequestMapping(value = "/admin/updateCriteria", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String updateCriterias(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject(postBody);
        Criteria criteria = new CustomJSONParser().jsonToCriteria(jsono);
        criteriaInterface.updateCriteria(criteria);
        ArrayList<Parameters> alp = new CustomJSONParser().jsonToListParams(jsono.getJSONArray("params"), criteria.getId());
        LOG.info(alp.toString());
        parameterInterface.deleteParams(criteria.getId());
        for (Parameters p : alp) {
            parameterInterface.insertParams(p);
        }
        return jsono.toString();
    }

    @RequestMapping(value = "/admin/addTherm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String addTherm(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject(postBody);
        LOG.info(jsono.toString());
        thermInterface.insertTherm(jsono.getString("name"));
        return jsono.toString();
    }

    @RequestMapping(value = "/admin/allTherms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getAllTherms(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject();
        List<Therm> therms = thermInterface.getList();
        LOG.info(therms.toString());
        jsono.put("thermlist", new CustomJSONParser().thermsToJsonArray(therms));
        return jsono.toString();
    }

    @RequestMapping(value = "/admin/deleteTherm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String deleteTherm(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject(postBody);
        LOG.info(jsono.toString());
        thermInterface.delteTherm(jsono.getInt("id"));
        return jsono.toString();
    }

    @RequestMapping(value = "/admin/newCriteria", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String newCriteria(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject(postBody);
        int captionId = captionInterface.insertCaption(jsono.getString("name"));
        List<Flooding> lf = floodingInterface.getList();
        List<Therm> lt= thermInterface.getList();
        int criteriaId;
        for (Flooding f : lf) {
            criteriaId = criteriaInterface.insertCriteria(lt.get(0).getId());
            LOG.info(""+criteriaId);
            criteriaInterface.insertIntermediateToCaption(criteriaId, captionId);
            criteriaInterface.insertIntermediateToFlooding(criteriaId, f.getId());
        }
        return jsono.toString();
    }

    @RequestMapping(value = "/admin/allCriteria", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getAllCriteria() throws JSONException {
        JSONArray jsonArray =new CustomJSONParser().captionsToJsonArray(captionInterface.list());
        return jsonArray.toString();
    }

    @RequestMapping(value = "/admin/deleteCaption", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String deleteCaption(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject(postBody);
        LOG.info(jsono.toString());
        captionInterface.deleteCaption(jsono.getInt("id"));
        return jsono.toString();
    }

    @RequestMapping(value = "/admin/updateCaption", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes ="application/json;charset=UTF-8")
    public @ResponseBody
    String updateCaption(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject(postBody);
        LOG.info(jsono.toString());
        captionInterface.updateName(jsono.getInt("id"), jsono.getString("name"));
        return jsono.toString();
    }

    @RequestMapping(value = "/admin/newFlooding", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes ="application/json;charset=UTF-8")
    public @ResponseBody
    String insertFlooding(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject(postBody);
        LOG.info(jsono.toString());
        int floodingId = floodingInterface.insert(jsono.getString("name"));
        resultInterface.insert();
        List<Caption> captions = captionInterface.list();
        List<Therm> lt= thermInterface.getList();
        int criteriaId;
        for (Caption caption : captions)
        {
            criteriaId = criteriaInterface.insertCriteria(lt.get(0).getId());
            LOG.info(""+criteriaId);
            criteriaInterface.insertIntermediateToCaption(criteriaId, caption.getId());
            criteriaInterface.insertIntermediateToFlooding(criteriaId, floodingId);
        }
        return jsono.toString();
    }

    @RequestMapping(value = "/admin/deleteFlooding", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String deleteFlooding(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject(postBody);
        LOG.info(jsono.toString());
        List<Integer> criteriasId = criteriaInterface.getCriteriaByFlooding(jsono.getInt("id"));
        floodingInterface.delete(jsono.getInt("id"));
        for (int id : criteriasId)
        {
            criteriaInterface.delete(id);
        }
        return jsono.toString();
    }

    @RequestMapping(value = "/admin/updateFlooding", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String updateFlooding(@RequestBody String postBody) throws JSONException {
        JSONObject jsono = new JSONObject(postBody);
        LOG.info(jsono.toString());
        floodingInterface.update(jsono.getInt("id"), jsono.getString("name"));
        return jsono.toString();
    }

    @RequestMapping(value = "/admin/allFlooding", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getFloodings() throws JSONException {
        JSONObject jsono = new JSONObject();
        LOG.info(jsono.toString());
        JSONArray jsonArray =new CustomJSONParser().floodingsToJsonArray(floodingInterface.getList());
        return jsonArray.toString();
    }



}