import java.util.Scanner;

public class Reader {
    static Scanner scanner = new Scanner(System.in);

    public static String readString(String fieldName) {
        System.out.println("Enter " + fieldName + ": ");
        return scanner.nextLine();
    }

    public static int readInt(String fieldName) {
        System.out.println("Enter " + fieldName + ": ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            throw new RuntimeException("Invalid Entry...\n");
        }
    }

    public static double readDouble(String fieldName) {
        System.out.println("Enter " + fieldName + ": ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            throw new RuntimeException("Invalid Entry...\n");
        }
    }

    public static int selectOption() {
        System.out.println("Select Option: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            throw new RuntimeException("Invalid Choice...\n");
        }

    }
}
