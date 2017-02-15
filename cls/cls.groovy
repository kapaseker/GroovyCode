class Info{
    Long id;
    String type;
}

def info = new Info(id:9,type:"ambition");

println info.id;
println info.type;

info.id = 90;
println info.id;

info.type = "apple";
println info.type;
println info.@type;
// also can access with @ oeprator
info.@type = "google";
println info.@type;

println info.properties;

trait CarAblility {
    def scroll(){
        println "Scroll with ${wheelName()}";
    }
    abstract String wheelName();
}

class BenzCar implements CarAblility {
    String wheelName(){
        "Benz Wheel";
    }
}

def glBenz = new BenzCar();
glBenz.scroll();

trait PrivateWallet {
    private String cash(){
        "No money";
    }
    def showMoney(){
        println "i have ${cash()}";
    }
}

class MyWallet implements PrivateWallet {
    String cash() {
        "\$456";
    }
}

def wallet = new  MyWallet();
// still call PrivateWallet.cash
wallet.showMoney();

trait PublicBox {
    public String ball = "basketBall";
    private int type = 34;
    def getType(){
        type;
    }
}

class MyBox implements PublicBox {

}

def box = new MyBox();
// not allowed to access type
// println box.@type;
println box.type;
// the public field in trait is renamed
println box.PublicBox__ball;

// multi inherit supported by trait;
trait Fly {
    def flying(){
        println "We will fly on sky";
    }
}

trait Walk {
    def running(){
        println "We will running on the floor";
    }
}
// this is multi inheritance
class Man implements Walk,Fly {
    String name;
}

def runningMan = new Man(name:"Jack");
runningMan.flying();

// we overload a function
class AnoMan extends Man {
    def running(){
        println "I can't running";
    }
}

def noRunMan = new AnoMan(name:"QQ");
noRunMan.running();

// duck typing,call a method not declared in this class
trait SpeakingDuck {
    String speak(){
        needMan("gua gua gua");
    }
}
// if needMan is not declared,it will call the methodMissing function
class Duck implements SpeakingDuck {
    // String quark(String name){
    //     "${name.toUpperCase()}"
    // }
    String methodMissing(String name,args) {
        "the function $name called with $args !"                     
    }
}

def dk = new Duck();
println dk.speak();

// test dynamic type
trait DynamicObj {
    private Map props = [:];
    def methodMissing(String name,args){
        "${name.toUpperCase()} is called with args";
    }
    def propertyMissing(String name){
        props[name];
    }
    void setProperty(String prop,value){
        props[prop] = value;
    }
}

class InheritDynamic implements DynamicObj {
    String name = "inherit dynamic object";
    def speaking(){
        "Gua Gua Gua";
    }
}

def obj = new InheritDynamic();
println obj.age;
obj.age = 34;
println obj.age;
// existing method
println obj.speaking();
// dynamic method
println obj.sasuck();
// dynamic inheritance
trait Extra {
    def nothing(){
        "it is nothing"
    }
}

class Box {
    def google(){
        "google is good";
    }
}

def bx  = new Box();
// missing method
// println bx.nothing();

println bx.google();
// dynamic implementation of trait
def bxExtra = bx as Extra;
println bxExtra.nothing();

trait Bundle {
    def mustBe() {
        "i must be the god";
    }
}
// using .withTraits to dynamic inherit multi trait
def bundleBox = bx.withTraits Extra,Bundle;
println bundleBox.mustBe();
println bxExtra.nothing();
// class also supprt dynamic method
class TestMissiing {
    def methodMissing(String name,args){
        "$name is called"
    }
}

def tm = new TestMissiing();
println tm.google();