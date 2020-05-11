package com.yyg.core.data.net.interceptor;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.yyg.core.constant.BaseApiConstants;
import com.yyg.core.constant.BaseConstants;
import com.yyg.core.data.net.BaseApis;
import com.yyg.core.data.net.BaseResponse;
import com.yyg.core.data.net.DataResponse;
import com.yyg.core.data.protocol.UserInfo;
import com.lcy.base.core.rx.RxBus;
import com.lcy.base.core.utils.GsonConvertUtil;
import com.lcy.base.core.utils.SpUtil;
import com.yyg.core.app.BaseApplication;
import com.yyg.core.event.BaseEvent;
import com.yyg.core.event.BaseEventCode;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Call;

/**
 * Token刷新拦截器
 * <p>
 *
 * @author lcy
 * @date 2019/1/23
 * email:lcyzxin@gmail.com
 * version 1.0
 */
public class TokenInterceptor implements Interceptor {

    private static final Charset UTF8 = StandardCharsets.UTF_8;

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        // 排除登录接口和验证服务器接口的token验证
        if (request.url().url().toString().contains(BaseConstants.APP_API_LOGIN)) {
            request.newBuilder().removeHeader(BaseConstants.OK_HTTP_HEADER_TOKEN);
        }
        // 验证是否
        if (request.url().url().toString().contains(BaseConstants.APP_API_LOGIN) && isTokenExpired(response, true)) {
            return response;
        }
        if (isTokenExpired(response, false)) {
            response.close();
            // Token过期移除原Token信息
            // 同步请求方式，获取最新的Token
            String newToken = getNewToken();
            // 根据和服务端的约定判断token过期
            if (!TextUtils.isEmpty(newToken)) {
                SpUtil.getInstance().putString(BaseConstants.KEY_SP_LOGIN_USER_TOKEN, newToken);
                //使用新的Token，创建新的请求
                Request newRequest = chain.request();
                Request.Builder builder = newRequest.newBuilder();
                //  移除旧的失效token
                builder.removeHeader(BaseConstants.OK_HTTP_HEADER_TOKEN);
                //  添加新的token信息
                builder.addHeader(BaseConstants.OK_HTTP_HEADER_TOKEN, "Bearer " + newToken);
                newRequest = builder.build();
                //重新请求
                return chain.proceed(newRequest);
            }
        }
        return response;

    }

    /**
     * 根据Response，判断Token是否失效
     */
    private boolean isTokenExpired(Response response, boolean checkPassword) throws IOException {
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            //获取响应体的字符串
            String bodyString = buffer.clone().readString(Objects.requireNonNull(charset));
            BaseResponse baseResponse = GsonConvertUtil.fromJson(bodyString, BaseResponse.class);

            if (baseResponse.getCode() == BaseApiConstants.PASSWORD_ERROR && checkPassword) {
                //  发送通知,退出登录
                RxBus.INSTANCE.post(new BaseEvent<String>(BaseEventCode.EVENT_LOGOUT, null));
                return true;
            }
            return baseResponse.getCode() == BaseApiConstants.TOKEN_EXPIRE
                    || baseResponse.getCode() == BaseApiConstants.TOKEN_ERROR;
        }
        return false;
    }

    /**
     * 同步请求方式，获取最新的Token
     */
    private String getNewToken() throws IOException {
        // 通过获取token的接口，同步请求接口
        String token = null;
        BaseApis taskService = BaseApplication.Companion.instance().getAppComponent().provideRetrofit().create(BaseApis.class);
        String username = SpUtil.getInstance().getString(BaseConstants.KEY_SP_LOGIN_USER_ACCOUNT, null);
        String password = SpUtil.getInstance().getString(BaseConstants.KEY_SP_LOGIN_USER_PASSWORD, null);
        Call<DataResponse<UserInfo>> call = taskService.refreshAccessToken(username, password);
        DataResponse<UserInfo> response = call.execute().body();
        if (response != null && response.getStatus() && response.getData() != null) {
            token = response.getData().getToken();
        }
        return token;
    }
}
