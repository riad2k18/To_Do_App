package com.example.to_do_list;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WorkRepository {
    private WorkDao workDao;
    private LiveData<List<Work>> allworkes;


    public WorkRepository(Application application) {
        WorkDatabase database = WorkDatabase.getInstance(application);
        workDao = database.workDao();
        allworkes = workDao.getallworks();
    }

    public void insert(Work work) {
        new InsertWorkasynctask(workDao).execute(work);
    }

    public void update(Work work) {
        new UpdateWorkasynctask(workDao).execute(work);
    }

    public void delete(Work work) {
        new DeleteWorkasynctask(workDao).execute(work);
    }

    public void deleteallworks() {
        new DeleteallWorkasynctask(workDao).execute();
    }

    public LiveData<List<Work>> getAllworkes() {
        return allworkes;
    }

    private static class InsertWorkasynctask extends AsyncTask<Work, Void, Void> {
        private WorkDao workDao;

        public InsertWorkasynctask(WorkDao workDao) {
            this.workDao = workDao;
        }

        @Override
        protected Void doInBackground(Work... works) {
            workDao.insert(works[0]);
            return null;
        }
    }

    private static class UpdateWorkasynctask extends AsyncTask<Work, Void, Void> {
        private WorkDao workDao;

        public UpdateWorkasynctask(WorkDao workDao) {
            this.workDao = workDao;
        }

        @Override
        protected Void doInBackground(Work... works) {
            workDao.update(works[0]);
            return null;
        }
    }

    private static class DeleteWorkasynctask extends AsyncTask<Work, Void, Void> {
        private WorkDao workDao;

        public DeleteWorkasynctask(WorkDao workDao) {
            this.workDao = workDao;
        }

        @Override
        protected Void doInBackground(Work... works) {
            workDao.delete(works[0]);
            return null;
        }
    }

    private static class DeleteallWorkasynctask extends AsyncTask<Void, Void, Void> {
        private WorkDao workDao;

        public DeleteallWorkasynctask(WorkDao workDao) {
            this.workDao = workDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            workDao.deleteallworks();
            return null;
        }


    }
}