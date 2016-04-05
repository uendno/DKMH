package com.thangtv.dkmh.activities;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.thangtv.dkmh.R;
import com.thangtv.dkmh.models.Course;
import com.thangtv.dkmh.services.DKMHClient;
import com.thangtv.dkmh.services.DKMHService;
import com.thangtv.dkmh.ultils.Color;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCourseActivity extends AppCompatActivity {

    private Course course;
    private int primaryColor;
    private int darkColor;

    private Toolbar toolbar;

    private EditText idCourse;
    private EditText nameCourse;
    private EditText fromTiet;
    private EditText toTiet;
    private EditText siSo;
    private EditText giangVien;
    private EditText phongHoc;
    private EditText soTinChi;
    private EditText ghiChu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        idCourse = (EditText) findViewById(R.id.idCourse);
        nameCourse = (EditText) findViewById(R.id.nameCourse);
        fromTiet = (EditText) findViewById(R.id.fromTiet);
        toTiet = (EditText) findViewById(R.id.toTiet);
        siSo = (EditText) findViewById(R.id.siSo);
        giangVien = (EditText) findViewById(R.id.giangVien);
        phongHoc = (EditText) findViewById(R.id.phongHoc);
        soTinChi = (EditText) findViewById(R.id.soTinChi);
        ghiChu = (EditText) findViewById(R.id.ghiChu);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Add a new course");


        Intent intent = getIntent();
        course = (Course) intent.getSerializableExtra("course");


        if (course != null) {
            setContent();

            primaryColor = intent.getIntExtra("color", 0);
            darkColor = Color.alterColor(primaryColor, 0.9f);

            toolbar.setSubtitle(course.getNameCourse());
            toolbar.setTitle(course.getIdCourse());

            toolbar.setBackgroundColor(primaryColor);

            //Set color for status bar

            Window window = this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // only for gingerbread and newer versions
                window.setStatusBarColor(darkColor);
            }

        }


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void setContent() {
        idCourse.setText(course.getIdCourse());
        nameCourse.setText(course.getNameCourse());

        String tiet = course.getTiet();
        int idx = tiet.indexOf('-');
        fromTiet.setText(tiet.substring(0, idx));
        toTiet.setText(tiet.substring(idx + 1, tiet.length()));

        siSo.setText(course.getSiSo() + "");
        giangVien.setText(course.getGiaoVien());
        phongHoc.setText(course.getPhongHoc());
        soTinChi.setText(course.getSoTinChi() + "");
        ghiChu.setText(course.getGhiChu());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_course, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_done) {


            if (getIntent().getSerializableExtra("course") == null) {

                course = new Course();

                if (!validate()) return super.onOptionsItemSelected(item);

                course.setIdCourse(idCourse.getText().toString());
                course.setNameCourse(nameCourse.getText().toString());
                course.setTiet(fromTiet.getText().toString() + "-" + toTiet.getText().toString());
                course.setSiSo(Integer.parseInt(siSo.getText().toString()));
                course.setGiaoVien(giangVien.getText().toString());
                course.setPhongHoc(phongHoc.getText().toString());
                course.setSoTinChi(Integer.parseInt(soTinChi.getText().toString()));
                course.setGhiChu(ghiChu.getText().toString());

                DKMHService service = DKMHClient.getInstance(getApplicationContext()).create(DKMHService.class);
                Call<Void> call = service.addCourse(course);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(EditCourseActivity.this, "Created successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(EditCourseActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (getIntent().getSerializableExtra("course") != null) {

                if (!validate()) return super.onOptionsItemSelected(item);

                course.setIdCourse(idCourse.getText().toString());
                course.setNameCourse(nameCourse.getText().toString());
                course.setTiet(fromTiet.getText().toString() + "-" + toTiet.getText().toString());
                course.setSiSo(Integer.parseInt(siSo.getText().toString()));
                course.setGiaoVien(giangVien.getText().toString());
                course.setPhongHoc(phongHoc.getText().toString());
                course.setSoTinChi(Integer.parseInt(soTinChi.getText().toString()));
                course.setGhiChu(ghiChu.getText().toString());

                DKMHService service = DKMHClient.getInstance(getApplicationContext()).create(DKMHService.class);
                Call<Course> call = service.updateCourse(course.getId(), course);

                call.enqueue(new Callback<Course>() {
                    @Override
                    public void onResponse(Call<Course> call, Response<Course> response) {
                        Toast.makeText(EditCourseActivity.this, "Updated successfully: ", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Course> call, Throwable t) {

                    }
                });
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private boolean validate() {

        if (idCourse.getText().toString().equals("")) {
            toast();
            idCourse.requestFocus();
            return false;
        }

        if (nameCourse.getText().toString().equals("")) {
            toast();
            nameCourse.requestFocus();
            return false;
        }

        if (fromTiet.getText().toString().equals("")) {
            toast();
            fromTiet.requestFocus();
            return false;
        }

        if (toTiet.getText().toString().equals("")) {
            toast();
            toTiet.requestFocus();
            return false;
        }

        if (Integer.parseInt(fromTiet.getText().toString()) > Integer.parseInt(toTiet.getText().toString())) {
            Toast.makeText(EditCourseActivity.this, "Invalid value", Toast.LENGTH_SHORT).show();
            toTiet.requestFocus();
            return false;
        }

        if (siSo.getText().toString().equals("")) {
            toast();
            siSo.requestFocus();
            return false;
        }

        if (giangVien.getText().toString().equals("")) {
            toast();
            giangVien.requestFocus();
            return false;
        }

        if (phongHoc.getText().toString().equals("")) {
            toast();
            phongHoc.requestFocus();
            return false;
        }

        if (soTinChi.getText().toString().equals("")) {

            toast();
            soTinChi.requestFocus();
            return false;
        }

        if (ghiChu.getText().toString().equals("")) {
            toast();
            ghiChu.requestFocus();
            return false;
        }

        return true;
    }

    public void toast() {
        Toast.makeText(EditCourseActivity.this, "Missing component", Toast.LENGTH_SHORT).show();
    }
}
