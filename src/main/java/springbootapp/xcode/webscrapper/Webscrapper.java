package springbootapp.xcode.webscrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

public class Webscrapper {

    private static final String baseURL = "https://newyork.craigslist.org/search/sss?sort=rel&query=";


    public static void main(String[] args) throws JsonProcessingException {
        Webscrapper scrapper = new Webscrapper();
        scrapper.execute();
    }

    public void execute() throws JsonProcessingException {
        String searchQuery = "Iphone 6s" ;


        // Get of hold the HTML
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        HtmlPage page = null;
        try {
            String searchUrl = baseURL + URLEncoder.encode(searchQuery, "UTF-8");
            page = client.getPage(searchUrl);

            System.out.println(page.asXml());
        }catch(Exception e){
            e.printStackTrace();
        }

        // Treat the HTML
        List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//li[@class='result-row']") ;
        if(items.isEmpty()){
            System.out.println("No items found !");
        }else{


            /**
             * Extract the item and price
             * Map it to the Item object
             * Map that to JSON String
             */
            for(HtmlElement htmlItem : items){
                HtmlAnchor itemAnchor = ((HtmlAnchor) htmlItem.getFirstByXPath(".//p[@class='result-info']/a"));

                HtmlElement spanPrice = ((HtmlElement) htmlItem.getFirstByXPath(".//a/span[@class='result-price']")) ;

                String itemName = itemAnchor.asText();
                String itemUrl =  itemAnchor.getHrefAttribute();

                // It is possible that an item doesn't have any price
                String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText() ;

                System.out.println( String.format("Name : %s Url : %s Price : %s", itemName, itemPrice, itemUrl));

                Item item = new Item();
                item.setTitle(itemAnchor.asText());
                item.setUrl( /*baseUrl +*/ itemAnchor.getHrefAttribute());
                item.setPrice(new BigDecimal(itemPrice.replace("$", "")));

                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(item) ;

                System.out.println(jsonString);

            }
        }

    }

}
