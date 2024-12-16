public class Storage<T> {
    private final T value;

    // Конструктор, который принимает объект
    public Storage(T value) {
        this.value = value;
    }

    // Метод для получения значения или значения по умолчанию
    public T getValueOrDefault(T defaultValue) {
        return (value != null) ? value : defaultValue;
    }

    @Override
    public String toString() {
        return "Хранилище содержит: " + (value != null ? value.toString() : "null");
    }
}