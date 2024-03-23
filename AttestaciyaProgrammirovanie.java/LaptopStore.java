import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Laptop {
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

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class LaptopStore {
    private List<Laptop> laptops;

    public LaptopStore() {
        laptops = new ArrayList<>();
    }

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    public void filterLaptops() {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> criteria = new HashMap<>();
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Введите минимальный объем ОЗУ:");
                criteria.put("ram", scanner.next());
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД:");
                criteria.put("storage", scanner.next());
                break;
            case 3:
                System.out.println("Введите название операционной системы:");
                criteria.put("os", scanner.next());
                break;
            case 4:
                System.out.println("Введите цвет:");
                criteria.put("color", scanner.next());
                break;
            default:
                System.out.println("Неправильный выбор");
                return;
        }

        List<Laptop> filteredLaptops = new ArrayList<>(laptops);
        for (Map.Entry<String, String> entry : criteria.entrySet()) {
            filteredLaptops.removeIf(laptop -> !matchesCriteria(laptop, entry.getKey(), entry.getValue()));
        }

        System.out.println("Ноутбуки, соответствующие критериям:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    private boolean matchesCriteria(Laptop laptop, String criterion, String value) {
        switch (criterion) {
            case "ram":
                return laptop.getRam() >= Integer.parseInt(value);
            case "storage":
                return laptop.getStorage() >= Integer.parseInt(value);
            case "os":
                return laptop.getOs().equalsIgnoreCase(value);
            case "color":
                return laptop.getColor().equalsIgnoreCase(value);
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();
        store.addLaptop(new Laptop("Dell", 16, 512, "Windows", "Black"));
        store.addLaptop(new Laptop("Apple", 8, 256, "macOS", "Silver"));
        store.addLaptop(new Laptop("HP", 16, 1024, "Windows", "Grey"));

        store.filterLaptops();
    }
}