public class RoutsInfo {
    RoutsInfo next;
    int administrativeDistance;
    int metric;
    String type;
    String nextHope;
    String flags;

    static int count = 0;
    public RoutsInfo setRout() {
//        next = new RoutsInfo();

        this.administrativeDistance = count + 1;
        this.metric = 10*count + 10;
        if (count % 2 == 0) {
            this.nextHope = "3.3.3.3";
            this.type = "BGP";
        }
        else {
            this.type = "OSPF";
            this.nextHope = "2.2.2.2";
        }
            if (administrativeDistance == 1)
            this.flags = "best";
        else
            this.flags = "";

        count++;

        return this;
    }
}

