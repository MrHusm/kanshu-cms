package com.yxsd.kanshu.log;



import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@SuppressWarnings({"deprecation", "TryWithIdenticalCatches"})
public class ConnectUtil {

    private static final String TAG = ConnectUtil.class.getSimpleName();

    private ConnectUtil() {
    }

    /**
     * 用post方式发送http请求
     *
     * @param url    url
     * @param params 参数map<key,value>
     * @return 返回页面内容
     * @throws Exception
     */
    public static String post(String url, Map<String, String> params,
                              boolean addTokenId, boolean userGZip) throws Exception {
        HttpPost httpost = null;
        try {
            httpost = new HttpPost(url);
        } catch (Exception e) {
            throw new Exception("参数不正确");
        }
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        // StringBuilder str = new StringBuilder("");

        String key;
        String value;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            nvps.add(new BasicNameValuePair(key, value));
        }

        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {

        }
        String strRet = execute(getPostHttpClient(), httpost, addTokenId, userGZip);
        return strRet;
    }

    /**
     * 用get方式发送http请求，如果没有返回结果则抛出异常
     *
     * @param url url链接
     * @return 返回页面内容字符串
     * @throws IOException
     * @throws UnknownHostException
     */
    public static String get(String url, boolean addUserInfo, boolean usingGZip)
            throws Exception, IOException {
        System.out.println( " url = " + url);
        String s = "";
        try {
            HttpGet httpget = new HttpGet(url.trim());
            s = execute(httpget, addUserInfo, usingGZip);
            if (s == null || s.length() == 0) {
                throw new Exception("无法获取数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;

    }

    public static String get(String url) throws Exception, IOException {
        return get(url, true, true);
    }

    public static String get(String url, boolean adduserinfo) throws Exception, IOException {
        return get(url, adduserinfo, true);
    }

    /**
     * 将输入流转换成字符串，按行读取，并添加上换行符"\n"
     *
     * @param is 输入流 InputStream
     * @return 字符串
     * @throws Exception
     */
    public static String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is), 8192);
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                line = null;
            }

        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 从网络上获取
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static InputStream getInputStream(String url) throws Exception {
        DefaultHttpClient httpclient = getHttpClient();
        try {
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = null;
            // if(httpclient.isProxy())
            // {
            // response = httpclient.execute(httpclient.getProxyTarget(),
            // httpget);
            // }
            // else
            {
                response = httpclient.execute(httpget);
            }

            if (null != response
                    && null != response.getStatusLine()
                    && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    return entity.getContent();
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new Exception("网络错误");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("网络错误");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new Exception("网络错误");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("网络错误");
        }
        return null;
    }

    private static String execute(HttpClient httpclient, HttpUriRequest req,
                                  boolean addUserInfo, boolean usingZip) throws Exception {
        String result = "";

        HttpResponse response = null;
        InputStream instream = null;
        try {
            // set header
            if (addUserInfo) {
                String userTokenId = "aGQxZWo2MkA6MTMxNDc2MjYxOToz";
                if (userTokenId == null || userTokenId.length() <= 0) {
                    userTokenId = "aGQxZWo2MkA6MTMxNDc2MjYxOToz";
                }
                req.setHeader("tokenId", userTokenId);
            }

            if (usingZip) {
                req.addHeader("Accept-Encoding", "gzip");
            }
            // if(httpclient.isProxy())
            // {
            // response = httpclient.execute(httpclient.getProxyTarget(), req);
            // }
            // else
            {
                response = httpclient.execute(req);
            }
            HttpEntity entity = response.getEntity();

            String statusCode = response.getStatusLine().toString();
            System.out.println( "statusCode = " + statusCode);

            Header contentEncoding = response
                    .getFirstHeader("Content-Encoding");
            if (entity != null
                    && response.getStatusLine().toString().contains("200")) {
                instream = entity.getContent();
                if (contentEncoding != null
                        && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    System.out.println( "it is gzip mode = " + contentEncoding.getValue());
                    InputStream is = new GZIPInputStream(instream);
                    instream = null;
                    instream = is;
                } else {
                    if (contentEncoding == null) {
                        System.out.println( "contentEncoding == null");
                    } else {
                        System.out.println( "equalsIgnoreCase isn't gzip mode ");
                    }
                }
                result = convertStreamToString(instream);
            }
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            throw new Exception("网络连接超时、稍候重试.");
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new Exception("网络连接超时、稍候重试.");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new Exception("无法连接服务器、稍候重试.");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new Exception("网络错误、稍候重试.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("网络错误、稍候重试.");
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                    instream = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static String execute(HttpUriRequest req, boolean addUserInfo,
                                  boolean usingZip) throws Exception {

        return execute(getHttpClient(), req,
                addUserInfo, usingZip);
    }

    public static DefaultHttpClient getPostHttpClient() {

        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
        HttpConnectionParams.setSoTimeout(httpParams, 30000);
        HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
        HttpClientParams.setRedirecting(httpParams, true);
        String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
        HttpProtocolParams.setUserAgent(httpParams, userAgent);
        return new DefaultHttpClient(httpParams);
    }

    public static DefaultHttpClient getHttpClient() {
        DefaultHttpClient httpClient = null;
        httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 100000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                600000);
        httpClient.getParams().setParameter(
                CoreConnectionPNames.SOCKET_BUFFER_SIZE, 8192);
        httpClient.getParams().setParameter("http.protocol.content-charset",
                "UTF-8");
        return httpClient;
    }


    public static byte[] getData(String url) throws Exception {
        return getData(url, true);
    }

    public static byte[] getData(String url, boolean usingGZip)
            throws Exception {

        System.out.println( " url = " + url);
        byte[] bytes = null;
        HttpGet httpget = new HttpGet(url);
        bytes = getData(getHttpClient(), httpget, usingGZip);
        if (bytes == null) {
            throw new Exception("无法获取数据");
        }

        return bytes;

    }

    private static byte[] getData(HttpClient httpclient, HttpUriRequest req, boolean usingZip) throws Exception {

        HttpResponse response = null;
        InputStream instream = null;
        try {
            if (usingZip) {
                req.addHeader("Accept-Encoding", "gzip");
            }
            response = httpclient.execute(req);
            HttpEntity entity = response.getEntity();
            String statusCode = response.getStatusLine().toString();
            System.out.println( "statusCode = " + statusCode);
            Header contentEncoding = response.getFirstHeader("Content-Encoding");
            if (entity != null
                    && response.getStatusLine().toString().contains("200")) {
                instream = entity.getContent();
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    InputStream is = new GZIPInputStream(instream);
                    instream = null;
                    instream = is;
                } else {
                    if (contentEncoding == null) {
                        System.out.println( "contentEncoding == null");
                    } else {
                        System.out.println( "equalsIgnoreCase isn't gzip mode ");
                    }
                }
                if (instream != null) {
                    String strResult = convertStreamToString(instream);
                    try {
                        instream.close();
                    } catch (Exception e) {
                    }
                    if (strResult != null) {
                        return strResult.getBytes();
                    }
                }
                return null;
            } else {
                throw new Exception("网络错误、稍候重试");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new Exception("无法连接服务器、稍候重试.");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new Exception("网络错误、稍候重试.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("网络错误、稍候重试.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("网络错误、稍候重试.");
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                    instream = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void throwNoNet() throws Exception {

    }

    public static String postJsonData(String uploadUrl, String json) throws Exception {

        throwNoNet();
        try {
            URL url = new URL(uploadUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url
                    .openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/json");
            DataOutputStream dos = new DataOutputStream(httpURLConnection
                    .getOutputStream());

            dos.write(json.getBytes("utf-8"));// 发送表单字段数据
            dos.flush();
            if (httpURLConnection.getResponseCode() == 200) {
                // 读取服务器返回结果
//				String result = convertStreamToString(instream);
                return "";
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new Exception("无法连接服务器");
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new Exception("连接超时");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("网络错误");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("网络错误");
        }

        return null;

    }

    /**
     * @desc POST 流形式
     * @author ygzhang
     * @time 2016/12/15
     * @version <2.7.0 历史版本
     */
    public static String postByteData(String uploadUrl, byte[] data,String params) throws Exception {
        throwNoNet();
        try {
            URL url = new URL(uploadUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url
                    .openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Multiple-Param", params);
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/octet-stream");
            DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.write(data);
            dos.flush();

            dos = null;
            if (httpURLConnection.getResponseCode() == 200) {
                // 读取服务器返回结果
                InputStream instream = httpURLConnection.getInputStream();
                String result = convertStreamToString(instream);
                return result;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new Exception("无法连接服务器");
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new Exception("连接超时");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("网络错误");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("网络错误");
        }

        return null;
    }

    public static String postByteData(String uploadUrl, byte[] data, int ek) throws Exception {
        throwNoNet();
        try {
            URL url = new URL(uploadUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Multiple-Param", UrlManager.getUrlForMoreParams(""));
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/octet-stream");
            httpURLConnection.setRequestProperty("ek", ek + "");
            httpURLConnection.setRequestProperty("uid", "10000");
            System.out.println("data post ByteData method = " + data.length);
            DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.write(data);
            dos.flush();

            if (httpURLConnection.getResponseCode() == 200) {
                // 读取服务器返回结果
                // 读取服务器返回结果

                InputStream instream = httpURLConnection.getInputStream();
                String result = convertStreamToString(instream);
                return result;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new Exception("无法连接服务器");
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new Exception("连接超时");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("网络错误");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("网络错误");
        }
        return null;
    }
}
