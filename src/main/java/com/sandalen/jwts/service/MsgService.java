package com.sandalen.jwts.service;

import com.sandalen.jwts.dao.MsgMapper;
import com.sandalen.jwts.dao.MsgUserMapper;
import com.sandalen.jwts.entity.*;
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

    public int getNotReadMsgCount(MsgUserExample example){
        int count = msgUserMapper.countByExample(example);
        return count;
    }

    public List<PublicMsgTitleBean> getPubilicMsgTitle(int uid){
        List<PublicMsgTitleBean> pubilicMsgTitle = msgMapper.getPubilicMsgTitle(uid);
        return pubilicMsgTitle;
    }

    public int changeReadState(MsgUser msgUser){
        int i = msgUserMapper.updateByPrimaryKey(msgUser);
        return i;
    }

    public MsgUser selectMsgUser(MsgUserExample example){
        List<MsgUser> msgUsers = msgUserMapper.selectByExample(example);
        return msgUsers.get(0);
    }

    public PublicMsgDetailBean getPublicMsgDetail(int msid){
        PublicMsgDetailBean publicMsgDetail = msgMapper.getPublicMsgDetail(msid);
        return publicMsgDetail;
    }

    public int deleteMsg(MsgUserExample example){
        int i = msgUserMapper.deleteByExample(example);
        return i;
    }
}
