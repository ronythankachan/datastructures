package designpatterns.singleton;

class Logger{
    private int loggerCount;
    private static Logger logger;
    private Logger(){
        loggerCount++;
        System.out.println("Logger count -> "+loggerCount);
    }
    public static Logger getLogger(){
        if(logger == null) logger = new Logger();
        return logger;
    }
    public void log(String str) {
        System.out.println(str);
    }
}
public class Main {
    public static void main(String[] args) {
        Logger.getLogger().log("User1 logs");
        Logger.getLogger().log("User2 logs");
    }
}