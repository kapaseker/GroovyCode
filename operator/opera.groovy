// this is a elvis operator 
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