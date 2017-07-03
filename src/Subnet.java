//import com.sun.org.apache.xalan.internal.lib.NodeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

class RouteNode {
    public RouteNode(char ch) {
        value = ch;
        link = new HashMap<>();
        bIsEnd = false;
    }

    private char value;
    private HashMap<Character, RouteNode> link;
    private boolean bIsEnd;
    private Prefix nodePrefix;

    public class Prefix {
        Prefix() {
            nodeRout = new ArrayList<>();
        }
        ArrayList<RoutsInfo> nodeRout;
        public Node.nodeInfo aboutThatIp;

        public void showPrefix() {
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("IP-address: " + aboutThatIp.ipInCidr);
            System.out.println("Routing info: ");
            System.out.println("Маршрут 1: ");
            System.out.println("Метрика: " + nodeRout.get(0).metric);
            System.out.println("Адм. дистанция: " + nodeRout.get(0).administrativeDistance);
            System.out.println("Флаги: " + nodeRout.get(0).flags);
            System.out.println("Next-hope: " + nodeRout.get(0).nextHope);
            System.out.println("Тип: " + nodeRout.get(0).type);
            System.out.println("Маршрут 2: ");
            System.out.println("Метрика: " + nodeRout.get(1).metric);
            System.out.println("Адм. дистанция: " + nodeRout.get(1).administrativeDistance);
            System.out.println("Флаги: " + nodeRout.get(1).flags);
            System.out.println("Next-hope: " + nodeRout.get(0).nextHope);
            System.out.println("Тип: " + nodeRout.get(1).type);
            System.out.println("--------------------------------------------------------------------------");

        }
    }

    public void setPrefix(ArrayList<RoutsInfo> info, Node.nodeInfo ipInf) {
        Prefix copyPref = new Prefix();
        copyPref.aboutThatIp = ipInf;
        copyPref.nodeRout = info;
        this.nodePrefix = copyPref;
    }

    public Prefix getPrefix() {
        return this.nodePrefix;
    }

    public HashMap<Character, RouteNode> getLink() {
        return link;
    }

    public char getValue() {
        return value;
    }

    public void setIsEnd(boolean val) {
        bIsEnd = val;
    }

    public boolean isEnd() {
        return bIsEnd;
    }
}

// Implements the actual Trie
class RouteTable {

    public RouteTable() {
        root = new RouteNode((char)0);
    }

    private RouteNode root;

    // Поместить ip-address в таблицу-маршрутизации(дерево)
    public void putNodeInfo(Node.nodeInfo key, ArrayList<RoutsInfo> rout) {
        // Find length of the given word
        int length = key.binaryIp.length();
        RouteNode current = root;
        String allKey = "";

        // проходим все символы слова из словаря, эл. к. служ. ключом
        for(int i = 0; i < length; i++) {
            HashMap<Character, RouteNode> child = current.getLink();
            char key_i = key.binaryIp.charAt(i);

            // Если дочерняя нода для текущего узла уже есть
            if(child.containsKey(key_i)) {
                allKey += key_i;
                current = child.get(key_i);
            }
            else   // иначе создать дочернюю ноду
            {
                RouteNode temp = new RouteNode(key_i);
                child.put(key_i, temp);
                allKey += key_i;
                current = temp;
            }
        }

        // Установить bIsEnd true для последнего символа
        current.setIsEnd(true);
        System.out.println(allKey);
        current.setPrefix(rout, key);
        RouteNode.Prefix pref = current.getPrefix();
        System.out.println("Поместили адрес в узел. Вот что в префиксе: ");
        pref.showPrefix();
    } //КЛЮЧ - АДРЕС УЗЛА, ЗНАЧЕНИЕ - СЕТИ

    public String getMatchingPrefix(String nodeAddress)  { // инпут - длинное слово, ищем самое большое в него входящее
        String result = ""; // иниц. рез. строки
        RouteNode.Prefix prefix;
        int length = nodeAddress.length();  // Найти длину инпут строки

        RouteNode current = root;

        int i, prevMatch = 0;
        for (i = 0 ; i < length; i++ ) {
            // Найти текущий символ строки
            char ch = nodeAddress.charAt(i);

            // HashMap текцщего узла для спуска вниз
            HashMap<Character, RouteNode> child = current.getLink();

            // проверка, есть ли у текцщего узла дочерние
            if (child.containsKey(ch)) {
                result += ch;          // увеличить результат на один ключ
                current = child.get(ch); // сделать дочернюю ноду текущей

                // если это конец слова, то увеличить prevMatch
                if (current.isEnd())
                    prevMatch = i + 1;
            }
            else  break;
        }

        if (!current.isEnd())
            return result.substring(0, prevMatch);

        else {
            prefix = current.getPrefix();
            System.out.println("Нашли: ");//+prefix.aboutThatIp.ipInCidr);
            prefix.showPrefix();
            return result;
        }

    }
}

 // ------------------------------------------------
           // TCP/IP NETWORK INFORMATION
// ------------------------------------------------
// IP Entered = ..................: 192.168.1.12
// CIDR = ........................: /30
// Netmask = .....................: 255.255.255.252
// Network Address = .............: 192.168.1.12

// Broadcast Address = ...........: 192.168.1.15
// Usable IP Addresses = .........: 2
// First Usable IP Address = .....: 192.168.1.13
// Last Usable IP Address = ......: 192.168.1.14

   /* public static String long2dotted(long ip) {
        // if ip is bigger than 255.255.255.255 or smaller than 0.0.0.0
        if (ip > 4294967295l || ip < 0)
        {
            throw new IllegalArgumentException("invalid ip");
        }
        StringBuilder ipAddress = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            int shift = i * 8;
            ipAddress.append((ip & (0xff << shift)) >> shift);
            if (i > 0) {
                ipAddress.append(".");
            }
        }
        return ipAddress.toString();
    }*/