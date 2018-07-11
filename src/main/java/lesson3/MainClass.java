package lesson3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class MainClass {


    public static void main(String[] args) {
        task1();
        task2();
    }

    private static void task1() {
        File file_1 = new File("C:\\files\\file_1.txt");
        byte[] b = new byte[50];
        try (FileInputStream fis = new FileInputStream(file_1)) {
            while ((fis.read(b) != -1)) {
                for (byte bytes : b) {
                    System.out.print(bytes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void task2() {
        //создание файлов
        File file1 = new File("C:\\files\\file11.txt");
        File file2 = new File("C:\\files\\file22.txt");
        File file3 = new File("C:\\files\\file33.txt");
        File file4 = new File("C:\\files\\file44.txt");
        File file5 = new File("C:\\files\\file55.txt");
        File bigFile = new File("C:\\files\\bigFile.txt");

        //создание массива определенного размера
        byte[] bytes = new byte[100];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = 7;
        }
        try (FileOutputStream fos = new FileOutputStream("C:\\files\\file11.txt")) {
            //запись массива в файл
            fos.write(bytes);
            //копирование файлов
            copyFileUsingStream(file1, file2);
            copyFileUsingStream(file1, file3);
            copyFileUsingStream(file1, file4);
            copyFileUsingStream(file1, file5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //создание потоков ввода
            FileInputStream in1, in2, in3, in4, in5;
            //создание потока ввода
            DataInputStream dis;
            //создание потока вывода
            FileOutputStream out;
            //создание экземпляров потоков ввода
            in1 = new FileInputStream(file1);
            in2 = new FileInputStream(file2);
            in3 = new FileInputStream(file3);
            in4 = new FileInputStream(file3);
            in5 = new FileInputStream(file5);
            //создание множества потоков ввода
            ArrayList<FileInputStream> allIn = new ArrayList<>();
            //добавление потоков в множество
            allIn.add(in1);
            allIn.add(in2);
            allIn.add(in3);
            allIn.add(in4);
            allIn.add(in5);
            //создание перечисления
            Enumeration<FileInputStream> streams = Collections.enumeration(allIn);
            //создание экземпляра обьединяющего потока ввода,
            // обернутого в буферизированный поток и поток ввода примитивных типов
            dis = new DataInputStream(
                    new BufferedInputStream(
                            new SequenceInputStream(streams)));
            //создание экземпляра потока вывода
            out = new FileOutputStream(bigFile);
            //  создание переменной для чтения из обьединяющего потока
            int rb = dis.read();
            while (rb != -1) {
                out.write(rb);
                rb = dis.read();
            }
            dis.close();
            out.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}


