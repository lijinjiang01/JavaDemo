import java.util.Scanner;

public class PrintTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        for (int i = 1; i <= s; i++) {
            for (int j = 1; j <= 2 * s - 1; j++) {
                if (j <= (s - i ) || j >= (s + i))
                    System.out.print(" ");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
    }
}
