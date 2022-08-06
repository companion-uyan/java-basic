package person.companion.basic.exercise.domain.iplist;

import java.util.HashSet;

public class HashSetIpList implements IpList {

    private HashSet<Integer> ipSet = null;

    public HashSetIpList() {
        ipSet = new HashSet<>();
        ipSet.add(ipToInt("192.168.1.3"));
    }

    @Override
    public boolean isInList(String ip) {
        return ipSet.contains(ipToInt(ip));
    }

    /**
     * 将ip字符串转化为整数
     * @param ip
     * @return
     */
    private int ipToInt(String ip) {
        int ret = 0;
        String[] ipStr = ip.split("\\.");
        for (int i = 0; i < 4; i++) {
            ret <<= 8;
            ret += Integer.valueOf(ipStr[i]);
        }
        return ret;
    }
}