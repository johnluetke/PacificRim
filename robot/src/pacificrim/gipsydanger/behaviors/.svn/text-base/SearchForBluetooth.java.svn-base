package pacificrim.gipsydanger.behaviors;

import pacificrim.gipsydanger.GipsyDanger;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.robotics.subsumption.Behavior;

public class SearchForBluetooth implements Behavior {

    public void action() {
    	GipsyDanger.debug("BT: waiting...");
    	NXTConnection conn = Bluetooth.waitForConnection(1000, NXTConnection.RAW);
    	if (conn == null) {
    		GipsyDanger.debug("BT: timed out");
    	}
    	else {
    		GipsyDanger.debug("BT: connected!");
    	}
    }

    public void suppress() {
    }

    public boolean takeControl() {
        return true;
    }

}
