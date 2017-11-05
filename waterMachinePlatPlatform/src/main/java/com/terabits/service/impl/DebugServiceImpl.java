package com.terabits.service.impl;

import com.terabits.service.DeviceService;
import com.terabits.utils.PlatformGlobal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("debugService")
public class DebugServiceImpl {

    @Autowired
    private DeviceService deviceService;

    /**
     * 对displayIdS中的每一个displayId进行循环命令发送
     * @param displayIdS
     * @param openTime
     * @param coldPulse
     * @param hotPulse
     * @param heartRate
     * @return
     * @throws Exception
     */
    public int debugManipulate(String displayIdS, String openTime, String coldPulse, String hotPulse, String heartRate) throws Exception{
        int result = 1;
        //将出入的用“，”隔开的display区分开，对每一台设备下发同样的指令
        String[] displayIdArray = displayIdS.split(",");
        for(String displayId : displayIdArray){
            String deviceId = deviceService.selectTerminalByDisplayid(displayId).getDeviceid();

            //对openTime进行处理发送
            if (openTime != "") {
                byte[] txd1 = new byte[5];
                txd1[0] = (byte) 0xBB;
                txd1[1] = (byte) Integer.valueOf(openTime).intValue();
                txd1[2] = (byte) 0x1f;
                txd1[3] = (byte) 0x2f;
                txd1[4] = (byte) 0xFB;
                try {
                    PlatformGlobal.command(txd1, deviceId);
                } catch (Exception e) {
                    result = 0;
                    e.printStackTrace();
                }
            }

            //对冷热水脉冲数进行处理发送
            byte tmp1, tmp2;
            if (coldPulse == "")
                tmp1 = 100;
            else
                tmp1 = (byte) (Integer.valueOf(coldPulse).intValue());
            if (hotPulse == "")
                tmp2 = 100;
            else
                tmp2 = (byte) (Integer.valueOf(hotPulse).intValue());
            byte[] txd2 = new byte[6];
            txd2[0] = (byte) 0xBC;
            txd2[1] = tmp1;
            txd2[2] = tmp2;
            txd2[3] = (byte) 0x1f;
            txd2[4] = (byte) 0x2f;
            txd2[5] = (byte) 0xFC;
            try {
                PlatformGlobal.command(txd2, deviceId);
            } catch (Exception e) {
                result = 0;
                e.printStackTrace();
            }

            //对心跳包发送频率进行处理发送
            if (heartRate != "") {
                byte[] txd3 = new byte[5];
                txd3[0] = (byte) 0xBD;
                txd3[1] = (byte) Integer.valueOf(heartRate).intValue();
                txd3[2] = (byte) 0x1f;
                txd3[3] = (byte) 0x2f;
                txd3[4] = (byte) 0xFD;
                try {
                    PlatformGlobal.command(txd3, deviceId);
                } catch (Exception e) {
                    result = 0;
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
