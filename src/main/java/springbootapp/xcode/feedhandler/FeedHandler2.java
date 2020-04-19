package springbootapp.xcode.feedhandler;

import springbootapp.xcode.feedhandler.domain.Document;
import springbootapp.xcode.feedhandler.domain.DocumentDb;
import springbootapp.xcode.feedhandler.domain.Resource;
import springbootapp.xcode.feedhandler.domain.WebService;

import java.util.List;

/**
 * Declarative
 */
public class FeedHandler2 implements FeedHandler {

    WebService webservice = new WebService();
    DocumentDb documentDb = new DocumentDb();


    public void handle(List<Document> changes) {
        changes.stream()
                .filter(doc -> doc.getType().equals("important"))
                .forEach( doc ->{
                    try {
                        Resource resource = webservice.create(doc);
                        doc.setId(resource.getId());
                        doc.setStatus("processed");
                    } catch (Exception e) {
                        doc.setStatus("failed");
                        doc.setErrorDescription(e.getMessage());
                    }
                    documentDb.update(doc);
                });
    }

}
