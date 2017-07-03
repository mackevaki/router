import java.util.HashMap;

public class RouterTab {/*
    HashMap <String, Subnet> subnets = new HashMap<String, Subnet>(); // ip - segm
    HashMap <String, String> nodeToSubnetMap = new HashMap<String, String>();

    public Subnet getSubnetWithIp(String ipSegment) {
        return subnets.get(ipSegment);
    }

    public String getIpSegmentForNode(String nodeIp) {
        String ipSegment = nodeToSubnetMap.get(nodeIp);
        return ipSegment == null ? "nope" : ipSegment;
    }

    public Node getNodeWithIp (String nodeIp) {
        String ipSegment = nodeToSubnetMap.get(nodeIp);
        if (ipSegment == null) return null;

        Subnet segment = getSubnetWithIp(ipSegment);
        if (segment == null) return null;

        return segment.getNodeWithIp(nodeIp);
    }

    class MyProtocolData {
        private String header;
        private String srcIp;
        private String dstIp;
        private int chunkBit;
        private int dataLength;
        private byte[] data;
    }*/
}

