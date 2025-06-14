package com.home.sabir.hibernate.LazyLoading;

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

            //Extra:
            //To break the LAZY loading just close the session here and commit the session before close
            //session.getTransaction().commit();
            //session.close();
            //since our courses are lazy loaded this schould now fail!, because we close the session before all the courses are loaded and we want to access it afterwards.
            //to solve this problem:
            //option 1: load data before the session is closed
            //option 2: query with HQL -> see Main2

            System.out.println("Courses: " + instructor.getCourses()); //Courses will be loaded here when they are requested

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
