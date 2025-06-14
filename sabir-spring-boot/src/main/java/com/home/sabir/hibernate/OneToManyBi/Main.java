package com.home.sabir.hibernate.OneToManyBi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //start transaction
            session.beginTransaction();

            //get the instructor
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("Courses: " + instructor.getCourses());

            Course course = new Course("swimming adv course");
            Course course1 = new Course("guitar");

            instructor.add(course);
            instructor.add(course1);

            session.save(course);
            session.save(course1);

            session.delete(course1);


            //commit transaction
            session.getTransaction().commit();

        } catch(Exception e){
            e.printStackTrace();
        } finally{
            //close session if exception is thrown
            session.close();

            factory.close();
        }
    }
}
