package Multithreading;

import java.util.Scanner;

public class Test6WaitNotify {
    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify vn = new WaitAndNotify();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    vn.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    vn.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }
}

class WaitAndNotify {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread started...");
            wait(); // 1) отдаем intrinsic lock, 2) ждем пока будет вызван notify()
            System.out.println("Producer thread resumed...");
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this) {
            System.out.println("Waiting for return key pressed");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            notify();

            Thread.sleep(5000);
        }
    }
}
