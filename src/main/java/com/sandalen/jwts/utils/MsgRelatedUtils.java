package com.sandalen.jwts.utils;

import com.sandalen.jwts.beans.UserInfoCountBean;
import com.sandalen.jwts.entity.MsgUserExample;
import com.sandalen.jwts.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MsgRelatedUtils {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private MsgService msgService;

    public UserInfoCountBean getNotReadCount(String token){
        String id = jwtUtils.getId(token);
        MsgUserExample msgUserExample = new MsgUserExample();
        MsgUserExample.Criteria criteria = msgUserExample.createCriteria();
        criteria.andUidEqualTo(Integer.parseInt(id));
        System.out.println("uid" + id);
        criteria.andIsreadEqualTo(0);
        int notReadMsgCount = msgService.getNotReadMsgCount(msgUserExample);
        System.out.println("count" + notReadMsgCount);
        UserInfoCountBean userInfoCountBean = new UserInfoCountBean();
        userInfoCountBean.setUser(Integer.parseInt(id));
        userInfoCountBean.setInfoCount(notReadMsgCount);
        return userInfoCountBean;
    }
}
