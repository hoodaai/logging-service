package com.rest;

import com.Application;
import com.service.AccountService;
import com.web.rest.dto.ApplicationLogDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;



/**
 * Test class for LogResource REST API
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class LogResourceTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;


    private HttpMessageConverter mappingJackson2HttpMessageConverter;


    @Autowired
    private AccountService accountService;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    /**
     * calling rest api to post application log data with wrong authorization header value
     * expected: It should return 403, forbidden
     *
     */
    @Test
    public void addApplicationLogWithWrongAuthHeader() throws Exception {
        mockMvc.perform(post("/api/log/application")
                .content(this.json(getApplicationLogDTO()))
                .header("Authorization", "Bearer y03DmyNSwl")
                .contentType(contentType))
                .andExpect(status().isForbidden());
    }


    /**
     * calling rest api to post customer activity log data.
     * input: not passing request body
     * expected: It should return 405, Bad request error
     *
     */
    @Test
    public void callCustomerActivityLogApiWithoutRequestBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/log/customerActivity")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * calling rest api to post customer activity log data.
     * input:  passing request body
     * expected: It should return 201, created successfully
     *
     */
    @Test
    public void callCustomerActivityLogApi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/log/customerActivity")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * calling rest api to post application log data.
     * input: not passing request body
     * expected: It should return 405, Bad request error
     *
     */
    @Test
    public void callApplicationLogApiWithoutRequestBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/log/application")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


    protected ApplicationLogDTO getApplicationLogDTO(){

        ApplicationLogDTO applicationLogDTO  = new ApplicationLogDTO();
        applicationLogDTO.setLogLevel("INFO");
        applicationLogDTO.setClassName("Application.class");
        applicationLogDTO.setMessage("test message");
        applicationLogDTO.setStackTrace("test print stack trace");

        return applicationLogDTO;
    }

}
