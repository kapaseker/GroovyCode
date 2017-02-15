
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