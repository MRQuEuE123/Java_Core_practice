# SOLID

---

## 1. Принцип единственной ответственности (SRP)

### Нарушение SRP

В этом примере класс **Animal** сам отвечает за хранение данных, вывод информации и кормление животного. То есть в одном классе собрано несколько обязанностей, что затрудняет расширение кода.

```java
// Нарушение SRP: один класс отвечает сразу за данные, вывод и кормление
class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    // Вывод информации о животном
    public void printInfo() {
        System.out.println("Animal: " + name);
    }

    // Кормление животного
    public void feed() {
        System.out.println("Feeding " + name);
    }
}

public class SRPViolationExample {
    public static void main(String[] args) {
        Animal animal = new Animal("Leo");
        animal.printInfo();
        animal.feed();
    }
}
```

### Соблюдение SRP

Теперь обязанности разделены: класс **Animal** хранит данные, а классы **AnimalPrinter** и **AnimalFeeder** отвечают за вывод информации и кормление соответственно.

```java
// Класс, отвечающий только за данные животного.
class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}

// Класс для вывода информации о животном.
class AnimalPrinter {
    public void printInfo(Animal animal) {
        System.out.println("Animal: " + animal.getName());
    }
}

// Класс для кормления животного.
class AnimalFeeder {
    public void feed(Animal animal) {
        System.out.println("Feeding " + animal.getName());
    }
}

public class SRPCorrectExample {
    public static void main(String[] args) {
        Animal animal = new Animal("Leo");
        AnimalPrinter printer = new AnimalPrinter();
        AnimalFeeder feeder = new AnimalFeeder();
        
        printer.printInfo(animal);
        feeder.feed(animal);
    }
}
```

_Объяснение:_  
Разделение обязанностей упрощает изменение, тестирование и повторное использование каждого компонента.

---

## 2. Принцип открытости/закрытости (OCP)

### Нарушение OCP

Здесь метод **makeSound** определяется через проверку типа животного с помощью условных операторов. При добавлении нового типа придётся изменять этот метод, что нарушает принцип OCP.

```java
class AnimalWithType {
    private String name;
    private String type; // "Dog", "Cat", и т.д.

    public AnimalWithType(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public String getName() {
        return name;
    }
}

class AnimalSoundMaker {
    // Нарушение OCP: при добавлении нового животного нужно менять этот метод.
    public String makeSound(AnimalWithType animal) {
        if (animal.getType().equals("Dog")) {
            return "Bark";
        } else if (animal.getType().equals("Cat")) {
            return "Meow";
        }
        return "";
    }
}

public class OCPViolationExample {
    public static void main(String[] args) {
        AnimalWithType dog = new AnimalWithType("Buddy", "Dog");
        AnimalWithType cat = new AnimalWithType("Kitty", "Cat");
        AnimalSoundMaker soundMaker = new AnimalSoundMaker();
        
        System.out.println(dog.getName() + " says: " + soundMaker.makeSound(dog));
        System.out.println(cat.getName() + " says: " + soundMaker.makeSound(cat));
    }
}
```

### Соблюдение OCP

Используем полиморфизм: базовый абстрактный класс **Animal** с абстрактным методом **makeSound()** и конкретные подклассы, реализующие этот метод.

```java
abstract class AnimalOCP {
    protected String name;
    
    public AnimalOCP(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public abstract String makeSound();
}

class Dog extends AnimalOCP {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public String makeSound() {
        return "Bark";
    }
}

class Cat extends AnimalOCP {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    public String makeSound() {
        return "Meow";
    }
}

public class OCPCorrectExample {
    public static void main(String[] args) {
        AnimalOCP dog = new Dog("Buddy");
        AnimalOCP cat = new Cat("Kitty");
        
        System.out.println(dog.getName() + " says: " + dog.makeSound());
        System.out.println(cat.getName() + " says: " + cat.makeSound());
    }
}
```

_Объяснение:_  
Код открыт для расширения (новые животные добавляются через новые классы), но закрыт для изменения уже существующей логики.

---

## 3. Принцип подстановки Лисков (LSP)

### Нарушение LSP

Класс **Bird** имеет метод **fly()**, и наследник **Penguin** вынужден его переопределять, но выбрасывает исключение, так как пингвины не летают. Это приводит к неожиданному поведению при подстановке.

```java
class Bird {
    public void fly() {
        System.out.println("Flying...");
    }
}

class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins can't fly");
    }
}

public class LSPViolationExample {
    public static void main(String[] args) {
        Bird bird = new Penguin();
        bird.fly();  // Ошибка во время выполнения!
    }
}
```

### Соблюдение LSP

Выделяем поведение полёта в отдельный интерфейс. Те птицы, которые могут летать, реализуют интерфейс **Flyable**, а пингвин – нет.

```java
// Базовый класс для птиц
class BirdLSP {
    private String name;
    
    public BirdLSP(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}

// Интерфейс для тех, кто может летать.
interface Flyable {
    void fly();
}

class Sparrow extends BirdLSP implements Flyable {
    public Sparrow(String name) {
        super(name);
    }
    
    @Override
    public void fly() {
        System.out.println(getName() + " is flying");
    }
}

class PenguinLSP extends BirdLSP {
    public PenguinLSP(String name) {
        super(name);
    }
    // Пингвин не реализует Flyable, так как не умеет летать.
}

public class LSPCorrectExample {
    public static void main(String[] args) {
        Sparrow sparrow = new Sparrow("Jack");
        PenguinLSP penguin = new PenguinLSP("Pingu");
        
        sparrow.fly();
        // У пингвина нет метода fly(), что соответствует его возможностям.
    }
}
```

_Объяснение:_  
Подклассы не нарушают контракт базового класса, так как поведение, специфичное для летающих птиц, выделено в отдельный интерфейс.

---

## 4. Принцип разделения интерфейсов (ISP)

### Нарушение ISP

Интерфейс **AnimalActions** требует реализовать методы, которые не подходят для всех животных (например, метод **fly()** для слона).

```java
interface AnimalActions {
    void fly();
    void swim();
}

class DuckViolation implements AnimalActions {
    @Override
    public void fly() {
        System.out.println("Duck flying");
    }
    
    @Override
    public void swim() {
        System.out.println("Duck swimming");
    }
}

class ElephantViolation implements AnimalActions {
    @Override
    public void fly() {
        // Слон не умеет летать, но метод обязан быть реализован.
        throw new UnsupportedOperationException("Elephants can't fly");
    }
    
    @Override
    public void swim() {
        System.out.println("Elephant swimming");
    }
}

public class ISPViolationExample {
    public static void main(String[] args) {
        AnimalActions duck = new DuckViolation();
        AnimalActions elephant = new ElephantViolation();
        
        duck.fly();
        duck.swim();
        elephant.swim();
        // elephant.fly(); // Выбросит исключение.
    }
}
```

### Соблюдение ISP

Разбиваем большой интерфейс на несколько специализированных. Теперь животные реализуют только те интерфейсы, которые им подходят.

```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class DuckCorrect implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("Duck flying");
    }
    
    @Override
    public void swim() {
        System.out.println("Duck swimming");
    }
}

class ElephantCorrect implements Swimmable {
    @Override
    public void swim() {
        System.out.println("Elephant swimming");
    }
}

public class ISPCorrectExample {
    public static void main(String[] args) {
        Flyable duckFlyer = new DuckCorrect();
        Swimmable duckSwimmer = new DuckCorrect();
        Swimmable elephant = new ElephantCorrect();
        
        duckFlyer.fly();
        duckSwimmer.swim();
        elephant.swim();
    }
}
```

_Объяснение:_  
Каждый класс реализует только те методы, которые действительно нужны, что уменьшает количество ненужного кода и снижает связность.

---

## 5. Принцип инверсии зависимостей (DIP)

### Нарушение DIP

Класс **Zoo** напрямую создаёт конкретные объекты животных, что затрудняет замену или тестирование компонентов.

```java
abstract class AnimalDIP {
    protected String name;
    
    public AnimalDIP(String name) {
        this.name = name;
    }
    
    public abstract String makeSound();
}

class DogDIP extends AnimalDIP {
    public DogDIP(String name) {
        super(name);
    }
    
    @Override
    public String makeSound() {
        return "Bark";
    }
}

class CatDIP extends AnimalDIP {
    public CatDIP(String name) {
        super(name);
    }
    
    @Override
    public String makeSound() {
        return "Meow";
    }
}

class ZooDIPViolation {
    // Нарушение: создание конкретных объектов внутри класса.
    public void addAnimals() {
        AnimalDIP dog = new DogDIP("Buddy");
        AnimalDIP cat = new CatDIP("Kitty");
        
        System.out.println(dog.makeSound());
        System.out.println(cat.makeSound());
    }
    
    public static void main(String[] args) {
        new ZooDIPViolation().addAnimals();
    }
}
```

### Соблюдение DIP

Класс **Zoo** зависит от абстракции – интерфейса **AnimalService**. Конкретные реализации (например, **AnimalServiceImpl**) передаются через конструктор (внедрение зависимости).

```java
interface AnimalService {
    void makeAnimalSound();
}

class AnimalServiceImpl implements AnimalService {
    private AnimalDIP animal;
    
    public AnimalServiceImpl(AnimalDIP animal) {
        this.animal = animal;
    }
    
    @Override
    public void makeAnimalSound() {
        System.out.println(animal.name + " says " + animal.makeSound());
    }
}

class ZooDIPCorrect {
    private List<AnimalService> animalServices;
    
    // Зависимость передаётся через конструктор
    public ZooDIPCorrect(List<AnimalService> animalServices) {
        this.animalServices = animalServices;
    }
    
    public void letAnimalsSound() {
        for (AnimalService service : animalServices) {
            service.makeAnimalSound();
        }
    }
    
    public static void main(String[] args) {
        List<AnimalService> services = new ArrayList<>();
        services.add(new AnimalServiceImpl(new DogDIP("Buddy")));
        services.add(new AnimalServiceImpl(new CatDIP("Kitty")));
        
        ZooDIPCorrect zoo = new ZooDIPCorrect(services);
        zoo.letAnimalsSound();
    }
}
```

_Объяснение:_  
Класс **ZooDIPCorrect** зависит от абстракции (интерфейса **AnimalService**), а конкретные реализации передаются извне. Это облегчает замену компонентов и тестирование.
