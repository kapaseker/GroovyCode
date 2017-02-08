def str = "50.134.23.3.5.20160215";
int firstDot = str.indexOf('.');
int secondDot = str.indexOf('.',firstDot+1);
def version = str.substring(0,secondDot);
print version;
print '\n';

def name = '''
apple
google
microsoft
huawei
''';

print name;

def stripNewLine = '''\
remove a new line by \
adding a backslash 
''';

print stripNewLine;

print 'The Euro currency symbol: \u20AC \n';

def myName = "xie peng gang";

print "this is you name : ${myName}.\n";

def person = [name:"Qian\'s",age:23];

print "$person.name has a age of $person.age \n";

print "1 add 2 is ${1+2}.\n";

def value = 23;
def closerStr = "value = ${->value};\n";

print closerStr;

value = 45;

print closerStr;

// single quote doesn't support exprection
def singleQuote = 'this is ${value}';

println singleQuote;
// this is plain java String
def javaStr = "this java String";
def grooyThing = "good";
// this is a GString , because it has a interpolated expression
def groovyStr = "this is grooy String ${grooyThing}";

println javaStr;
println groovyStr;

println "javaStr is String ? ${javaStr instanceof String}";
println "javaStr is GString ? ${javaStr instanceof GString}";

println "groovyStr is String ? ${groovyStr instanceof String}";
println "groovyStr is String ? ${groovyStr instanceof GString}";