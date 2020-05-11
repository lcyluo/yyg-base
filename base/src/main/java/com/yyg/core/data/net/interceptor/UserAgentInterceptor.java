package com.yyg.core.data.net.interceptor;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.yyg.core.constant.BaseConstants;
import com.lcy.base.core.utils.DeviceUtil;
import com.lcy.base.core.utils.SpUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 配置共同请求参数
 *
 * @author lh
 */
public class UserAgentInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder()
                .addHeader(BaseConstants.OK_HTTP_HEADER_CLIENT, "Android")
                .addHeader(BaseConstants.OK_HTTP_HEADER_VERSION_NAME, DeviceUtil.getVersionName());
        String token = SpUtil.getInstance().getString(BaseConstants.KEY_SP_LOGIN_USER_TOKEN, "");
        if (!request.url().url().toString().contains(BaseConstants.APP_API_LOGIN) && !TextUtils.isEmpty(token)) {
            builder.addHeader(BaseConstants.OK_HTTP_HEADER_TOKEN, "Bearer " + token);
        }
        request = builder.build();
        return chain.proceed(request);
    }
}
