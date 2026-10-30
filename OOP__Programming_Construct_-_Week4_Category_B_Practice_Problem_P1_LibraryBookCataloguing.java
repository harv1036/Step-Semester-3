class LibraryBookCataloguing {

    public static void main(String[] args) {
        String[] titles = {"Clean Code", "Untitled Draft", "1984", "Notes"};
        String[] isbns = {"978-0132350884", "", "9780451524935", ""};

        for (int i = 0; i < titles.length; i++) {
            LibraryBook book;
            if (isbns[i].isEmpty()) {
                book = new LibraryBook(titles[i]);
            } else {
                book = new LibraryBook(titles[i], isbns[i]);
            }
            System.out.println(book.title + " | " + book.isbn + " | Catalogued: " + book.catalogued);
        }
    }
}

class LibraryBook {
    String title;
    String isbn;
    boolean catalogued;

    public LibraryBook(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
        this.catalogued = true;
    }

    public LibraryBook(String title) {
        this(title, "PENDING");
    }
}
