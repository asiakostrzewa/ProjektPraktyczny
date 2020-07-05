import database.Slip;
import http.HttpClient;
import http.SlipDto;
import http.SlipResponse;

public class AdviceService {
    private static final String URL = "https://api.adviceslip.com/";
    private final HttpClient httpClient = new HttpClient();

    public SlipDto getRandomAdvice(){
        return httpClient.fetch(URL + "advice", SlipResponse.class).getSlip();
    }

    public void saveAdvice(SlipDto slip){
        Slip slipToSave = new Slip(slip);
    }
}