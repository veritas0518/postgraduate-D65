package Day30;

/**
 * @ClassName: AccountTest
 * @Description:
 * @Author: TianXing.Xue
 * @Date: 2021/7/26 14:13
 * @Version:
 **/

class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    //存钱
    public  synchronized void deposit(double amt) {
        if (amt > 0) {

            balance += amt;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+ "存钱成功，余额为：" + balance);
        }
    }
}

class Customer extends Thread {
    private Account acct;

    public Customer(Account acct) {
        this.acct = acct;
    }

    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }

    }
}

public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account(0);
        Customer c1 = new Customer(account);
        Customer c2 = new Customer(account);

        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();
    }
}
