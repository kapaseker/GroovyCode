// this is a elvis operator 

def printDownln(String info){
    println info+"\u2193\u2193\u2193";
}

def val = 2;
println val?:3;

def person = [name:"bob",age:"23"];
println person.name;

println person?.hight;
println person.hight;

person = null;
// ?. will protect person is a null object
println person?.name;
// println person.name;

class User{
    public final String name;
    public User(String nm){
        this.name = nm;
    }
    public String getName(){
        return "My Name : $name";
    }
}

User me = new User("Qian");
// this will call getter
println me.name;
// this will call origin field
println me.@name;

//store object function to a variable
def str = "this is a big thing";
def uppperFun = str.&toUpperCase;
println uppperFun();

//function pointer is a closer , aha! like javascript
def makeResults(List elements,Closure fun){
    def results = [];

    elements.each { 
        results<<fun(it)
    }    

    return results;
}

def add5Fun(int a){
    return a+5;
}

def addNum = [1,2,3,4,8,5];
def result = makeResults(addNum,this.&add5Fun);
println result;

// regular operator
// define a regular operator
def match = ~/foo/;
def needMatch = "this is a good fod";
// =~ is a find operator , isMatch is a Mather object now;
def isMatch = needMatch =~ match;
println isMatch.find();
// ==~ is Match operator(it will return a boolean),matchBool is a boolean;
def matchBool = needMatch ==~ match;
println matchBool;
// spread operator *.
class Car {
    String model;
    String brand;
}

def cars = [
    new Car(model:"vechal",brand:"benz"),
    new Car(model:"suv",brand:"range rover"),
    null
];
// call brand on each Car object and return a array, if there is a object that is null then return null;
def brands = cars*.brand;
println brands;

// he spread operator can be used on any class which implements the Iterable interface:
class Son {
    String name;
    int age;
}

class SonList implements Iterable<Son> {
    def sons = [
        new Son(age: 12, name: 'Micheal'),
        new Son(age: 16, name: 'Jordan')];

    @Override
    Iterator<Son> iterator() {
        return sons.iterator();
    }
}

def sonNames = new SonList()*.name;
println sonNames;

// use * to spread method arguments,like javascript;
def int process(int a,int b, int c){
    return a+b*c;
}
// if this array's elements' length is greater than 3, it will throw a exception;
def argus = [2,6,9];
println process(*argus);

// use * spread a list;
def argusBig = [1,3,*argus,9,10];
println argusBig;

//spread a map with *:
def mapA = [a:1,b:2];
def mapAll = [a:2,c:2,*:mapA];
println mapAll;

// range operator ..
def rangArray = 0..9;
println rangArray;

def rangeChar = 'a'..'e';
println rangeChar;

// next is a exclusive range
def rangUpper = 0..<9;
println rangUpper;

// The spaceship operator (<=>) delegates to the compareTo method:
printDownln "spaceship operator";
println 1<=>2;
println 3<=>3;
println 4<=>2;

// subscript operator
printDownln 'subscript operator';
def arraySub = [1,2,3,4,5,6,7,9];
println arraySub[4];
println arraySub[4..7];
// change array 
arraySub[2..7] = [3,4,5];
println arraySub;

// overloading getAt and putAt
class Student {
    Long id;
    String name;
    def getAt(int i){
        switch(i) {
            case 0:
                return id;
            break
            case 1:
                return name;
            break;
            default:
                throw new IllegalArgumentException("Not such Arguments");
        }
    }
    def void putAt(int i ,def var){
        if(i == 0){
            id = var;
        }else if(i == 1){
            name = var;
        }
    }

    def String toString(){
        return "id:"+this.id+";name:"+this.name;
    }
}

// using getAt
def yang = new Student(id:1,name:"YangYang");
println yang[0];
println yang[1];
// using putAt
yang[0] = 3;
println yang;

// Membership operator
printDownln("Membership operator");
def inArray = [2,3,4,5,6,9];
println 1 in inArray;
println 4 in inArray;

//Identity operator == ,like javascript, use '.is'(a function) to compare reference
printDownln("== is Identity operator")
def leftObj = [1,2,3,4];
def rightObj = [1,2,3,4];

println leftObj == rightObj;
println leftObj.is(rightObj);
println leftObj.is(leftObj);

// Coercion operator 'as'
int a = 2;
long b = a as long;
println b;

Integer intObj = 3;
// use as operator
printDownln("Using as operator")
String strObj = intObj as String;
println strObj;
// use () operator
String strObj2 = (String) intObj;
println strObj2;

class Bottom {
    int type = 0;
    String name = "good";
    def asType(Class cls){
        if(cls == Upper){
            return new Upper(type:this.type);
        }else if(cls == Middle){
            return new Middle(name:this.name);
        }

        throw new ClassCastException("Bottom cannot be coerced into $cls");
    }
}

class Upper {
    int type = 0;
}

class Middle {
    String name = "job";
}


def btm  = new Bottom(type:90,name:"James");
def up = btm as Upper;
println up.@type;
println ((btm as Middle).@name);
// groovy allow operator overloading

printDownln("operator overloading")
class Bucket {
    int size;
    Bucket(int size) { this.size = size }
    // overloading plus
    Bucket plus(Bucket other) {                     
        return new Bucket(this.size + other.size)
    }
}

def bk = new Bucket(23);
def lk = new Bucket(17);

println ((bk+lk).@size);