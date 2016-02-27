package com.cmy.http;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpInvoker {


    public static void main(String[] args) throws Exception {
//        postWithFile();
        post();
    }

    public static void postWithFile() throws Exception {
        String filePath = "d:/测试.txt";
        // 创建http客户端和post
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
//        HttpPost httpPost = new HttpPost("http://localhost:8080/ssm/demo/uploadImg.action");
        HttpPost httpPost = new HttpPost("http://121.41.41.156:8080/demo/uploadImg.action");

        // 设置上传的文件
        FileBody fileBody = new FileBody(new File(filePath));
        // 设置请求参数
        JSONObject param = new JSONObject();
        param.put("img", "测试.txt");
        param.put("fileName", "a.txt");
        param.put("venueID", 47);
        param.put("userID", 66);
        param.put("token", "tokenValue");
        param.put("x", "测试");
        StringBody sb = new StringBody(param.toString(),
                ContentType.APPLICATION_JSON);
        
        // 设置上传实体,包含文件和参数
        MultipartEntityBuilder mpEntity = MultipartEntityBuilder.create();
        // 设置请求的编码格式
        mpEntity.setCharset(Charset.forName("UTF-8"));
        // 设置浏览器兼容模式
        mpEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        
        mpEntity.addPart("file", fileBody);
        mpEntity.addPart("data", sb);
        httpPost.setEntity(mpEntity.build());

        // 执行请求
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 获取返回实体
        HttpEntity entity = response.getEntity();
        System.out.println(entity);

        // 解析实体内容
        String responseString = EntityUtils.toString(entity, "UTF-8");
        System.out.println(responseString);
        
        httpClient.close();
    }

    public static void post() throws Exception {
        // 创建http客户端和post
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://121.41.41.156:8080/user/login.action");
        
        List<NameValuePair> nvps = new ArrayList<NameValuePair>(); 

        // 设置参数
        JSONObject param = new JSONObject();
        param.put("userName", "银江运动中心城西馆");
        param.put("password", "123456");
        nvps.add(new BasicNameValuePair("data", 
                param.toJSONString()));

        httpPost.setEntity(
                new UrlEncodedFormEntity(nvps, "UTF-8"));
        
        // 执行请求
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 获取返回实体
        HttpEntity entity = response.getEntity();
        // 解析实体内容
        String responseString = EntityUtils.toString(entity, "UTF-8");
        System.out.println(responseString);
        
        httpClient.close();
    }
}