package com.eSkaVision.eSkaVisionTestingSpringBoot.unit.controller;

import com.eSkaVision.eSkaVisionTestingSpringBoot.controller.ProductController;
import com.eSkaVision.eSkaVisionTestingSpringBoot.model.Product;
import com.eSkaVision.eSkaVisionTestingSpringBoot.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
        //    @ExtendWith(SpringExtension.class) - Register as a Spring Unit test
@WebMvcTest(ProductController.class)
        //    @WebMvcTest - Annotation will enable us to write a Spring MVC test
        //    that focuses only on Spring MVC components.
        //    Using this annotation will disable full auto-configuration and instead apply only
        //    configuration relevant to MVC tests
        //
        //    Adding these two annotations will mark test as a Spring test just for ProductController
        //    it will disable auto-configuration for other Spring components, testing this controller
        //    in isolation. We cannot make use of a real ProductService Component, because it will not
        //    be instantiated into the ApplicationContext.
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc; // This will allow us to make calls to Spring MVC API

    @MockBean
    private ProductService productServiceImpl; // This will mock a Spring Bean and Inject it where is needed

    // This test uses AssertEquals to check the validity of the response
    @Test
    void checkStatus_Should_ReturnLive_When_StatusPathIsCalled_AssertUsingAssertEquals() throws Exception {
        //build request, execute GET to /status
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/status").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals("Live!", mvcResult.getResponse().getContentAsString());
    }

    // This test uses ResultMatchers to check the validity of the response
    @Test
    void checkStatus_Should_ReturnLive_When_StatusPathIsCalled_AssertUsingResultMatchers() throws Exception {
        //build request, execute GET to /status and assert result using Response Matchers
        mockMvc.perform(MockMvcRequestBuilders.get("/status").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //check is response status is 200
                .andExpect(content().string("Live!"))
                .andReturn();
    }

    // This test uses ResultMatchers with JSONAssert to check the validity of the response
    @Test
    void getProduct_Should_ReturnString_When_ProductsPathIsCalled_AssertUsingResultMatchers() throws Exception {
        String expectedResult = "{\"id\":1,\"name\":\"Cheese\",\"price\":10.0,\"quantity\":100}";
        String expectedResultWithoutSomePropertiesAndEscapeChars = "{id: 1, name: Cheese, price: 10.0}";

        when(productServiceImpl.getProductById(1L, true))
                .thenReturn(new Product(1L, "Cheese", 10.0, 100L));

        // build request, execute GET to /product and assert result using Response Matchers with JSONAssert
        mockMvc.perform(MockMvcRequestBuilders.get("/products?id=1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //check is response status is 200
                .andExpect(content().json(expectedResult)) //it will succeed even if a property will be missing
                .andExpect(content().json(expectedResultWithoutSomePropertiesAndEscapeChars))
                .andReturn();
        // behind the scene .andExpect(content().json(...)) uses JSONAssert calling assertEquals
        // with strict mode deactivated

        verify(productServiceImpl, times(1)).getProductById(1L, true);
    }

    // This test demonstrates some of the capabilities of JSONAssert to check the validity of the response
    @Test
    void getProduct_Should_ReturnString_When_ProductsPathIsCalled_AssertUsingJSONAssert() throws Exception {
        String expectedResult = "{\"id\":1,\"name\":\"Cheese\",\"price\":10.0,\"quantity\":100,\"storeName\":null}";
        String expectedResultWithoutSomePropertiesAndEscapeChars = "{id:1,name:Cheese,price:10.0}";

        when(productServiceImpl.getProductById(anyLong(), anyBoolean()))
                .thenReturn(new Product(1L, "Cheese", 10.0, 100L));

        // build request, execute GET to /product
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/products?id=1").accept(MediaType.APPLICATION_JSON))
                .andReturn();

        // strict mode, everything should match, the structure should be the same
        JSONAssert.assertEquals(expectedResult, mvcResult.getResponse().getContentAsString(), true);

        // strict mode off, properties may be missing, escape characters can be missing too
        JSONAssert.assertEquals(expectedResultWithoutSomePropertiesAndEscapeChars,
                mvcResult.getResponse().getContentAsString(), false);

        verify(productServiceImpl, times(1)).getProductById(anyLong(), anyBoolean());
    }
}
