import java.util.Scanner;

public class Main {

    private static final String URL = "https://api.adviceslip.com/";

    public static void main(String[] args) {
        displayMenu();
    }

    public static void displayMenu() {

        boolean contineuing = true;

        while (contineuing) {
            System.out.println("AdviceBook");
            System.out.println("Wybierz jedną z opcji");
            System.out.println("1. Wylosuj cytat");
            System.out.println("2. Wyszukaj cytat");
            System.out.println("3. Moje cytaty");    // eksport cytatów, wyświetlanie, usuwanie
            System.out.println("0. Zakończ");

            Scanner scanner = new Scanner(System.in);
            final int nextInt = scanner.nextInt();

            switch (nextInt) {
                case 0: {
                    contineuing = false;
                    break;
                }
                case 1: {
                    getRandomAdvice();
                    break;
                }
                case 2: {
                    System.out.println("w toku");
                    break;
                }
                case 3: {
                    System.out.println("w toku");
                    break;
                }
                default: {
                    System.out.println("Funkcja nie obsługiwana");
                }
            }
        }

    }

    public static void getRandomAdvice() {
        HttpClient httpClient = new HttpClient();
        Slip slip = httpClient.fetch(URL + "advice", SlipResponse.class).getSlip();
        System.out.println(slip.getAdvice());
    }

}
