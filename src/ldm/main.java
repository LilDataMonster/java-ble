package ldm;

import blufi.espressif.BlufiClient;
import blufi.espressif.params.BlufiConfigureParams;
import blufi.espressif.params.BlufiParameter;
import tinyb.BluetoothManager;

public class main {

    private static final int[] OP_MODE_VALUES = {
            BlufiParameter.OP_MODE_STA,
            BlufiParameter.OP_MODE_SOFTAP,
            BlufiParameter.OP_MODE_STASOFTAP
    };

    public static void main(String[] args) {
        BluetoothManager manager = BluetoothManager.getBluetoothManager();
        boolean discoveryStarted = manager.startDiscovery();

        System.out.println("The discovery started: " + (discoveryStarted ? "true" : "false"));

//        String device = "D8:A0:1D:5C:AA:96";
//        String ssid = "WIFI_SSID";
//        String password = "WIFI_PASSWORD";
        String device = args[0];
        String ssid = args[1];
        String password = args[2];
        
        // set configuration parameters
        BlufiConfigureParams params = new BlufiConfigureParams();
        params.setOpMode(OP_MODE_VALUES[0]); 		// station op mode
        params.setStaSSIDBytes(ssid.getBytes());	// ssid
        params.setStaPassword(password);			// password
        
        // setup client to connect to BLE
    	BlufiClient client = new BlufiClient(device);
    	client.setPostPackageLengthLimit(20);
    	
    	client.connect();
//    	client.negotiateSecurity();
    	client.configure(params);
    	
    	try {
    		// wait 5 seconds
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	client.close();
    }
    
}
