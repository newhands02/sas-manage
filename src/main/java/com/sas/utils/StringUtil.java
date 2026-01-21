package com.sas.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StringUtil {
    public static String spiltUnit(String str){
        String result=str;
        if(str!=null){
            result=str.replaceAll("亿|万|千|百", "");
        }
        return result;
    }
    public static String spiltJsonStrUnit(JSONObject data,String dataKey){
        String str=data.getString(dataKey);
        String result=str;
        if(str!=null){
            result="false".equals(str)?"0":str.replaceAll("亿|万|千|百", "");
        }else {
            result="0";
        }
        return result;
    }
    public static float str2float(String str){
        if("false".equals(str) || str==null){
            return 0;
        }
        return Float.parseFloat(str);
    }

    public static String floatRound2Str(float f){
        return String.format("%.2f", f);
    }
    public static String doubleRound2Str(double d){
        return String.format("%.2f", d);
    }
    public static List<JSONObject> sortJsonArrayByKey(String key, JSONArray allData){
        // 将 JSONArray 转换为 ArrayList<JSONObject>
        List<JSONObject> dataList = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            dataList.add(allData.getJSONObject(i));
        }
        // 使用 Collections.sort() 方法进行排序
        Collections.sort(dataList, new Comparator<JSONObject>() {
            private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                try {
                    Date date1 = dateFormat.parse(o1.getString("报告期"));
                    Date date2 = dateFormat.parse(o2.getString("报告期"));
                    return date2.compareTo(date1); // 降序排列
                } catch (ParseException e) {
                    return 0;
                }
            }
        });
        return dataList;
    }
    public static boolean isBlank(String str){
        return str==null || "".equals(str);
    }
}
