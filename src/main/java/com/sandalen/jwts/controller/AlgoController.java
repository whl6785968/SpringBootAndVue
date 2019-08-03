package com.sandalen.jwts.controller;

import com.sandalen.jwts.beans.RespBean;
import com.sandalen.jwts.beans.UserInfoCountBean;
import com.sandalen.jwts.entity.*;
import com.sandalen.jwts.service.AlgoService;
import com.sandalen.jwts.service.MsgService;
import com.sandalen.jwts.service.UserService;
import com.sandalen.jwts.utils.CookieUtils;
import com.sandalen.jwts.utils.JwtUtils;
import com.sandalen.jwts.utils.MsgRelatedUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompClientSupport;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/algo")
public class AlgoController {
    @Autowired
    private AlgoService algoService;
    @Autowired
    private MsgService msgService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private MsgRelatedUtils msgRelatedUtils;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    //查出所有设备，查询设备下某个时间段的数据，若数据异常，则发送消息
    @ResponseBody
    @RequestMapping("/staticsErrorDataPoint")
//    @SendTo("/topic/publicMsg")
    public RespBean staticsErrorDataPoint(HttpServletRequest request){
        try {
            int errorDataPoint = algoService.staticsErrorDataPoint();
            List<DataPoint> errDataPoint = algoService.getErrDataPoint();

            DataAnaVo dataAnaVo = new DataAnaVo();
            dataAnaVo.setCount(errorDataPoint);
            dataAnaVo.setDataPoint(errDataPoint);

            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdNotEqualTo(3);
            List<User> userList = userService.getUserList(userExample);
            if(errorDataPoint>30){
                Msg msg = new Msg();
                msg.setIsemer(true);
                msg.setMdate(new Date());
                msg.setName("test");
                msg.setOther("水质异常！！！");
                msg.setType("水质异常");
                msg.setPoi("南京理工大学");
                msg.setUid(3);
                msgService.sendNotice(msg);
                Integer msid = msg.getId();
                for(User user : userList){
                    MsgUser msgUser = new MsgUser();
                    msgUser.setMsid(msid);
                    msgUser.setUid(user.getId());
                    msgUser.setIsread(0);
                    msgService.insertMsgUser(msgUser);
                }
            }

//            Cookie cookie = CookieUtils.getCookie(request.getCookies(), "X-Token");
//            String token = cookie.getValue();
//            UserInfoCountBean notReadCount = msgRelatedUtils.getNotReadCount(token);

            simpMessagingTemplate.convertAndSend("/topic/publicMsg","请更新消息");

            return RespBean.ok("计算成功",dataAnaVo);
//            return RespBean.ok("查询成功",errorDataPoint);

        }catch (Exception e){
            e.printStackTrace();
            return RespBean.error("计算失败");
        }
    }



    @ResponseBody
    @RequestMapping("/calcuErrorDataPoint")
    public RespBean calcuErrorDataPoint(){
        String[] args = new String[5];
        try{
            algoService.calcuErrorDataPoint(args);
            return RespBean.ok("计算成功");
        }
        catch (Exception e){
            return RespBean.error("计算失败");
        }

    }

    @ResponseBody
    @RequestMapping("/predicLabel")
    public RespBean predictLabel(){
        String[] args = new String[5];
        try{
            double acc = algoService.PredictLabelByRF(args);
            return RespBean.ok("预测成功",1.0-acc);
        }catch (Exception e){
            return RespBean.error("发生错误");
        }
    }

}
