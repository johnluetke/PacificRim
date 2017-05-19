package pacificrim.gipsydanger.data;

import pacificrim.gipsydanger.GipsyDanger;
import pacificrim.gipsydanger.XMLUtil;

public class Packet implements IXMLSerializable {

    private static final String XML_TAG_NAME = "Packet";
    private static final String[] XYZ = new String[] {"x", "y", "z"};
    private static final String[] ROLLPITCHYAW = new String[] {"roll", "pitch", "yaw"};
    
    /**
     * Time stamp of when this packet was captured
     */
    public long timestamp;
    /**
     * X,Y,Z values of acceleration
     */
    public float[] attitude;
    /**
     * X,Y,Z values of acceleration
     */
    public float[] acceleration;  
    /**
     * X,Y,Z values of degrees rotation
     */
    public float[] rotation;
    /**
     * X,Y,Z values of tilt.
     */
    public float[] tilt;

    public static Packet capture() {
        Packet p = new Packet();
        p.timestamp = System.currentTimeMillis();
        p.attitude[0] = GipsyDanger.ATTITUDE.getRoll();
        p.attitude[1] = GipsyDanger.ATTITUDE.getPitch();
        p.attitude[2] = GipsyDanger.ATTITUDE.getYaw();
        GipsyDanger.ACCELEROMETER.fetchAllAccel(p.acceleration);
        
        return p;
    }
    
    private Packet() {
        attitude = new float[3];
    	acceleration = new float[3];
    	rotation = new float[3];
    	tilt = new float[3];
    }
    
    /**
     * Returns the XML tag this class should use when serialized
     * 
     * @return 
     */
    public String getTagName() {
        return XML_TAG_NAME;
    }
    
    /**
     * Returns this Packet as an XML string
     * 
     * @return
     */
    public String asXML() {
        StringBuilder sb = new StringBuilder();
        sb.append(XMLUtil.getXMLStartTag(XML_TAG_NAME));
        
        sb.append(XMLUtil.serialize("timestamp", timestamp));
        sb.append(XMLUtil.serialize("attitude", ROLLPITCHYAW, attitude));
        sb.append(XMLUtil.serialize("acceleration", XYZ, acceleration));
        //sb.append(XMLUtil.serialize("rotation", XYZ, rotation));
        //sb.append(XMLUtil.serialize("tilt", XYZ, tilt));
        
        sb.append(XMLUtil.getXMLEndTag(XML_TAG_NAME));
                
        return sb.toString();
    }
}
