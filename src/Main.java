import java.util.Scanner;

class Student {
    public static String[] names = new String[10];
    public static String[] faculties = new String[10];
    public static String[] nims = new String[10];
    public static String[] programs = new String[10];
    public static String[] borrowedBooks = new String[10];

    // Method untuk memeriksa apakah NIM yang dimasukkan oleh user ada di database mahasiswa
    int checkStudent(){
        Scanner scanString = new Scanner (System.in);
        System.out.print("Enter your NIM: ");
        String input = scanString.nextLine();
        for (int i = 0; i < nims.length; i++) {
            if (input.equals(nims[i])) {
                System.out.println("Successfully logged in as student\n");
                return 1;
            }
        }
        System.out.println("NIM not found\n");
        return 0;
    }

    // Method untuk meminjam buku oleh mahasiswa
    void borrowBook(int j){
        Main main = new Main();
        Scanner scanString = new Scanner (System.in);
        System.out.print("Masukkan ID buku yang ingin Anda pinjam: ");
        String bookId = scanString.nextLine();
        for (int i = 0; i < main.bookIds.length; i++) {
            if (bookId.equals(main.bookIds[i])) {
                System.out.println("Buku dengan ID " + bookId + " berhasil dipinjam");
                main.stocks[i] -= 1; // Mengurangi stok buku yang dipinjam dari database buku
                break;
            }
        }
        borrowedBooks[j] = bookId; // Menyimpan ID buku yang dipinjam oleh mahasiswa
    }

    // Method untuk menampilkan daftar buku yang dipinjam oleh mahasiswa
    void displayBorrowedBooks(){
        System.out.println("Borrowed Books: ");
        for (int i = 0; i < borrowedBooks.length; i++) {
            if (borrowedBooks[i] == null) {
                break;
            } else {
                System.out.printf("%d. %s\n", i+1, borrowedBooks[i]);
            }
        }
    }
}

class Admin {
    Student student = new Student();
    public static String username = "admin";
    public static String password = "1231";

    // Method untuk memeriksa login admin
    int checkAdmin(){
        Scanner scanString = new Scanner(System.in);
        System.out.print("Masukkan nama pengguna Anda (admin): ");
        String user = scanString.nextLine();
        System.out.print("Masukkan kata sandi Anda (admin): ");
        String pass = scanString.nextLine();
        if (user.equals(username) && pass.equals(password)) {
            System.out.println("Berhasil login sebagai admin\n");
            return 1;
        }
        else {
            System.out.println("Admin not found\n");
            return 0;
        }
    }

    // Method untuk menambahkan data mahasiswa
    void addStudent(int i){
        Scanner scanString = new Scanner(System.in);
        System.out.print("Masukkan nama mahasiswa: ");
        Student.names[i] = scanString.nextLine();

        System.out.print("Masukkan fakultas mahasiswa: ");
        Student.faculties[i] = scanString.nextLine();

        System.out.print("Masukkan NIM mahasiswa: ");
        Student.nims[i] = scanString.nextLine();
        while(true){
            if (String.valueOf(Student.nims[i]).length() != 15 ) {
                System.out.print("NIM harus 15 digit!!!\n");
                System.out.print("Masukkan NIM mahasiswa: ");
                student.nims[i] = scanString.nextLine();
            } else {
                break;
            }
        }

        System.out.print("Masukkan program studi mahasiswa: ");
        Student.programs[i] = scanString.nextLine();

        System.out.print("Student data added successfully.\n");
    }

    // Method untuk menampilkan data mahasiswa
    void displayStudent(int i){
        System.out.println("Student data " + (i+1));
        System.out.println("Nama: " + Student.names[i]);
        System.out.println("Fakultas: " + Student.faculties[i]);
        System.out.println("NIM: " + Student.nims[i]);
        System.out.println("Program Studi: " + Student.programs[i]);
    }
}

class Main {
    //book data
    public static String[] bookIds = new String[] {"388c-e681-9152", "ed90-be30-5cdb", "d95e-0c4a-9523"};
    public static String[] bookTitles = new String[] {"title", "title", "title"};
    public static String[] authors = new String[] {"author", "author", "author"};
    public static String[] categories = new String[] {"History", "History", "History"};
    public static int[] stocks = new int[] {4, 0, 2};

    // Method untuk menampilkan menu utama
    void mainMenu(){
        System.out.println("\n==== Library System ====");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.println("3. Exit");
        System.out.print("Choice Option: ");
    }

    // Method untuk menampilkan menu mahasiswa
    void studentMenu(){
        System.out.println("\n==== Student Menu ====");
        System.out.println("1. Buku Terpinjam");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Logout");
        System.out.print("Choice Option: ");
    }

    // Method untuk menampilkan menu admin
    void adminMenu(){
        System.out.println("\n==== Admin Menu ====");
        System.out.println("1. Add Student");
        System.out.println("2. Display Registered Student");
        System.out.println("3. Logout");
        System.out.print("Choice Option : ");
    }

    // Method untuk menampilkan daftar buku
    void displayBooks(){
        System.out.println("===========================================================================================================================================");
        System.out.println("|| No. || Book ID\t\t\t || Book Title\t\t\t || Author\t\t || Category\t\t || Stock\t || ");
        for (int i = 0; i < 3; i++){
            System.out.printf("|| %d   || %s\t\t || %s\t\t\t || %s\t\t || %s\t\t || %d\t\t || \n", i, bookIds[i], bookTitles[i], authors[i], categories[i], stocks[i]);
        }
        System.out.println("===========================================================================================================================================");
    }

    public static void main(String[] args) {
        Student student = new Student();
        Admin admin = new Admin();
        Main main = new Main();
        mainMenu:
        while (true) {
            main.mainMenu();
            Scanner scanInt = new Scanner(System.in);
            int choice = scanInt.nextInt();
            if (choice == 1) {
                if (student.checkStudent() == 0) {
                    continue mainMenu;
                }
                int j = 0;
                while (true) {
                    main.studentMenu();
                    choice = scanInt.nextInt();
                    if (choice == 1){
                        student.displayBorrowedBooks();
                    } else if (choice == 2){
                        main.displayBooks();
                        student.borrowBook(j);
                        j++;
                    } else {
                        break;
                    }
                }
            } else if (choice == 2) {
                if (admin.checkAdmin() == 0) {
                    continue mainMenu;
                }
                while (true) {
                    main.adminMenu();
                    choice = scanInt.nextInt();
                    int i = 0;
                    if (choice == 1) {
                        admin.addStudent(i);
                        i++;
                    } else if (choice == 2) {
                        admin.displayStudent(i);
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }
}
