package com.example.librarygui;
import com.example.librarygui.interfaces.Banner;
import com.example.librarygui.interfaces.FileIO;

import java.io.File;
import java.util.*;

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

    public List<User> getUsers() {
        return users;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Rating> getRatings() {
        return ratings;
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
            FileIO.writeMultipleStrings("medialab/ratings/"+ rating.getId() + ".bin", new String[]{
                    rating.getRating(),
                    rating.getComment(),
                    rating.getBook().getIsbn(),
                    rating.getUser().getUsername()
            });
        }
    }

    public void saveBooks() {
        FileIO.format_directory("medialab/books");
        if (books == null) return;
        for (Book book : books) {
            FileIO.writeMultipleStrings("medialab/books/"+ book.getIsbn() + ".bin", new String[]{
                    book.getTitle(),
                    book.getAuthor(),
                    book.getIsbn(),
                    book.getPublisher(),
                    book.getYear(),
                    book.getCopies(),
            });
        }
    }

    public void saveLoans() {
        FileIO.format_directory("medialab/loans");
        if (loans == null) return;
        for (Loan loan : loans) {
            FileIO.writeMultipleStrings("medialab/loans/"+ loan.getId() + ".bin", new String[]{
                    loan.getId(),
                    loan.getLoanDate(),
                    loan.getBook().getTitle(),
                    loan.getUser().getUsername()
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
            admin_usernames.add(admin.getUsername());
            FileIO.writeMultipleStrings("medialab/admins/" + admin.getUsername() + ".bin", new String[]{
                    admin.getUsername(),
                    admin.getPassword(),
                    admin.getFirstName(),
                    admin.getLastName(),
                    admin.getAdt(),
                    admin.getEmail()
            });
        }
        for (User user : users) {
            if(admin_usernames.contains(user.getUsername())) continue;
            FileIO.writeMultipleStrings("medialab/users/" + user.getUsername() + ".bin", new String[]{
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getAdt(),
                    user.getEmail()
            });
        }
    }

    public void saveCategories() {
        FileIO.format_directory("medialab/categories");
        if (categories == null) return;
        // Αποθήκευση των κατηγοριών στο αρχείο
        for (Category category : categories) {
            FileIO.writeMultipleStrings("medialab/categories/" + category.getName() + ".bin", category.getISBNs().toArray(new String[0]));
        }
    }

    public boolean userExists(String query) {
        if(users == null) return false;
        for (User user : users) {
            if (user.getUsername().equals(query) || user.getEmail().equals(query) || user.getAdt().equals(query)) {
                return true;
            }
        }
        return false;
    }

    public boolean bookExists(String isbn) {
        if(books != null)
            for (Book book : books) {
                if (book.getIsbn().equals(isbn)) {
                    return true;
                }
            }
        return false;
    }

    public boolean categoryExists(String name) {
        if(categories != null)
            for (Category category : categories) {
                if (category.getName().equals(name)) {
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
        if (books != null && bookExists(book.getIsbn())) {


            List<Loan> delete_Loans = new ArrayList<>();
            for (Loan loan : getLoans()) {
                if (loan.getBook().getIsbn().equals(book.getIsbn())) {
                    delete_Loans.add(loan);
                }
            }
            for(Loan l : delete_Loans){
                if(l!=null) removeLoan(l);
            }


            List<Rating> delete_Ratings = new ArrayList<>();
            for (Rating rating : getRatings()) {
                if (rating.getBook().getIsbn().equals(book.getIsbn())) {
                    delete_Ratings.add(rating);
                }
            }
            for(Rating r : delete_Ratings){
                if(r!=null) removeRating(r);
            }


            books.remove(book);
            return true;
        }
        return false;
    }


    public boolean removeUser(User user) {
            if (users != null && userExists(user.getUsername())) {

                for (Admin a : admins) {
                    if (a.getUsername().equals(user.getUsername())) {
                        admins.remove(a);
                    }
                }


                List<Loan> delete_Loans = new ArrayList<>();
                for (Loan loan : getLoans()) {
                    if (loan.getUser().getUsername().equals(user.getUsername())) {
                        delete_Loans.add(loan);
                    }
                }
                for(Loan l : delete_Loans){
                    if(l!=null) removeLoan(l);
                }


                List<Rating> delete_Ratings = new ArrayList<>();
                for (Rating rating : getRatings()) {
                    if (rating.getUser().getUsername().equals(user.getUsername())) {
                        delete_Ratings.add(rating);
                    }
                }
                for(Rating r : delete_Ratings){
                    if(r!=null) removeRating(r);
                }


                users.remove(user);
                return true;
            }
            return false;
        }

    public boolean removeCategory(Category category) {
        if (categories != null) {

            // also delete all the books associated with this category
            List<Book> delete_Books = new ArrayList<>();
            for (Book book : getBooks()) {
                if (getBooksCategory(book)!= null && getBooksCategory(book).equals(category.getName())) {
                    delete_Books.add(book);
                }
            }
            for(Book b : delete_Books){
                if(b!=null) removeBook(b);
            }

            categories.remove(category);
            return true;
        }
        return false;
    }


        public boolean removeLoan(Loan loan) {
            if (loans != null) {
                loans.remove(loan);
                loan.getBook().addCopy();
                editBook(loan.getBook().getIsbn(), loan.getBook());
                return true;
            }
            return false;
        }

        public void removeRating(String id) {
            if(ratings != null)
                for (Rating r : ratings) {
                    if (r.getId().equals(id)) {
                        ratings.remove(r);
                        return;
                    }
                }
        }

    public void editBook(String prev_isbn, Book book) {
        if(books != null)
            for (Book b : books) {
                if (b.getIsbn().equals(prev_isbn)) {
                    b.setTitle(book.getTitle());
                    b.setAuthor(book.getAuthor());
                    b.setIsbn(book.getIsbn());
                    b.setPublisher(book.getPublisher());
                    b.setYear(book.getYear());
                    b.setCopies(book.getCopies());
                    return;
                }
            }
        Banner.showErrorBanner("Error", "Book to edit not found");
    }

    public void editUser(String prev_username, String username, String password, String firstName, String lastName, String adt, String email) {
        if(users != null)
            for (User user : users) {
                if (user.getUsername().equals(prev_username)) {
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setAdt(adt);
                    user.setEmail(email);
                    return;
                }
            }
        Banner.showErrorBanner("Error", "User to edit not found");
    }

    public boolean editCategory(String prev_name, String name) {
        if(categories != null)
            for (Category category : categories) {
                if (category.getName().equals(prev_name)) {
                    category.setName(name);
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
                if (book.getTitle().contains(query) || book.getIsbn().contains(query) || book.getAuthor().contains(query) || book.getYear().contains(query)) {
                    results.add(book);
                }
            }
        return results;
    }

    public List<User> searchUsers(String query) {
        List<User> results = new ArrayList<>();
        if(users != null)
            for (User user : users) {
                if (user.getUsername().contains(query) || user.getAdt().contains(query) || user.getEmail().contains(query) || user.getFirstName().contains(query) || user.getLastName().contains(query)){
                    results.add(user);
                }
            }
        return results;
    }

    public List<Loan> getUsersLoans(String username) {
        List<Loan> results = new ArrayList<>();
        if(loans != null)
            for (Loan loan : loans) {
                if ( loan.getUser().getUsername().equals(username)) {
                    results.add(loan);
                }
            }
        return results;
    }

    public void addBookToCategory(String category, String isbn) {
        if(categories != null)
            for (Category c : categories) {
                if (c.getName().equals(category)) {
                    c.addISBN(isbn);
                    return;
                }
            }
    }

    public void removeBookFromCategory(String category, String isbn) {
        if(categories != null)
            for (Category c : categories) {
                if (c.getName().equals(category)) {
                    c.removeISBN(isbn);
                    return;
                }
            }
    }

    public String getBooksCategory(Book book) {
        // Searches if the book's isbn exists in the List in this category
        // If it does, return the category name
        // If it doesn't, return null
        for (Category category : categories) {
            if (category.getISBNs().contains(book.getIsbn())) {
                return category.getName();
            }
        }
        return null;
    }

    public List<Book> sortBooks(String criteria, String order) {
        if(books == null) return null;
        List<Book> sortedBooks = new ArrayList<>(books);
        if (criteria.equals("Title")) {
            sortedBooks.sort(Comparator.comparing(Book::getTitle));
        } else if(criteria.equals("Author")) {
            sortedBooks.sort(Comparator.comparing(Book::getAuthor));
        } else if(criteria.equals("ISBN")) {
            sortedBooks.sort(Comparator.comparing(Book::getIsbn));
        } else if(criteria.equals("Publisher")) {
            sortedBooks.sort(Comparator.comparing(Book::getPublisher));
        } else if(criteria.equals("Year")) {
            sortedBooks.sort(Comparator.comparing(Book::getYear));
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
            sortedUsers.sort(Comparator.comparing(User::getUsername));
        } else if(criteria.equals("First Name")) {
            sortedUsers.sort(Comparator.comparing(User::getFirstName));
        } else if(criteria.equals("Last Name")) {
            sortedUsers.sort(Comparator.comparing(User::getLastName));
        } else if(criteria.equals("ADT")) {
            sortedUsers.sort(Comparator.comparing(User::getAdt));
        } else if(criteria.equals("Email")) {
            sortedUsers.sort(Comparator.comparing(User::getEmail));
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
            if (admin.getUsername().equals(loggedUser.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public String getNewLoanId() {
        if(loans.size() == 0) return "1";
        //get last item's id and increment it by 1
        return String.valueOf(Integer.parseInt(loans.get(loans.size()-1).getId()) + 1);
    }

    public String getNewRatingId() {
        if(ratings.size() == 0) return "1";
        //get last item's id and increment it by 1
        return String.valueOf(Integer.parseInt(ratings.get(ratings.size()-1).getId()) + 1);
    }

    public void addRating(String rating, String review, Book book, User user) {
        if(ratings == null) ratings = new ArrayList<>();
        ratings.add(new Rating(getNewRatingId(), rating, review, book, user));
    }

    public void editRating(String rating, String comment, Book book, User user) {
        if(ratings != null)
            for (Rating r : ratings) {
                if (r.getBook().getIsbn().equals(book.getIsbn()) && r.getUser().getUsername().equals(user.getUsername())) {
                    r.setRating(rating);
                    r.setComment(comment);
                    return;
                }
            }
    }

    public Rating getRating(Book book, User user) {
        if(ratings != null)
            for (Rating r : ratings) {
                if (r.getBook().getIsbn().equals(book.getIsbn()) && r.getUser().getUsername().equals(user.getUsername())) {
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
                if (r.getBook().getIsbn().equals(book.getIsbn())) {
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
                if (r.getBook().getIsbn().equals(book.getIsbn())) {
                    sum += Double.parseDouble(r.getRating());
                    count++;
                }
            }
        if (count == 0) return 0;
        return sum / count;
    }


}
