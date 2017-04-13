import java.util.concurrent.*;

public class ExchangerDemo {

public static void main(String args[]) {

Exchanger<String> exch = new Exchanger<String>();

new GetString(exch);
new WriteString(exch);

}

}

class GetString implements Runnable {

Exchanger<String> exch;

GetString(Exchanger<String> exch) {

this.exch = exch;
Thread t = new Thread(this);
t.start();

}

public void run() {

try {
String str = exch.exchange(new String());
System.out.println(str);
}
catch(InterruptedException e) {
System.out.println(e);
}



}

}

class WriteString implements Runnable {

Exchanger<String> exch;
String str;

WriteString(Exchanger<String> exch) {

this.exch = exch;
str = "Srini";
Thread t = new Thread(this);
t.start();

}

public void run() {

try {
exch.exchange(str);
}
catch(InterruptedException e) {
System.out.println(e);
}

}

}









