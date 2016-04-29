package com.jasraj.HibernateExecution;

import com.jasraj.entity.User;
import com.jasraj.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Jaskirat on 29-04-2016.
 */
public class HibernateMain {
    public static void main(String[] args)
    {
        User user1=new User();
        user1.setName("Krishnav Singh");

        SessionFactory sessionFactory= HibernateUtil.getSession();
        Session sessionOne=sessionFactory.getCurrentSession();

        sessionOne.beginTransaction();

        sessionOne.save(user1);

        sessionOne.getTransaction().commit();

        sessionOne.close();
    }
}
