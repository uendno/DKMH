package com.thangtv.dkmh.services;

import com.thangtv.dkmh.models.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by uendno on 04/04/2016.
 */
public interface DKMHService {

    @GET("all")
    Call<List<Course>> getAllCourses();

    @POST("create")
    Call<Void> addCourse(@Body Course course);

    @POST("{id}/update")
    Call<Course> updateCourse(@Path("id") int id, @Body Course course);

    @GET("{id}/delete")
    Call<Void> deleteCourse(@Path("id") int id);
}
