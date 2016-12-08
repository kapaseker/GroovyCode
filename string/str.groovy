def str = "50.134.23.3.5.20160215";
int firstDot = str.indexOf('.');
int secondDot = str.indexOf('.',firstDot+1);
def version = str.substring(0,secondDot);
print version;
