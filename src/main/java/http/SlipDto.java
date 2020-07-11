package http;

public class SlipDto {

    private long id;
    private String advice;

    public SlipDto(long id, String advice) { //DTO - data transfer object
        this.id = id;
        this.advice = advice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

}
