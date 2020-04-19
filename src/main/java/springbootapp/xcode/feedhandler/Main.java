package springbootapp.xcode.feedhandler;

import springbootapp.xcode.feedhandler.domain.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("-------------START OF PROGRAM-------------");
        FeedHandler handler = new FeedHandler5();
        List<Document> docs = getDocs();
        handler.handle(docs);
        System.out.println("-------------END OF PROGRAM-------------");
    }

    public static List<Document> getDocs() {
        List<Document> docs = Arrays.asList( new Document("LCR", "important"),
                new Document("Coverage", "extra"),
                new Document("Claim", "low priority"),
                new Document("Policy", "important"),
                new Document("Rating", "important")
        );
        return docs;
    }
}
