package control;

import lombok.extern.slf4j.Slf4j;
import model.archive.Archive;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * Controls the data flow between the program and the online database.
 */
@Slf4j
public class ComControll
{
    /**
     * Archive object to be uploaded.
     */
    Archive input;
    /**
     * Downloaded Archive object.
     */
    Archive output;
    /**
     * The Entity Manager.
     */
    EntityManager entityManager;
    /**
     * EM factory.
     */
    EntityManagerFactory emf;

    /**
     * Creates the Entity Managger.
     */
    public ComControll()
    {
        emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
        entityManager = emf.createEntityManager();
    }

    /**
     * Sets the output to the given Archive object.
     * @param temmp the Archive object to be sent.
     */
    public void setOutput(Archive temmp)
    {
        output = temmp;
    }


    /**
     * Ask the last 10 interactions from the database.
     * @return is the least 10 interactions.
     */
    public List<Archive> getHystorry()
    {
        TypedQuery<Archive> q = entityManager.createQuery("Select e From Archive e",Archive.class)
                .setMaxResults(10);
        List<Archive> temp = q.getResultList();
        return temp;
    }

    /**
     * Uploads the output to tha database.
     */
    public void upload()
    {
        entityManager.getTransaction().begin();
        entityManager.persist(output);
        entityManager.getTransaction().commit();
        log.info("A new registry was uploaded to the database");
    }

    /**
     * CLoses the entety managger.
     */
    public void closeInteraction()
    {
        entityManager.close();
    }
}
