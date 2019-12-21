package android.example.livedataroommvvm.dao;

import android.example.livedataroommvvm.model.PersonModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PersonModelDao {

    @Query("select * from PersonModel")
    LiveData<List<PersonModel>> getAllPersonItems();

    @Delete
    void removePersonDetails(PersonModel personModel);


    @Insert(onConflict = REPLACE)
    long addPersonDetails(PersonModel personModel);

}
