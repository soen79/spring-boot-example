package springbootapp.xcode.feedhandler.domain;

public class Document implements Cloneable{

    private String name;
    private String type;
    private String id;
    private String status;
    private String errorDescription;


    public Document(String name, String type){
        this.name = name;
        this.type = type;
        this.status = "new";
    }

    public String getName(){
        return this.name;
    };

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
