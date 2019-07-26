package com.sandalen.jwts.controller;

import com.alibaba.fastjson.JSON;
import com.sandalen.jwts.beans.RespBean;
import com.sandalen.jwts.beans.UserInfoCountBean;
import com.sandalen.jwts.entity.*;
import com.sandalen.jwts.service.MsgService;
import com.sandalen.jwts.service.UserService;
import com.sandalen.jwts.utils.JwtUtils;
import edu.princeton.cs.algs4.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/msg")
@Controller
public class MsgController {
    @Autowired
    private MsgService msgService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @ResponseBody
    @PostMapping("/sendNotice")
    public RespBean sendNotice(@RequestBody Msg msg){
        Integer uid = msg.getUid();
        int i = msgService.sendNotice(msg);
        Integer msid = msg.getId();

        MsgUser yourself = new MsgUser();
        yourself.setMsid(msid);
        yourself.setUid(uid);
        yourself.setIsread(1);
        msgService.insertMsgUser(yourself);

        List<User> users = userService.getUserExceptSelf(uid);
        for(User user : users ){
            MsgUser msgUser = new MsgUser();
            msgUser.setMsid(msid);
            msgUser.setUid(user.getId());
            msgUser.setIsread(0);
            msgService.insertMsgUser(msgUser);
        }

        if(i != 0){
            return RespBean.ok("提交成功");
        }
        return RespBean.error("提交失败");
    }


    @MessageMapping("/getMsgCount")
    @SendTo("/topic/publicMsg")
        public UserInfoCountBean getMsgCount(String token){
        System.out.println("接收到数据");
//        String id = jwtUtils.getId(token);
//        MsgUserExample msgUserExample = new MsgUserExample();
//        MsgUserExample.Criteria criteria = msgUserExample.createCriteria();
//        criteria.andIdEqualTo(Integer.parseInt(id));
//        criteria.andIsreadEqualTo(0);
//        int notReadMsgCount = msgService.getNotReadMsgCount(msgUserExample);
//        Map<String,Object> map = new HashMap<>();
//        map.put("notReadMsgCount",notReadMsgCount);
//        String notReadJson = JSON.toJSONString(map);
        UserInfoCountBean userInfoCountBean = getNotReadCount(token);

        return userInfoCountBean;
    }

    @ResponseBody
    @RequestMapping("/getNotReadCount")
    public RespBean initNotReadCount(String token){
        System.out.println("初始化NotReadCount");
        UserInfoCountBean notReadCount = getNotReadCount(token);

        return RespBean.ok("查询成功",notReadCount);
    }

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


    public void remindUser(){
        MsgUser msgUser = new MsgUser();

    }

    @ResponseBody
    @RequestMapping("/getMsgList")
    public RespBean getMsgList(){
        MsgExample msgExample = new MsgExample();
        msgExample.createCriteria();
        List<Msg> msgList = msgService.getMsgList(msgExample);
        return RespBean.ok("查找成功",msgList);
    }

    @ResponseBody
    @RequestMapping("/getPublicMsgTitle")
    public RespBean getPubilicMsgTitle(int uid){
        List<PublicMsgTitleBean> publicMsgTitle = msgService.getPubilicMsgTitle(uid);
        return RespBean.ok("查询成功",publicMsgTitle);
    }

    @ResponseBody
    @RequestMapping("/changeReadState")
    public RespBean changeReadState(int msid,int uid){
        MsgUserExample example = new MsgUserExample();
        MsgUserExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andMsidEqualTo(msid);
        MsgUser msgUser = msgService.selectMsgUser(example);
        msgUser.setIsread(1);
        msgService.changeReadState(msgUser);
        return RespBean.ok("修改成功");
    }

}
