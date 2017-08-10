import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

/**
 *
 * @author IamUsman
 */
public class ReadSerial implements SerialPortEventListener, Runnable {

    static CommPortIdentifier portId;
    static Enumeration portList;
    static SerialPort port;
    static InputStream inputStream;
    static Thread readThread;
    static byte buffer[];
    static BufferedReader br;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        portList = CommPortIdentifier.getPortIdentifiers();
        
        
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals("/dev/ttyUSB0")) {
                    if (!portId.isCurrentlyOwned()) {
                        ReadSerial rp = new ReadSerial();
                    } else {
                        System.out.println("This port is already used by some other program");
                    }

                }
            }
        }
    }

    public void ReadingPorts() {
        try {
            port = (SerialPort) portId.open("Custom", 500);
            inputStream = port.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("** Connected To COM6 **");
            port.addEventListener(this);
            port.notifyOnDataAvailable(true);
            port.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            port.enableReceiveTimeout(500);
            System.out.println("................................");
            readThread = new Thread(this);
            readThread.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void serialEvent(SerialPortEvent event) {

        switch (event.getEventType()) {

            case SerialPortEvent.DATA_AVAILABLE:
                buffer = new byte[8];
                try {
                    while (inputStream.available() > 0) {
                        int numBytes = inputStream.read(buffer);
                        System.out.println(new String(buffer,0,numBytes));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                break;

        }




    }

    @Override
    public void run() {
        try {
            System.out.println("In Run");
            Thread.sleep(2000);
        } catch (Exception ex) {
            ex.printStackTrace();;
        }

    }
}