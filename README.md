# MineCraftServerInfo
Get MineCraft Server Info by http.


Java version from https://github.com/PluginsKers/Motd_For_Minecraft

接口地址：http://yourDomainOrIP:8080/info 
请求方式  GET

参数|示例|描述
-|-|-
ip|mc.zgl.ink|服务器IP地址
port|19132|服务器端口(port if null default 19132)

返回JSON数据
参数|示例|描述
-|-|-
status|online|服务器唯一状态识别
ip|mc.zgl.ink|返回查询IP
port|19132|返回查询端口
motd|myWorld|服务器广播内容Motd
agreement|422|协议版本
version|1.16.201|客户端版本
online|0|服务器在线人数
max|100|服务器人数上限
gamemode|Survival|游戏模式
delay|8|连接服务器延迟(ms)

eg.

http://127.0.0.1:8080/info?ip=mc.zgl.ink&port=19132
