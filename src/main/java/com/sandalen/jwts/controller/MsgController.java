package com.sandalen.jwts.controller;

import com.sandalen.jwts.beans.RespBean;
import com.sandalen.jwts.entity.Msg;
import com.sandalen.jwts.entity.MsgExample;
import com.sandalen.jwts.entity.MsgUser;
import com.sandalen.jwts.entity.User;
import com.sandalen.jwts.service.MsgService;
import com.sandalen.jwts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/msg")
@Controller
public class MsgController {
    @Autowired
    private MsgService msgService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/sendNotice")
    public RespBean sendNotice(@RequestBody Msg msg){
        Integer uid = msg.getUid();
        int i = msgService.sendNotice(msg);
        Integer msid = msg.getId();

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

}
