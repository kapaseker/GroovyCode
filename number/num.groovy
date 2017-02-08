int a = 0;
println a;
// so strange , it doesn't auto convert to long;
def val = Integer.MAX_VALUE + 1;
println val;

println val instanceof Integer;
val += 1;
println val as long;

def longVal = 2147483648;
println longVal;
println longVal as Integer;
println longVal instanceof Long;

//Binary literal
def binVal = 0b111;
println binVal;

int binInt = 0b00101;
println binInt;

println 1e3;