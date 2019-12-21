package android.example.livedataroommvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.example.livedataroommvvm.enums.StatusEnum;
import android.example.livedataroommvvm.model.PersonModel;
import android.example.livedataroommvvm.viewModel.PersonlistViewModel;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewPersonActivity extends AppCompatActivity implements View.OnClickListener {

    PersonlistViewModel personlistViewModel;

    EditText personNameEditText;
    EditText itemNameEditText;
    TextView submitButton;
    TextView showButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_person);

        findViews();
        setClickListeners();

        personlistViewModel = ViewModelProviders.of(this).get(PersonlistViewModel.class);

        personlistViewModel.getStatus().observe(AddNewPersonActivity.this, new Observer<StatusEnum>() {
            @Override
            public void onChanged(StatusEnum statusEnum) {
                Toast.makeText(AddNewPersonActivity.this,statusEnum.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void findViews()
    {
        personNameEditText = findViewById(R.id.personNameEditText);
        itemNameEditText = findViewById(R.id.itemNameEditText);
        submitButton = findViewById(R.id.submitButton);
        showButton = findViewById(R.id.showButton);
    }

    private void setClickListeners()
    {
        submitButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.submitButton:
                if(itemNameEditText.getText()!=null && personNameEditText.getText()!=null) {
                    PersonModel personModel = new PersonModel(itemNameEditText.getText().toString(), personNameEditText.getText().toString());
                    personlistViewModel.insertItem(personModel);
                }
                else
                {
                    Toast.makeText(this,"Please fill valid details",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.showButton:
                Intent intent = new Intent(AddNewPersonActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
