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
import server.pharma_red_v2.mbr.entity.Mbr;
import server.pharma_red_v2.mbr.facade.MbrFacade;
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

    public List<StockCardTxn> findReservedAndApprovedByItemCd(String itemCd) {
        return em.createQuery("Select s from StockCardTxn s where s.stockCardId.itemId.itemCd = :itemCd and (s.mbrId.status = :stat or s.mbrId.status = :stat2)")
                .setParameter("itemCd", itemCd)
                .setParameter("stat", "RESERVED")
                .setParameter("stat2", "APPROVED")
                .getResultList();
    }
    
     public List<StockCardTxn> findReservedAndApprovedByItemCdAndCompanyCd(String itemCd, String companyCd) {
        return em.createQuery("Select s from StockCardTxn s where s.stockCardId.itemId.itemCd = :itemCd and s.stockCardId.companyId.code = :companyCd and (s.mbrId.status = :stat or s.mbrId.status = :stat2)")
                .setParameter("itemCd", itemCd)
                .setParameter("companyCd", companyCd)
                .setParameter("stat", "RESERVED")
                .setParameter("stat2", "APPROVED")
                .getResultList();
    }

    @EJB
    private MbrFacade mbrFacade;
    @EJB
    private StockCardCFacade stkFacade;

    public StockCardTxn insert(int mbrId, int stkId, StockCardTxn txn) {
        Mbr mbr = mbrFacade.findById(mbrId);
        StockCardC stk = stkFacade.findById(stkId);
        txn.setMbrId(mbr);
        txn.setStockCardId(stk);
        em.persist(txn);
        em.flush();
        return em.find(StockCardTxn.class, txn.getId());
    }
}
