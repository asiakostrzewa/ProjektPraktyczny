import database.Slip;
import http.SlipDto;

import java.util.Scanner;

public class Menu {
    private final AdviceService adviceService;

    public Menu(AdviceService adviceService){
        this.adviceService = adviceService;
    }

    public void displayMenu() {

        boolean contineuing = true;

        while (contineuing) {
            System.out.println();
            System.out.println("Welcome in Advice book");
            System.out.println("Wybierz jedną z opcji");
            System.out.println("1. Wylosuj cytat");
            System.out.println("2. Wyszukaj cytat");
            System.out.println("3. Moje cytaty");    // TODO eksport cytatów, wyświetlanie, usuwanie
            System.out.println("0. Zakończ");

            int option = -1; //zabezpieczenie przed wpisaniem czegoś innego niż liczba
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
            }

            switch (option) {
                case 0: {
                    contineuing = false;
                    break;
                }
                case 1: {
                    SlipDto randomAdvice = adviceService.getRandomAdvice();
                    String advice = (String) randomAdvice.getAdvice();
                    adviceService.saveAdvice(randomAdvice); //to co przyszło z sieci zapisujemy do bazy danych
                    System.out.println("****** Cytat dla Ciebie ******");
                    System.out.println(advice);
                    System.out.println("******************************");
                    break;
                }
                case 2: {
                    System.out.println("Pracujemy nad tym");
                    break;
                }
                case 3: {
                    System.out.println("Pracujemy nad tym");
                    break;
                }
                case -1: {
                    System.out.println("Wpisz liczbę!");
                    break;
                }
                default: {
                    System.out.println("Funkcja nie obsługiwana");
                }
            }
        }

    }
}