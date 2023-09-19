package Models;

public class SliderDataModel {
    //This class represent the firebase realtime database values
    //In firebase realtime database must create child nodes with Capital letter with leftovers are simple like"Mtitle"
    //or you face .this by when creating constructor ".this lmcast"
    //Slider View data models
    private String Mtitle;
    private String Mthumb;
    private String Mvid;

    //alt+ins to create contributors from variables to firebase requests
    public SliderDataModel() {
    }

    public SliderDataModel(String mtitle, String mthumb, String mvid) {
        Mtitle = mtitle;
        Mthumb = mthumb;
        Mvid = mvid;
    }

    //getter and setter

    public String getMtitle() {
        return Mtitle;
    }

    public void setMtitle(String mtitle) {
        Mtitle = mtitle;
    }

    public String getMthumb() {
        return Mthumb;
    }

    public void setMthumb(String mthumb) {
        Mthumb = mthumb;
    }

    public String getMvid() {
        return Mvid;
    }

    public void setMvid(String mvid) {
        Mvid = mvid;
    }
}
