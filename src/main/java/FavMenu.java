import database.Slip;
import database.SlipDao;
import http.SlipDto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FavMenu {
    private final AdviceService adviceService;

    public FavMenu(AdviceService adviceService) {
        this.adviceService = adviceService;
    }

    public void displayFavMenu() {
        boolean contd = true;

        while (contd) {
            System.out.println();
            System.out.println("Wybierz jedną z opcji:");
            System.out.println("1. Pokaż ulubione");
            System.out.println("2. Usuń z ulubionych");
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
                    List<Slip> allAdvices = adviceService.getAllAdvices();
                    for (Slip slip : allAdvices) {
                        System.out.println(slip);
                    }
                    break;
                }
                case 2: {
                    System.out.println("Podaj ID do usunięcia");
                    int deleteID = scanner.nextInt();
                    SlipDao slipdao = new SlipDao();
                    slipdao.deleteID(deleteID);
                    int id = scanner.nextInt(); // wywołaj metodę usuń w slipdao z parametrem id


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
