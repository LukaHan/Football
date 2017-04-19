package com.football.football;

import java.util.List;

/**
 * Created by hanshaobo on 10/04/2017.
 */

public class TeamEntity {
    /**
     * key : 上海上港
     * list : [{"c1":"亚冠","c2":"03-15","c3":"19:30","c4T1":"上海上港","c4T1URL":"http://match.sports.sina.com.cn/football/team.php?id=41300","c4R":"3-2","c4T2":"浦和红钻","c4T2URL":"http://match.sports.sina.com.cn/football/team.php?id=2234","c51":"","c52":"视频暂无","c52Link":"","c53":"全场战报","c53Link":"http://sports.sina.com.cn/china/afccl/2017-03-15/doc-ifycnpit1957584.shtml?cre=360.ala.yg.team","c54":"","c54Link":""},{"c1":"中超","c2":"04-01","c3":"19:35","c4T1":"广州恒大","c4T1URL":"http://match.sports.sina.com.cn/football/team.php?id=5065","c4R":"3-2","c4T2":"上海上港","c4T2URL":"http://match.sports.sina.com.cn/football/team.php?id=41300","c51":"","c52":"视频集锦","c52Link":"http://video.sina.com.cn/p/sports/j/v/doc/2017-04-02/184665936999.html?cre=360.ala.yg.team","c53":"全场战报","c53Link":"http://sports.sina.com.cn/china/j/2017-04-01/doc-ifycwymx3290770.shtml?cre=360.ala.yg.team","c54":"","c54Link":""},{"c1":"中超","c2":"04-07","c3":"19:35","c4T1":"上海上港","c4T1URL":"http://match.sports.sina.com.cn/football/team.php?id=41300","c4R":"2-1","c4T2":"山东鲁能","c4T2URL":"http://match.sports.sina.com.cn/football/team.php?id=153","c51":"","c52":"视频暂无","c52Link":"","c53":"全场战报","c53Link":"http://sports.sina.com.cn/china/j/2017-04-07/doc-ifyecezv2465295.shtml?cre=360.ala.yg.team","c54":"","c54Link":""},{"c1":"亚冠","c2":"04-11","c3":"18:30","c4T1":"浦和红钻","c4T1URL":"http://match.sports.sina.com.cn/football/team.php?id=2234","c4R":"VS","c4T2":"上海上港","c4T2URL":"http://match.sports.sina.com.cn/football/team.php?id=41300","c51":"","c52":"视频暂无","c52Link":"","c53":"图文直播","c53Link":"http://match.sports.sina.com.cn/livecast/n/live.php?id=156969","c54":"","c54Link":""},{"c1":"中超","c2":"04-15","c3":"19:35","c4T1":"天津权健","c4T1URL":"http://match.sports.sina.com.cn/football/team.php?id=46554","c4R":"VS","c4T2":"上海上港","c4T2URL":"http://match.sports.sina.com.cn/football/team.php?id=41300","c51":"","c52":"视频暂无","c52Link":"","c53":"图文直播","c53Link":"http://match.sports.sina.com.cn/livecast/n/live.php?id=157613","c54":"","c54Link":""},{"c1":"中超","c2":"04-21","c3":"19:35","c4T1":"上海上港","c4T1URL":"http://match.sports.sina.com.cn/football/team.php?id=41300","c4R":"VS","c4T2":"河北华夏","c4T2URL":"http://match.sports.sina.com.cn/football/team.php?id=15652","c51":"","c52":"视频暂无","c52Link":"","c53":"图文直播","c53Link":"http://match.sports.sina.com.cn/livecast/n/live.php?id=157619","c54":"","c54Link":""}]
     */

    public String key;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * c1 : 亚冠
         * c2 : 03-15
         * c3 : 19:30
         * c4T1 : 上海上港
         * c4T1URL : http://match.sports.sina.com.cn/football/team.php?id=41300
         * c4R : 3-2
         * c4T2 : 浦和红钻
         * c4T2URL : http://match.sports.sina.com.cn/football/team.php?id=2234
         * c51 :
         * c52 : 视频暂无
         * c52Link :
         * c53 : 全场战报
         * c53Link : http://sports.sina.com.cn/china/afccl/2017-03-15/doc-ifycnpit1957584.shtml?cre=360.ala.yg.team
         * c54 :
         * c54Link :
         */

        public String c1;
        public String c2;
        public String c3;
        public String c4T1;
        public String c4T1URL;
        public String c4R;
        public String c4T2;
        public String c4T2URL;
        public String c51;
        public String c52;
        public String c52Link;
        public String c53;
        public String c53Link;
        public String c54;
        public String c54Link;
    }
}
