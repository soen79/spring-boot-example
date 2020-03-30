package integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by User on 12/7/2019.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  //Embedded server startup in this test
public class HelloControllerIT {

    @LocalServerPort    // Discovers the random port assigned
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;


    @Before
    public void setup() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }


    @Test
    public void getHello() throws Exception {

        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
    }
}
