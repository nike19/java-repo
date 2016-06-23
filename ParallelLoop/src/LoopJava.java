import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoopJava {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(3);
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add("one");
			list.add("two");
			list.add("three");
			list.add("four");
			list.add("five");
			list.add("six");
			list.add("seven");
			list.add("eight");
			list.add("nine");
			list.add("ten");
			
			for (final Object o : list) {
				exec.submit(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(3000);
							System.out.println(o.toString());
						} catch (InterruptedException e) {
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
