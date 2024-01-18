import java.util.Scanner;

public class Reader {
    static Scanner scanner = new Scanner(System.in);

    public static String readField(String fieldName) {
        System.out.println("Enter " + fieldName + ": ");
        return scanner.nextLine();
    }

    public static int readFieldInt(String fieldName) {
        System.out.println("Enter " + fieldName + ": ");
        return scanner.nextInt();
    }

    public static double readFieldDouble(String fieldName) {
        System.out.println("Enter " + fieldName + ": ");
        return scanner.nextDouble();
    }
}
