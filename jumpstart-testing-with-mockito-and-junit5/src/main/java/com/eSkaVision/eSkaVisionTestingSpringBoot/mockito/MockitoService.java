package com.eSkaVision.eSkaVisionTestingSpringBoot.mockito;

import com.eSkaVision.eSkaVisionTestingSpringBoot.model.MockitoObject;

public interface MockitoService {
    int calculateSum();
    void setMockitoRepository(MockitoRepository mockitoRepository);
    int calculateNewSum(int id);
    void save(MockitoObject o);
}
