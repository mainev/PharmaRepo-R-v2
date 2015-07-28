/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.sqlsvr.Nutratech_DB.facade;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import server.pharma_red_v2.sqlsvr_copy.entity.StockCardC;
import server.pharma_red_v2.sqlsvr_copy.facade.CompanyCFacade;
import server.pharma_red_v2.sqlsvr_copy.facade.ItemCFacade;
import server.pharma_red_v2.sqlsvr_copy.facade.StockCardCFacade;
import server.pharma_red_v2.sqlsvr_copy.facade.WarehouseCFacade;
import server.sqlsvr.Nutratech_DB.entity.StockCard;

/**
 *
 * @author maine
 */
@Stateless
public class StockCardFacade {

    /*
    
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
    
    /*
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

    /*
    @EJB
    private CompanyCFacade f1;
    @EJB
    private WarehouseCFacade f2;
    @EJB
    private ItemCFacade f3;
    @EJB
    private StockCardCFacade f4;

    public void create() {
        List<StockCard> stList = selectAll();
        for (StockCard sc : stList) {

            StockCardC scc = new StockCardC();

            scc.setCompanyId(f1.findByCode(sc.getCompanyCd()));
            scc.setWarehouseId(f2.findByCode(sc.getWarehouseCd()));
            scc.setInoutMode(sc.getInOutMode());
            scc.setItemId(f3.findByCode(sc.getItemCd().trim()));
            scc.setUnitCost(sc.getUnitCost());
            scc.setQty(sc.getQty());
            scc.setLotNo(sc.getLotNo());
            scc.setMfgDate(sc.getMfgDate());
            scc.setExpDate(sc.getExpiryDate());
            scc.setControlNo(sc.getControlNo());
            scc.setStatus(sc.getStatus());
            scc.setUom(sc.getUom());

            f4.insert(scc);

        }
    }
    */
    
    
}
