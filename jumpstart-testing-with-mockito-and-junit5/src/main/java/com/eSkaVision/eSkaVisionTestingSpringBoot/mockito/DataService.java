package com.eSkaVision.eSkaVisionTestingSpringBoot.mockito;

import com.eSkaVision.eSkaVisionTestingSpringBoot.model.Data;

public interface DataService {
    int calculateSum();
    void setDataRepository(DataRepository dataRepository);
    int calculateNewSum(int id);
    void save(Data o);
}
