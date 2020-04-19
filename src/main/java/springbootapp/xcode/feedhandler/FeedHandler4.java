package springbootapp.xcode.feedhandler;

import springbootapp.xcode.feedhandler.domain.Document;
import springbootapp.xcode.feedhandler.domain.DocumentDb;
import springbootapp.xcode.feedhandler.domain.Resource;
import springbootapp.xcode.feedhandler.domain.WebService;

import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * Exceptions to control flow
 */
public class FeedHandler4 implements FeedHandler{

    WebService webservice = new WebService();
    DocumentDb documentDb = new DocumentDb();


    public void handle(List<Document> changes) {
        changes.stream()
                .filter(doc -> isImportant(doc))
                .forEach( doc ->{
                        createResource(doc)
                                .thenAccept(resource -> updateToProcessed(doc, resource))
                                .exceptionally(e ->  { updateToFailed(doc,e); return null;})
                                .join();
                });
    }

    private CompletableFuture<Resource> createResource(Document doc) {
        return CompletableFuture.supplyAsync(() -> webservice.create(doc));
    }

    private void updateToProcessed(Document doc, Resource resource) {
        //int x = 5/0;
        doc.setId(resource.getId());
        doc.setStatus("processed");
        documentDb.update(doc);
    }

    private void updateToFailed(Document doc, Throwable e) {
        System.out.println(String.format("[%s] Error occurred, storing document with failed status",
                Thread.currentThread().getName()));
        doc.setStatus("failed");
        doc.setErrorDescription(e.getMessage());
        documentDb.update(doc);
    }

    private boolean isImportant(Document doc) {
        return doc.getType().equals("important");
    }

}
