package pacificrim.gipsydanger.data;

import pacificrim.gipsydanger.GipsyDanger;
import pacificrim.gipsydanger.XMLUtil;

public class Package implements IXMLSerializable {

    private static String XML_TAG_NAME = "Package";
    
    public long timestamp;
    public String VIN;
    public Packet[] packet;
    
    private Package() {}
    
    public static Package create() {
        Package pkg = new Package();
        pkg.timestamp = System.currentTimeMillis();
        pkg.VIN = GipsyDanger.getVIN();
        pkg.packet = new Packet[GipsyDanger.DATA_QUEUE.size()];
        for (byte i = 0; i < GipsyDanger.DATA_QUEUE.size(); i++) {
            pkg.packet[i] = GipsyDanger.DATA_QUEUE.elementAt(i);
        }
        
        return pkg;
    }

    public String asXML() {
        StringBuilder sb = new StringBuilder();
        sb.append(XMLUtil.getXMLStartTag(XML_TAG_NAME));
        
        sb.append(XMLUtil.serialize("timestamp", timestamp));
        sb.append(XMLUtil.serialize("VIN", VIN));
        sb.append(XMLUtil.serialize("Packets", packet));
        
        sb.append(XMLUtil.getXMLEndTag(XML_TAG_NAME));
                
        return sb.toString();
    }
    
    
}
