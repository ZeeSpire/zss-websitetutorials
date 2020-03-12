package com.eSkaVision.eSkaVisionTestingSpringBoot.mockito;

import com.eSkaVision.eSkaVisionTestingSpringBoot.model.Data;

public class DataServiceImpl implements DataService {

    private DataRepository dataRepository;

    public void setDataRepository(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public int calculateSum(){
        int sum = 0;
        for(int value : dataRepository.retrieveAllData()){
            sum += value;
        }
        return sum;
    }

    public int calculateNewSum(int id){
        int sum = dataRepository.getStoredSumById(id);
        return sum + sum;
    }

    public void save(Data o){
        o = new Data(o.getName().toUpperCase());
        dataRepository.save(o);
    }
}
