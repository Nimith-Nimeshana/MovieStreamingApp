package Models;

public class SeriesDataModel {
    String Scast;
    String Sthumb;
    String Scover;
    String Sdis;
    String Sepisodes;
    String Stitle;
    String Slink;

    //empty constructor
    public SeriesDataModel() {
    }

    public SeriesDataModel(String scast, String sthumb, String scover, String sdis, String sepisodes, String stitle, String slink) {
        Scast = scast;
        Sthumb = sthumb;
        Scover = scover;
        Sdis = sdis;
        Sepisodes = sepisodes;
        Stitle = stitle;
        Slink = slink;
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



