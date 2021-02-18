package ink.zgl.mc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class McInfo{
        private String status;
        private String ip;
        private String port;
        private String motd;
        private String agreement;
        private String version;
        private String online;
        private String max;
        private String gamemode;
        private String delay;

    }