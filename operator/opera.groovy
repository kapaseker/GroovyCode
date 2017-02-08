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