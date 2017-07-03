import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class Node {
    private int netmask = 0;
    private int address = 0;
    private int network = 0;
    private int broadcast = 0;
    private int prefix;
    private int low = network + 1;
    private int high;

    class nodeInfo {
        String ipInCidr;
        int masklen;
        byte[] ipOctets;
        String binaryIp;
        String getDottedDecimalIp;
        String dottedDecimalMask;
        String subnetAddress;
        String binarySubnetAddress;
        String broadcAddress;
        String highAddress;

        void showAllInfo() {
            System.out.println("ip адрес в формате CIDR: "+ipInCidr);
            System.out.println("Длина префикса(маски): "+masklen);
            System.out.println("Зачем-то адрес в байтах: "+Arrays.toString(ipOctets));
            System.out.println("Двоичный адрес: "+binaryIp);
            System.out.println("ip в точечно-десятичном формате: "+getDottedDecimalIp);
            System.out.println("маска в точечно-десятичном формате: "+dottedDecimalMask);
            System.out.println("ip адрес подсети: "+subnetAddress);
            System.out.println("Двоичный адрес подсети: "+binarySubnetAddress);
            System.out.println("--------------------------------------------------------------------------");
        }
        private int broadcast() { return broadcast; }
        private int high() { return broadcast() - 1; }
        public String getHighAddress() { return format(toArray(high())); }

    }

    public ArrayList<RoutsInfo> getRoats() {
        RoutsInfo routsInfo = new RoutsInfo();
        ArrayList<RoutsInfo> rout = new ArrayList<>();
        rout.add(routsInfo.setRout());
        routsInfo.next = new RoutsInfo();
        rout.add(routsInfo.next.setRout());
        System.out.println("ADMINISTR DIST  "+routsInfo.administrativeDistance);
        return rout;
    }

    public nodeInfo getNodeInfo(String cidrNotation) {
        nodeInfo myInfo = new nodeInfo();
        myInfo.ipInCidr = cidrNotation; //////////////////
        String[] currentSe;
        cidrNotation = cidrNotation.replaceAll("/", "\\.");
        currentSe = cidrNotation.split("\\.");

        prefix = Integer.parseInt(currentSe[4]);
        myInfo.masklen = prefix; /////////////////////////////////////////

        //System.out.println(Arrays.toString(currentSe));

        String[] ipAddr;
        ipAddr = ArrayUtils.remove(currentSe, 4);

        //System.out.println(Arrays.toString(octets));
        myInfo.ipOctets = toBytes(ipAddr); /////////////////////////////////////////////////

        myInfo.binaryIp = toStr(toBinaryAddress(toBytes(ipAddr))); ////////////////////////

        for(int j = 0; j < prefix; ++j) {
            netmask |= (1 << 31-j);
        }
        //System.out.println(netmask);

        int addr = 0;
        for (int i = 1; i <= 4; ++i) {
            addr |= ((Integer.parseInt(ipAddr[i-1]) & 0xff) << 8*(4-i));
            //System.out.println(addr);
        }

        address = addr;
        network = (address & netmask);
        myInfo.getDottedDecimalIp = format(toArray(address)); ///////////////////////

        myInfo.dottedDecimalMask = format(toArray(netmask)); ////////////////////
        myInfo.binarySubnetAddress = toStr(toBinaryAddress(toBytes(toArray(network))));   //////////////////////////////////
        myInfo.subnetAddress = format(toArray(network)); ////////////////
        broadcast = network | ~(netmask);
        myInfo.broadcAddress = format(toArray(broadcast));
        high = broadcast - 1;
        myInfo.showAllInfo();
        return myInfo;
    }


    byte[] toBytes(String[] ipAddr) {
        byte[] octets = new byte[4];
        for(int i = 0; i < 4; i++) {
            octets[i] = (byte)Integer.parseInt(ipAddr[i]);
        }
        return octets;
    }


    byte[] toBytes(int[] ipAddr) {
        byte[] octets = new byte[4];
        for(int i = 0; i < 4; i++) {
            octets[i] = (byte)ipAddr[i];
        }
        return octets;
    }

    // Конвертировать packed integer адрес в 4-элементный массив

    private int[] toArray(int val) {
        int ret[] = new int[4];
        for (int j = 3; j >= 0; --j)
            ret[j] |= ((val >>> 8*(3-j)) & (0xff));
        return ret;
    }


    // Конвертировать 4-элементный массив в дестично-точечный формат

    private String format(int[] octets) {
        StringBuilder str = new StringBuilder();
        for (int i =0; i < octets.length; ++i){
            str.append(octets[i]);
            if (i != octets.length - 1) {
                str.append(".");
            }
        }
        return str.toString();
    }

    private String toStr(String[] octets) {
        StringBuilder str = new StringBuilder();
        for (int i =0; i < octets.length; ++i){
            str.append(octets[i]);
            if (i != octets.length - 1) {
                str.append("");
            }
        }
        return str.toString();
    }

    private String[] toBinaryAddress(byte[] octets) {
        for(int i = 0; i <= 3; i++){
            //System.out.println(octets[i]);
        }

        String[] currentBinary = new String[4];
        int i = 0;
        for (byte aByte : octets) {
            currentBinary[i] = Integer.toBinaryString(256 + (int) aByte);
            currentBinary[i] = currentBinary[i].substring(currentBinary[i].length()-8);
            //System.out.println(currentBinary[i]);
            i++;
        }
        return currentBinary;
    }

}
