package com.example.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springclient.Model.Employee;
import com.example.springclient.Retrofit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeList extends AppCompatActivity {


    private RecyclerView recView;
    private Adapter adapter;
    private FloatingActionButton fab;
    List<Employee> employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        recView.setLayoutManager(new LinearLayoutManager(this));
        employeeList = new ArrayList<>();
        adapter = new Adapter(EmployeeList.this,employeeList);


        //take user to add a new employee
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EmployeeList.this,MainActivity.class);
                startActivity(intent);
            }
        });


        loadEmployees();

    }

    private void loadEmployees() {


        RetrofitService retrofitService = new RetrofitService();
        EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);

        employeeApi.getAllEmployees().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                if(response.isSuccessful()){
                    getEmployees(response.body());

                }
            }

            private void getEmployees(List<Employee> body) {


                recView.setAdapter(adapter);
                employeeList.addAll(body);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable throwable) {

                Toast.makeText(EmployeeList.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void initViews(){

        recView = findViewById(R.id.recView);

        fab = findViewById(R.id.fab);
    }
}