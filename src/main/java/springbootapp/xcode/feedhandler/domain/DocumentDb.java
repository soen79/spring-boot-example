package springbootapp.xcode.feedhandler.domain;

public class DocumentDb {

    public void update(Document doc) {
        String threadName = Thread.currentThread().getName();
        System.out.println(
                String.format("[%s] Updating DB with document name: %s, resourceId: %s, status: %s",
                        threadName,
                        doc.getName() ,
                        doc.getId(),
                        doc.getStatus())
        );
    }
}
