/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.sqlsvr.Nutratech_DB.facade;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import server.sqlsvr.Nutratech_DB.entity.StockCard;

/**
 *
 * @author maine
 */
@Stateless
public class StockCardFacade {

    @PersistenceContext(unitName = "sqlsvrPU")
    private EntityManager em;

    public List<StockCard> selectAll() {
        return em.createQuery("Select s from StockCard s").getResultList();
    }

    /**
     * *
     * WARNING: This is a temporary method for calculating the remaining
     * quantity of an item in the sqlsvr. Revise this method for a more precise
     * result. Change the conditions if necessary.
     *
     * @param itemCd - code of the item to be checked
     * @return remaining quantity of the item
     */
    public float selectMaterialQuantity(String itemCd) {
        Date today = new Date();
        double result = 0;
        double in = 0;
        double out = 0;
        Query received = em.createQuery("SELECT SUM(s.qty) from StockCard s where s.status <> :status and s.expiryDate >= :today and s.inOutMode = :inout and s.itemCd = :itemCd")
                .setParameter("status", "Disapprove")
                .setParameter("today", today)
                .setParameter("inout", 'I')
                .setParameter("itemCd", itemCd);
        Query issued = em.createQuery("Select SUM(s.qty) from StockCard s where s.inOutMode = :inout and s.itemCd = :itemCd")
                .setParameter("inout", 'O')
                .setParameter("itemCd", itemCd);
        if (received.getSingleResult() != null) {
            in = (double) received.getSingleResult();
        }
        if (issued.getSingleResult() != null) {
            out = (double) issued.getSingleResult();
        }
        result = in - out;
        return (float) result;
    }
}
