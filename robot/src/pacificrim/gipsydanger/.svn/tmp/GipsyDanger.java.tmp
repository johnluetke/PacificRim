package pacificrim.gipsydanger;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Queue;

import nl.totan.sensors.L3G4200D;
import nl.totan.sensors.MMA7455L;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.I2CPort;
import lejos.nxt.I2CSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXT;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import pacificrim.gipsydanger.behaviors.CaptureDataPacket;
import pacificrim.gipsydanger.behaviors.Wait;
import pacificrim.gipsydanger.data.Package;
import pacificrim.gipsydanger.data.Packet;

public class GipsyDanger {

	public static final boolean DEBUG = false;
	public static final boolean LOG = true;

	public static final NXTRegulatedMotor DRIVE_MOTOR = Motor.A;
	public static final NXTRegulatedMotor STEER_MOTOR = Motor.C;

	public static final TouchSensor FRONT_RIGHT_SENSOR = new TouchSensor(
			SensorPort.S1);
	public static final TouchSensor FRONT_LEFT_SENSOR = new TouchSensor(
			SensorPort.S2);

	public static final MMA7455L ACCELOMETER = new MMA7455L(SensorPort.S4);
	public static final L3G4200D GYROSCOPE = new L3G4200D(SensorPort.S4);

	public static final long DATA_CAPTURE_INTERVAL = 1 /* seconds */* 1000; // milliseconds
	public static final Queue DATA_QUEUE = new Queue();
	public static final int MAX_DATA_PACKETS = 10;

	public static void main(String[] args) {

		SensorPort.S4.i2cEnable(I2CPort.HIGH_SPEED);

		Button.ESCAPE.addButtonListener(new ButtonListener() {
			@Override
			public void buttonPressed(Button arg0) {
			}

			@Override
			public void buttonReleased(Button arg0) {
				File file = new File("data.xml");
				FileOutputStream fos = null;

				try {
					fos = new FileOutputStream(file);
				} catch (IOException e) {
					System.err.println("Failed to create output stream");
					Button.waitForPress();
					System.exit(1);
				}

				DataOutputStream dos = new DataOutputStream(fos);

				try {
					dos.writeUTF(Package.create().asXML());
					dos.close(); // flush the buffer and write the file
				} catch (IOException e) {
					System.err.println("Failed to write to output stream");
				}

				Sound.beepSequence();
				NXT.exit(0);
			}
		});

		Behavior[] behaviors = new Behavior[] { new Wait(),
				new CaptureDataPacket() };
		Arbitrator controller = new Arbitrator(behaviors);

		controller.start();
	}

	public static void sendDataPackage(Package pkg) {
		// TODO Auto-generated method stub
	}

	public static void debug(String message) {
		if (DEBUG)
			LCD.drawScrollingString(message);
	}

	public static void log(String message) {
		if (LOG)
			LCD.drawScrollingString(message);
	}
}
