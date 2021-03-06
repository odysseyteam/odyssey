package com.syswin.toon.app.weex.module;

import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.syswin.toon.appstub.event.GlobalEvent;
import com.syswin.toon.appstub.event.bean.GlobalBean;
import com.syswin.toon.appstub.event.bean.Key;
import com.syswin.toon.appstub.event.bean.Value;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

/**
 * @Author : walid
 * @Data : 2017-04-01  09:26
 * @Describe : 用户信息modules
 */
public class WxGlobalEvent extends WXModule {

    @JSMethod(uiThread = true)
    public void post(final JSCallback callback) {
        GlobalEvent.post(new GlobalBean(Key.LOGIN_SUCCESS, new Value(0, "success", "")));
    }

    @JSMethod(uiThread = false)
    public void register(@Nullable final JSCallback callback) {
        GlobalEvent.register(callback, Key.LOGIN_SUCCESS).then(res -> {
            if (callback != null) {
                callback.invoke(JSON.toJSONString(res));
            }
        });
    }

}