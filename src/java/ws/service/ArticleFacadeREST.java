/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

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
import ws.*;

/**
 *
 * @author Zouheir
 */
@Stateless
@Path("articlestore")
public class ArticleFacadeREST extends AbstractFacade<Article> {
    @PersistenceContext(unitName = "RESTful_EShopPU")
    private EntityManager em;

    public ArticleFacadeREST() {
        super(Article.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Article entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Article entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("find/{id}")
    @Produces({"application/xml", "application/json"})
    public Article find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    @Path("listAll")
    public List<Article> findAll() {
        return super.findAll();
    }

    @GET
    @Path("list/{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Article> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("simcount/{name}")
    @Produces("text/plain")
    public int simcount(@PathParam("name") String name)
    {
        List<Article> list = this.findAll();
        int count = 0;
        for(Article A:list)
        {
            if(A.getName().equalsIgnoreCase(name)){
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
