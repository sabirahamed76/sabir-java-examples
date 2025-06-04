package com.home.sabir.springboot.SpringData.extractor;

import com.home.sabir.springboot.SpringData.entity.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentResultSetExtractor implements ResultSetExtractor<List<Student>> {

    @Override
    public List<Student> extractData(ResultSet res) throws SQLException, DataAccessException {
        List<Student> stdList=new ArrayList<Student>();

        while (res.next()){
            Student student=new Student();
            student.setId(Long.valueOf(res.getInt("id")));
            student.setFirstName(res.getString("first_name"));
            student.setLastName(res.getString("last_name"));
            student.setEmail(res.getString("email"));
            stdList.add(student);
        }
        return stdList;

    }
}
