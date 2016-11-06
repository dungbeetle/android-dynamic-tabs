package gobyfragment.we.com.gobyfragments;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/**
 * Created by hekeji on 16/11/2.
 */
public class HttpGetRequest extends Thread {

    final String HTTP_METHOD_GET = "GET";

    private String requestUrl;

    private Map<String, String> params;

    private Handler handler;

    private boolean isHttps;

    private String authorization;

    private String clientInfoHeader;

    public HttpGetRequest(String requestUrl, Map<String, String> params, Handler handler, boolean isHttps, String authorization, String clientInfoHeader) {
        this.requestUrl = requestUrl;
        this.params = params;
        this.handler = handler;
        this.isHttps = isHttps;
        this.authorization = authorization;
        this.clientInfoHeader = clientInfoHeader;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        try {
            if (isHttps) {
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, new TrustManager[]{new MyTrustManager()}, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
            }
            URL url = new URL(requestUrl + "?" + buildQueryParameters(params));
            urlConnection = (HttpURLConnection) url.openConnection();

    		/* optional request header */
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

    		/* optional request header */
//            urlConnection.setRequestProperty("Accept", "application/json");

            if (clientInfoHeader != null) {

                urlConnection.setRequestProperty("Zhuang-ClientInfo", clientInfoHeader);
            }
            if (authorization != null) {
                urlConnection.setRequestProperty("Authorization", authorization);
            }
            urlConnection.setRequestMethod(HTTP_METHOD_GET);
            urlConnection.setUseCaches(false);
            // try to get response
            int statusCode = urlConnection.getResponseCode();
            urlConnection.getResponseMessage();
            if (statusCode >= 200 && statusCode <= 400) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                String response = InputStreamTools.covert2String(inputStream);
                handler.sendMessage(Message.obtain(handler, statusCode, response));
            } else {
                handler.sendMessage(Message.obtain(handler, statusCode, ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

    }

    private String buildQueryParameters(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder data = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                data.append(entry.getKey()).append("=");
                data.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                data.append("&");
            }
            data.deleteCharAt(data.length() - 1);
        }
        return data.toString();
    }

}
