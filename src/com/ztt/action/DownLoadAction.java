//package com.ztt.action;
//
//import com.opensymphony.xwork2.ActionSupport;
//import org.apache.struts2.convention.annotation.*;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//
///**
// * @author zhaotian
// * @date 2018/5/2 17:21
// */
//
//@Namespace("/file")
//@ParentPackage("struts-default")
//public class DownLoadAction extends ActionSupport{
//    private String STORAGEPATH = "http://fs.w.kugou.com/201805021722/b3881c0bf169dc3b42982b9c4893d5ff/G123/M09/1E/13/uw0DAFo7CmOAckvsAEUM6BGXWvo816.mp3";
//    private String fileName;// 初始的通过param指定的文件名属性
//    private String inputPath;// 指定要被下载的文件路径
//    private InputStream inputStream;
//    public String getFileName() {
//
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
//    public void setInputPath(String inputPath) {
//        this.inputPath = inputPath;
//    }
//
//    public String getInputPath() {
//        return inputPath;
//    }
//
//    public void setInputStream(InputStream inputStream) {
//        this.inputStream = inputStream;
//    }
//
//    public InputStream getInputStream() throws Exception {
//
//        InputStream isReader = null;
//        int HttpResult; // 服务器返回的状态
//        URL url =new URL(STORAGEPATH); // 创建URL
//        URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
//        urlconn.connect();
//        HttpURLConnection httpconn =(HttpURLConnection)urlconn;
//        HttpResult = httpconn.getResponseCode();
//        if(HttpResult != HttpURLConnection.HTTP_OK) // 不等于HTTP_OK说明连接不成功
//            System.out.print("无法连接到");
//        else
//        {
//            try {
//                isReader = urlconn.getInputStream();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//        }
//        return isReader;
//    }
//    @Action(value = "download",results = {
//            @Result(name = "success", type = "stream", params = { "contentType",
//                    "application/octet-stream;charset=UTF-8", "inputName",
//                    "inputStream", "contentDisposition",
//                    "attachment;filename=\"${fileName}\"", "bufferSize", "4096" }),
//            @Result(name = "error",location = "/error.jsp")
//    })
//    public String execute() throws Exception {
//        fileName=new String("于文文-Englis.mp3".getBytes("utf-8"),"ISO_8859_1");
//        return SUCCESS;
//    }
//
//}
