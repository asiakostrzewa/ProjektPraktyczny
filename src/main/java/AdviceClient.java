import http.*;

public class AdviceClient {
    private final HttpClient httpClient = new HttpClient();
    private final String URL = PropertiesManager.getProperty("URL");

    public SlipDto getRandomAdvice(){
        String fetch = httpClient.fetch(URL + "advice");
        SlipResponse slipResponse = ResponseParser.parseFromString(fetch, SlipResponse.class);
        return slipResponse.getSlip();
    }

    public SlipResponse searchById(long id){
        String fetch = httpClient.fetch(URL + "advice");
        fetch += "}";
        SlipResponse slipResponse = ResponseParser.parseFromString(fetch, SlipResponse.class);
        return slipResponse;
    }

    public SearchResponse searchByString(String query){
        String fetch = httpClient.fetch(URL + "advice/search/" + query);
        SearchResponse searchResponse = ResponseParser.parseFromString(fetch, SearchResponse.class);
        return searchResponse;
    }
}
