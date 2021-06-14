package model;

public enum StatusOrder {
    NEW("Новый"),
    COMPLETED("Выполнен"),
    CANCEL("Отменён");

    private String title;

    StatusOrder(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
