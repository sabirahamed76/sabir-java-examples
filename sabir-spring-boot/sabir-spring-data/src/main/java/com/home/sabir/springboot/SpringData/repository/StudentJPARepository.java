package com.home.sabir.springboot.SpringData.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.home.sabir.springboot.SpringData.entity.Student;

public interface StudentJPARepository extends JpaRepository<Student, Long>{

	    List<Student> findByFirstName(String firstName);

	    List<Student> findByFirstNameContaining(String name);

	    List<Student> findByLastNameNotNull();

	    Student findByFirstNameAndLastName(String firstName,
	                                       String lastName);

	    //JPQL
	    @Query("select s from Student s where s.email = ?1")
	    Student getStudentByEmailAddress(String email);


	    //JPQL
	    @Query("select s.firstName from Student s where s.email = ?1")
	    String getStudentFirstNameByEmailAddress(String email);

	    //Native
	    @Query(
	            value = "SELECT * FROM students s where s.email = :email",
	            nativeQuery = true
	    )
	    Student getStudentByEmailAddressNative(String email);


	    //Native Named Param
	    @Query(
	            value = "SELECT * FROM students s where s.email = :email",
	            nativeQuery = true
	    )
	    Student getStudentByEmailAddressNativeNamedParam(
	            @Param("email") String email
	    );

	    @Modifying
	    @Transactional
	    @Query(
	            value = "update students set first_name = ?1 where email = ?2",
	            nativeQuery = true
	    )
	    int updateStudentNameByEmailId(String firstName, String email);
}
