import java.util.List;


/**
 * @author ethchil
 * @author sammydamdam
 */
public class ESPLocation {
	long timestamp;
	String mac;
	List<String> SSID;
	List<String> MacAddrs;
	List<String> Signal;

	public ESPLocation(long time, String MACAddress, List<String> ssid, List<String> mac, List<String> signal) {
		timestamp = time;
		this.mac = MACAddress;
		this.SSID = ssid;
		this.MacAddrs = mac;
		this.Signal = signal;
	}


}
