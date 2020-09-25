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
import ws.Client;

/**
 *
 * @author Zouheir
 */
@Stateless
@Path("client")
public class ClientFacadeREST extends AbstractFacade<Client> {
    @PersistenceContext(unitName = "RESTful_EShopPU")
    private EntityManager em;

    public ClientFacadeREST() {
        super(Client.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Client entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Client entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("find/{id}")
    @Produces({"application/xml", "application/json"})
    public Client find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    @Path("listAll")
    public List<Client> findAll() {
        return super.findAll();
    }

    @GET
    @Path("list/{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Client> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Produces("text/plain")
    @Path("check/{username}/{password}")
    public String checkClient(@PathParam("username") String usrname, @PathParam("password") String password) {
        List<Client> list = this.findAll();
        for(Client C:list)
        {
            System.out.println(C.getUsername());
            if(C.getUsername().equalsIgnoreCase(usrname) && C.getPassword().equals(password)){
                return C.getId()+"";
            }
        }
        return -1+"";  
    }
    
    @GET
    @Produces({"application/xml", "application/json"})
    @Path("findByName/{username}")
    public Client getByName(@PathParam("username") String usrname) 
    {
        List<Client> list = this.findAll();
        for(Client C:list)
        {
            if(C.getUsername().equalsIgnoreCase(usrname)){
                return C;
            }
        }
        return null;   
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
