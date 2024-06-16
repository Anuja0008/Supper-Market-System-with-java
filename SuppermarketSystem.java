import java.util.ArrayList;
import java.util.List;

public class SuppermarketSystem {

    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();

        inventoryManager.addProduct(new Product("Apple", 20.0, "Fruits", 500, "Iduwara"));
        inventoryManager.addProduct(new Product("Banana", 50.5, "Fruits", 400, "Banusha"));

        SalesTransaction transaction = new SalesTransaction();
        transaction.addProduct(inventoryManager.getInventory().get(0), 70);
        transaction.addProduct(inventoryManager.getInventory().get(1), 10);

        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateSalesReport(transaction);
        reportGenerator.generateInventoryReport(inventoryManager.getInventory());
    }
}

class Product {
    private String name;
    private double price;
    private String category; // Corrected typo
    private int quantity;
    private String supplier;

    public Product(String name, double price, String category, int quantity, String supplier) { // Corrected typo
        this.name = name;
        this.price = price;
        this.category = category; // Corrected typo
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSupplier() {
        return supplier;
    }
}

class Customer {
    private String name;
    private String contact;
    private List<Product> purchaseHistory;

    public Customer(String name, String contact) {
        this.name = name;
        this.contact = contact;
        this.purchaseHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public List<Product> getPurchaseHistory() {
        return purchaseHistory;
    }
}

class Employee {
    private String name;
    private String role;

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}

class SalesTransaction {
    private List<Product> productsSold;

    public SalesTransaction() {
        this.productsSold = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        productsSold.add(product);
        product.setQuantity(product.getQuantity() - quantity);
        System.out.println(quantity + " " + product.getName() + " sold");
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : productsSold) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}

class InventoryManager {
    private List<Product> inventory;

    public InventoryManager() {
        this.inventory = new ArrayList<>();
    }

    public void addProduct(Product product) {
        inventory.add(product);
    }

    public void updateProductQuantity(String productName, int quantity) {
        for (Product product : inventory) {
            if (product.getName().equals(productName)) {
                product.setQuantity(product.getQuantity() + quantity);
                break;
            }
        }
    }

    public List<Product> getInventory() {
        return inventory;
    }
}

class ReportGenerator {
    public void generateSalesReport(SalesTransaction transaction) {
        System.out.println("Total Sales: $" + transaction.getTotalPrice());
    }

    public void generateInventoryReport(List<Product> inventory) {
        System.out.println("Inventory Report");

        for (Product product : inventory) {
            System.out.println("Product: " + product.getName() + ", Quantity: " + product.getQuantity());
        }
    }
}
