package com.example.java.java_core.multi_thread;
// đồng bộ trong đa luồng

public class c_synchronize {
    private int balance = 1000;

    public c_synchronize() {
        System.out.println("Tài khoản của bạn là " + balance);
    }

    public synchronized void withdraw(int amount) {
        System.out.println("Đang thực hiện giao dịch rút tiền " + amount + "...");
        while (balance < amount) {
            System.out.println("Không đủ tiền rút!!!");
            try {
                wait();
            } catch (InterruptedException ie) {
                System.out.println(ie.toString());
            }

        }
        balance -= amount;
        System.out.println("Rút tiền thành công. Tài khoản của bạn hiện tại là " + balance);
    }

    public synchronized void deposit(int amount) {
        System.out.println("Đang thực hiện giao dịch nạp tiền " + amount + "...");
        balance += amount;
        System.out.println("Nạp tiền thành công. Tài khoản của bạn hiện tại là " + balance);
        notifyAll(); // Thông báo đã nạp tiền
    }

    public static void main(String[] args) {
        final c_synchronize c = new c_synchronize();
        Thread withdraw1 = new Thread() {
            public void run() {
                c.withdraw(2000);
            }
        };
        withdraw1.start();
        Thread withdraw2 = new Thread() {
            public void run() {
                c.withdraw(2000);
            }
        };
        withdraw2.start();
        Thread withdraw3 = new Thread() {
            public void run() {
                c.withdraw(8000);
            }
        };
        withdraw3.start();

        Thread deposit = new Thread() {
            public void run() {
                c.deposit(500);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                c.deposit(10500);
            }
        };
        deposit.start();
    }
}
