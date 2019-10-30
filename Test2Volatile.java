package Multithreading;

import java.util.Scanner;

public class Test2Volatile {
    public static void main(String[] args) {
        MyThread1 myThread2 = new MyThread1();
        myThread2.start();

        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        myThread2.shutdown();
    }
}

class MyThread1 extends Thread {
    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }
}
