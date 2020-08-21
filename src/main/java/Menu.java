import database.Slip;
import http.SearchResponse;
import http.SlipDto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final AdviceService adviceService;
    private final AdviceClient adviceClient;
    private final AdviceExporter adviceExporter;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(AdviceService adviceService, AdviceClient adviceClient, AdviceExporter adviceExporter) {
        this.adviceService = adviceService;
        this.adviceClient = adviceClient;
        this.adviceExporter = adviceExporter;
    }

    public void displayMenu() {
        boolean contineuing = true;

        while (contineuing) {
            System.out.println();
            System.out.println("Welcome in Advice book");
            System.out.println("Wybierz jedną z opcji");
            System.out.println("1. Wylosuj cytat");
            System.out.println("2. Wyszukaj cytat");
            System.out.println("3. Moje cytaty");
            System.out.println("0. Zakończ"); //eksport cytatów, wyświetlanie, usuwanie

            int nextInt = -1; //zabezpieczenie przed wpisaniem czegoś innego niż liczba
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                nextInt = scanner.nextInt();
            }

            switch (nextInt) {

                case 0: {
                    contineuing = false;
                    break;
                }
                case 1: {
                    SlipDto randomAdvice = adviceClient.getRandomAdvice();
                    String advice = randomAdvice.getAdvice();
                    System.out.println("");
                    System.out.println("****** Cytat dla Ciebie ******");
                    System.out.println(advice);
                    System.out.println("******************************");
                    //w case1: Małe menu w nowej metodzie (w while (flaga)) z trzema opcjami: 1 - losuj następny cytat, 2 - zapisz aktualny cytat do bazy danych (dodaj do ulubionych), 3 - cofnij do menu głównego
                    /*Rozwiązanie 1. Utworzenie osobnej klasy SmallMenu do obsługi zagadnienia, żeby nie zaśmiecać bieżącej klasy

                    SmallMenu smallMenu = new SmallMenu(adviceService);
                    smallMenu.smallMenu1(randomAdvice);

                    Rozwiązanie 2.*/
                    MenuCase1(randomAdvice);
                    break;
                }
                case 2: {
                    System.out.println("Czego szukasz?");
                    String search = scanner.next();

                    try {
                        //int searchInt = Integer.parseInt(search);
                        //System.out.println(adviceClient.searchById(nextInt));
                        SearchResponse sr = adviceClient.searchByString(search);
                        System.out.println(sr);
                    }
                    catch (NumberFormatException e) {
                        //System.out.println(adviceClient.searchByString(search));
                        System.out.println(e.getMessage());
                    }
                    /*List<Slip> allAdvices = adviceService.getAllAdvices();
                    System.out.println(Arrays.toString(allAdvices.toArray()));*/
                    break;
                }
                case 3: {
                    //w case3: Małe menu w osobnej metodzie (pętla while (flaga) wyjście z pętli na 0) z trzema opcjami: 1 - wyświetl moje ulubione, 2 - usuń z ulubionych (prośba o podanie id), 0 - powrót do menu głównego
                    /*Rozwiązanie 1. Utworzenie osobnej klasy FavMenu do obsługi zagadnienia, żeby nie zaśmiecać bieżącej klasy

                    new FavMenu (adviceService).displayFavMenu();
                    ...

                    Rozwiązanie 2.*/
                    List<Slip> allAdvice = adviceService.getAllAdvice();
                    MenuCase3(allAdvice);
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

    private void MenuCase1(SlipDto randomAdvice) {
        boolean flaga = true;
        while (flaga) {
            System.out.println();
            System.out.println("Wybierz jedną z opcji:");
            System.out.println("1. Losuj następny cytat");
            System.out.println("2. Dodaj do ulubionych");
            System.out.println("3. Cofnij do Menu Głównego");

            int nextInt = -1; //zabezpieczenie przed wpisaniem czegoś innego niż liczba
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                nextInt = scanner.nextInt();
            }

            switch (nextInt) {
                case 1: {
                    randomAdvice = adviceClient.getRandomAdvice();
                    String advice = randomAdvice.getAdvice();
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
                case 3: {
                    //Powrót do Menu Głównego
                    flaga = false;
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

    private void MenuCase3(List<Slip> allAdvice) {
        boolean development = true;
        Long Id;

        while (development) {
            System.out.println();
            System.out.println("Wybierz jedną z opcji:");
            System.out.println("1. Wyświetl ulubione cytaty");
            System.out.println("2. Usuń cytat z ulubionych");
            //System.out.println("3. Eksport do pliku ulubionych cytatów");
            //System.out.println("4. Eksport do Excel'a");
            System.out.println("0. Powrót do Menu Głównego");

            int nextInt = -1; //zabezpieczenie przed wpisaniem czegoś innego niż liczba
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                nextInt = scanner.nextInt();
            }

            switch (nextInt) {
                case 0: {
                    development = false;
                    break;
                }
                case 1: {
                    System.out.println();
                    System.out.println("Ulubione cytaty");
                    System.out.println(Arrays.toString(allAdvice.toArray()));
                    break;
                }
                case 2: {
                    System.out.println();
                    System.out.println("Podaj ID do usunięcia");
                    Id = scanner.nextLong();
                    adviceService.deleteID(Id); //wywołanie metody Usuń w slipdao z parametrem id
                }
                /*case 3: {
                    adviceExporter.exportToFile(allAdvice);
                    break;
                }
                case 4: {
                    adviceExporter.exportToSheet(allAdvice);
                    break;
                }*/
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