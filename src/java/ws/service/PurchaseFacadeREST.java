/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import ws.Purchase;

/**
 *
 * @author Zouheir
 */
@Stateless
@Path("purchase")
public class PurchaseFacadeREST extends AbstractFacade<Purchase> {
    @PersistenceContext(unitName = "RESTful_EShopPU")
    private EntityManager em;

    public PurchaseFacadeREST() {
        super(Purchase.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Purchase entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Purchase entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Purchase find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Purchase> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Purchase> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("clientCart/{id}")
    @Produces({"application/xml", "application/json"})
    public List<Purchase> purchasesByClient(@PathParam("id") int id) {
        List<Purchase> list = this.findAll();
        List<Purchase> new_list = new ArrayList<Purchase>();
        
        int count = 0;
        for(Purchase P:list)
        {
            if(P.getClientId().getId() == id && P.getType().equals("buy")){
                new_list.add(P);
            }
        }
        return new_list;
    }
    
    @GET
    @Path("clientCart/{client_id}/{article_id}")
    @Produces("text/plain")
    public int purchasesByClient(@PathParam("client_id") int C, @PathParam("article_id") int A)
    {
        List<Purchase> list = this.purchasesByClient(C);
        int count = 0;
        for(Purchase P:list)
        {
            if(P.getArticleId().getId() == A){
                count++;
            }
        }
        return count;
    }
    
    @GET
    @Path("clientFav/{id}")
    @Produces({"application/xml", "application/json"})
    public List<Purchase> favByClient(@PathParam("id") int id) {
        List<Purchase> list = this.findAll();
        List<Purchase> new_list = new ArrayList<Purchase>();
        
        int count = 0;
        for(Purchase P:list)
        {
            if(P.getClientId().getId() == id && P.getType().equals("fav")){
                new_list.add(P);
            }
        }
        return new_list;
    }
    
    @GET
    @Path("clientFav/{client_id}/{article_id}")
    @Produces("text/plain")
    public int favByClient(@PathParam("client_id") int C, @PathParam("article_id") int A)
    {
        List<Purchase> list = this.favByClient(C);
        int count = 0;
        for(Purchase P:list)
        {
            if(P.getArticleId().getId() == A){
                count++;
            }
        }
        return count;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
