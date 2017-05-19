package pacificrim.gipsydanger.behaviors;

import lejos.robotics.subsumption.Behavior;
import pacificrim.gipsydanger.GipsyDanger;
import pacificrim.gipsydanger.data.Packet;

public class CaptureDataPacket implements Behavior {

    @Override
    public void action() {
        Packet p = Packet.capture();
        
        GipsyDanger.DATA_QUEUE.push(p);
        
        if (GipsyDanger.DATA_QUEUE.size() > GipsyDanger.MAX_DATA_PACKETS) {
            GipsyDanger.DATA_QUEUE.pop();
        }
        
        //GipsyDanger.debug("PKT: " + p.timestamp + " (" + GipsyDanger.DATA_QUEUE.size() + ")");
    }

    @Override
    public void suppress() {
        // Shouldn't be anything we need to stop doing....
    }

    /**
     * This behavior should take over when there are no captured data packets
     * or when the current time is greater than the last captured packet's timestamp +
     * the capture interval
     */
    @Override
    public boolean takeControl() {
        if (GipsyDanger.DATA_QUEUE.size() > 0) {
            long lastTimestamp = ((Packet)GipsyDanger.DATA_QUEUE.elementAt(GipsyDanger.DATA_QUEUE.size() - 1)).timestamp;
            return (lastTimestamp + GipsyDanger.DATA_CAPTURE_INTERVAL) < System.currentTimeMillis();
        }
        
        return true;
    }

}