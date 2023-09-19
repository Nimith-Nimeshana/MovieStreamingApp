package Models;

public class EpisodesDataModel {
    String Epart;
    String Ethumb;
    String Evid;

    public EpisodesDataModel() {
    }

    public EpisodesDataModel(String epart, String ethumb, String evid) {
        Epart = epart;
        Ethumb = ethumb;
        Evid = evid;
    }

    public String getEpart() {
        return Epart;
    }

    public void setEpart(String epart) {
        Epart = epart;
    }

    public String getEthumb() {
        return Ethumb;
    }

    public void setEthumb(String ethumb) {
        Ethumb = ethumb;
    }

    public String getEvid() {
        return Evid;
    }

    public void setEvid(String evid) {
        Evid = evid;
    }
}
