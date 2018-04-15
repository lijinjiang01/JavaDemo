public class MultiplicationList {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == i)
                    System.out.print(i + "x" + j + "=" + i * j);
                else
                    System.out.print(i + "x" + j + "=" + i * j+"  ");
            }
            while (i < 9) {
                System.out.println("");
                break;
            }
        }
    }
}
