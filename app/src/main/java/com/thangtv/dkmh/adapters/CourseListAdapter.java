package com.thangtv.dkmh.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.thangtv.dkmh.R;
import com.thangtv.dkmh.activities.CourseActivity;
import com.thangtv.dkmh.models.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uendno on 04/04/2016.
 */
public class CourseListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Course> courseList;
    private int resLayout;
    private List<Integer> colorList;

    public CourseListAdapter(Context context, List<Course> courseList, int resLayout) {
        this.context = context;
        this.courseList = courseList;
        this.resLayout = resLayout;

        ColorGenerator generator = ColorGenerator.MATERIAL;
        colorList = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            colorList.add(generator.getRandomColor());
        }

        Log.d("TEST_ABC", "CourseListAdapter: color " + colorList.size());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(resLayout, parent, false);
        Log.d("TEST_ABC", "onCreateViewHolder: ");
        return new CourseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CourseViewHolder hd = (CourseViewHolder) holder;
        final Course course = courseList.get(position);
        final int color = colorList.get(position);
        Log.d("TEST_ABC", "onBindViewHolder: " + course.getNameCourse());
        hd.idCourse.setText(course.getIdCourse());
        hd.nameCourse.setText(course.getNameCourse());
        hd.tiet.setText(course.getTiet());
        hd.giangVien.setText(course.getGiaoVien());

        TextDrawable drawable = TextDrawable.builder().buildRound(course.getNameCourse().charAt(0) + "", color);
        hd.avatar.setImageDrawable(drawable);
        hd.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseActivity.class);
                intent.putExtra("course", course);
                intent.putExtra("color", color);
                context.startActivity(intent);
            }
        });
    }

    //Helper methods
    public Course removeCourse(int position) {
        final Integer color = colorList.remove(position);
        final Course course = courseList.remove(position);

        notifyItemChanged(position);
        return course;
    }

    public void addCourse(int position, Course course) {
        ColorGenerator generator = ColorGenerator.MATERIAL;
        colorList.add(position, generator.getRandomColor());
        courseList.add(position, course);
        notifyItemChanged(position);
    }

    public void moveCourse(int fromPostion, int toPosition) {
        final int color = colorList.remove(fromPostion);
        final Course course = courseList.remove(fromPostion);
        colorList.add(color);
        courseList.add(course);

        notifyItemMoved(fromPostion, toPosition);
    }

    public void animateTo(List<Course> courseList) {
        applyAndAnimateRemovals(courseList);
        applyAndAnimateAdditions(courseList);
        applyAndAnimateMovedItems(courseList);
    }

    private void applyAndAnimateRemovals(List<Course> newCourseList) {
        for (int i = courseList.size() - 1; i >= 0; i--) {
            final Course course = courseList.get(i);
            if (!newCourseList.contains(course)) {
                removeCourse(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Course> newCourseList) {
        for (int i = 0; i < newCourseList.size(); i++) {
            final Course course = newCourseList.get(i);
            if (!courseList.contains(course)) {
                addCourse(i, course);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Course> newCourseList) {
        for (int toPosition = newCourseList.size() - 1; toPosition >= 0; toPosition--) {
            final Course course = newCourseList.get(toPosition);
            final int fromPosition = courseList.indexOf(course);
            if (fromPosition >= 0 && toPosition != fromPosition) {
                moveCourse(fromPosition, toPosition);
            }
        }
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {

        TextView idCourse;
        TextView nameCourse;
        TextView tiet;
        TextView giangVien;
        ImageView avatar;
        View container;

        public CourseViewHolder(View parent) {
            super(parent);

            idCourse = (TextView) parent.findViewById(R.id.idCourse);
            nameCourse = (TextView) parent.findViewById(R.id.nameCourse);
            tiet = (TextView) parent.findViewById(R.id.tiet);
            giangVien = (TextView) parent.findViewById(R.id.giangVien);
            avatar = (ImageView) parent.findViewById(R.id.avatar);
            container = (View) parent.findViewById(R.id.container);

        }

    }
}
