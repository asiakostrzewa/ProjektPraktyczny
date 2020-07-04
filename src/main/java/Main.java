public class Main {

    private static final String URL = "https://api.adviceslip.com/";

    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        Slip slip = httpClient.fetch(URL + "advice", SlipResponse.class).getSlip();
        System.out.println(slip.getAdvice());
    }
}
