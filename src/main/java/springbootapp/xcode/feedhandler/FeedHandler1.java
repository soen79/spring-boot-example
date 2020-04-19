package springbootapp.xcode.feedhandler;


import springbootapp.xcode.feedhandler.domain.Document;
import springbootapp.xcode.feedhandler.domain.DocumentDb;
import springbootapp.xcode.feedhandler.domain.Resource;
import springbootapp.xcode.feedhandler.domain.WebService;

import java.util.List;

/**
 * Imperative
 */
public class FeedHandler1 implements FeedHandler{

    WebService webservice = new WebService();
    DocumentDb documentDb = new DocumentDb();

    public void handle(List<Document> changes) {

        for (int i = 0; i < changes.size(); i++) {
            Document doc = changes.get(i);
            if (doc.getType().equals("important")) {

                try {
                    Resource resource = webservice.create(doc);
                    doc.setId(resource.getId());
                    doc.setStatus("processed");
                } catch (Exception e) {
                    doc.setStatus("failed");
                    doc.setErrorDescription(e.getMessage());
                }
                documentDb.update(doc);
            }
        }
    }
}

