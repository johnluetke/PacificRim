package pacificrim.gipsydanger;

import java.util.ArrayList;

import pacificrim.gipsydanger.data.IXMLSerializable;
import pacificrim.gipsydanger.data.Packet;

public class XMLUtil {

    private static StringBuilder sb = new StringBuilder();
    
    /**
     * Returns an XML start tag with the given name
     * 
     * @param tagname name of the tag
     * 
     * @return &lt;tagname&gt;
     */
    public static String getXMLStartTag(String tagname) {
        sb.delete(0, sb.length());
        sb.append('<');
        sb.append(tagname);
        sb.append('>');
        
        return sb.toString();
    }
    
    /**
     * Returns an XML end tag with the given name
     * 
     * @param tagname name of the tag
     * 
     * @return &lt;/tagname&gt;
     */
    public static String getXMLEndTag(String tagname) {
        sb.delete(0, sb.length());
        sb.append('<');
        sb.append('/');
        sb.append(tagname);
        sb.append('>');
        
        return sb.toString();
    }
    
    public static String serialize(String tagname, IXMLSerializable item) {
        StringBuilder sb = new StringBuilder();
        sb.append(getXMLStartTag(tagname));
        sb.append(item.asXML());
        sb.append(getXMLEndTag(tagname));
        
        return sb.toString();
    }
    
    /**
     * Serializes a long
     * 
     * @param tagname the XML tag name for the array
     * @param names array of tag names for items in the array. (index % names.length) will select tag name
     * @param array array of floats to serialize
     * 
     * @return
     */
    public static String serialize(String tagname, long val) {
        return serialize(tagname, val+"");
    }
    
    /**
     * Serializes a float
     * 
     * @param tagname the XML tag name for the array
     * @param names array of tag names for items in the array. (index % names.length) will select tag name
     * @param array array of floats to serialize
     * 
     * @return
     */
    public static String serialize(String tagname, float val) {
        return serialize(tagname, val+"");
    }
    
    /**
     * Serializes a string
     * 
     * @param tagname the XML tag name for the array
     * @param names array of tag names for items in the array. (index % names.length) will select tag name
     * @param array array of floats to serialize
     * 
     * @return
     */
    public static String serialize(String tagname, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getXMLStartTag(tagname));
        sb.append(str);
        sb.append(getXMLEndTag(tagname));
        
        return sb.toString();
    }
    
    /**
     * Serializes an array of Packets
     * 
     * @param tagname the XML tag name for the array
     * @param array array of objects to serialize
     * 
     * @return
     */
    public static String serialize(String tagname, IXMLSerializable[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append(getXMLStartTag(tagname));
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i].asXML());
        }
        sb.append(getXMLEndTag(tagname));
        
        return sb.toString();
    }
    
    /**
     * Serializes an array of floats
     * 
     * @param tagname the XML tag name for the array
     * @param array array of floats to serialize
     * 
     * @return
     */
    public static String serialize(String tagname, float[] array) {
        return serialize(tagname, new String[] {tagname}, array);
    }
    
    /**
     * Serializes an array of floats
     * 
     * @param tagname the XML tag name for the array
     * @param names array of tag names for items in the array. (index % names.length) will select tag name
     * @param array array of floats to serialize
     * 
     * @return
     */
    public static String serialize(String tagname, String[] names, float[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append(getXMLStartTag(tagname));
        for (int i = 0; i < array.length; i++) {
            sb.append(serialize(names[i % names.length], array[i]));
        }
        sb.append(getXMLEndTag(tagname));
        
        return sb.toString();
    }

}
