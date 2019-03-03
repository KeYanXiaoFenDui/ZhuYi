package com.zy.util;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SOAPRequestUtil {

    private final String certAPIPath = "http://jjrzc.cirea.cn/WebServer/CertInformation.asmx";
    private final String getCertInformation = "http://tempuri.org/getCertInformation";
    private final String getCertRecordInformation = "http://tempuri.org/getCertRecordInformation";
    private final String key = "jjr2018Info";
    private static String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString();
    public static void main(String[] args){
        SOAPRequestUtil s = new SOAPRequestUtil();
        try{
            s.getSoapInputStream("李俊良","","经纪人","getCertInformation");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 对服务器端返回的XML文件流进行解析
     *
     * @param city
     *            用户输入的城市名称
     * @return 字符串 用#分割
     */
    public String getWeather(String city) {
        try {
            // 使用Dom解析
            Document doc;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 获取调用接口后返回的流
            InputStream is = getSoapInputStream("","","","");
            doc = db.parse(is);
            // xml的元素标签是"<string>值1</string><string>值2</string>……"
            NodeList nl = doc.getElementsByTagName("return");
            StringBuffer sb = new StringBuffer();
            for (int count = 0; count < nl.getLength(); count++) {
                Node n = nl.item(count);
                if (n.getFirstChild().getNodeValue().equals("查询结果为空！")) {
                    sb = new StringBuffer("#");
                    break;
                }
                // 解析并以"#"为分隔符,拼接返回结果
                sb.append(n.getFirstChild().getNodeValue() + "#");
            }
            is.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 用户把SOAP请求发送给服务器端，并返回服务器点返回的输入流
     *
     * @param city 用户输入的城市名称
     *
     * @return 服务器端返回的输入流，供客户端读取
     *
     * @throws Exception
     *
     * @备注：有四种请求头格式1、SOAP 1.1； 2、SOAP 1.2 ； 3、HTTP GET； 4、HTTP POST
     * 参考---》http://
     * www.webxml.com.cn/WebServices/WeatherWebService.asmx?op=getWeatherbyCityName
     */
    private InputStream getSoapInputStream(String name,String idNo,String type,String method) throws Exception {
        try {
            // 获取请求规范
            String soap = getSoapRequest(name,idNo,type,method);
            if (soap == null) {
                return null;
            }
            // 调用的webserviceURL
            URL url = new URL(
                    certAPIPath);
            URLConnection conn = url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length",
                    Integer.toString(soap.length()));
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            // 调用的接口方法是
            if(method.equals("getCertInformation")){
                conn.setRequestProperty("SOAPAction",getCertInformation);
            }else if(method.equals("getCertRecordInformation")){
                conn.setRequestProperty("SOAPAction",getCertRecordInformation);
            }

            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
            osw.write(soap);
            osw.flush();
            osw.close();
            // 获取webserivce返回的流
            InputStream is = conn.getInputStream();


            StringBuilder sb = new StringBuilder();
            String line;

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String str = sb.toString();
            System.out.println("str::"+str);



            return is;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 获取SOAP的请求头，并替换其中的标志符号为用户输入的城市
     *
     * @param city： 用户输入的城市名称
     *
     * @return 客户将要发送给服务器的SOAP请求规范
     *
     * @备注：有四种请求头格式1、SOAP 1.1； 2、SOAP 1.2 ； 3、HTTP GET； 4、HTTP POST
     * 参考---》http://
     *
     * 本文采用：SOAP 1.1格式
     */
    private String getSoapRequest(String name,String idNo,String type,String method) {
        StringBuffer sb = new StringBuffer();
        if(method.equals("getCertInformation")){
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            sb.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
            sb.append("  <soap:Body>");
            sb.append("    <getCertInformation xmlns=\"http://tempuri.org/\">");
            sb.append("      <name>"+name+"</name>");
            sb.append("      <idcard>"+idNo+"</idcard>");
            sb.append("      <time>"+date+"</time>");
            String vkey = MD5(name+idNo+date+key).toUpperCase();
            sb.append("      <vkey>"+vkey+"</vkey>");
            sb.append("    </getCertInformation>");
            sb.append("  </soap:Body>");
            sb.append("</soap:Envelope>");
        }
        if(method.equals("getCertRecordInformation")){
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            sb.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
            sb.append("  <soap:Body>");
            sb.append("    <getCertRecordInformation xmlns=\"http://tempuri.org/\">");
            sb.append("      <name>"+name+"</name>");
            sb.append("      <idcard>"+idNo+"</idcard>");
            sb.append("      <regtype>"+type+"</regtype>");
            sb.append("      <time>"+date+"</time>");
            String vkey = MD5(name+idNo+type+date+key).toUpperCase();
            sb.append("      <vkey>"+vkey+"</vkey>");
            sb.append("    </getCertRecordInformation>");
            sb.append("  </soap:Body>");
            sb.append("</soap:Envelope>");
        }
        return sb.toString();
    }
    private static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

}
