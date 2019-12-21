package android.example.livedataroommvvm.viewModel;

import android.app.Application;
import android.content.Intent;
import android.example.livedataroommvvm.AppDatabase;
import android.example.livedataroommvvm.enums.StatusEnum;
import android.example.livedataroommvvm.model.PersonModel;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PersonlistViewModel extends AndroidViewModel {

    private final LiveData<List<PersonModel>> itemAndPersonList;
    static MutableLiveData<StatusEnum> statusEnumLiveData = new MutableLiveData<>();

    private AppDatabase appDatabase;

    public PersonlistViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        itemAndPersonList = appDatabase.itemAndPersonModel().getAllPersonItems();
    }

    public LiveData<List<PersonModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public LiveData<StatusEnum> getStatus()
    {
        return statusEnumLiveData;
    }

    public void insertItem(PersonModel personModel)
    {
        new InsertAsyncTask(appDatabase).execute(personModel);
    }

    public void deleteItem(PersonModel personModel)
    {
        new DeleteAsyncTask(appDatabase).execute(personModel);
    }

    private static class DeleteAsyncTask extends AsyncTask<PersonModel,Void,Void>
    {

        private AppDatabase db;

        DeleteAsyncTask(AppDatabase appDatabase)
        {
            this.db = appDatabase;
        }

        @Override
        protected Void doInBackground(PersonModel... personModels) {
            db.itemAndPersonModel().removePersonDetails(personModels[0]);
            return null;
        }
    }

    private static class InsertAsyncTask extends AsyncTask<PersonModel,Void,Long>
    {

        private AppDatabase db;

        InsertAsyncTask(AppDatabase appDatabase)
        {
            this.db = appDatabase;
        }

        @Override
        protected Long doInBackground(PersonModel... personModels) {
            return db.itemAndPersonModel().addPersonDetails(personModels[0]);

        }

        @Override
        protected void onPostExecute(Long aVoid) {
            super.onPostExecute(aVoid);
            statusEnumLiveData.setValue(StatusEnum.SUCCESS);
        }
    }
}
