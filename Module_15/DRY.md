## Принцип DRY (Don't Repeat Yourself)

---

### Нарушение DRY

В данном примере для кормления разных животных используются несколько методов, почти идентичных по содержанию. Это приводит к дублированию кода, которое в дальнейшем затруднит его изменение.

```java
class AnimalFeederViolation {
    // Отдельные методы для каждого типа животного, дублирующие логику вывода
    public void feedDog(String dogName) {
        System.out.println("Feeding dog: " + dogName);
    }

    public void feedCat(String catName) {
        System.out.println("Feeding cat: " + catName);
    }

    public void feedBird(String birdName) {
        System.out.println("Feeding bird: " + birdName);
    }
}

public class DRYViolationExample {
    public static void main(String[] args) {
        AnimalFeederViolation feeder = new AnimalFeederViolation();
        feeder.feedDog("Buddy");
        feeder.feedCat("Kitty");
        feeder.feedBird("Tweety");
    }
}
```

### Соблюдение DRY

Исправленный вариант использует один метод, который принимает тип животного и его имя. Таким образом, дублирование кода устраняется: одна реализация решает все случаи.

```java
class AnimalFeederDRY {
    // Универсальный метод для кормления любого животного
    public void feed(String animalType, String animalName) {
        System.out.println("Feeding " + animalType + ": " + animalName);
    }
}

public class DRYCorrectExample {
    public static void main(String[] args) {
        AnimalFeederDRY feeder = new AnimalFeederDRY();
        feeder.feed("dog", "Buddy");
        feeder.feed("cat", "Kitty");
        feeder.feed("bird", "Tweety");
    }
}
```

_Объяснение:_  
Принцип DRY призывает избегать повторения кода. В исправленном примере одна универсальная функция заменяет три почти одинаковых метода, что облегчает сопровождение и масштабирование кода.