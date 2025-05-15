import java.util.*;

class Book {
    String title;
    String author;
    double price;
    double rating;

    Book(String title, String author, double price, double rating) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.rating = rating;
    }

    void display() {
        System.out.println("📘 " + title + " by " + author);
        System.out.println("   ₹" + price + " | ⭐ " + rating + "/5");
    }
}

class User {
    String username;
    String password;

    User(String u, String p) {
        username = u;
        password = p;
    }
}

public class BookWormHaven {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, String> users = new HashMap<>();
    static ArrayList<Book> cart = new ArrayList<>();
    static Map<String, ArrayList<Book>> categories = new LinkedHashMap<>();

    public static void main(String[] args) {
        loadBooks();
        welcomeScreen();
    }

    static void welcomeScreen() {
        System.out.println("\n🪔 Welcome to 🇮🇳 Book Worm Haven 🪔\n");
        while (true) {
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int opt = sc.nextInt();
            sc.nextLine();

            if (opt == 1) signUp();
            else if (opt == 2) {
                if (signIn()) mainMenu();
            }
            else break;
        }
    }

    static void signUp() {
        System.out.print("Enter new username: ");
        String uname = sc.nextLine();
        System.out.print("Enter new password: ");
        String pass = sc.nextLine();
        if (users.containsKey(uname)) {
            System.out.println("⚠️ Username already exists");
        } else {
            users.put(uname, pass);
            System.out.println("✅ Sign Up successful. Please sign in");
        }
    }

    static boolean signIn() {
        System.out.print("Username: ");
        String uname = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        if (users.containsKey(uname) && users.get(uname).equals(pass)) {
            System.out.println("✅ Login successful");
            return true;
        } else {
            System.out.println("❌ Invalid credentials");
            return false;
        }
    }

    static void mainMenu() {
        cart.clear();
        while (true) {
            System.out.println("\n📚 Main Menu - Book Worm Haven");
            System.out.println("1. Browse Book Categories");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Logout");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            if (choice == 1) browseBooks();
            else if (choice == 2) viewCart();
            else if (choice == 3) checkout();
            else if (choice == 4) {
                System.out.println("👋 Logged out successfully");
                break;
            }
        }
    }

    static void browseBooks() {
        System.out.println("\n📚 Book Categories:");
        int i = 1;
        for (String category : categories.keySet()) {
            System.out.println(i + ". " + category);
            i++;
        }
        System.out.print("Select a category: ");
        int ch = sc.nextInt();
        sc.nextLine();
        if (ch < 1 || ch > categories.size()) {
            System.out.println("⚠️ Invalid choice");
            return;
        }

        String selectedCategory = new ArrayList<>(categories.keySet()).get(ch - 1);
        ArrayList<Book> books = categories.get(selectedCategory);

        System.out.println("\n📚 " + selectedCategory + " Books:");
        for (int j = 0; j < books.size(); j++) {
            System.out.println((j + 1) + ".");
            books.get(j).display();
        }

        System.out.print("Enter book number to add to cart (0 to go back): ");
        int bookChoice = sc.nextInt();
        if (bookChoice >= 1 && bookChoice <= books.size()) {
            cart.add(books.get(bookChoice - 1));
            System.out.println("✅ Book added to cart");
        }
    }

    static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("\n🛒 Your cart is empty");
            return;
        }
        System.out.println("\n🛒 Your Cart:");
        double total = 0;
        int i = 1;
        for (Book b : cart) {
            System.out.print(i++ + ". ");
            b.display();
            total += b.price;
        }
        System.out.println("Total: ₹" + total);
    }

    static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("🛒 Cart is empty. Add some books first");
            return;
        }
        viewCart();
        System.out.print("Proceed to payment (yes/no): ");
        sc.nextLine();
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            System.out.println("💳 Payment successful. Thank you for shopping with Book Worm Haven");
            cart.clear();
        } else {
            System.out.println("❌ Checkout cancelled");
        }
    }

    static void loadBooks() {
        categories.put("Indian Fiction", new ArrayList<>(Arrays.asList(
            new Book("2 States", "Chetan Bhagat", 180, 4.2),
            new Book("The White Tiger", "Aravind Adiga", 250, 4.4),
            new Book("Train to Pakistan", "Khushwant Singh", 220, 4.1),
            new Book("The God of Small Things", "Arundhati Roy", 300, 4.3),
            new Book("The Palace of Illusions", "Chitra Banerjee", 350, 4.5)
        )));
        categories.put("Mythology", new ArrayList<>(Arrays.asList(
            new Book("Sita", "Amish Tripathi", 250, 4.5),
            new Book("Ram: Scion of Ikshvaku", "Amish Tripathi", 270, 4.4),
            new Book("Karna's Wife", "Kavita Kane", 280, 4.2),
            new Book("Ajaya", "Anand Neelakantan", 260, 4.3),
            new Book("Asura", "Anand Neelakantan", 300, 4.6)
        )));
        categories.put("Biographies", new ArrayList<>(Arrays.asList(
            new Book("Wings of Fire", "Dr. A.P.J Abdul Kalam", 200, 4.8),
            new Book("Playing It My Way", "Sachin Tendulkar", 350, 4.6),
            new Book("My Experiments with Truth", "Mahatma Gandhi", 180, 4.7),
            new Book("Indira: The Life of Indira Nehru Gandhi", "Katherine Frank", 320, 4.1),
            new Book("Narendra Modi: A Political Biography", "Andy Marino", 290, 4.0)
        )));
        categories.put("Self Help", new ArrayList<>(Arrays.asList(
            new Book("You Can Win", "Shiv Khera", 230, 4.5),
            new Book("Life's Amazing Secrets", "Gaur Gopal Das", 240, 4.6),
            new Book("Think Like a Monk", "Jay Shetty", 300, 4.4),
            new Book("The Power of Your Subconscious Mind", "Joseph Murphy", 260, 4.5),
            new Book("Atomic Habits", "James Clear", 400, 4.7)
        )));
        categories.put("Children's Books", new ArrayList<>(Arrays.asList(
            new Book("Panchtantra Tales", "Vishnu Sharma", 150, 4.3),
            new Book("The Jungle Book", "Rudyard Kipling", 180, 4.4),
            new Book("Grandma's Bag of Stories", "Sudha Murty", 160, 4.5),
            new Book("The Blue Umbrella", "Ruskin Bond", 140, 4.6),
            new Book("Malgudi Days", "R.K. Narayan", 200, 4.4)
        )));
    }
}
