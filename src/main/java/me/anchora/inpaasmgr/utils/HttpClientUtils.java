package me.anchora.inpaasmgr.utils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.log4j.Logger;

public class HttpClientUtils {
    private static final Logger logger = Logger.getLogger(HttpClientUtils.class);
    private static final String APPLICATION_JSON = "application/json";

    private static X509TrustManager tm = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    @SuppressWarnings("deprecation")
    public static HttpClient getInstance() {
        HttpClient client = new DefaultHttpClient();
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[] { tm }, null);
        } catch (Exception e) {
            LogUtil.exception(logger, e);
        }
        SSLSocketFactory ssf = new SSLSocketFactory(ctx);
        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = client.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", ssf, 443));
        client = new DefaultHttpClient(ccm, client.getParams());
        return client;
    }

    public static String postByHttp(String url, String inputParam) {
        return postByHttp(url, inputParam, APPLICATION_JSON);
    }
    
    public static String postByHttp(String url, Object vo, Map<String, String> headers) {
        return postByHttp(url, JacksonUtils.toJSON(vo), APPLICATION_JSON, headers);
    }
    
    public static String postByHttp(String url, String inputParam, String contentType) {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        return post(url, inputParam, contentType, httpclient, null);
    }

    public static String postByHttp(String url, String inputParam, String contentType, Map<String, String> header) {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        return post(url, inputParam, contentType, httpclient, header);
    }

    public static String postByHttps(String url, String inputParam, String contentType) {
        DefaultHttpClient httpclient = (DefaultHttpClient) getInstance();

        return post(url, inputParam, contentType, httpclient, null);
    }

    public static String getByHttp(String url) {
        return getByHttp(url, null, APPLICATION_JSON);
    }

    public static String getByHttp(String url, String inputParam) {
        return getByHttp(url, inputParam, APPLICATION_JSON);
    }

    public static String getByHttp(String url, Object vo, Map<String, String> headers) {
        return getByHttp(url, FormUtil.toForm(vo), APPLICATION_JSON, headers);
    }

    public static String getByHttp(String url, String inputParam, String contentType) {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        return get(url, inputParam, contentType, httpclient, new HashMap<String, String>());
    }

    public static String getByHttp(String url, String inputParam, String contentType, Map<String, String> headers) {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        return get(url, inputParam, contentType, httpclient, headers);
    }

    public static String getByHttps(String url, String inputParam, String contentType, Map<String, String> headers) {
        DefaultHttpClient httpclient = (DefaultHttpClient) getInstance();

        return get(url, inputParam, contentType, httpclient, headers);
    }

    public static String putByHttp(String url, Object vo, Map<String, String> headers) {
        return putByHttp(url, JacksonUtils.toJSON(vo), APPLICATION_JSON, headers);
    }

    public static String putByHttp(String url, String inputParam, String contentType, Map<String, String> headers) {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        return put(url, inputParam, contentType, httpclient, headers);
    }

    public static String deleteByHttp(String url, Object vo, Map<String, String> headers) {
        return deleteByHttp(url, JacksonUtils.toJSON(vo), APPLICATION_JSON, headers);
    }

    public static String deleteByHttp(String url, String inputParam, String contentType, Map<String, String> headers) {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        return delete(url, inputParam, contentType, httpclient, headers);
    }

    private static String post(String url, String inputParam, String contentType, HttpClient httpclient, Map<String, String> headers) {

        String restr = null;

        try {
            logger.debug("Start call url:" + url);
            logger.debug("Param is " + inputParam);
            StringEntity reqEntity = new StringEntity(inputParam);
            reqEntity.setContentType(contentType);
            httpclient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 0);// 连接超时
            httpclient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, 0); // 读取超时

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(reqEntity);
            if (headers != null) {
                StringBuffer sb = new StringBuffer();
                for (String key : headers.keySet()) {
                    httpPost.addHeader(key, headers.get(key));
                    sb.append(key + "=" + headers.get(key) + ",");
                }
                logger.debug("Header is " + sb);
            }
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            restr = httpclient.execute(httpPost, responseHandler);
            logger.debug("Result of call url(" + url + ") is:" + restr);
        } catch (Exception e) {
            LogUtil.exception(logger, e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return restr;
    }

    private static String get(String url, String inputParam, String contentType, HttpClient httpclient, Map<String, String> headers) {

        String restr = null;

        try {
            logger.debug("Start call url:" + url);
            logger.debug("Param is " + inputParam);
            httpclient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 0);// 连接超时
            httpclient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, 0); // 读取超时

            HttpGet httpGet;
            if (inputParam != null) {
                StringEntity reqEntity = new StringEntity(inputParam);
                reqEntity.setContentType(contentType);
                httpGet = new HttpGet(url + "?" + inputParam);
            } else {
                httpGet = new HttpGet(url);
            }

            if (headers != null) {
                StringBuffer sb = new StringBuffer();
                for (String key : headers.keySet()) {
                    httpGet.addHeader(key, headers.get(key));
                    sb.append(key + "=" + headers.get(key) + ",");
                }
                logger.debug("Header is " + sb);
            }

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            restr = httpclient.execute(httpGet, responseHandler);
            logger.debug("Result of call url(" + url + ") is:" + restr);
        } catch (Exception e) {
            LogUtil.exception(logger, e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return restr;
    }

    private static String put(String url, String inputParam, String contentType, HttpClient httpclient, Map<String, String> headers) {

        String restr = null;

        try {
            logger.debug("Start call url:" + url + "");
            logger.debug("Param is " + inputParam);
            StringEntity reqEntity = new StringEntity(inputParam);
            reqEntity.setContentType(contentType);
            httpclient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 0);// 连接超时
            httpclient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, 0); // 读取超时

            HttpPut httpPut = new HttpPut(url);
            httpPut.setEntity(reqEntity);

            if (headers != null) {
                StringBuffer sb = new StringBuffer();
                for (String key : headers.keySet()) {
                    httpPut.addHeader(key, headers.get(key));
                    sb.append(key + "=" + headers.get(key) + ",");
                }
                logger.debug("Header is " + sb);
            }

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            restr = httpclient.execute(httpPut, responseHandler);
            logger.debug("Result of call url(" + url + ") is:" + restr);
        } catch (Exception e) {
            LogUtil.exception(logger, e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return restr;
    }

    private static String delete(String url, String inputParam, String contentType, HttpClient httpclient, Map<String, String> headers) {

        String restr = null;

        try {
            logger.debug("Start call url:" + url + "");
            logger.debug("Param is " + inputParam);
            StringEntity reqEntity = new StringEntity(inputParam);
            reqEntity.setContentType(contentType);
            httpclient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 0);// 连接超时
            httpclient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, 0); // 读取超时

            HttpDelete httpDelete = new HttpDelete(url);

            if (headers != null) {
                StringBuffer sb = new StringBuffer();
                for (String key : headers.keySet()) {
                    httpDelete.addHeader(key, headers.get(key));
                    sb.append(key + "=" + headers.get(key) + ",");
                }
                logger.debug("Header is " + sb);
            }

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            restr = httpclient.execute(httpDelete, responseHandler);
            logger.debug("Result of call url(" + url + ") is:" + restr);
        } catch (Exception e) {
            LogUtil.exception(logger, e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

        return restr;
    }
}
