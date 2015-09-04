/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.transaction.facade;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.pharma_red_v2.mbr.entity.BatchItemRequirement;
import server.pharma_red_v2.mbr.facade.BatchItemRequirementFacade;
import server.pharma_red_v2.sqlsvr_copy.entity.StockCardC;
import server.pharma_red_v2.sqlsvr_copy.facade.StockCardCFacade;
import server.pharma_red_v2.transaction.entity.StockCardTxn;

/**
 *
 * @author maine
 */
@Stateless
public class StockCardTxnFacade {

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<StockCardTxn> selectAll() {
        return em.createQuery("Select s from StockCardTxn s").getResultList();
    }

//    public List<StockCardTxn> findReservedAndApprovedByItemCd(String itemCd) {
//        return em.createQuery("Select s from StockCardTxn s where s.stockCardId.itemId.itemCd = :itemCd and (s.mbrId.status = :stat or s.mbrId.status = :stat2)")
//                .setParameter("itemCd", itemCd)
//                .setParameter("stat", "RESERVED")
//                .setParameter("stat2", "APPROVED")
//                .getResultList();
//    }
//    public List<StockCardTxn> findReservedAndApprovedByItemCdAndCompanyCd(String itemCd, String companyCd) {
//        return em.createQuery("Select s from StockCardTxn s where s.stockCardId.itemId.itemCd = :itemCd and s.stockCardId.companyId.code = :companyCd and (s.mbrId.status = :stat or s.mbrId.status = :stat2)")
//                .setParameter("itemCd", itemCd)
//                .setParameter("companyCd", companyCd)
//                .setParameter("stat", "RESERVED")
//                .setParameter("stat2", "APPROVED")
//                .getResultList();
//    }
    public List<StockCardTxn> findStockCardByBatchNo(String batchNo) {
        return em.createQuery("SELECT s FROM  StockCardTxn s WHERE s.batchItemReqId.batchId.batchNo = :batchNo")
                .setParameter("batchNo", batchNo)
                .getResultList();
    }

    public List<StockCardTxn> findStockCardByBatchNoAndItemCategory(String batchNo, String category) {
        return em.createQuery("SELECT s FROM  StockCardTxn s WHERE s.batchItemReqId.batchId.batchNo = :batchNo and s.stockCardId.itemId.itemCategoryId.code = :category")
                .setParameter("batchNo", batchNo)
                .setParameter("category", category)
                .getResultList();
    }

    @EJB
    private BatchItemRequirementFacade batchItemReqFacade;
    @EJB
    private StockCardCFacade stkFacade;

    public StockCardTxn insert(int batchId, int stkId, StockCardTxn txn) {
        BatchItemRequirement batchItemReq = batchItemReqFacade.findById(batchId);
        StockCardC stk = stkFacade.findById(stkId);
        txn.setBatchItemReqId(batchItemReq);
        txn.setStockCardId(stk);
        em.persist(txn);
        em.flush();
        return em.find(StockCardTxn.class, txn.getId());
    }

    public void deleteStockCardTxn(int stkId) {
        StockCardTxn txn = em.find(StockCardTxn.class, stkId);
        em.remove(txn);
    }

    public StockCardTxn findById(int id) {
        return em.find(StockCardTxn.class, id);
    }

    public StockCardC getStockCard(int stockCardTxnId) {
        StockCardTxn txn = this.findById(stockCardTxnId);
        StockCardC stk = txn.getStockCardId();
        return stk;
    }

}
