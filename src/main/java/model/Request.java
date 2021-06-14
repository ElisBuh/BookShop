package model;

public class Request {
    private Integer id;
    private Book book;
    private Integer countRequest;


    public Request(Integer id, Book book) {
        this.book = book;
        this.countRequest = 1;
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getCountRequest() {
        return countRequest;
    }

    public void setCountRequest(Integer countRequest) {
        this.countRequest = countRequest;
    }

    @Override
    public String toString() {
        return "Запрос " +
                "id:" + id +
                ", Книга " + book +
                ", Количество запросов: " + countRequest;
    }
}
