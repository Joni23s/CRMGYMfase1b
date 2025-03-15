import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            System.out.println("✅ Conexión exitosa con la base de datos.");
        } catch (Exception e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
        }
    }
}