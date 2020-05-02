package com.diane.manage.util;

import java.util.*;

public class CommonUtil {
    public static String signUtil(Map<String,Object> parms){

        List<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(parms.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>() {
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        String sign="";

        Iterator<Map.Entry<String,Object>> iterator = list.iterator();
        while(iterator.hasNext()){
            sign=sign.concat(iterator.next().toString()).concat("&");

            // System.out.println(iterator.next());
        }
        sign = sign.substring(0,sign.length()-1);
       // System.out.println(sign);
        return sign;

    }


    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        id = id.replace("-", "");
        return id;
    }

    public static int randomInt(int min, int max){
        return new Random().nextInt(max)%(max-min+1) + min;
    }




}
