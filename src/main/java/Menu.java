import database.Slip;
import http.SlipDto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final AdviceService adviceService;

    public Menu(AdviceService adviceService) {
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
                    //todo: w case1: Małe menu w nowej metodzie (w while (flaga)) z trzema opcjami: 1 - losuj następny cytat, 2 - zapisz aktualny cytat do bazy danych (dodaj do ulubionych), 3 - cofnij do menu głównego
                    SmallMenu smallMenu = new SmallMenu(adviceService);
                    smallMenu.smallMenu1();
                    break;
                }
                case 2: {
                    System.out.println("Pracujemy nad tym");
                    break;
                }
                case 3: {
                    // todo w case3: Małe menu w osobnej metodzie (pętla while (flaga) wyjście z pętli na 0) z trzema opcjami: 1 - wyświetl moje ulubione, 2 - usuń z ulubionych (prośba o podanie id), 0 - powrót do menu głównego
                    /*List allAdvices = adviceService.getAllAdvices();
                    System.out.println(Arrays.toString(allAdvices.toArray())); //drukujemy pętlą/for-each'em lub ArrayListą*/
                    System.out.println("Pracujemy nad tym");
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