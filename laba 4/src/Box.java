public class Box<T> {
    private T item;

    public Box() {
        this.item = null;
    }

    public void put(T item) {
        if (this.item != null) {
            throw new IllegalStateException("Коробка уже заполнена!");
        }
        this.item = item;
    }

    public T take() {
        T tempItem = this.item;
        this.item = null;
        return tempItem;
    }

    public boolean isFull() {          // Метод для проверки на заполненность
        return this.item != null;
    }

    @Override
    public String toString() {
        return "Box{" + item + '}';
    }
}