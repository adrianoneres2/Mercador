package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
 
    public static final SessionFactory session = buildSession();
 

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSession() {
 
    try{
         Configuration cfg = new Configuration();
                       cfg.configure("persistence.xml");
 
        return cfg.buildSessionFactory();
 
       }catch(Throwable b){
 
            System.out.println("Não deu \n" + b);
            throw new ExceptionInInitializerError();
       }
   }
 
   public static SessionFactory getSessionFactory(){
       return session;
   }
}