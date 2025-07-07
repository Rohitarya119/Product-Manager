import java.util.*;
import java.util.stream.Collectors;

class Product {
    int id;
    String name;
    String category;
    double price;
    double rating;

    public Product(int id, String name, String category, double price, double rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return id + ". " + name + " | " + category + " | ₹" + price + " | ⭐" + rating;
    }
}

public class ProductManager {
    static List<Product> products = Arrays.asList(
            new Product(1, "Laptop", "Electronics", 55000, 4.5),
            new Product(2, "Phone", "Electronics", 30000, 4.2),
            new Product(3, "T-Shirt", "Clothing", 999, 4.1),
            new Product(4, "Shoes", "Footwear", 1999, 3.8),
            new Product(5, "Headphones", "Electronics", 2499, 4.0),
            new Product(6, "Jeans", "Clothing", 1500, 3.9)
    );

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Product Management ===");
            System.out.println("1. View All Products");
            System.out.println("2. Filter by Category");
            System.out.println("3. Sort by Price");
            System.out.println("4. View Top Rated Products (>= 4.0)");
            System.out.println("5. Group Products by Category");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewAllProducts();
                case 2 -> {
                    System.out.print("Enter category: ");
                    String category = sc.nextLine();
                    filterByCategory(category);
                }
                case 3 -> sortByPrice();
                case 4 -> topRatedProducts();
                case 5 -> groupByCategory();
                case 6 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);

        sc.close();
    }

    static void viewAllProducts() {
        System.out.println("\n--- All Products ---");
        products.forEach(System.out::println);
    }

    static void filterByCategory(String category) {
        System.out.println("\n--- Filtered Products (" + category + ") ---");
        List<Product> filtered = products.stream()
                .filter(p -> p.category.equalsIgnoreCase(category))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) System.out.println("No products found.");
        else filtered.forEach(System.out::println);
    }

    static void sortByPrice() {
        System.out.println("\n--- Products Sorted by Price (Low to High) ---");
        products.stream()
                .sorted(Comparator.comparingDouble(p -> p.price))
                .forEach(System.out::println);
    }

    static void topRatedProducts() {
        System.out.println("\n--- Top Rated Products (⭐ >= 4.0) ---");
        products.stream()
                .filter(p -> p.rating >= 4.0)
                .forEach(System.out::println);
    }

    static void groupByCategory() {
        System.out.println("\n--- Products Grouped by Category ---");
        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));

        grouped.forEach((category, list) -> {
            System.out.println("\n" + category + ":");
            list.forEach(System.out::println);
        });
    }
}
