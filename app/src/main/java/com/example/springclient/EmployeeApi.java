package com.example.springclient;

import com.example.springclient.Model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeApi {

    @GET("/employee/get-all")
    Call<List<Employee>> getAllEmployees();

    @POST("employee/save")
    Call<Employee> save(@Body Employee employee);


}
