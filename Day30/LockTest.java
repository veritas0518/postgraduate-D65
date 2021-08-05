package Day30;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: LockTest
 * @Description: 解决线程安全问题的方式三：Lock锁 --- JDK5.0新增
 * @Author: TianXing.Xue
 * @Date: 2021/7/26 13:22
 * @Version: 1.0
 *
 *  1.面试题： synchronized 和 lock 的异同
 *          相同：二者都可以解决线程的安全问题
 *          不同：synchronized机制在执行完相应的同步代码以后，自动的释放同步监视器
 *               Lock需要手动的启动同步（lock()），同时结束同步也需要手动的实现（unlock()）
 *
 *  2.使用的优先顺序：
 *  Lock -> 同步代码块（已经进入了方法体，分配了相应资源） -> 同步方法（在方法体之外）
 *
 *  3.面试题：如何解决线程安全问题？有几种方式？
 **/
class Window implements Runnable{
    private int ticket=100;
    //1.实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            try{

                //2.调用锁定的lock()方法
                lock.lock();

                if(ticket>0){
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+":售票，票号为"+ticket);
                    ticket--;
                }else{
                    break;
                }
            }finally{
                //3.调用解锁的方法：unlock()
                lock.unlock();
            }
        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        Window w1 =new Window();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w1);
        Thread t3 = new Thread(w1);

        t1.setName("窗口一");
        t2.setName("窗口一");
        t3.setName("窗口一");

        t1.start();
        t2.start();
        t3.start();


    }
}
