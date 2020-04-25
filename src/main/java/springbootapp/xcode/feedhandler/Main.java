package springbootapp.xcode.feedhandler;

import springbootapp.xcode.feedhandler.domain.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("-------------START OF PROGRAM-------------");

        // Execute the fabulous doc handler
        new FeedHandler1().handle(getDocs());

        System.out.println("-------------END OF PROGRAM-------------");
    }

    /** Simulate a source of documents */
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
