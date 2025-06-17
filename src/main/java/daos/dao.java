package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class dao {
    protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("AmazonPU");
}
