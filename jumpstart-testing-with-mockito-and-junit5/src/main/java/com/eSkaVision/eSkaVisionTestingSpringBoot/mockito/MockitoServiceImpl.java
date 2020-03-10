package com.eSkaVision.eSkaVisionTestingSpringBoot.mockito;

import com.eSkaVision.eSkaVisionTestingSpringBoot.model.MockitoObject;

public class MockitoServiceImpl implements MockitoService {

    private MockitoRepository mockitoRepository;

    public void setMockitoRepository(MockitoRepository mockitoRepository) {
        this.mockitoRepository = mockitoRepository;
    }

    public int calculateSum(){
        int sum = 0;
        for(int value : mockitoRepository.retrieveAllData()){
            sum += value;
        }
        return sum;
    }

    public int calculateNewSum(int id){
        int sum = mockitoRepository.getStoredSumById(id);
        return sum + sum;
    }

    public void save(MockitoObject o){
        o = new MockitoObject(o.getName().toUpperCase());
        mockitoRepository.save(o);
    }
}
