package com.example.to_do_list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class Work_viewmodel extends AndroidViewModel {
    private WorkRepository repository;
    private LiveData<List<Work>>allworks;

    public Work_viewmodel(@NonNull Application application) {
        super(application);
        repository=new WorkRepository(application);
        allworks=repository.getAllworkes();
    }

    public void insert(Work work){
        repository.insert(work);
    }
    public void update(Work work){
        repository.update(work);
    }
    public void delete(Work work){
        repository.delete(work);
    }
    public void deleteallworks(){
        repository.deleteallworks();
    }
    public LiveData<List<Work>>getAllworks(){
        return allworks;
    }
}
