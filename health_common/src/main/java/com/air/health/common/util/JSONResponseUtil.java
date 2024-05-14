package com.air.health.common.util;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;

public class JSONResponseUtil {

    /**
     * 输出JSON格式数据
     * @param httpServletRequest
     * @param httpServletResponse
     * @param obj
     * @throws IOException
     * @throws ServletException
     */
    public static void writeJSON(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object obj) throws IOException, ServletException {
        //设置编码格式
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //处理跨域问题
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET");

        //输出JSON
        PrintWriter out = httpServletResponse.getWriter();
        out.write(JSON.toJSONString(obj));
        out.flush();
        out.close();
    }
}

