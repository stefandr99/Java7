package execute;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*class HelloRunnable implements Runnable {
    private final String filename;
    private final String message;
    private final int count;

    public HelloRunnable(String filename, String message, int count) {
        this.filename = filename;
        this.message = message;
        this.count = count;
    }
    @Override
    public void run() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < count; i++) {
                out.write(message + "\n");
            }
        } catch (IOException e) {
            System.err.println("Oops..." + e);
        }
    }
    public static void main(String[] args) {
        Runnable r1 = new HelloRunnable("hello.txt", "Hello Worl", 1000);
        Runnable r2 = new HelloRunnable("hello.txt", "Ciao Mondo", 1000);
        new Thread(r1).start();
        new Thread(r2).start();
    }
}*/

class Buffer {
    private long number = -1;

    public long getNumber() {
        return number;
    }
    public void setNumber(long number) {
        this.number = number;
    }
}

class Producer extends Thread {
    private final Buffer buffer;
    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            buffer.setNumber(i);
            System.out.println("Number produced:" + i);
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}

class Consumer extends Thread {
    private final Buffer buffer;
    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            long value = buffer.getNumber();
            System.out.println(
                    "Number consumed: " + value);
        }
    }
}

/*class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        new Producer(buffer).start();
        new Consumer(buffer).start();
    }
}*/
