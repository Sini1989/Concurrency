
import java.util.concurrent.*;

public class PhaserDemo {

static int phase;

public static void main(String args[]) {

Phaser ph = new Phaser();

ph.register();
new PhaserThread(ph,"A");
new PhaserThread(ph,"B");
new PhaserThread(ph,"C");

phase = ph.getPhase();
System.out.println("Current Phase is : " + phase);
ph.arriveAndAwaitAdvance();
try{
	Thread.sleep(300);
}
catch(InterruptedException e)
{
	System.out.println(e);
}
System.out.println("Phase " + phase + " completed");

phase = ph.getPhase();
System.out.println("Current Phase is : " + phase);
ph.arriveAndAwaitAdvance();
System.out.println("Phase " + phase + " completed");

ph.arriveAndDeregister();

if(ph.isTerminated())
System.out.println("All phases are completed");

}

}

class PhaserThread implements Runnable{

Phaser ph;
String str;

PhaserThread(Phaser p, String s) {

ph = p;
str = s;
Thread t = new Thread(this);
t.start();

}

public void run() {

ph.register();
System.out.println("Thread " + str + " entered phase " + ph.getPhase());
ph.arriveAndAwaitAdvance();

try{
	Thread.sleep(500);
}
catch(InterruptedException e) {
	System.out.println(e);
}


System.out.println("Thread " + str + " entered phase " + ph.getPhase());
ph.arriveAndAwaitAdvance();

ph.arriveAndDeregister();

}

}















