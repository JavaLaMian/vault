package com.vault.demo.controller.backstage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.vault.demo.bean.Tender;
import com.vault.demo.service.backstage.tender.BackTenderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/back_tender")
public class BacKTenderController {
    @Resource
    BackTenderService bts;

    @ResponseBody
    @RequestMapping("/tender_data")
    public JSONObject getTenderData(){
        JSONObject object = new JSONObject();
        Map data = new HashMap();
        List<Map> tenderMap = bts.selTender();
        System.out.println(JSON.toJSON(tenderMap).toString());
        //JsonArray array = (JsonArray)JSON.toJSON(tenderMap);
        //System.out.println("+++"+array.toString());
        object.put("total",tenderMap.size());
        object.put("rows",JSON.toJSON(tenderMap));
        System.out.println(data.toString());
        return object;
    }
}
