package springbootapp.xcode.feedhandler.domain;

public class Resource {

    public Resource(String id, String name) {
        this.id = id;
        this.name = name;
    }
    private String id;

    private String name;


    public String getId() {
        return this.id;
    }
}
