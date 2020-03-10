package com.eSkaVision.eSkaVisionTestingSpringBoot.mockito;

import com.eSkaVision.eSkaVisionTestingSpringBoot.model.MockitoObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoServiceTest_Clean_WithAnnotations {
    //create service under test and inject all mocks needed
    //there is no need to manually inject mockitoRepositoryMock, just create it with @Mock
    @InjectMocks
    MockitoServiceImpl ms;

    //mock repository to test service in isolation
    @Mock
    MockitoRepository mockitoRepositoryMock;

    @Test
    void calculateSum_Should_ReturnResult_When_DataIsProvided() {
        //mock repository to test service in isolation
        when(mockitoRepositoryMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3});

        //call method under test
        int result = ms.calculateSum();

        //verify if method on the mock is called by service under test
        //it is mostly used when a method that is called on a mock does not have a return
        verify(mockitoRepositoryMock, times(1)).retrieveAllData();

        //assert result
        assertEquals(6, result);
    }

    @Test
    public void calculateSum_Should_ReturnZero_When_DataIsEmpty() {
        //mock repository to test service in isolation
        when(mockitoRepositoryMock.retrieveAllData()).thenReturn(new int[]{});

        //call method under test
        int result = ms.calculateSum();

        //verify if method on the mock is called by service under test
        verify(mockitoRepositoryMock, times(1)).retrieveAllData();

        //assert result
        assertEquals(0, result);
    }

    @Test
    public void calculateSum_Should_ThrowException_When_DataIsNull() {
        assertThrows(NullPointerException.class, () -> {
            //mock repository to test service in isolation
            when(mockitoRepositoryMock.retrieveAllData()).thenReturn(null);

            //call method under test
            ms.calculateSum();
        });
    }

    @Test
    void calculateNewSum_Should_ReturnResult_When_DataIsProvided() {
        //return 2 when method is called with any int value
        when(mockitoRepositoryMock.getStoredSumById(anyInt())).thenReturn(2);

        //set mock to service
        ms.setMockitoRepository(mockitoRepositoryMock);

        //call method under test
        int result = ms.calculateNewSum(1);

        //verify if method on the mock is called by service under test with any argument
        verify(mockitoRepositoryMock, times(1)).getStoredSumById(anyInt());

        //assert result
        assertEquals(4, result);
    }

    @Test
    void save_ShouldCallRepository_With_GivenParam() {
        // call method under test
        MockitoObject o = new MockitoObject("MockitoObject");
        ms.save(o);

        //create expected object
        MockitoObject expected = new MockitoObject("MOCKITOOBJECT");

        // because the method does not return anything we can check
        // if mock method was called with an expected parameter
        ArgumentCaptor<MockitoObject> captor = ArgumentCaptor.forClass(MockitoObject.class);
        verify(mockitoRepositoryMock, times(1)).save(captor.capture());

        //assert captured argument
        assertEquals(expected, captor.getValue());
    }
}
