//class Logger {
//    private int loggerCount;
//    public static final Logger logger = new Logger();
//    private Logger(){
//        loggerCount++;
//        System.out.println("Logger count -> "+loggerCount);
//    }
//    public void log(String str){
//        System.out.println(str);
//    }
//}

/*
This is the simplest implementation of the singleton pattern. The object is created when the class is loaded
and because the variable is final, it cannot be changed later. The constructor is private which means object
creation from outside is not possible either. Let's the  working in action.
Lets run a sample code.
(Run the code)
This is pretty straight forward, we can call this anywhere from our code. But there are some drawbacks to this method.

1. CPU time is wasted because the logger object is always created even its never used.
2. memory is wasted because the logger object is always created even its never used.
3. no exception handling if the object creation fails.

Let's fix these problems.
 */
//public class Logger{
//    private int loggerCount;
//    private static Logger logger;
//    private Logger(){
//        loggerCount++;
//        System.out.println("Logger count -> "+loggerCount);
//    }
//    public static Logger getLogger(){
//        if(logger == null) logger = new Logger();
//        return logger;
//    }
//    public void log(String str) {
//        System.out.println(str);
//    }
//}
/*
The logger object is not created during class initialization. The final is removed because of this reason and we made it private because
we don't want anyone to initialize the variable directly from outside and it could be potentially null if accessed before initiliaztion.
Now the creation of logger object is called lazy initialization. That is, the object is created when needed. Not during the class
initialization. This makes sure that the object is created only if not one created before. If one is created before, this returns the
previously created object. Is this perfect for us? Not yet.
1. Because this is not thread safe, imagine two threads trying get the logger at the same time. Both of the threads will see that the logger
is null and create an object. Thus we end up with two objects.
How do we make it thread safe?
 */

//public class Logger{
//    private int loggerCount;
//    private static Logger logger;
//    private Logger(){
//        loggerCount++;
//        System.out.println("Logger count -> "+loggerCount);
//    }
//    synchronized public static Logger getLogger(){
//        if(logger == null) logger = new Logger();
//        return logger;
//    }
//    public void log(String str) {
//        System.out.println(str);
//    }
//}

/*
Its simple. Add the keyword synchronized to the function. This keyword makes sure that only one thread can
execute the function at a time. This is called a lock(mutex). Now the code is thread safe. Only one object will be created. We are not done yet.
1. Acquiring a lock is expensive. Every thread now has to wait for the previous thread to relinquish the lock.
This will impact the performance. How do we solve it?
 */

//public class Logger{
//    private int loggerCount;
//    private static volatile Logger logger;
//    private Logger(){
//        loggerCount++;
//        System.out.println("Logger count -> "+loggerCount);
//    }
//    public static Logger getLogger(){
//        if(logger == null){
//            synchronized (Logger .class){
//                if(logger == null) logger = new Logger();
//            }
//        }
//        return logger;
//    }
//    public void log(String str) {
//        System.out.println(str);
//    }
//}
/*
Instead of making the whole function synchronous, we can check if the object is already created. If yes, other threads dont have to acquire the lock. It can directly get the logger object.
The outer 'if' condition checks if the logger is already created. Multiple threads can come at this point and see that the object is not created. Then one thread will entire the synchronized block
and check again if the object is created by another thread. If not it will create the object. All the other threads that comes after the object creation won't have to acquire the lock. Some may notice
that we have added 'volatile' keyword to the logger variable declaration. Why is this?
CPU reorder write operation. For example thread A comes and sees that the logger variable is null and enters into a lock and sees again that the logger is null. Then it writes the reference of the newly created
object to logger and ready to release the lock. Just when thread A is about to release the lock, thread B comes and sees that the logger is not null and uses it. But the write is not yet completed and this causes
inconsistent values. Volatile makes sure that the writes by thread A is completed before thread B can access.

There are ways to avoid double-checking lock. Refer initialization-on-demand holder

 */