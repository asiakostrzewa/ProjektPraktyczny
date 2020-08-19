package http;

import java.util.List;

public class SearchResponse {
    private int totalResult;
    private String query;
    private List<SlipDto> slips;

    //Constructory dla wszystkich
    public SearchResponse(int totalResult, String query, List<SlipDto> slip) {
        this.totalResult = totalResult;
        this.query = query;
        this.slips = slip;
    }

    //Gettery i settery dla wszystkich
    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<SlipDto> getSlip() {
        return slips;
    }

    public void setSlip(List<SlipDto> slip) {
        this.slips = slip;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("SearchResponse{" +
                "totalResult=" + totalResult +
                ", query='" + query+"\n");

        for (SlipDto slip: slips) {
            result.append(slip.toString());
            result.append('\n');
        }
        return result.toString();
    }
}
