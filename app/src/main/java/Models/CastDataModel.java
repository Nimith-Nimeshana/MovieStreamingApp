package Models;

public class CastDataModel {
    String Cname;
    String Clink;

    public CastDataModel() {
    }

    public CastDataModel(String cname, String clink) {
        Cname = cname;
        Clink = clink;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getClink() {
        return Clink;
    }

    public void setClink(String clink) {
        Clink = clink;
    }
}
