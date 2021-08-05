package Day30;

/**
 * @ClassName: WindowTest5
 * @Description: 使用同步方法处理继承Thread类的方式中的线程安全问题
 * @Author: TianXing.Xue
 * @Date: 2021/7/26 11:24
 * @Version: 5.0
 * <p>
 * 关于同步方法的总结：
 * 1.同步方法仍然涉及到同步监视器，只是不需要我们显式的声明。
 * 2.非静态的同步方法，同步监视器：this
 * 静态的同步方法，同步监视器：当前类本身
 **/

class Window5 extends Thread {
    private static int ticket = 100;

    public void run() {
        while (true) {
            show();
        }
    }

    private static synchronized void show() {  //同步监视器：Windows5.class
        if (ticket > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": 卖票，票号为：" + ticket);
            ticket--;
        }
    }
}

public class WindowTest5 {
    public static void main(String[] args) {
        Window5 window5 = new Window5();

        Thread t1 = new Thread(window5);
        Thread t2 = new Thread(window5);
        Thread t3 = new Thread(window5);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}
