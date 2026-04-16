import java.util.*;

public class PocketBank {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();
    static boolean sortedByID = false;

    static void generateTransactions(int n) {
        String[] merchants = {"Starbucks", "Amazon", "Steam Games", "Whole Foods", "Shell Gas", "Netflix"};
        String[] categories = {"Food", "Shopping", "Entertainment", "Groceries", "Transport", "Subscriptions"};

        Random rand = new Random();
        int baseID = 100000;

        transactions.clear();

        for (int i = 0; i < n; i++) {
            baseID += rand.nextInt(100) + 1;

            int index = rand.nextInt(merchants.length);

            String merchant = merchants[index];
            String category = categories[index];

            double amount = (rand.nextInt(50000) + 100) / 100.0;


            transactions.add(new Transaction(baseID, merchant, amount, category));
        }

        sortedByID = false;
        System.out.println(n + " transactions generated.");
    }

    static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }



    static void selectionSortAmount() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        int i, j, min;
        Transaction temp;

        for (i = 0; i < transactions.size(); i++) {
            min = i;

            for (j = i + 1; j < transactions.size(); j++) {
                if (transactions.get(min).amount > transactions.get(j).amount) {
                    min = j;
                }
            }

            // swap with the next minimum
            temp = transactions.get(i);
            transactions.set(i, transactions.get(min));
            transactions.set(min, temp);
        }

        sortedByID = false;
        System.out.println("Sorted by amount.");
    }

    static void insertionSortID() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }
        for (int i = 1; i < transactions.size(); i++) {
            Transaction key = transactions.get(i);
            int j = i ;

            while (j > 0 && transactions.get(j-1).id > key.id) {
                transactions.set(j, transactions.get(j-1));
                j--;
            }

            transactions.set(j , key);
        }

        sortedByID = true;
        System.out.println("Sorted by ID.");
    }

    static int binarySearch(int key) {

        int left = 0;
        int right = transactions.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (transactions.get(mid).id == key)
                return mid;
            else if (transactions.get(mid).id < key)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return -1;
    }

    static void filterByCategory(String category)
    {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }
        boolean found = false;

        for (Transaction t : transactions) {
            if (t.category.equalsIgnoreCase(category)) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found.");
        }
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- PocketBank Admin Menu ---");
            System.out.println("1. View All Transactions");
            System.out.println("2. Sort by Amount");
            System.out.println("3. Sort by ID");
            System.out.println("4. Search by ID");
            System.out.println("5. Filter by Category");
            System.out.println("6. Generate Transactions");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    viewTransactions();
                    break;

                case 2:
                    selectionSortAmount();
                    break;

                case 3:
                    insertionSortID();
                    break;

                case 4:
                    if (transactions.isEmpty()) {
                        System.out.println("No transactions available.");
                        break;
                    }
                    if (!sortedByID) {
                        System.out.println(" Sort by ID first!");
                        break;
                    }

                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();

                    int index = binarySearch(id);

                    if (index != -1)
                        System.out.println(transactions.get(index));
                    else
                        System.out.println("Not found.");
                    break;

                case 5:
                    scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    filterByCategory(category);
                    break;

                case 6:
                    System.out.print("Enter size: ");
                    int size = scanner.nextInt();
                    generateTransactions(size);
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
    }
}
