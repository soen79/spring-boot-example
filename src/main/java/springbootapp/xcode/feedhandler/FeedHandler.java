package springbootapp.xcode.feedhandler;

import springbootapp.xcode.feedhandler.domain.Document;

import java.util.List;

public interface FeedHandler {

    void handle(List<Document> changes);
}
