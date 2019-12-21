package android.example.livedataroommvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.example.livedataroommvvm.adapters.ItemsAndPersonAdapter;
import android.example.livedataroommvvm.enums.StatusEnum;
import android.example.livedataroommvvm.model.PersonModel;
import android.example.livedataroommvvm.viewModel.PersonlistViewModel;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    PersonlistViewModel personlistViewModel;

    ItemsAndPersonAdapter itemsAndPersonAdapter;
    RecyclerView personListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personListRecyclerView = findViewById(R.id.personListRecyclerView);

        itemsAndPersonAdapter = new ItemsAndPersonAdapter(new ArrayList<PersonModel>(),this);
        personListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        personListRecyclerView.setAdapter(itemsAndPersonAdapter);

        personlistViewModel = ViewModelProviders.of(this).get(PersonlistViewModel.class);

        personlistViewModel.getItemAndPersonList().observe(MainActivity.this, new Observer<List<PersonModel>>() {
            @Override
            public void onChanged(List<PersonModel> personModels) {
                itemsAndPersonAdapter.addItems(personModels);
            }
        });
    }

    @Override
    public boolean onLongClick(View view) {
        PersonModel personModel = (PersonModel) view.getTag();
        personlistViewModel.deleteItem(personModel);
        return true;
    }
}
