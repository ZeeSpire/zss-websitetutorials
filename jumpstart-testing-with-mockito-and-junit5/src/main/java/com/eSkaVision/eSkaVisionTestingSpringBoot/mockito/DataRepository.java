package com.eSkaVision.eSkaVisionTestingSpringBoot.mockito;

public interface DataRepository {
    int[] retrieveAllData();
    int getStoredSumById(int id);
    void save(Object o);
}
