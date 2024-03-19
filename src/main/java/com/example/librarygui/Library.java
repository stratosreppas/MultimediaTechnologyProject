package com.example.librarygui;
import com.example.librarygui.interfaces.Banner;
import com.example.librarygui.interfaces.FileIO;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Library {

    public List<Book> books;
    public List<User> users;
    public List<Admin> admins;
    public List<Loan> loans;
    public List<Category> categories;
    public List<Rating> ratings;
    public User loggedUser;

    public Library() {
        this.books = loadBooks();
        this.users = loadUsers();
        this.admins = loadAdmins();
        this.categories = loadCategories();
        this.loans = loadLoans();
        this.ratings = loadRatings();
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
                                book_info[5]
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
        List<Category> categories = new ArrayList<>();
        File categoryDirectory = new File("medialab/categories/");
        // Check if the directory exists
        if (!categoryDirectory.exists() || !categoryDirectory.isDirectory()) {
            System.err.println("Category directory not found or is not a directory.");
            return categories;
        }
        // Get list of files in the directory
        File[] categoryFiles = categoryDirectory.listFiles();
        // Iterate over the files and load user information
        if (categoryFiles != null) {
            for (File file : categoryFiles) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".bin")) {
                        String[] category_info = FileIO.readMultipleStrings(file.getAbsolutePath());
                        if(category_info!= null)
                            if(category_info[0]!="")
                                categories.add(new Category(
                                fileName.substring(0, fileName.length() - 4),
                                        new ArrayList<>(Arrays.asList(category_info))
                                ));
                        else
                                categories.add(new Category(
                                        fileName.substring(0, fileName.length() - 4)
                                ));
                    }
                }
            }
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

    public List<Rating> loadRatings() {
        List<Rating> ratings = new ArrayList<>();
        File ratingsDirectory = new File("medialab/ratings/");
        // Check if the directory exists
        if (!ratingsDirectory.exists() || !ratingsDirectory.isDirectory()) {
            System.err.println("Ratings directory not found or is not a directory.");
            return ratings;
        }
        // Get list of files in the directory
        File[] ratingFiles = ratingsDirectory.listFiles();
        // Iterate over the files and load user information
        if (ratingFiles != null) {
            for (File file : ratingFiles) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".bin")) {
                        String[] rating_info = FileIO.readMultipleStrings(file.getAbsolutePath());
                        ratings.add(new Rating(
                                fileName.substring(0, fileName.length() - 4),
                                rating_info[0],
                                rating_info[1],
                                searchBooks(rating_info[2]).get(0),
                                searchUsers(rating_info[3]).get(0)
                        ));
                    }
                }
            }
        }
        return ratings;
    }

    public void saveRatings() {
        FileIO.format_directory("medialab/ratings");
        if (ratings == null) return;
        for (Rating rating : ratings) {
            FileIO.writeMultipleStrings("medialab/ratings/"+ rating.id + ".bin", new String[]{
                    rating.rating,
                    rating.comment,
                    rating.book.isbn,
                    rating.user.username
            });
        }
    }

    public void saveBooks() {
        FileIO.format_directory("medialab/books");
        if (books == null) return;
        for (Book book : books) {
            FileIO.writeMultipleStrings("medialab/books/"+ book.title + ".bin", new String[]{
                    book.title,
                    book.author,
                    book.isbn,
                    book.publisher,
                    book.year,
                    book.copies,
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
        FileIO.format_directory("medialab/categories");
        if (categories == null) return;
        // Αποθήκευση των κατηγοριών στο αρχείο
        for (Category category : categories) {
            FileIO.writeMultipleStrings("medialab/categories/" + category.name + ".bin", category.isbns.toArray(new String[0]));
        }
    }

    public boolean userExists(String query) {
        if(users == null) return false;
        for (User user : users) {
            if (user.username.equals(query) || user.email.equals(query) || user.adt.equals(query)) {
                return true;
            }
        }
        return false;
    }

    public boolean bookExists(String isbn) {
        if(books != null)
            for (Book book : books) {
                if (book.isbn.equals(isbn)) {
                    return true;
                }
            }
        return false;
    }

    public boolean categoryExists(String name) {
        if(categories != null)
            for (Category category : categories) {
                if (category.name.equals(name)) {
                    return true;
                }
            }
        return false;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addBook(Book book) {
        if(books==null) books = new ArrayList<>();
        books.add(book);
    }

    public void addCategory(String name) {
        if(categories==null) categories = new ArrayList<>();
        categories.add(new Category(name));
    }

    public void addLoan(Loan loan) {
        if(loans==null) loans = new ArrayList<>();
        loans.add(loan);
    }

    public boolean removeBook(Book book) {
        if (books != null) {
            books.remove(book);

            Iterator<Loan> iterator = loans.iterator();
            while (iterator.hasNext()) {
                Loan loan = iterator.next();
                if (loan.book.isbn.equals(book.isbn)) {
                    iterator.remove(); // Use iterator to safely remove the loan
                }
            }

            return true;
        }
        return false;
    }


    public boolean removeUser(User user) {
            if (users != null) {

                users.remove(user);

                for (Admin a : admins) {
                    if (a.username.equals(user.username)) {
                        admins.remove(a);
                    }
                }
                for (Loan loan : loans) {
                    if (loan.user.username.equals(user.username)) {
                        removeLoan(loan);
                    }
                }
                return true;
            }
            return false;
        }

    public boolean removeCategory(Category category) {
        if (categories != null) {
            categories.remove(category);
            // also delete all the books associated with this category
            if (books != null) {
                Iterator<Book> iterator = books.iterator();
                while (iterator.hasNext()) {
                    Book book = iterator.next();
                    if (category.isbns.contains(book.isbn)) {
                        iterator.remove(); // Use iterator to safely remove the book
                    }
                }
            }
            return true;
        }
        return false;
    }


        public boolean removeLoan(Loan loan) {
            if (loans != null) {
                loans.remove(loan);
                loan.book.addCopy();
                editBook(loan.book.isbn, loan.book);
                return true;
            }
            return false;
        }

    public void editBook(String prev_isbn, Book book) {
        if(books != null)
            for (Book b : books) {
                if (b.isbn.equals(prev_isbn)) {
                    b.title = book.title;
                    b.author = book.author;
                    b.isbn = book.isbn;
                    b.publisher = book.publisher;
                    b.year = book.year;
                    b.copies = book.copies;
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

    public boolean editCategory(String prev_name, String name) {
        if(categories != null)
            for (Category category : categories) {
                if (category.name.equals(prev_name)) {
                    category.name = name;
                    return true;
                }
            }
        Banner.showErrorBanner("Error", "Category to edit not found");
        return false;
    }

    public List<Book> searchBooks(String query) {
        List<Book> results = new ArrayList<>();
        if(books != null)
            for (Book book : books) {
                if (book.title.contains(query) || book.author.contains(query) || book.isbn.contains(query) || book.year.contains(query)) {
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

    public void addBookToCategory(String category, String isbn) {
        if(categories != null)
            for (Category c : categories) {
                if (c.name.equals(category)) {
                    c.addISBN(isbn);
                    return;
                }
            }
    }

    public void removeBookFromCategory(String category, String isbn) {
        if(categories != null)
            for (Category c : categories) {
                if (c.name.equals(category)) {
                    c.isbns.remove(isbn);
                    return;
                }
            }
    }

    public String getBooksCategory(Book book) {
        // Searches if the book's isbn exists in the List in this category
        // If it does, return the category name
        // If it doesn't, return null
        for (Category category : categories) {
            if (category.isbns.contains(book.isbn)) {
                return category.name;
            }
        }
        return null;
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
        } else if(criteria.equals("Category")) {
            sortedBooks.sort((book1, book2) -> getBooksCategory(book1).compareTo(getBooksCategory(book2)));
        } else if(criteria.equals("Rating")) {
            sortedBooks.sort((book1, book2) -> Double.compare(getBookRatingAverage(book2), getBookRatingAverage(book1)));
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

    public String getNewRatingId() {
        if(ratings.size() == 0) return "1";
        //get last item's id and increment it by 1
        return String.valueOf(Integer.parseInt(ratings.get(ratings.size()-1).id) + 1);
    }

    public void addRating(String rating, String review, Book book, User user) {
        if(ratings == null) ratings = new ArrayList<>();
        ratings.add(new Rating(getNewRatingId(), rating, review, book, user));
    }

    public void editRating(String rating, String review, Book book, User user) {
        if(ratings != null)
            for (Rating r : ratings) {
                if (r.book.isbn.equals(book.isbn) && r.user.username.equals(user.username)) {
                    r.rating = rating;
                    r.comment = review;
                    return;
                }
            }
    }

    public Rating getRating(Book book, User user) {
        if(ratings != null)
            for (Rating r : ratings) {
                if (r.book.isbn.equals(book.isbn) && r.user.username.equals(user.username)) {
                    return r;
                }
            }
        return null;
    }

    public void removeRating(Rating rating) {
        if(ratings != null) ratings.remove(rating);
    }

    public int getBookRatingCount(Book book) {
        int count = 0;
        if(ratings != null)
            for (Rating r : ratings) {
                if (r.book.isbn.equals(book.isbn)) {
                    count++;
                }
            }
        return count;
    }

    public double getBookRatingAverage(Book book) {
        double sum = 0;
        int count = 0;
        if(ratings != null)
            for (Rating r : ratings) {
                if (r.book.isbn.equals(book.isbn)) {
                    sum += Double.parseDouble(r.rating);
                    count++;
                }
            }
        if (count == 0) return 0;
        return sum / count;
    }


}
