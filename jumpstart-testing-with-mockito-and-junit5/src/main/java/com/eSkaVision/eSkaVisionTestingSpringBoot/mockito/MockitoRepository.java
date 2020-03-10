package com.eSkaVision.eSkaVisionTestingSpringBoot.mockito;

public interface MockitoRepository {
    int[] retrieveAllData();
    int getStoredSumById(int id);
    void save(Object o);
}
