package springbootapp.xcode.feedhandler;

import springbootapp.xcode.feedhandler.domain.Document;
import springbootapp.xcode.feedhandler.domain.DocumentDb;
import springbootapp.xcode.feedhandler.domain.Resource;
import springbootapp.xcode.feedhandler.domain.WebService;

import java.util.List;

/**
 * Tell a story
 */
public class FeedHandler3 implements FeedHandler {

    WebService webservice = new WebService();
    DocumentDb documentDb = new DocumentDb();


    public void handle(List<Document> changes) {
        changes.stream()
                .filter(doc -> isImportant(doc))
                .forEach( doc ->{
                    try {
                        Resource resource = createResource(doc);
                        updateToProcessed(doc, resource);
                    } catch (Exception e) {
                        updateToFailed(doc, e);
                    }
                });
    }

    private void updateToFailed(Document doc, Exception e) {
        doc.setStatus("failed");
        doc.setErrorDescription(e.getMessage());
        documentDb.update(doc);
    }

    private void updateToProcessed(Document doc, Resource resource) {
        doc.setId(resource.getId());
        doc.setStatus("processed");
        documentDb.update(doc);
    }

    private Resource createResource(Document doc) {
        return webservice.create(doc);
    }

    private boolean isImportant(Document doc) {
        return doc.getType().equals("important");
    }


}
