package com.eSkaVision.eSkaVisionTestingSpringBoot.mockito;

import com.eSkaVision.eSkaVisionTestingSpringBoot.model.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DataServiceTest_Clean {
    //create service under test
    DataService ms = new DataServiceImpl();

    //mock repository to test service in isolation
    DataRepository dataRepositoryMock = mock(DataRepository.class);

    @BeforeEach
    public void beforeEach(){
        //set mock to service
        ms.setDataRepository(dataRepositoryMock);
    }

    @Test
    public void calculateSum_Should_ReturnResult_When_DataIsProvided() {
        //mock repository to test service in isolation
        when(dataRepositoryMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3});

        //call method under test
        int result = ms.calculateSum();

        //verify if method on the mock is called by service under test
        //it is mostly used when a method that is called on a mock does not have a return
        verify(dataRepositoryMock, times(1)).retrieveAllData();

        //assert result
        assertEquals(6, result);
    }

    @Test
    public void calculateSum_Should_ReturnZero_When_DataIsEmpty() {
        //mock repository to test service in isolation
        when(dataRepositoryMock.retrieveAllData()).thenReturn(new int[]{});

        //call method under test
        int result = ms.calculateSum();

        //verify if method on the mock is called by service under test
        verify(dataRepositoryMock, times(1)).retrieveAllData();

        //assert result
        assertEquals(0, result);
    }

    @Test
    public void calculateSum_Should_ThrowException_When_DataIsNull() {
        assertThrows(NullPointerException.class, () -> {
            //mock repository to test service in isolation
            when(dataRepositoryMock.retrieveAllData()).thenReturn(null);

            //call method under test
            ms.calculateSum();
        });
    }

    @Test
    void calculateNewSum_Should_ReturnResult_When_DataIsProvided() {
        //return 2 when method is called with any int value
        when(dataRepositoryMock.getStoredSumById(anyInt())).thenReturn(2);

        //set mock to service
        ms.setDataRepository(dataRepositoryMock);

        //call method under test
        int result = ms.calculateNewSum(1);

        //verify if method on the mock is called by service under test with any argument
        verify(dataRepositoryMock, times(1)).getStoredSumById(anyInt());

        //assert result
        assertEquals(4, result);
    }

    @Test
    void save_ShouldCallRepository_With_GivenParam() {
        // call method under test
        Data o = new Data("MockitoObject");
        ms.save(o);

        //create expected object
        Data expected = new Data("MOCKITOOBJECT");

        // because the method does not return anything we can check
        // if mock method was called with an expected parameter
        ArgumentCaptor<Data> captor = ArgumentCaptor.forClass(Data.class);
        verify(dataRepositoryMock, times(1)).save(captor.capture());

        //assert captured argument
        assertEquals(expected, captor.getValue());
    }
}
