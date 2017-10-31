package com.terabits.service.impl;

import com.terabits.mapper.AdminMapper;
import com.terabits.mapper.DeviceMapper;
import com.terabits.meta.po.Device.TerminalPO;
import com.terabits.service.DeviceService;
import com.terabits.utils.DBTools;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
    public TerminalPO selectOneTerminal(String displayId) {
        SqlSession session = DBTools.getSession();
        DeviceMapper deviceMapper= session.getMapper(DeviceMapper.class);
        TerminalPO terminalPO=new TerminalPO();
        try {
            terminalPO=deviceMapper.selectOneTerminal(displayId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return terminalPO;
    }


    public List<TerminalPO> selectAllTerminal(){
        SqlSession session = DBTools.getSession();
        DeviceMapper deviceMapper= session.getMapper(DeviceMapper.class);
        List<TerminalPO> terminalPOList=new ArrayList<TerminalPO>();
        try {
            terminalPOList=deviceMapper.selectAllTerminal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return terminalPOList;
    }

    public String selectLocation(String displayId) {
        SqlSession session = DBTools.getSession();
        DeviceMapper deviceMapper= session.getMapper(DeviceMapper.class);
        String location="";
        try {
            location=deviceMapper.selectLocation(displayId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return location;
    }
}
