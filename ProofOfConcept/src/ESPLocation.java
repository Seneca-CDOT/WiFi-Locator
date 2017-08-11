import java.util.List;

public class ESPLocation {
	long timestamp;
	String MACAddress;
	List<String> ssid;
	List<String> mac;
	List<String> signal;

	public ESPLocation(long time, String MACAddress, List<String> ssid, List<String> mac, List<String> signal) {
		timestamp = time;
		this.MACAddress = MACAddress;
		this.ssid = ssid;
		this.mac = mac;
		this.signal = signal;
	}


}
