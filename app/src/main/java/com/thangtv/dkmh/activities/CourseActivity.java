package com.thangtv.dkmh.activities;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.thangtv.dkmh.R;
import com.thangtv.dkmh.models.Course;
import com.thangtv.dkmh.services.DKMHClient;
import com.thangtv.dkmh.services.DKMHService;
import com.thangtv.dkmh.ultils.Color;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView idCourse;
    private TextView nameCourse;
    private TextView tiet;
    private TextView siSo;
    private TextView giangVien;
    private TextView phongHoc;
    private TextView soTinChi;
    private TextView ghiChu;
    private TextView about;

    private int primaryColor;
    private int darkColor;

    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Intent intent = getIntent();
        course = (Course) intent.getSerializableExtra("course");
        primaryColor = intent.getIntExtra("color", 0);
        darkColor = Color.alterColor(primaryColor, 0.9f);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setSubtitle(course.getNameCourse());
        toolbar.setTitle(course.getIdCourse());

        toolbar.setBackgroundColor(primaryColor);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



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


        idCourse = (TextView) findViewById(R.id.idCourse);
        nameCourse = (TextView) findViewById(R.id.nameCourse);
        tiet = (TextView) findViewById(R.id.tiet);
        siSo = (TextView) findViewById(R.id.siSo);
        giangVien = (TextView) findViewById(R.id.giangVien);
        phongHoc = (TextView) findViewById(R.id.phongHoc);
        soTinChi = (TextView) findViewById(R.id.soTinChi);
        ghiChu = (TextView) findViewById(R.id.ghiChu);
        about = (TextView) findViewById(R.id.about);


        idCourse.setText(course.getIdCourse());
        nameCourse.setText(course.getNameCourse());
        tiet.setText(course.getTiet());
        siSo.setText(course.getSiSo() + "");
        giangVien.setText(course.getGiaoVien());
        phongHoc.setText(course.getPhongHoc());
        soTinChi.setText(course.getSoTinChi() + "");
        ghiChu.setText(course.getGhiChu());

        about.setTextColor(primaryColor);
        idCourse.setTextColor(primaryColor);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_edit) {
            Intent intent = new Intent(this, EditCourseActivity.class);
            intent.putExtra("course", course);
            intent.putExtra("color", primaryColor);
            startActivity(intent);
            finish();
        } else if (id==R.id.action_delete) {
            DKMHService service = DKMHClient.getInstance(getApplicationContext()).create(DKMHService.class);
            Call<Void> call = service.deleteCourse(course.getId());
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(CourseActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(CourseActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }
}
