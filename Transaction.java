public class Transaction {
    int id;
    String merchant;
    double amount;
    String category;


    public Transaction(int id, String merchant, double amount, String category) {
        this.id = id;
        this.merchant = merchant;
        this.amount = amount;
        this.category = category;

    }


    public String toString() {
        return String.format(
                "ID: %-8d | Merchant: %-15s | Amount: $%8.2f | Category: %-15s",
                id, merchant, amount, category
        );
    }
}
