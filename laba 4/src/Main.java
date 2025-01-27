import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    // Метод для добавления трехмерной точки в коробку
    public static <T> void putPointInBox(Box<T> box, T item) {
        if (box.isFull()) {
            System.out.println("Коробка уже заполнена!");
        } else {
            box.put(item);
            System.out.println("\nТочка добавлена в коробку: " + item);
        }
    }
    public static <T, P> List<P> universal(List<T> inputList, Function<T, P> f) {
        List<P> resultList = new ArrayList<>();
        for (T element : inputList) {
            resultList.add(f.apply(element));
        }
        return resultList;
    }
    public static <T> List<T> filter(List<T> list, Predicate<T> predict) {
        List<T> filteredList = new ArrayList<>();
        for (T i : list) {
            if (predict.test(i)) {
                filteredList.add(i );
            }
        }
        return filteredList;
    }
    public static <T, R> R reduce(List<T> list, Reducer<R, T> reducer, R initialValue) {
        R result = initialValue;
        for (T element : list) {
            result = reducer.reduce(result, element);
        }
        return result;
    }

    public static <T, P> P collection(List<T> list, Function<List<T>, P> collectionCreator, Function<List<T>, P> listProcessor) {
        return listProcessor.apply(list);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1.1
        Box<Integer> box = new Box<>();
        Integer userInput = null;
        while (userInput == null) {
            System.out.print("1.1 Введите целое число: ");
            try {
                userInput = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода! Пожалуйста, введите целое число.");
                scanner.next();
            }
        }

        // Помещаем число в коробку
        try {
            box.put(userInput);
            System.out.println("Число успешно помещено в коробку: " + box);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        Integer extractedNumber = box.take();
        if (extractedNumber != null) {
            System.out.println("Извлеченное число: " + extractedNumber);
        } else {
            System.out.println("Коробка пустая!");
        }

        if (box.isFull()) {
            System.out.println("Коробка все еще заполнена.");
        } else {
            System.out.println("Коробка пуста.");
        }


        // 1.2
        System.out.println("1.2:");
        Storage<Integer> numberStorage1 = new Storage<>(null);
        System.out.println(numberStorage1);
        System.out.println("Значение: " + numberStorage1.getValueOrDefault(0));

        Storage<Integer> numberStorage2 = new Storage<>(99);
        System.out.println(numberStorage2);
        System.out.println("Значение: " + numberStorage2.getValueOrDefault(-1));

        Storage<String> stringStorage1 = new Storage<>(null);
        System.out.println(stringStorage1);
        System.out.println("Значение: " + stringStorage1.getValueOrDefault("default"));

        Storage<String> stringStorage2 = new Storage<>("hello");
        System.out.println(stringStorage2);
        System.out.println("Значение: " + stringStorage2.getValueOrDefault("hello world"));


          //2.3
        // Создаем коробку для трехмерной точки
        Box<Point3D> pointBox = new Box<>();

        // Создаем точку
        Point3D point = new Point3D(1.0, 2.0, 3.0);

        // Добавляем точку в коробку
        putPointInBox(pointBox, point);

        // Попробуем добавить еще раз
        putPointInBox(pointBox, new Point3D(4.0, 5.0, 6.0));

        // Извлекаем точку
        Point3D takenPoint = pointBox.take();
        System.out.println("Извлеченная точка: " + takenPoint);

        // Коробка для String
        Box<String> stringBox = new Box<>();
        String text = "Hello, world!";
        putPointInBox(stringBox, text);  // Добавление строки в коробку

        // Попытка добавить строку снова
        putPointInBox(stringBox, "New string");  // Коробка уже полна

        // Извлечение строки из коробки
        String extractedText1 = stringBox.take();
        System.out.println("Извлеченная строка: " + extractedText1);
        //3.1
        System.out.println("3.1 Выберите действие:");
        System.out.println("1 - Преобразовать строки в их длины");
        System.out.println("2 - Сделать отрицательные числа положительными");
        System.out.println("3 - Найти максимальные элементы массивов");
        System.out.print("Ваш выбор: ");

        try {
            int choice2 = scanner.nextInt();
            scanner.nextLine();

            switch (choice2) {
                case 1 -> {
                    List<String> stringList = Arrays.asList("qwerty", "asdfg", "zx");
                    System.out.println("Исходный список строк: " + stringList);

                    List<Integer> lengths = universal(stringList, String::length);
                    System.out.println("Длины строк: " + lengths);
                }
                case 2 -> {
                    List<Integer> numbers = Arrays.asList(1, -3, 7);
                    System.out.println("Исходный список чисел: " + numbers);

                    List<Integer> positives = universal(numbers, Math::abs);
                    System.out.println("Положительные числа: " + positives);
                }
                case 3 -> {
                    List<int[]> arrays = List.of(
                            new int[]{1, 2, 5},
                            new int[]{-3, 3, 3},
                            new int[]{10, 30, 333}
                    );
                    System.out.println("Исходные массивы: ");
                    arrays.forEach(array -> System.out.println(Arrays.toString(array)));

                    List<Integer> maxValues = universal(arrays, array -> Arrays.stream(array).max().orElseThrow());
                    System.out.println("Максимальные значения: " + maxValues);
                }
                default -> System.out.println("Неверный выбор.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода! Введите корректное значение.");
        }
        //3.2
        System.out.println("3.2 Выберите пример для тестирования:");
        System.out.println("1 - Фильтрация строк длиной меньше 3 символов");
        System.out.println("2 - Фильтрация положительных чисел");
        System.out.println("3 - Фильтрация массивов с положительными элементами");


        int choice3 =scanner.nextInt();

        switch (choice3) {
            case 1 -> {
                List<String> stringList = Arrays.asList("qwerty", "asdfg", "zx");
                List<String> filteredStrings = filter(stringList, str -> str.length() >= 3);
                System.out.println("Исходный список строк: " + stringList);
                System.out.println("Отфильтрованный список: " + filteredStrings);
            }
            case 2 -> {
                // Пример 2: числа -> удаляем положительные
                List<Integer> numbers = Arrays.asList(1, -3, 7);
                List<Integer> filteredNumbers = filter(numbers, num -> num <= 0);
                System.out.println("Исходный список чисел: " + numbers);
                System.out.println("Отфильтрованный список: " + filteredNumbers);
            }
            case 3 -> {
                List<int[]> arrays = List.of(
                        new int[]{1, 2, 3},
                        new int[]{-1, -2, -33},
                        new int[]{44, -77, -15},
                        new int[]{1330, -222, -322}
                );
                List<int[]> filteredArrays = filter(arrays, array -> Arrays.stream(array).allMatch(num -> num <= 0));
                System.out.println("Исходный список массивов: " + Arrays.deepToString(arrays.toArray()));
                System.out.println("Отфильтрованный список: " + Arrays.deepToString(filteredArrays.toArray()));
            }
            default -> System.out.println("Неверный выбор!");
        }
        //3.3
        List<String> strings = List.of("qwerty", "asdfg", "zx");
        String qq = reduce(strings, (a, b) -> a + b, "");
        System.out.println("3.3 Объединённая строка: " + qq);


        List<Integer> numbers = List.of(1, -3, 7);
        int sum = reduce(numbers, Integer::sum, 0);
        System.out.println("Сумма чисел: " + sum);


        List<List<Integer>> Lists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(12,-6)
        );
        int totalSize = reduce(Lists, (a, b) -> a + b.size(), 0);
        System.out.println("Общее количество элементов: " + totalSize);
        //3.4
        List<Integer> numbers1 = List.of(1, -3, 7);
        Map<Boolean, List<Integer>> partitionedNumbers = collection(numbers1,
                (list) -> new HashMap<>(),
                (list) -> list.stream().collect(Collectors.partitioningBy(num -> num > 0))
        );
        System.out.println("3.4 Положительные и отрицательные числа: " + partitionedNumbers);

        List<String> strings1 = List.of("qwerty", "asdfg", "zx", "qw");
        Map<Integer, List<String>> groupedByLength = collection(strings1,
                (list) -> new HashMap<>(),
                (list) -> list.stream().collect(Collectors.groupingBy(String::length))
        );
        System.out.println("Группировка строк по длине: " + groupedByLength);

        List<String> uniqueStrings = List.of("qwerty", "asdfg", "qwerty", "qw");
        Set<String> uniqueSet = collection(uniqueStrings,
                (list) -> new HashSet<>(),
                (list) -> new HashSet<>(list)
        );
        System.out.println("Уникальные строки: " + uniqueSet);
        scanner.close();
    }
}