package com.jasraj.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Jaskirat on 29-04-2016.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactoryObject;
    private static SessionFactory buildSessionFactory(){
        try
        {
            Configuration config=new Configuration();
            config.configure("/resources/hibernate.cfg.xml");
            System.out.println("Hibernate Configuration Loaded");

            ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            System.out.println("Hibernate Service Registry Created");

            SessionFactory sessionFactory=config.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSession()
    {
        if(sessionFactoryObject==null)
            sessionFactoryObject=buildSessionFactory();
        return sessionFactoryObject;
    }
}
