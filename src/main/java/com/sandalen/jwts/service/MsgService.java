package com.sandalen.jwts.service;

import com.sandalen.jwts.dao.MsgMapper;
import com.sandalen.jwts.dao.MsgUserMapper;
import com.sandalen.jwts.entity.Msg;
import com.sandalen.jwts.entity.MsgExample;
import com.sandalen.jwts.entity.MsgUser;
import com.sandalen.jwts.entity.MsgUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgService {
    @Autowired
    private MsgMapper msgMapper;

    @Autowired
    private MsgUserMapper msgUserMapper;

    public int sendNotice(Msg msg){
        int insert = msgMapper.insert(msg);
        return insert;
    }

    public List<Msg> getMsgList(MsgExample msgExample){
        List<Msg> msgs = msgMapper.selectByExample(msgExample);
        return msgs;
    }

    public int insertMsgUser(MsgUser msgUser){
        int insert = msgUserMapper.insert(msgUser);
        return insert;
    }

    public List<MsgUser> getNotRead(MsgUserExample example){
        List<MsgUser> msgUsers = msgUserMapper.selectByExample(example);
        return msgUsers;
    }
}
