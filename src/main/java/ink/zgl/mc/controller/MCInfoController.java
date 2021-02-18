package ink.zgl.mc.controller;

import ink.zgl.mc.entity.McInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@RestController
@RequestMapping
@Slf4j
public class MCInfoController {

    @GetMapping("/info")
    public McInfo getMcInfo(@RequestParam String ip,@RequestParam(required = false) Integer port){

        try {
            return get(ip, port);
        }catch (Exception e){
            McInfo mcInfo = new McInfo();
            mcInfo.setStatus("offline");
            return mcInfo;

        }
    }

    public McInfo get(String ip,Integer port) throws IOException {

            DatagramSocket socket = new DatagramSocket();
            String s = "0100000000240D12D300FFFF00FEFEFEFEFDFDFDFD12345678";
            byte[] buffer = HexUtils.fromHexString(s);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(ip),port==null?19132:port);
            long l = System.currentTimeMillis();
            socket.send(packet);

            //创建字节数组
            byte[] data=new byte[1024];
            //创建数据包对象，传递字节数组
            DatagramPacket dp=new DatagramPacket(data, data.length);

            socket.receive(dp);
            long l1 = System.currentTimeMillis();
            socket.close();
            String s1 = new String(dp.getData());
            s1=s1.substring(s1.indexOf("MCPE"));
            s1=s1.substring(0,s1.lastIndexOf(";"));
            String[] split = s1.split(";");
            McInfo mcInfo=new McInfo();
            mcInfo.setStatus("online")
                .setIp(ip)
                .setPort(String.valueOf(port))
                .setMotd(split[1])
                .setAgreement(split[2])
                .setVersion(split[3])
                .setOnline(split[4])
                .setMax(split[5])
                .setGamemode(split[8])
                .setDelay(String.valueOf(l1-l));
            return mcInfo;
    }



}
