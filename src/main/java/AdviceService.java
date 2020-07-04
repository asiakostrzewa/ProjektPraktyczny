public class AdviceService {
    private static final String URL = "https://api.adviceslip.com/";

    public Slip getRandomAdvice(){
        HttpClient httpClient = new HttpClient();
        return httpClient.fetch(URL + "advice", SlipResponse.class).getSlip();
    }
}
