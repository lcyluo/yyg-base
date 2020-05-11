package com.yyg.common.util;

import android.text.TextUtils;

import com.yyg.common.constant.CommonConstants;
import com.lcy.base.core.utils.SpUtil;
import com.yyg.core.constant.BaseConstants;
import com.yyg.core.data.protocol.UserInfo;

/**
 * 管理用户登录相关信息
 *
 * @author lh
 */
public class SpLoginManager {

    private SpLoginManager() {
    }

    /**
     * 提供单例获取入口
     */
    public static SpLoginManager getInstance() {
        return SpLoginManagerHolder.holder;
    }

    private static class SpLoginManagerHolder {
        private static final SpLoginManager holder = new SpLoginManager();
    }

    /**
     * 保存用户信息
     *
     * @param userInfo       用户信息
     * @param saveLoginState 是否存储用户登录状态(用于用户首次选择体验项目)
     */
    public void saveLoginInfo(UserInfo userInfo, boolean saveLoginState) {
        // 存储用户ID
        // SpUtil.getInstance().putString(CommonConstants.KEY_SP_LOGIN_USER_ID, userInfo.getUserId());
        // 存储用户token
        // SpUtil.getInstance().putString(BaseConstants.KEY_SP_LOGIN_USER_TOKEN, userInfo.getToken());
        // 存储用户团队id
        // SpUtil.getInstance().putString(CommonConstants.KEY_SP_LOGIN_TEAM_ID, userInfo.getTeamId());
        //  存储用户团队名称
        // SpUtil.getInstance().putString(CommonConstants.KEY_SP_LOGIN_TEAM_NAME, userInfo.getTeamName());
        //  存储用户登录状态

        SpUtil.getInstance().putBoolean(CommonConstants.KEY_SP_LOGIN_STATE, saveLoginState);
        //  存储用户名
        if (!TextUtils.isEmpty(userInfo.getAccount())) {
            SpUtil.getInstance().putString(BaseConstants.KEY_SP_LOGIN_USER_ACCOUNT, userInfo.getAccount());
        }
        //  存储用户密码
        if (!TextUtils.isEmpty(userInfo.getPassword())) {
            SpUtil.getInstance().putString(BaseConstants.KEY_SP_LOGIN_USER_PASSWORD, userInfo.getPassword());
        }
    }

    /**
     * 获取用户的登录状态
     *
     * @return 是否登录
     */
    public boolean isUserLogin() {
        return SpUtil.getInstance().getBoolean(CommonConstants.KEY_SP_LOGIN_STATE, false);
    }

    /**
     * 清除用户登录信息
     */
    public void clearLoginInfo() {
        SpUtil.getInstance().remove(CommonConstants.KEY_SP_LOGIN_USER_ID);
        SpUtil.getInstance().remove(BaseConstants.KEY_SP_LOGIN_USER_TOKEN);
        SpUtil.getInstance().remove(CommonConstants.KEY_SP_LOGIN_STATE);
    }

}
