package model;

public enum StatusBook {
    INSTOCK("В Наличии на складе"),
    ABSENT("Нет в наличии на складе");

    private String title;

    StatusBook(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
