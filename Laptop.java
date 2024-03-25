import java.util.*;

public class Laptop {
    private String brand;
    private int ram;
    private int storage;
    private String os;
    private String color;

    public Laptop(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    public static void filterNotebooks(Set<Laptop> notebooks, Map<String, Object> filterCriteria) {
        for (Laptop notebook : notebooks) {
            boolean passFilter = true;
            for (Map.Entry<String, Object> entry : filterCriteria.entrySet()) {
                String criterion = entry.getKey();
                Object value = entry.getValue();

                switch (criterion) {
                    case "ОЗУ":
                        if (notebook.ram < (int) value) {
                            passFilter = false;
                        }
                        break;
                    case "Объем ЖД":
                        if (notebook.storage < (int) value) {
                            passFilter = false;
                        }
                        break;
                    case "Операционная система":
                        if (!notebook.os.equals(value)) {
                            passFilter = false;
                        }
                        break;
                    case "Цвет ":
                        if (!notebook.color.equals(value)) {
                            passFilter = false;
                        }
                        break;
                }
            }
            if (passFilter) {
                System.out.println(notebook.brand);
            }
        }
    }

    public static void main(String[] args) {

        Set<Laptop> notebooks = new HashSet<>();
        notebooks.add(new Laptop("HP", 8, 256, "Windows", "Серебристый"));
        notebooks.add(new Laptop("Dell", 16, 512, "Linux", "Черный"));
        notebooks.add(new Laptop("Lenovo", 12, 128, "Windows", "Белый"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите цифру интересуещего вас фильра:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        Map<String, Object> filterCriteria = new HashMap<>();
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Введите минимальный размер ОЗУ:");
                int minRam = scanner.nextInt();
                filterCriteria.put("ram", minRam);
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД:");
                int minStorage = scanner.nextInt();
                filterCriteria.put("storage", minStorage);
                break;
            case 3:
                System.out.println("Введите операционную систему:");
                String os = scanner.next();
                filterCriteria.put("os", os);
                break;
            case 4:
                System.out.println("Введите цвет:");
                String color = scanner.next();
                filterCriteria.put("color", color);
                break;
        }

        System.out.println("Найденные варианты по вашему запросу:");
        filterNotebooks(notebooks, filterCriteria);
        scanner.close();
    }
}