import http.SlipDto;

import java.util.Scanner;

/* todo: w case1: Małe menu w nowej metodzie (w while (flaga)) z trzema opcjami:
1 - losuj następny cytat,
2 - zapisz aktualny cytat do bazy danych (dodaj do ulubionych),
3 - cofnij do menu głównego */

public class SmallMenu {
    private final AdviceService adviceService;

    public SmallMenu(AdviceService adviceService) {
        this.adviceService = adviceService;
    }

    public void smallMenu1(SlipDto randomAdvice) {
        boolean contd = true;

        while (contd) {
            System.out.println();
            System.out.println("Wybierz jedną z opcji:");
            System.out.println("1. Losuj następny cytat");
            System.out.println("2. Dodaj do ulubionych");
            System.out.println("0. Cofnij do Menu Głównego");

            int smallOption = -1; //zabezpieczenie przed wpisaniem czegoś innego niż liczba
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                smallOption = scanner.nextInt();
            }

            switch (smallOption) {
                case 0: {
                    contd = false;
                    break;
                }
                case 1: {
                    randomAdvice = adviceService.getRandomAdvice();
                    String advice = (String) randomAdvice.getAdvice();
                    System.out.println("");
                    System.out.println("****** Cytat dla Ciebie ******");
                    System.out.println(advice);
                    System.out.println("******************************");
                    break;
                }
                case 2: {
                    //Dodaj do ulubionych
                    adviceService.saveAdvice(randomAdvice); //to co przyszło z sieci zapisujemy do bazy danych
                    System.out.println("Cytat został zapisany do ulubionych");
                    break;
                }
                case -1: {
                    System.out.println("Wpisz liczbę!");
                    break;
                }
                default: {
                    System.out.println("Funkcja nieobsługiwana");
                }
            }
        }
    }
}