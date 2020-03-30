package springbootapp;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by User on 12/7/2019.
 * A unit test that mocks the servlet request and response through your endpoint:
 */
@RunWith(SpringRunner.class)
@SpringBootTest        // Entire application Context is created
@AutoConfigureMockMvc
public class HelloControllerTest {

    //Allows you to send HTTP requests into the DispatcherServlet and make assertions about the result
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getHello() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

}
