/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SB;

import Entity.Testtb;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pooja
 */
@Stateless
public class TestBean {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    public Collection<Testtb> displayTest(){
        return em.createNamedQuery("Testtb.findAll").getResultList();
    }
    
    public void deleteTest(Integer test_id){
        Testtb t = em.find(Testtb.class, test_id);
        em.remove(t);
    }
    
    public void insertTest(String test_name,String test_type){
        Testtb tb = new Testtb();
        tb.setTestName(test_name);
        tb.setTestType(test_type);
        
        em.persist(tb);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
