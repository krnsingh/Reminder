package com.jasraj.HibernateExecution;

import com.jasraj.entity.User;
import com.jasraj.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HibernateMain {
     private static Session sessionOne;
     private static SessionFactory sessionFactory;
    public static void insert()
    {
        User user1 = new User();
        user1.setName("Harkirat Singh");
        sessionOne = sessionFactory.getCurrentSession();
        sessionOne.beginTransaction();
        sessionOne.save(user1);
        sessionOne.getTransaction().commit();

        HibernateUtil.shutdown();
    }

    public static void retrieve()
    {
        List userobjects;
        User obj;
        try {

            sessionOne = sessionFactory.getCurrentSession();
            sessionOne.beginTransaction();
            Query query = sessionOne.createQuery("from User");
            userobjects = query.list();
            for(Object ob: userobjects)
            {
                obj = (User) ob;
                System.out.println(obj.getName());
            }

            sessionOne.getTransaction().commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            HibernateUtil.shutdown();
        }

    }
    public static void main(String[] args)
    {
        sessionFactory = HibernateUtil.getSession();
        retrieve();
    }
}
