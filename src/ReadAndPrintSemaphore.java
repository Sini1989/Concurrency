import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

class ReadAndPrintSem {

String str;
String[] stringArray = new String[5];
int i=0;
Semaphore semCons = new Semaphore(0);
Semaphore semProd = new Semaphore(1);

public void enterString() {

	try {
		semProd.acquire();
	} catch (InterruptedException e) {
		System.out.println(e);
	}

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
try {
str = br.readLine();
}
catch(IOException e)
{
System.out.println(e);
}
semCons.release();

}

public void getString() {

try {
	semCons.acquire();
} catch (InterruptedException e) {
	System.out.println(e);
}

stringArray[i] = str;
i++;
semProd.release();

}
}

class WriteThreadSem implements Runnable{

ReadAndPrintSem rap;
Thread t;

WriteThreadSem(ReadAndPrintSem rap) {

this.rap = rap;
t = new Thread(this);
t.start();

}

public void run() {

while(rap.i<5)
rap.enterString();

}
}

class ReadThreadSem implements Runnable{

Thread t;
ReadAndPrintSem rap;

ReadThreadSem(ReadAndPrintSem rap) {

this.rap = rap;
t = new Thread(this);
t.start();

}

public void run() {

while(rap.i<5)
rap.getString();
for(int j=0;j<5;j++)
System.out.println(rap.stringArray[j]);
}
}


public class ReadAndPrintSemaphore {

public static void main(String args[]) throws InterruptedException {
	
System.out.println("Enter data :");
ReadAndPrintSem rap = new ReadAndPrintSem();
new WriteThreadSem(rap);
new ReadThreadSem(rap);

}
}







