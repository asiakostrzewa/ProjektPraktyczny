import database.Slip;
import database.SlipDao;
import http.HttpClient;
import http.SearchResponse;
import http.SlipDto;
import http.SlipResponse;
import http.HttpClient;

import java.util.List;

public class AdviceService {
    /*private static final String URL = "https://api.adviceslip.com/";
    private final HttpClient httpClient = new HttpClient();

    public SlipDto getRandomAdvice() {
        return httpClient.fetch(URL + "advice", SlipResponse.class).getSlip();
    }*/

    public void saveAdvice(SlipDto slip) {
        Slip slipToSave = new Slip(slip);
        SlipDao slipDao = new SlipDao();
        slipDao.insertOrUpdate(slipToSave);
    }

    public List<Slip> getAllAdvice() { //nie void, tylko List bo zwracamy listę
        SlipDao slipDao = new SlipDao();
        List<Slip> slips = slipDao.getAll(); //slips = all
        return slips;
    }

    public void deleteID(Long Id) {
        SlipDao slipDao = new SlipDao();
        System.out.println("Succes: " + slipDao.deleteId(Id));
    }

    /*public void getFavouriteAdvices() {
    }

    public void searchById (int id){
    }

    public SearchResponse searchByString(String query){
        //https://api.adviceslip.com/#object-slip
        return httpClient.fetch(URL + "advice/search/" + query, SearchResponse.class);
    }*/
}