
import java.util.concurrent.*;

public class CylBarrier {

public static void main(String args[]) {

CyclicBarrier cyl = new CyclicBarrier(3,new RunThread());
//CyclicBarrier cyl = new CyclicBarrier(3);

new NewThread(cyl,"First thread");
new NewThread(cyl,"Second thread");
new NewThread(cyl,"Third thread");
new NewThread(cyl,"Fourth thread");
new NewThread(cyl,"Fifth thread");
new NewThread(cyl,"Sixth thread");


}

}

class NewThread implements Runnable{

CyclicBarrier cyl;
Thread t;

NewThread(CyclicBarrier cyl,String name) {

this.cyl = cyl;
t = new Thread(this,name);

t.start();

}

public void run() {

try {
System.out.println("Thread : " + t.getName() + " is started");
cyl.await();
}
catch(InterruptedException e)
{
System.out.println(e);
}
catch(BrokenBarrierException e)
{
System.out.println(e);

}

}

}


class RunThread implements Runnable{

public void run() {

System.out.println("Action complete");

}

}
