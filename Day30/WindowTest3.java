package Day30;

/**
 * @ClassName: WindowTest3
 * @Description: 使用同步代码块解决继承Thread类的方式的线程安全问题
 * @Author: TianXing.Xue
 * @Date: 2021/7/26 10:50
 * @Version: 3.0
 *
 *  说明：在继承Thread类创建多线程的方式中，慎用this充当同步监视器，考虑使用当前类充当同步监视器
 *
 **/

class Window3 extends Thread {
    private int ticket = 100;
   static Object obj = new Object();

    public void run() {
        //synchronized (obj)
        synchronized (Window3.class) {   //synchronized就是同步的意思
            while (true) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + ": 卖票，票号为：" + ticket);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticket--;
                } else {
                    break;
                }
            }
        }

    }
}

public class WindowTest3 {
    public static void main(String[] args) {
        Window3 window3 = new Window3();

        Thread t1 = new Thread(window3);
        Thread t2 = new Thread(window3);
        Thread t3 = new Thread(window3);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}
