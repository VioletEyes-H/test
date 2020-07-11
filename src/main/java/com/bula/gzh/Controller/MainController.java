package com.bula.gzh.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bula.gzh.Entity.quanmama;
import com.bula.gzh.Service.InfoService;
import com.bula.gzh.Util.HttpClient;
import com.bula.gzh.Util.Result;
import com.bula.gzh.Util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(
        value = "获取数据的接口",
        description = "获取数据的接口"
)
public class MainController {
    @Autowired
    private InfoService infoService;

    public MainController() {
    }

    @GetMapping({"/getInfo"})
    @ApiOperation(
            value = "根据关键字查询数据",
            httpMethod = "GET",
            notes = "获取查询到的数据"
    )
    public Result getInfo(@RequestParam(required = false) @ApiParam(value = "关键字", required = false) String key, @RequestParam(required = false, defaultValue = "15") @ApiParam("规定每页展示多少条数据，默认：15条") int limit, @RequestParam(required = false, defaultValue = "1") @ApiParam("页数") int page) {
        return this.infoService.findByTitle(key, limit, page);
    }

    @GetMapping({"/list"})
    @ApiOperation(
            value = "查询没有过期的活动",
            httpMethod = "GET",
            notes = "如果当前时间 > 活动结束时间endtime证明活动过期，已经过期的数据就不存在"
    )
    public Result getDate(@RequestParam(required = false, defaultValue = "15") @ApiParam("规定每页展示多少条数据，默认：15条") int limit, @RequestParam(required = false, defaultValue = "1") @ApiParam("页数") int page) {
        return this.infoService.findByTitleForTime(limit, page);
    }

    @GetMapping({"/getInfoById"})
    @ApiOperation(
            value = "根据id查询数据",
            httpMethod = "GET",
            notes = "根据前端传入的id，进行单条数据查询，返回给前端"
    )
    public Result getId(@RequestParam(required = true) @ApiParam("需要查询的id") int id) {
        return this.infoService.findById(id);
    }

    @GetMapping({"/deleteById"})
    @ApiOperation(
            value = "根据id删除数据",
            httpMethod = "GET",
            notes = "根据前端传入的id，进行单条，或者多条数据删除。传入数组，例如：['1','2','3'...]"
    )
    public Result deleteId(@RequestParam(required = true) @ApiParam("需要删除的id数组") @RequestBody Integer[] ids) {
        return ids.length < 1 ? ResultGenerator.genFailResult("参数异常") : this.infoService.deleteById(ids);
    }

    @PostMapping("/keyword/{keyword}")
    @ApiOperation(
            value = "调用劵妈妈接口",
            httpMethod = "POST",
            notes = "传入keyword值，调用券妈妈接口，返回数据"
    )
    public Result queryByKeyword(@PathVariable(value = "keyword") @ApiParam("需要搜索的关键字") String keyword) {
        JSONObject data = new JSONObject();
        data.put("code", "551");
        data.put("v", "5.5.1");
        data.put("test", "0");
        data.put("keyword", keyword);
        data.put("pageindex", "1");
        data.put("sort", "19");
        data.put("searchtype", "1");
        data.put("storeType", "1");
        data.put("searchPosition", "4");
        String path = "http://m.quanmama.com/apios/v2/appZdmComposeSearch.ashx";
        JSONObject result = JSONObject.parseObject(HttpClient.post(path, data.getInnerMap()));
        List<quanmama> quanmamaList = new ArrayList<>();
        if (result.getString("error_code").equals("0")) {
            JSONObject resultJSONObjectData = result.getJSONObject("data");
            JSONArray rows = resultJSONObjectData.getJSONArray("rows");
            for (int i = 0; i < rows.size(); i++) {
                quanmama quanmama = JSONObject.parseObject(String.valueOf(rows.getJSONObject(i)), com.bula.gzh.Entity.quanmama.class);
                String link = quanmama.getArticle_link();
                if (link.contains("url=")) {
                    String tempLink = link.substring(link.indexOf("url=") + 4);
                    try {
                        quanmama.setArticle_link(URLDecoder.decode(tempLink, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    quanmamaList.add(quanmama);
                }
                if (!link.contains("quanmama")) {
                    quanmamaList.add(quanmama);
                }
            }
        }
        return ResultGenerator.genSuccessResult(quanmamaList);
    }
}

