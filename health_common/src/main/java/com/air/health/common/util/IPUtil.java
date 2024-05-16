package com.air.health.common.util;

/**
 * @Title: IPUtil
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.util
 * @Date 2024/5/15 17:37
 * @description:
 */

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Liutx
 * @since 2023-11-28 10:05
 */
@Slf4j
public class IPUtil {

//    @Value("${app.ip.db.path}")
    private static final String DB_PATH = "D:\\Programs\\Java\\Air\\AirHealth_Server\\health_common\\src\\main\\resources\\ip2region\\ip2region.xdb";

    private static final ThreadLocal<Searcher> searcherThreadLocal = ThreadLocal.withInitial(() -> {
        try {
            return Searcher.newWithFileOnly(DB_PATH);
        } catch (Exception e) {
            log.error("初始化 IP 归属地查询失败: {}", e.getMessage());
            return null;
        }
    });


    public static String getIPRegion(HttpServletRequest request) {
        String ip = getIPAddress(request);
        Searcher searcher = searcherThreadLocal.get();
        if (searcher == null) {
            log.error("IP 归属地查询失败，返回空");
            return null;
        }
        try {
            long startTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
            log.info("IP: {}, Region: {}, IO Count: {}, Took: {} μs", ip, region, searcher.getIOCount(), cost);
            return region;
        } catch (Exception e) {
            log.error("IP: {} 获取 IP 归属地错误，错误原因: {}", ip, e.getMessage());
            return null;
        } finally {
            closeSearcher();
        }
    }


    private static String getIPAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    public static void closeSearcher() {
        try {
            Searcher searcher = searcherThreadLocal.get();
            if (Objects.nonNull(searcher)) {
                searcher.close();
                searcherThreadLocal.remove();
            }
        } catch (Exception e) {
            log.error("关闭异常", e);
        }
    }
}