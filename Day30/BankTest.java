package Day30;

/**
 * @ClassName: BankTest
 * @Description: 使用同步机制将单例模式中的懒汉式改写为线程安全的
 * @Author: TianXing.Xue
 * @Date: 2021/7/26 11:34
 * @Version:
 **/
public class BankTest {
}

class Bank {
    private Bank() {
    }

    private static Bank instance = null;

    //方式一：效率稍差
//    private static synchronized Bank getInstance() {
//        if (instance == null) {
//            instance = new Bank();
//        }
//        return instance;
//    }

    //方式二:效率更高
    private static Bank getInstance() {
        if (instance == null) {
            synchronized (Bank.class) {
                instance = new Bank();
            }
        }
        return instance;
    }
}