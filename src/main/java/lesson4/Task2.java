package lesson4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task2 implements Runnable  {
    List<Thread> threads = new ArrayList<>();

    static String text = "something text...";
    private File file4_1 = new File("C:\\files\\file4_2.txt");

   private static void task2(){
       try (FileWriter out = new FileWriter(new File("C:\\files\\file4_2.txt"))){
           Thread t1 = new Thread(new Task2());
           Thread t2 = new Thread(new Task2());
           Thread t3 = new Thread(new Task2());
           t1.start();
           t2.start();
           t3.start();
           out.write(text + System.lineSeparator());
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    public static void main(String[] args) {

    }
    @Override
    public synchronized void run() {
        for (int i = 0; i < 10; i++) {
            task2();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}
