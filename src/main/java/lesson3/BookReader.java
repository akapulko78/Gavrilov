package lesson3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookReader {
    static List<Character> page = new ArrayList<>();

    final static int pageSize = 1800;

    public static void main(String[] args) {
        printpage(page, 3);
    }

    static void printpage(List<Character> page, int pageNumber) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("C:\\files\\fileX.txt"))) {
            int symbol;
            char[] buf = new char[10000];

            while ((symbol = reader.read
                    (buf, pageSize * pageNumber - pageSize, 1800)) != -1) {
                reader.read(buf, pageSize * pageNumber - pageSize, 1800);
                System.out.print(reader.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



