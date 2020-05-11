package com.yyg.core.data.net.interceptor;

import android.text.TextUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 超时拦截器
 *
 * @author lh
 */
public class TimeoutInterceptor implements Interceptor {

    public static final String CONNECT_TIMEOUT = "CONNECT_TIMEOUT";
    public static final String READ_TIMEOUT = "READ_TIMEOUT";
    public static final String WRITE_TIMEOUT = "WRITE_TIMEOUT";

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        int connectTimeout = chain.connectTimeoutMillis();
        int readTimeout = chain.readTimeoutMillis();
        int writeTimeout = chain.writeTimeoutMillis();

        String connectNew = request.header(CONNECT_TIMEOUT);
        String readNew = request.header(READ_TIMEOUT);
        String writeNew = request.header(WRITE_TIMEOUT);

        if (connectNew != null && !TextUtils.isEmpty(connectNew)) {
            connectTimeout = Integer.parseInt(connectNew);
        }
        if (readNew != null && !TextUtils.isEmpty(readNew)) {
            readTimeout = Integer.parseInt(readNew);
        }
        if (writeNew != null && !TextUtils.isEmpty(writeNew)) {
            writeTimeout = Integer.parseInt(writeNew);
        }
        return chain
                .withConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .withReadTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .withWriteTimeout(writeTimeout, TimeUnit.MILLISECONDS)
                .proceed(request);
    }
}
