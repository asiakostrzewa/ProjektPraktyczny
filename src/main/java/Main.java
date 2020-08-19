public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu(new AdviceService(), new AdviceClient(), new AdviceExporter());
        menu.displayMenu();
    }
}
