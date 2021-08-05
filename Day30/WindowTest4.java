package Day30;

/**
 * @ClassName: WindowTest4
 * @Description: 使用同步方法实现Runnable接口的线程安全问题
 * @Author: TianXing.Xue
 * @Date: 2021/7/26 11:17
 * @Version: 4.0
 **/

class Window4 implements Runnable {
    private int ticket = 100;

    public void run() {
        while (true) {
            show();
        }
    }

    private synchronized void show() {  //同步监视器：this  这个this是唯一的
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + ": 卖票，票号为：" + ticket);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket--;
        }
    }
}


public class WindowTest4 {
    public static void main(String[] args) {
        Window4 window4 = new Window4();

        Thread t1 = new Thread(window4);
        Thread t2 = new Thread(window4);
        Thread t3 = new Thread(window4);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}
