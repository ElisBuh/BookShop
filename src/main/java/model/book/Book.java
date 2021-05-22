package model.book;

public class Book {
    private int id;
    private String nameBook;
    private String nameAuthor;
    private StatusBook statusBook;
    private int request;

    public Book(int id, String nameBook, String nameAuthor, StatusBook statusBook) {
        this.nameBook = nameBook;
        this.nameAuthor = nameAuthor;
        this.id = id;
        this.statusBook = statusBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public int getId() {
        return id;
    }

    public void setStatusBook(StatusBook statusBook) {
        this.statusBook = statusBook;
    }

    public StatusBook getStatusBook() {
        return statusBook;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", nameBook='" + nameBook + '\'' +
                ", nameAuthor='" + nameAuthor + '\'' +
                ", statusBook=" + statusBook +
                '}';
    }
}
