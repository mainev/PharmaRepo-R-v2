/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.sqlsvr_copy.facade;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import server.pharma_red_v2.StockStatus;
import server.pharma_red_v2.sqlsvr_copy.entity.StockCardC;

/**
 *
 * @author maine
 */
@Stateless
public class StockCardCFacade {

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<StockCardC> selectAll() {

        return em.createQuery("Select s from StockCardC s").getResultList();
    }

    public void insert(StockCardC stockCardC) {
        em.persist(stockCardC);
    }

    public StockCardC findById(int id) {
        return em.find(StockCardC.class, id);
    }

    /**
     * This method selects all available stock card from all companies.
     *
     * @param companyCd
     * @param itemCd
     * @return
     */
    public List<StockCardC> findStockCardByItemCd(String item) {

        //temporary warehouseId
        //Raw Materials - Balubad
        short rmWarehouseId = 5;
        short pmWarehouseId = 2;

        String stat = "Approved";

        //add condition for expiry date
        Query query = em.createQuery("Select s from StockCardC s where s.itemId.itemCd = :item and (s.warehouseId.id = :rm_warehouse_id OR s.warehouseId.id =:pm_warehouse_id) and s.inoutMode = :inOutMd and s.status = :stat AND s.stockStatus = :stockStatus order by s.expDate")
                .setParameter("stockStatus", StockStatus.AVAILABLE)
                .setParameter("item", item)
                .setParameter("rm_warehouse_id", rmWarehouseId)
                .setParameter("pm_warehouse_id", pmWarehouseId)
                .setParameter("inOutMd", "I")
                .setParameter("stat", stat);
        return query.getResultList();
    }

    public List<StockCardC> findAvailableStockCardByCompanyCdAndItemId(String companyCd, int itemId) {

        //temporary warehouseId
        //Raw Materials - Balubad
        short rmWarehouseId = 5;
        short pmWarehouseId = 2;

        String stat = "Approved";

        //add condition for expiry date
        Query query = em.createQuery("Select s from StockCardC s where s.companyId.code = :companyCd and s.itemId.id = :itemId and (s.warehouseId.id = :rm_warehouse_id OR s.warehouseId.id =:pm_warehouse_id) and s.inoutMode = :inOutMd and s.status = :stat AND s.stockStatus = :stockStatus AND s.expDate > :dateToday order by s.expDate")
                .setParameter("stockStatus", StockStatus.AVAILABLE)
                .setParameter("companyCd", companyCd)
                .setParameter("itemId", itemId)
                .setParameter("rm_warehouse_id", rmWarehouseId)
                .setParameter("pm_warehouse_id", pmWarehouseId)
                .setParameter("inOutMd", "I")
                .setParameter("stat", stat)
                .setParameter("dateToday", new Date());

        return query.getResultList();
    }

    public List<StockCardC> findAvailableStockCard(int companyId, int itemId) {
        //temporary warehouseId
        //Raw Materials - Balubad
        short rmWarehouseId = 5;
        short pmWarehouseId = 2;

        String stat = "Approved";

        //add condition for expiry date
        Query query = em.createQuery("Select s from StockCardC s where s.companyId.id = :companyId and s.itemId.id = :itemId and (s.warehouseId.id = :rm_warehouse_id OR s.warehouseId.id =:pm_warehouse_id) and s.inoutMode = :inOutMd and s.status = :stat AND s.stockStatus = :stockStatus AND s.expDate > :dateToday order by s.expDate")
                .setParameter("stockStatus", StockStatus.AVAILABLE)
                .setParameter("companyId", companyId)
                .setParameter("itemId", itemId)
                .setParameter("rm_warehouse_id", rmWarehouseId)
                .setParameter("pm_warehouse_id", pmWarehouseId)
                .setParameter("inOutMd", "I")
                .setParameter("stat", stat)
                .setParameter("dateToday", new Date());

        return query.getResultList();
    }

    public void updateStockCardStatusToDepleted(int stkId) {
        StockCardC stk = this.findById(stkId);
        stk.setStockStatus(StockStatus.DEPLETED);
    }

    public void updateStockCardStockStatus(int stkId, StockStatus stockStat) {
        StockCardC stk = this.findById(stkId);
        stk.setStockStatus(stockStat);
    }

    public StockCardC findByControlNo(String controlNo) {
        StockCardC result = new StockCardC();
        try {
            result = (StockCardC) em.createQuery("Select s from StockCardC s where s.controlNo = :controlNo")
                    .setParameter("controlNo", controlNo)
                    .getSingleResult();
            return result;
        } catch (Exception e) {
            System.out.println("Stockcard Search ERROR: Stockcard with control no: " + controlNo + " not found.");
        }
        return null;
    }
}
