package sqliteDemo;

public class ProducT {
    // Поля класса
    private int id;

    private String prodid;

    private String title;

    private Long cost;

    public ProducT(int id, String prodid, String title, Long cost) {
        this.id = id;
        this.prodid = prodid;
        this.title = title;
        this.cost = cost;
    }
// Конструктор


    // Выводим информацию по продукту
    @Override
    public String toString() {
        return String.format("id_товара | Товар %s | Товар%s | %s",
                this.prodid, this.title, this.cost);
    }
}
