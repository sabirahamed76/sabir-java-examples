package com.home.sabir.springboot.SpringData.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.home.sabir.springboot.SpringData.entity.Student;
import com.home.sabir.springboot.SpringData.rowmapper.CustomStudentRowMapper;

@Repository
public class StudentJDBCTemplateRepository {


    private final JdbcTemplate jdbcTemplate;

    public StudentJDBCTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

  
    
    @Transactional(readOnly=true)
    public int saveStudent(Student student) {
        return jdbcTemplate.update("INSERT INTO students (email) VALUES (?)",  student.getEmail());
    }
    
    @Transactional(readOnly=true)
    public Student create(Student student) {
		final String sql = "insert into students(first_name,last_name,email) values(?,?,?)";
		
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {           

                @Override
                public PreparedStatement createPreparedStatement(Connection connection)
                        throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, student.getFirstName());
                    ps.setString(2, student.getLastName());
                    ps.setString(3, student.getEmail());
                    return ps;
                }
            }, holder);

		int newStudentId = holder.getKey().intValue();
		student.setId(Long.valueOf(newStudentId));		
		return student;
	}
    

    @Transactional(readOnly=true)
    public int updateStudent(Long id, String name, String email) {
        return jdbcTemplate.update("UPDATE students SET email = ? WHERE id = ?",  email, id);
    }

    @Transactional(readOnly=true)
    public int deleteStudent(Long id) {
        return jdbcTemplate.update("DELETE FROM students WHERE id = ?", id);
    }
    
    //--------------------Getting Single Value----------------
    //Get Count from table as single value from  jdbcTemplate queryForObject
    public int countStudents() {
    	String sql = "SELECT COUNT(*) FROM students";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    //Get Email from table as single value from jdbcTemplate queryForObject
	public String findStudentEmailById(Long id) {
        String sql = "SELECT email FROM students WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);

    }

	//-------------------Getting Single Object------------------------------
    //Get Single Student Object by using BeanPropertyRowMapper from JDBCTemplate queryForObject
    public Student getStudentById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM students WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Student.class));
    }
    
    //Get Single Student Object by using Custom RowMapper from JDBCTemplate queryForObject
    public Student findByStudentId(Long id) {

        String sql = "SELECT * FROM students WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CustomStudentRowMapper());

    }
    
    //Get Single Student Object by Direct Mapping to Student Object from JDBCTemplate queryForObject
    public Student findByStudentIdDirect(Long id) {
        String sql = "SELECT *FROM students WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Student(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                ));
    }
    
  
  //-------------------Getting Multiple Student as List------------------------------
    //Get Multiple Student List by using BeanPropertyRowMapper from jdbcTemplate query
    public List<Student> getAllStudents() {
        return jdbcTemplate.query("SELECT * FROM students", new BeanPropertyRowMapper<>(Student.class));
    }
    

    //Direct Mapping to all Student Objects into List  from jdbcTemplate query
    public List<Student> listAllStudents() {

        String sql = "SELECT * FROM students";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Student(
                                rs.getLong("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email")
                        )
        );
    }
    
    
    
    
    
}
