## Принцип KISS (Keep It Simple, Stupid)

---

### Нарушение KISS

В этом примере для простой задачи (печать списка имён животных) используется избыточная логика: лишние проверки, преобразования и разветвлённые условия. Это усложняет код без необходимости.

```java
import java.util.ArrayList;
import java.util.List;

class ComplexAnimalPrinter {
    // Избыточная логика для простой задачи печати списка имен
    public void printAnimalNames(List<String> animalNames) {
        // Проверка на null и пустоту
        if (animalNames != null && animalNames.size() > 0) {
            // Создание дополнительной коллекции, хотя можно использовать исходную
            ArrayList<String> names = new ArrayList<>();
            for (String name : animalNames) {
                // Лишнее создание нового объекта строки
                names.add(new String(name));
            }
            // Избыточное ветвление, которое не добавляет полезной функциональности
            for (int i = 0; i < names.size(); i++) {
                if (i % 2 == 0) {
                    System.out.println("Even-indexed: " + names.get(i));
                } else {
                    System.out.println("Odd-indexed: " + names.get(i));
                }
            }
        } else {
            System.out.println("No animals to print.");
        }
    }
}

public class KISSViolationExample {
    public static void main(String[] args) {
        List<String> animals = List.of("Lion", "Tiger", "Elephant");
        new ComplexAnimalPrinter().printAnimalNames(animals);
    }
}
```

### Соблюдение KISS

В исправленном примере код стал проще и понятнее: выполняется лишь необходимая проверка, затем просто выводятся имена животных без лишних преобразований и условий.

```java
import java.util.List;

class SimpleAnimalPrinter {
    // Простая и понятная реализация печати списка имен животных
    public void printAnimalNames(List<String> animalNames) {
        if (animalNames == null || animalNames.isEmpty()) {
            System.out.println("No animals to print.");
            return;
        }
        for (String name : animalNames) {
            System.out.println(name);
        }
    }
}

public class KISSCorrectExample {
    public static void main(String[] args) {
        List<String> animals = List.of("Lion", "Tiger", "Elephant");
        new SimpleAnimalPrinter().printAnimalNames(animals);
    }
}
```

_Объяснение:_  
Принцип KISS требует, чтобы решение было максимально простым. В исправленном варианте мы убрали избыточные проверки и преобразования, что делает код легче для понимания и поддержки.
