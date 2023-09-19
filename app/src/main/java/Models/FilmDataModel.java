package Models;

public class FilmDataModel {

    //This class represent the firebase realtime database values
    //In firebase realtime database must create child nodes with Capital letter with leftovers are simple like"Mtitle"
    //or you face .this by when creating constructor ".this lmcast"
    //films data models
    private String Fcast;
    private String Fcover;
    private String Fdis;
    private String Flink;
    private String Fthumb;
    private String Ftitle;

    public FilmDataModel() {
    }

    public FilmDataModel(String fcast, String fcover, String fdis, String flink, String fthumb, String ftitle) {
        Fcast = fcast;
        Fcover = fcover;
        Fdis = fdis;
        Flink = flink;
        Fthumb = fthumb;
        Ftitle = ftitle;
    }

    public String getFcast() {
        return Fcast;
    }

    public void setFcast(String fcast) {
        Fcast = fcast;
    }

    public String getFcover() {
        return Fcover;
    }

    public void setFcover(String fcover) {
        Fcover = fcover;
    }

    public String getFdis() {
        return Fdis;
    }

    public void setFdis(String fdis) {
        Fdis = fdis;
    }

    public String getFlink() {
        return Flink;
    }

    public void setFlink(String flink) {
        Flink = flink;
    }

    public String getFthumb() {
        return Fthumb;
    }

    public void setFthumb(String fthumb) {
        Fthumb = fthumb;
    }

    public String getFtitle() {
        return Ftitle;
    }

    public void setFtitle(String ftitle) {
        Ftitle = ftitle;
    }
}
