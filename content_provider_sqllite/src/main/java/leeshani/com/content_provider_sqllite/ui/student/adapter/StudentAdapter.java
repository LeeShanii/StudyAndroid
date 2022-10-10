package leeshani.com.content_provider_sqllite.ui.student.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import leeshani.com.content_provider_sqllite.R;
import leeshani.com.content_provider_sqllite.data.model.Student;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> students;
    private final IClickStudent iClickStudent;

    public interface IClickStudent {
        void editStudent(Student student);

        void deleteStudent(Student student);
    }

    public StudentAdapter(IClickStudent iClickStudent) {
        this.iClickStudent = iClickStudent;
    }

    public void setData(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = students.get(position);
        if (student == null) {
            return;
        }
        holder.tvStudentName.setText(student.getStudentName());
        holder.tvClassName.setText(student.getClasses());
        holder.tvDate.setText(student.getDate());
        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickStudent.editStudent(student);
            }
        });
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickStudent.deleteStudent(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvStudentName;
        private final TextView tvDate;
        private final TextView tvClassName;
        private final TextView tvDelete;
        private final TextView tvEdit;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClassName = itemView.findViewById(R.id.tvClassName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDelete = itemView.findViewById(R.id.tvDelete);
            tvEdit = itemView.findViewById(R.id.tvEdit);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
        }

    }

}