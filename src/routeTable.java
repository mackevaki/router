import java.util.ArrayList;

/* Routing table top structure. */
/*
public class routeTable {
    private RouteNode root;

    public routeTable() {
        root = new RouteNode((char)0);
    }

    public class prefix {
        public String octets;
        public int prefixlen;
    }

    //static routeNode top;
    //routeNode current;
    //long count;

    public String format(String[] octets) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < octets.length; ++i) {
            str.append(octets[i]);
        }
        return str.toString();
    }*/

/*    public routeNode createNode(routeNode table) {
        routeNode newNode = new routeNode();
        newNode.top = table;
        return newNode;
    }
*/
    /* Allocate new route node*/
  /*  public routeNode routeNodeNew(routeNode table) { //GLAVNAYA
        routeNode node = createNode(table);
        return node;
    }
*//*
    //Allocate new route node with prefix set.
    public routeNode routeNodeSet(){//(prefix prefix) {
        routeNode node = new routeNode(); //routeNodeNew(table);
        //node.parent = this.current;
        //node.p.prefixlen = prefix.prefixlen; //copy
       // node.p.octets = prefix.octets;
        return node;
    }

    public void putAddress(String[] binaryAddress, int cidrmask) {
        String addr = format(binaryAddress);
        //prefix pref;
        //pref = new prefix();
        routeNode current = this.root;
        //current = new routeNode();
        //current = root;

        for (int i = 0; i < cidrmask; i++) {
            System.out.println(addr);
            //pref.octets = addr.substring(0, i + 1);
            //System.out.println("значение для проверки: " + pref.octets);
            //pref.prefixlen = i + 1;
            //System.out.println("длина ключа: " + pref.prefixlen);

            if (addr.charAt(i) == '0') {
                if (current.link[0] == null) {
                   // if (cidrmask - i == 1) {
                     //   current.link[0] = new routeNode();
                        //current.link[0].p.octets = pref.octets;
                        //current.link[0].p.prefixlen = pref.prefixlen;
                       // System.out.print("Дошли до конца.");
                        //return;
                    //}
                    //current.link[0] = new routeNode();
                    current.link[0] = new routeNode();//routeNodeSet();//(pref);
                    current = current.link[0];
                    System.out.println("0 -х пишем в линк[0] " + current.link[0]);

                            System.out.println("а теперь мы прошли уже " + addr.substring(0, i + 1));// + current.p.octets);

                } else {
                    //System.out.println("Это текущее сurr " + current);
                   // System.out.println("Мы тут, потому что ребенок есть и это " + current.link[0].p.octets);
                    current = current.link[0];
                    System.out.println("а теперь мы прошли уже " + addr.substring(0, i + 1));// + current.p.octets);
                    //System.out.println("а теперь мы " + current.p.octets);
                }
            }
            else if (addr.charAt(i) == '1') {
                if (current.link[1] == null) {
                    //if (cidrmask - i == 1) {
                        //current.link[1] = new routeNode();
                        //current.link[1].p.octets = pref.octets;
                        //current.link[1].p.prefixlen = pref.prefixlen;
                        //System.out.print("Дошли до конца.");
                        //return;
                    //}
                    //current.link[1] = new routeNode();
                    current.link[1] = new routeNode(); //routeNodeSet();//routeTable.top, pref);
                    current = current.link[1];
                    System.out.println("1 -х пишем в линк[1] " + current.link[1]);
                    System.out.println("а теперь мы прошли уже " + addr.substring(0, i + 1));
                    //System.out.println("а теперь мы " + current.p.octets);
                } else {
                    //System.out.println("Это текущее сurr " + current.p.octets);
                    System.out.println("Мы тут, потому что ребенок есть и это " + current.link[0]);
                    current = current.link[0];
                    System.out.println("а теперь мы прошли уже " + addr.substring(0, i + 1));
//                    System.out.println("а теперь мы " + current.p.octets);

                }
                //System.out.println("записали в правый узел: " + current.link[1].p.octets);
            }
        }

        ArrayList value = current.value;
        value.add(addr);
        current.setEnd();
        //current = routeTable.top;
    }
*/
/*
    public routeNode findAddress(String[] binaryAddress, int cidrmask) {
        String addr = format(binaryAddress);
        //prefix pref = new prefix();
        routeNode current = root;
        //pref.octets = addr.substring(0, cidrmask);
        //pref.prefixlen = cidrmask;
        System.out.println("Ищем адр " + addr + " с маской " + cidrmask);

        for (int i = 0; i < cidrmask; i++) {
            if (addr.charAt(i) == '0') {
                if (current.link[0] == null) {
                    System.out.println("ребенка нет  -> не нашли ");
                    return null;
                }

                System.out.println("ща 0, к левому " + i);
                //System.out.println(current.link[0].p.octets);
                current = current.link[0];
                //System.out.println(current.p.octets);
            }
            else if (addr.charAt(i) == '1') {
                if (current.link[1] == null) {
                    System.out.println("ребенка нет -> не нашли  ");
                    return null;
                }
                System.out.println("ща 1, к правому " + i);
                //System.out.println(current.link[1].p.octets);
                current = current.link[1];
                //System.out.println(current.p.octets);
            }
        }*/
        // System.out.println(current.p.octets + "          " + current.p.prefixlen);
        //System.out.println(pref.octets + "      " + pref.prefixlen);
        //if ((current.p.octets.equals(pref.octets)) && (current.p.prefixlen == pref.prefixlen)) {
  /*      if (current!= null && current.isEnd()) {
            if (current.value.get(0).equals(addr)) {
                System.out.println("Мы нашли адрес в таблице! " + current.value.get(0) + "  ====  " + addr);
                return current;
            } else {
                System.out.println("Мы не нашли адрес в таблице! Хотя должны были...");
                return null;
            }
        }
        else {
            System.out.println("Такого адрес нет в таблице!:(");
            return null;
        }
    }
}
*/


    /*prefix(prefix pre) {
        octets = pre.octets; //copy
        prefixlen = pre.prefixlen; //copy
    }

    prefix() {
        octets = null;
        prefixlen = 0;
    }*/