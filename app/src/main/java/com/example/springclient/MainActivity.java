package com.example.springclient;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.springclient.Model.Employee;
import com.example.springclient.Retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etName , etLocation , etBranch;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        RetrofitService retrofitService = new RetrofitService();
        EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);



        //save data on click
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString();
                String location = etLocation.getText().toString();
                String branch  = etBranch.getText().toString();


                Employee employee = new Employee();
                employee.setName(name);
                employee.setLocation(location);
                employee.setBranch(branch);

                employeeApi.save(employee)
                        .enqueue(new Callback<Employee>() {
                            @Override
                            public void onResponse(Call<Employee> call, Response<Employee> response) {

                                Toast.makeText(MainActivity.this, "Employee saved", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Employee> call, Throwable throwable) {

                                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    public void initViews(){

        etName = findViewById(R.id.etName);
        etLocation = findViewById(R.id.etLocation);
        etBranch = findViewById(R.id.etBranch);

        btnSave = findViewById(R.id.btnSave);
    }
}