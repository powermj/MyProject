package com.zhxg.fgw.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created: balin on 2016/4/3 11:43.
 */
@SuppressWarnings({"unused", "WeakerAccess", "SameParameterValue"})
public class HttpHelper {
    private static final Logger logger = LoggerFactory.getLogger(HttpHelper.class);

    public static String getParam(HttpServletRequest request, String paraName, boolean trim, String def) {
        String value = request.getParameter(paraName);
        if (value == null || "undefined".equals(value) || "null".equals(value) || "None".equals(value)) {
            return def;
        }

        if (trim) {
            return value.trim();
        }

        return value;
    }

    public static String getParam(HttpServletRequest request, String paraName) {
        return getParam(request, paraName, true, null);
    }

    public static int getFirstIntParam(HttpServletRequest request, int def, String... paraName) {
        String value = getFirstParam(request, paraName);
        return str2int(value, def);
    }

    /**
     * 获取第一个有效的参数值
     * @param request -
     * @param paraName - 参数数组，参数个数不定
     * @return -
     */
    public static String getFirstParam(HttpServletRequest request, String... paraName) {
        if (paraName == null) {
            return null;
        }
        for (String name : paraName) {
            String value = getParam(request, name, true, null);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    public static String getFirstHeaderValue(HttpServletRequest request, String... paraName) {
        if (paraName == null) {
            return null;
        }
        for (String name : paraName) {
            String value = request.getHeader(name);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    public static int getIntParam(HttpServletRequest request, String paraName, int def) {
        String value = getParam(request, paraName);

        return str2int(value, def);
    }

    public static int getIntParam(HttpServletRequest request, String paraName) {
        String value = getParam(request, paraName);

        return getIntParam(request, paraName, 0);
    }

    public static long getLongParam(HttpServletRequest request, String paraName, long def) {
        String value = getParam(request, paraName);

        return str2lng(value, def);
    }

    public static long getLongParam(HttpServletRequest request, String paraName) {
        String value = getParam(request, paraName);

        return getLongParam(request, paraName, 0);
    }

    public static double getDoubleParam(HttpServletRequest request, String paraName, double def) {
        String val = getParam(request, paraName, true, Double.toString(def));
        if (val == null || "".equals(val)) {
            return def;
        }

        try {
            return Double.parseDouble(val);
        } catch (NumberFormatException ignored) {
            System.err.println("parse double error: " + val);
        }

        return def;
    }

    public static double getDoubleParam(HttpServletRequest request, String paraName) {
        return getDoubleParam(request, paraName, 0);
    }

    public static Double getDouble(HttpServletRequest request, String paraName) {
        String val = getParam(request, paraName);
        if (val == null || "".equals(val)) {
            return null;
        }
        try {
            return Double.parseDouble(val);
        } catch (NumberFormatException ignored) {
            System.err.println("parse double error: " + val);
        }

        return null;
    }

    public static Map<String, String> getParam2Map(HttpServletRequest request, String... params) {
        Map<String, String> map = new HashMap<>();
        for (String paraName : params) {
            map.put(paraName, getParam(request, paraName));
        }

        return map;
    }

    public static int str2int(String value, int def) {
        if (value == null || "".equals(value)) {
            return def;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return def;
    }

    public static int str2int(String value) {
        return str2int(value, 0);
    }

    public static long str2lng(String value, long def) {
        if (value == null || "".equals(value)) {
            return def;
        }

        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return def;
    }

    public static long str2lng(String value) {
        return str2lng(value, 0);
    }


    public static boolean isAjax(HttpServletRequest request) {
        String contentType = request.getContentType();
        String requestType = request.getHeader("X-Requested-With");
        return ("XMLHttpRequest".equals(requestType) || "text/html".equals(contentType));
    }

    public static void printText(HttpServletResponse response, String contentType, String text) {
        response.setContentType(contentType);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
            response.getWriter().write(text);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void printText(HttpServletResponse response, String text) {
        printText(response, "text/plain;charset=UTF-8", text);
    }

    public static void printXml(HttpServletResponse response, String text) {
        printText(response, "text/xml;charset=UTF-8", text);
    }

    public static void printJson(HttpServletResponse response, String text) {
        printText(response, "application/json;charset=UTF-8", text);
    }

    public static void printJson(HttpServletResponse response, boolean flag, String msg) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("code", flag ? 0 : 1);
        node.put("success", flag);
        if (msg != null)
            node.put("msg", msg);

        printJson(response, node.toString());
    }

    public static void printSuccessJson(HttpServletResponse response, String msg) {
        printJson(response, true, msg);
    }

    public static void printFailureJson(HttpServletResponse response, String msg) {
        printJson(response, false, msg);
    }

    public static void printFailureResult(HttpServletResponse response, Map<String,Object> result) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("success", false);
        for(Map.Entry<String,Object> entity: result.entrySet()){
            node.put(entity.getKey(),entity.getValue().toString());
        }
        printJson(response, node.toString());
    }

    public static void renderFailurePage(HttpServletResponse response, int statusCode, String msg) {
        response.setContentType("text/plain; charset=UTF-8");
        if (msg == null) {
            msg = "internal error";
        }
        String errorText = String.format("<html><title>%d - error</title><body>%d: %s</body></html>", statusCode, statusCode, msg);
        printText(response, errorText);
    }

    @SuppressWarnings({"Duplicates"})
    public static String getIp(HttpServletRequest request) {
//        System.out.println(request.getRemoteAddr());
//        System.out.println(request.getRemoteHost());
//        System.out.println(request.getHeader("x-forwarded-for"));
//        System.out.println(request.getHeader("WL-Proxy-Client-IP"));
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public static String getMACAddress(HttpServletRequest request) throws Exception {
        String ip = getIp(request);
        String line;
        String macAddress = "";
        final String MAC_ADDRESS_PREFIX = "MAC Address = ";
        final String LOOPBACK_ADDRESS = "127.0.0.1";
        //如果为127.0.0.1,则获取本地MAC地址。
        if (LOOPBACK_ADDRESS.equals(ip)) {
            InetAddress inetAddress = InetAddress.getLocalHost();
            //貌似此方法需要JDK1.6。
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            //下面代码是把mac地址拼装成String
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                //mac[i] & 0xFF 是为了把byte转化为正整数
                String s = Integer.toHexString(mac[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);
            }
            //把字符串所有小写字母改为大写成为正规的mac地址并返回
            macAddress = sb.toString().trim().toUpperCase();
            return macAddress;
        }
        //获取非本地IP的MAC地址
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                int index = line.indexOf(MAC_ADDRESS_PREFIX);
                if (index != -1) {
                    macAddress = line.substring(index + MAC_ADDRESS_PREFIX.length()).trim().toUpperCase();
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return macAddress;
    }


    public static String getParameters(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Enumeration paramNames = request.getParameterNames();
        int idx = 0;
        while (paramNames.hasMoreElements()) {
            idx ++ ;
            if (idx > 1) {
                sb.append("&");
            }

            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);

            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                sb.append(paramName).append("=").append(paramValue);
            } else {
                sb.append(paramName).append("=").append(Arrays.toString(paramValues));
            }
        }

        return sb.toString();
    }

    @SuppressWarnings("SpellCheckingInspection")
    public static boolean isMobileBrowser(String userAgent) {
        if (userAgent == null) {
            return false;
        }
        String[] mobileAgents = {"micromessenger", "iphone", "ipod", "ipad", "android", "mobile", "blackberry", "webos", "incognito", "webmate", "bada", "nokia", "lg", "ucweb", "skyfire"};
        userAgent = userAgent.toLowerCase();
        for (String mobileAgent : mobileAgents) {
            if (userAgent.contains(mobileAgent)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isMobileBrowser(HttpServletRequest request) {
        return isMobileBrowser(request.getHeader("User-Agent"));

    }

    public static boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    public static boolean notBlank(String value) {
        return !isBlank(value);
    }

}
