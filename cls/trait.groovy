import groovy.transform.SelfType;
import groovy.transform.CompileStatic;

def printDownln(String info){
    println info+"\u2193\u2193\u2193";
}

interface IMessageHandler {
    void on(String msg);
}

trait DefMsgHandler implements IMessageHandler{
    void on(String msg){
        println "Recieving message : $msg";
    }
}

class SimpleHandler implements DefMsgHandler {}

def sh = new SimpleHandler();
sh.on("simple");

class SmplHandlerWithLog implements DefMsgHandler {
    void on (String msg){
        println "I will see the msg $msg";
        DefMsgHandler.super.on(msg);
    }
}
printDownln("Test SmplHandlerWithLog");
def splLog  = new SmplHandlerWithLog();
splLog.on("i have a baby");

trait LoggingHandler implements IMessageHandler {                            
    void on(String message) {
        println "Seeing $message in LoggingHandler"                     
        super.on(message)                                          
    }
}
printDownln("Test HandlerWithLogger");
class HandlerWithLogger implements DefMsgHandler, LoggingHandler {}
def logHandler = new HandlerWithLogger();
logHandler.on('test logging');
// let see the tripple handler 
printDownln("Test SayHandler")
trait SayHandler implements IMessageHandler{
    void on(String msg){
        if(msg.toLowerCase().startsWith("say")){
            println "I have seen this msg";
        }else{
            println "i will give supper $msg";
            super.on(msg);
        }
    }
}

class TripperHandler implements DefMsgHandler,SayHandler,LoggingHandler{}

def tpHandler = new TripperHandler();
tpHandler.on("Tripper inheritance");

tpHandler.on("Say Hello");

// SAM type coercion
trait SingleAbsMethod {
    void sayHelloTo(){println "Hellow $person"};
    abstract String getPerson();
}

SingleAbsMethod sam = {"Kobe"};
sam.sayHelloTo();

class Fruit {
    String name;
    int age;
    void shrinkJuice(){
        name = "fruit";
        age = 23;
        println "I Love the $name , it has $age years old";
    }
}

trait BaseApple {
    void shrinkJuice(){
        name = "Apple";
        age = 45;
        println "I'm an apple for my juice";
    }
}

class FuxiApple extends Fruit implements BaseApple{};
def apple = new FuxiApple();
apple.shrinkJuice();

// mixin
printDownln("show Mxin");
class Acls {
    void speak(){
        println("We are Acls");
    }
} 

class Bcls {
    private String name = "Bob Cash";
    void walk(){
        println("I am falling down");
    }
}
Acls.metaClass.mixin Bcls;
def clA = new Acls();
clA.speak();
clA.walk();
// it works
println clA.name;
// not allowed to access private field
// println clA.@name;
// it is still not the Bcls instance;
println (clA instanceof Bcls);

// fileds are not inherited;
trait IntCouple{
    int x = 4;
    int y = 9;
    int sum(){
        return x + y;
        // return getX()+getY();
    }
} 

class BaseCounple implements IntCouple {
    int x = 2;
    int y = 3;
    int f(){
        return sum();
    }
}
// always use IntCouple's fileds,use fuction is right
println ((new BaseCounple()).f());

printDownln("show selftype restriction");
class CommunicationService {
    static void sendMessage(String from, String to, String message) {       
        println "$from sent [$message] to $to"
    }
}

class Device { String id }                                                  

@SelfType(Device)
@CompileStatic
trait Communicating {
    void sendMessage(Device to, String message) {
        CommunicationService.sendMessage(id, to.id, message)                
    }
}

class MyDevice extends Device implements Communicating {}
class TaDevice extends Device implements Communicating {}                   

def bob = new MyDevice(id:'Bob')
def alice = new MyDevice(id:'Alice')
bob.sendMessage(alice,'secret')

def ta = new TaDevice(id:"tacheng");

ta.sendMessage(alice,'not good');

// not allowed the prefix or postfix
trait Counting {
    int x = 0;
    void inc() {
        // not allowed
        // x++
        x+=1;                             
    }
    void dec() {
        // not allowed;
        // --x;
        x-=1;                 
    }
}
class Counter implements Counting {}
def c = new Counter();
c.inc();
println c.x;