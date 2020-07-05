package http;

public class SlipResponse {

    private SlipDto slip;

    public SlipResponse(SlipDto slip) { this.slip = slip; }

    public SlipDto getSlip() {
        return slip;
    }

    public void setSlip(SlipDto slip) {
        this.slip = slip;
    }

}