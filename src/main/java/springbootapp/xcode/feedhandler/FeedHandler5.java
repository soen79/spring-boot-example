package springbootapp.xcode.feedhandler;

import springbootapp.xcode.feedhandler.domain.Document;
import springbootapp.xcode.feedhandler.domain.DocumentDb;
import springbootapp.xcode.feedhandler.domain.Resource;
import springbootapp.xcode.feedhandler.domain.WebService;

import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * Migrate to pure functional functions/methods
 */
public class FeedHandler5 implements FeedHandler{

    WebService webservice = new WebService();
    DocumentDb documentDb = new DocumentDb();


    public void handle(List<Document> changes) {
        changes.stream()
                .filter(doc -> isImportant(doc))
                .forEach( doc ->{
                        createResource(doc)
                                .thenAccept(resource -> documentDb.update(setToProcessed(doc, resource)))
                                .exceptionally(e ->  {documentDb.update(setToFailed(doc,e)); return null;})
                                .join();
                });
    }

    private CompletableFuture<Resource> createResource(Document doc) {
        return CompletableFuture.supplyAsync(() -> webservice.create(doc));
    }

    private Document setToProcessed(Document doc, Resource resource) {
        int x = 5/0;    // Simulate Error
        Integer.parseInt("AA");  // Simulate Error

        Document retDoc = null;
        try {
            retDoc = (Document)doc.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        retDoc.setId(resource.getId());
        retDoc.setStatus("processed");
        return retDoc;
    }

    private Document setToFailed(Document doc, Throwable e) {
        System.out.println(String.format("[%s] Error occurred, storing document with failed status",
                Thread.currentThread().getName()));
        Document retDoc = null;
        try {
            retDoc = (Document)doc.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        retDoc.setStatus("failed");
        retDoc.setErrorDescription(e.getMessage());
        return retDoc;
    }

    private boolean isImportant(Document doc) {
        return doc.getType().equals("important");
    }

}
