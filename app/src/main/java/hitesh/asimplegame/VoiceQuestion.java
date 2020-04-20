package hitesh.asimplegame;

public class VoiceQuestion {
    private int ID;
    private String QUESTION;
    private String ANSWER;

    public VoiceQuestion(String QUESTION, String ANSWER){
        this.ID = 0;
        this.ANSWER=ANSWER;
        this.QUESTION=QUESTION;
    }
    public VoiceQuestion(){

    }

    public int getID() {
        return ID;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }
}
