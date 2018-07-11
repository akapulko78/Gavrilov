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

            while ((reader.read()) != -1)  {
                if (reader.read() > pageNumber * pageSize - pageSize &&
                        reader.read() <= pageNumber * pageSize) {
                    page.add((char) reader.read());
                }
                else break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(page.toString());
    }
}



