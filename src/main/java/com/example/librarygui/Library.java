package com.example.librarygui;
import com.example.librarygui.interfaces.Banner;
import com.example.librarygui.interfaces.FileIO;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library {

    public List<Book> books;
    public List<User> users;
    public List<Admin> admins;
    public List<Loan> loans = null;
    public static List<Category> categories;
    public User loggedUser;

    public Library() {
        this.books = loadBooks();
        this.users = loadUsers();
        this.admins = loadAdmins();
        this.categories = loadCategories();
        this.loans = loadLoans();
    }

    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        File booksDirectory = new File("medialab/books/");

        // Check if the directory exists
        if (!booksDirectory.exists() || !booksDirectory.isDirectory()) {
            System.err.println("Books directory not found or is not a directory.");
            return books;
        }

        // Get list of files in the directory
        File[] bookFiles = booksDirectory.listFiles();

        // Iterate over the files and load book information
        if (bookFiles != null) {
            for (File file : bookFiles) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".bin")) {
                        String[] book_info = FileIO.readMultipleStrings(file.getAbsolutePath());
                        books.add(new Book(
                                book_info[0],
                                book_info[1],
                                book_info[2],
                                book_info[3],
                                book_info[4],
                                book_info[5],
                                book_info[6],
                                book_info[7]
                        ));
                    }
                }
            }
        }

        return books;
    }

    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File usersDirectory = new File("medialab/users/");
        // Check if the directory exists
        if (!usersDirectory.exists() || !usersDirectory.isDirectory()) {
            System.err.println("Users directory not found or is not a directory.");
            return users;
        }
        // Get list of files in the directory
        File[] userFiles = usersDirectory.listFiles();
        // Iterate over the files and load user information
        if (userFiles != null) {
            for (File file : userFiles) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".bin")) {
                        String[] user_info = FileIO.readMultipleStrings(file.getAbsolutePath());
                        users.add(new User(
                                user_info[0],
                                user_info[1],
                                user_info[2],
                                user_info[3],
                                user_info[4],
                                user_info[5]
                        ));
                    }
                }
            }
        }

        return users;
    }

    public List<Admin> loadAdmins() {
        List<Admin> admins = new ArrayList<>();
        File adminsDirectory = new File("medialab/admins/");

        // Check if the directory exists
        if (!adminsDirectory.exists() || !adminsDirectory.isDirectory()) {
            System.err.println("Admins directory not found or is not a directory.");
            return admins;
        }

        // Get list of files in the directory
        File[] adminFiles = adminsDirectory.listFiles();

        // Iterate over the files and load admin information
        if (adminFiles != null) {
            for (File file : adminFiles) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    System.out.println(fileName);
                    System.out.println(file.getAbsolutePath());
                    if (fileName.endsWith(".bin")) {
                        String[] admin_info = FileIO.readMultipleStrings(file.getAbsolutePath());
                        System.out.println(Arrays.toString(admin_info));
                        admins.add(new Admin(
                                admin_info[0],
                                admin_info[1],
                                admin_info[2],
                                admin_info[3],
                                admin_info[4],
                                admin_info[5]
                        ));

                        users.add(new User(
                                admin_info[0],
                                admin_info[1],
                                admin_info[2],
                                admin_info[3],
                                admin_info[4],
                                admin_info[5]
                        ));
                    }
                }
            }
        }

        return admins;
    }

    public List<Category> loadCategories() {
        // Φόρτωση των κατηγοριών από το αρχείο
          List<Category> categories = new ArrayList<>();
            String[] category_names = FileIO.readMultipleStrings("medialab/categories/$$contents.bin");
            if (category_names == null) return null;
            if (category_names[0].isEmpty()) return null;
            for (String category_name : category_names) {
                String[] category_info = FileIO.readMultipleStrings("medialab/categories/" + category_name + ".bin");
                categories.add(new Category(
                        category_name,
                        Arrays.stream(category_info).toList()
                ));
            }
            return categories;
    }

    private List<Loan> loadLoans() {
        List<Loan> loans = new ArrayList<>();
        File loansDirectory = new File("medialab/loans/");
        // Check if the directory exists
        if (!loansDirectory.exists() || !loansDirectory.isDirectory()) {
            System.err.println("Loans directory not found or is not a directory.");
            return loans;
        }
        // Get list of files in the directory
        File[] loanFiles = loansDirectory.listFiles();
        // Iterate over the files and load user information
        if (loanFiles != null) {
            for (File file : loanFiles) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".bin")) {
                        String[] loan_info = FileIO.readMultipleStrings(file.getAbsolutePath());
                        loans.add(new Loan(
                                loan_info[0],
                                loan_info[1],
                                searchBooks(loan_info[2]).get(0),
                                searchUsers(loan_info[3]).get(0)
                        ));
                    }
                }
            }
        }
        return loans;
    }

    public void loadTransactions() {
        // Φόρτωση των συναλλαγών από το αρχείο
    }


    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    public static List<Category> getCategories() {
        return categories;
    }

    public void saveBooks() {
        FileIO.format_directory("medialab/books");
        if (books == null) return;
        for (Book book : books) {
            FileIO.writeMultipleStrings("medialab/books/"+ book.title + ".bin", new String[]{
                    book.title,
                    book.author,
                    book.category,
                    book.isbn,
                    book.publisher,
                    book.year,
                    book.copies,
                    book.rating
            });
        }
    }

    public void saveLoans() {
        FileIO.format_directory("medialab/loans");
        if (loans == null) return;
        for (Loan loan : loans) {
            FileIO.writeMultipleStrings("medialab/loans/"+ loan.id + ".bin", new String[]{
                    loan.id,
                    loan.loanDate,
                    loan.book.title,
                    loan.user.username
            });
        }
    }

    public void saveUsersAndAdmins() {
        // Αποθήκευση των χρηστών στο αρχείο
        FileIO.format_directory("medialab/users");
        FileIO.format_directory("medialab/admins");
        if (users == null) return;
        List<String> admin_usernames = new ArrayList<>();
        for (Admin admin : admins){
            admin_usernames.add(admin.username);
            FileIO.writeMultipleStrings("medialab/admins/" + admin.username + ".bin", new String[]{
                    admin.username,
                    admin.password,
                    admin.firstName,
                    admin.lastName,
                    admin.adt,
                    admin.email
            });
        }
        for (User user : users) {
            if(admin_usernames.contains(user.username)) continue;
            FileIO.writeMultipleStrings("medialab/users/" + user.username + ".bin", new String[]{
                    user.username,
                    user.password,
                    user.firstName,
                    user.lastName,
                    user.adt,
                    user.email
            });
        }
    }

    public void saveCategories() {
        if (categories == null) return;
        // Αποθήκευση των κατηγοριών στο αρχείο
        for (Category category : categories) {
            FileIO.writeMultipleStrings("medialab/categories/" + category.name + ".bin", category.ISBNs.toArray(new String[0]));
        }
    }

    public void saveTransactions() {
        // Αποθήκευση των συναλλαγών στο αρχείο

    }

    public boolean userExists(String username) {
        if(users == null) return false;
        for (User user : users) {
            if (user.username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean bookExists(String title) {
        if(books != null)
            for (Book book : books) {
                if (book.title.equals(title)) {
                    return true;
                }
            }
        return false;
    }

    public void addUser(String username, String password, String firstName, String lastName, String adt, String email) {
        users.add(new User(username, password, firstName, lastName, adt, email));
    }

    public void addBook(Book book) {
        if(books==null) books = new ArrayList<>();
        books.add(new Book(book.title, book.author, book.isbn, "", book.year, book.publisher, book.copies, "0"));
    }

    public boolean removeBook(String title) {
        if (books != null)
            for (Book book : books) {
                if (book.title.equals(title)) {
                    books.remove(book);
                    return true;
                }
            }
        return false;
    }

    public boolean removeUser(String username) {
            if (users != null) {
                for (Admin admin : admins) {
                    if (admin.username.equals(username)) {
                        admins.remove(admin);
                    }
                }
                for (User user : users) {
                    if (user.username.equals(username)) {
                        users.remove(user);
                        return true;
                    }
                }
            }
            return false;
        }

    public void editBook(String prev_title, String title, String author, String year, String isbn, String publisher, String copies) {
        if(books != null)
            for (Book book : books) {
                if (book.title.equals(prev_title)) {
                    book.title = title;
                    book.author = author;
                    book.category = null;
                    book.year = year;
                    book.isbn = isbn;
                    book.publisher = publisher;
                    book.copies = copies;
                    return;
                }
            }
        Banner.showErrorBanner("Error", "Book to edit not found");
    }

    public void editUser(String prev_username, String username, String password, String firstName, String lastName, String adt, String email) {
        if(users != null)
            for (User user : users) {
                if (user.username.equals(prev_username)) {
                    user.username = username;
                    user.password = password;
                    user.firstName = firstName;
                    user.lastName = lastName;
                    user.adt = adt;
                    user.email = email;
                    return;
                }
            }
        Banner.showErrorBanner("Error", "User to edit not found");
    }

    public List<Book> searchBooks(String query) {
        List<Book> results = new ArrayList<>();
        if(books != null)
            for (Book book : books) {
                if (book.title.contains(query) || book.author.contains(query) || book.isbn.contains(query) || book.publisher.contains(query) || book.year.contains(query)) {
                    results.add(book);
                }
            }
        return results;
    }

    public List<User> searchUsers(String query) {
        List<User> results = new ArrayList<>();
        if(users != null)
            for (User user : users) {
                if (user.username.contains(query) || user.firstName.contains(query) || user.lastName.contains(query) || user.adt.contains(query) || user.email.contains(query)) {
                    results.add(user);
                }
            }
        return results;
    }

    public List<Loan> getUsersLoans(String username) {
        List<Loan> results = new ArrayList<>();
        if(loans != null)
            for (Loan loan : loans) {
                if ( loan.user.username.equals(username)) {
                    results.add(loan);
                }
            }
        return results;
    }

    public List<Book> sortBooks(String criteria, String order) {
        if(books == null) return null;
        List<Book> sortedBooks = new ArrayList<>(books);
        if (criteria.equals("Title")) {
            sortedBooks.sort((book1, book2) -> book1.title.compareTo(book2.title));
        } else if (criteria.equals("Author")) {
            sortedBooks.sort((book1, book2) -> book1.author.compareTo(book2.author));
        } else if (criteria.equals("Year")) {
            sortedBooks.sort((book1, book2) -> book1.year.compareTo(book2.year));
        } else if (criteria.equals("ISBN")) {
            sortedBooks.sort((book1, book2) -> book1.isbn.compareTo(book2.isbn));
        } else if (criteria.equals("Publisher")) {
            sortedBooks.sort((book1, book2) -> book1.publisher.compareTo(book2.publisher));
        } else if (criteria.equals("Copies")) {
            sortedBooks.sort((book1, book2) -> book1.copies.compareTo(book2.copies));
        } else if (criteria.equals("Rating")) {
            sortedBooks.sort((book1, book2) -> book1.rating.compareTo(book2.rating));
        }
        if (order.equals("descending")) {
            List<Book> reversedBooks = new ArrayList<>();
            for (int i = sortedBooks.size() - 1; i >= 0; i--) {
                reversedBooks.add(sortedBooks.get(i));
            }
            return reversedBooks;
        }
        return sortedBooks;
    }

    public List<User> sortUsers(String criteria, String order) {
        if(users == null) return null;
        List<User> sortedUsers = new ArrayList<>(users);
        if (criteria.equals("Username")) {
            sortedUsers.sort((user1, user2) -> user1.username.compareTo(user2.username));
        } else if (criteria.equals("First Name")) {
            sortedUsers.sort((user1, user2) -> user1.firstName.compareTo(user2.firstName));
        } else if (criteria.equals("Last Name")) {
            sortedUsers.sort((user1, user2) -> user1.lastName.compareTo(user2.lastName));
        } else if (criteria.equals("ADT")) {
            sortedUsers.sort((user1, user2) -> user1.adt.compareTo(user2.adt));
        } else if (criteria.equals("Email")) {
            sortedUsers.sort((user1, user2) -> user1.email.compareTo(user2.email));
        }
        if (order.equals("descending")) {
            List<User> reversedUsers = new ArrayList<>();
            for (int i = sortedUsers.size() - 1; i >= 0; i--) {
                reversedUsers.add(sortedUsers.get(i));
            }
            return reversedUsers;
        }
        return sortedUsers;
    }

    public boolean isAdmin(){
        if(loggedUser==null) return false;
        for (Admin admin : admins) {
            if (admin.username.equals(loggedUser.username)) {
                return true;
            }
        }
        return false;
    }

    public String getNewLoanId() {
        if(loans.size() == 0) return "1";
        //get last item's id and increment it by 1
        return String.valueOf(Integer.parseInt(loans.get(loans.size()-1).id) + 1);
    }
}
