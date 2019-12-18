package com.example.dbcrud.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbcrud.DatabaseHelper;
import com.example.dbcrud.EditDetails;
import com.example.dbcrud.R;
import com.example.dbcrud.models.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private final Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }


    @NonNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_student_list, parent, false);
        return (new StudentViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, final int position) {
        final Student student = studentList.get(position);
        holder.txtName.setText(student.getName());
        holder.txtEmail.setText(student.getEmail());
        holder.txtPhone.setText(student.getPhone());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(context);
                db.deleteStudent(student.getId());

                studentList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditDetails.class);
                intent.putExtra("id", String.valueOf(student.getId()));
                intent.putExtra("name", student.getName());
                intent.putExtra("email", student.getEmail());
                intent.putExtra("phone", student.getPhone());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtEmail, txtPhone;
        ImageView btnEdit, btnDelete;

        public StudentViewHolder(View view) {
            super(view);
                    
        txtName = itemView.findViewById(R.id.txtName);
        txtEmail = itemView.findViewById(R.id.txtEmail);
        txtPhone = itemView.findViewById(R.id.txtPhone);
        btnEdit = itemView.findViewById(R.id.imageEdit);
        btnDelete = itemView.findViewById(R.id.imageDelete);

        }
    }
}