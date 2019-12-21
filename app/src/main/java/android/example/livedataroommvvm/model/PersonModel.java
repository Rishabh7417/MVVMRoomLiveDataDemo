package android.example.livedataroommvvm.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PersonModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String itemName;
    private String personName;

    public PersonModel(String itemName, String personName)
    {
        this.itemName = itemName;
        this.personName = personName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPersonName() {
        return personName;
    }
}
