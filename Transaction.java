public class Transaction {
    int id;
    String merchant;
    double amount;
    String category;
    String date;

    public Transaction(int id, String merchant, double amount, String category, String date) {
        this.id = id;
        this.merchant = merchant;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String toString() {
        return "ID: " + id + " | " + merchant + " | $" + String.format("%.2f", amount)
                + " | " + category + " | " + date;
    }
}
