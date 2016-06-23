import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ModifiedIpAddress {

	public static void main(String[] args) throws IOException {
		InetAddress localhost = InetAddress.getLocalHost();
		// this code assumes IPv4 is used
		byte[] ip = localhost.getAddress();
		List<InetAddress> list = new ArrayList<InetAddress>();
		for (int i = 1; i <= 254; i++) {
			ip[3] = (byte) i;
			InetAddress address = InetAddress.getByAddress(ip);
			list.add(address);
		}

		ExecutorService exec = Executors.newFixedThreadPool(30);
		try {
			for (final InetAddress address : list) {
				exec.submit(new Runnable() {
					@Override
					public void run() {
						try {
							if (address.isReachable(1000)) {
								System.out.println(address + " machine is turned on and can be pinged");
							} else if (!address.getHostAddress().equals(address.getHostName())) {
								System.out.println(address + " machine is known in a DNS lookup");
							} else {
								System.out.println(address
										+ " the host address and host name are equal, meaning the host name could not be resolved");
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		} finally {
			exec.shutdown();
		}

	}
}
