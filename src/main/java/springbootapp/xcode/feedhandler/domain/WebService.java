package springbootapp.xcode.feedhandler.domain;

import java.time.LocalDateTime;
import java.util.Random;

public class WebService {
    public Resource create(Document document){

        Random random = new Random(LocalDateTime.now().getNano());
        double val = random.nextDouble();

        Resource resource = new Resource(String.valueOf(val), document.getName());
        String threadName = Thread.currentThread().getName();
        System.out.println(
                String.format("[%s] Webservice called for document name: %s, resourceId: %s ", threadName, document.getName() , resource.getId())
        );
        return resource;
    }
}
