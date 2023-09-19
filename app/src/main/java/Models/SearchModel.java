package Models;

public class SearchModel {

    private String Fcast;
    private String Fcover;
    private String Fdis;
    private String Flink;
    private String Fthumb;
    private String Ftitle;

    private String Scast;
    private String Sthumb;
    private String Scover;
    private String Sdis;
    private String Sepisodes;
    private String Stitle;
    private String Slink;

    public SearchModel() {
    }

    public SearchModel(String fcast,
                       String fcover,
                       String fdis,
                       String flink,
                       String fthumb,
                       String ftitle,
                       String scast,
                       String sthumb,
                       String scover,
                       String sdis,
                       String sepisodes,
                       String stitle,
                       String slink) {
        Fcast = fcast;
        Fcover = fcover;
        Fdis = fdis;
        Flink = flink;
        Fthumb = fthumb;
        Ftitle = ftitle;
        Scast = scast;
        Sthumb = sthumb;
        Scover = scover;
        Sdis = sdis;
        Sepisodes = sepisodes;
        Stitle = stitle;
        Slink = slink;
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

    public String getScast() {
        return Scast;
    }

    public void setScast(String scast) {
        Scast = scast;
    }

    public String getSthumb() {
        return Sthumb;
    }

    public void setSthumb(String sthumb) {
        Sthumb = sthumb;
    }

    public String getScover() {
        return Scover;
    }

    public void setScover(String scover) {
        Scover = scover;
    }

    public String getSdis() {
        return Sdis;
    }

    public void setSdis(String sdis) {
        Sdis = sdis;
    }

    public String getSepisodes() {
        return Sepisodes;
    }

    public void setSepisodes(String sepisodes) {
        Sepisodes = sepisodes;
    }

    public String getStitle() {
        return Stitle;
    }

    public void setStitle(String stitle) {
        Stitle = stitle;
    }

    public String getSlink() {
        return Slink;
    }

    public void setSlink(String slink) {
        Slink = slink;
    }
}

