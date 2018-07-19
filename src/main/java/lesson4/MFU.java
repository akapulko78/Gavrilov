package lesson4;

public class MFU {
    private Printer printer = new Printer();
    private Scanner scanner = new Scanner();

    public void print (int b) {
        printer.work(b);
    }
    public void scan (int b) {
        scanner.work(b);
    }
}
