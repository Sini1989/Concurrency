import java.util.concurrent.*;

public class CDLatch {

public static void main(String args[]) {

CountDownLatch cdl = new CountDownLatch(5);

new MyThread(cdl);

try {
cdl.await();
}
catch(InterruptedException e) {
System.out.println(e);
}

System.out.println("Done");

}

}

class MyThread implements Runnable {

CountDownLatch cdl;

MyThread(CountDownLatch cdl) {

this.cdl = cdl;
new Thread(this,"My thread").start();

}

public void run() {

for(int i=5;i>0;
		i--) {
System.out.println("Count : " + i);
cdl.countDown();
}

}

}

