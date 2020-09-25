/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import ws.Client;
import ws.service.ClientFacadeREST;

/**
 * REST Web Service
 *
 * @author Zouheir
 */
@Path("utils")
public class Utils {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Utils
     */
    public Utils() {
    }
    
    @GET
    @Produces("application/xml")
    @Path("check/{username}/{password}")
    public String checkClient(@PathParam("username") String usrname, @PathParam("password") String password) {
        ClientFacadeREST cf = new ClientFacadeREST();
        List<Client> list = cf.findAll();
        System.out.println(list.equals(cf));
        for(Client C:list)
        {
            System.out.println(C.getUsername());
            if(C.getUsername().equalsIgnoreCase(usrname) && C.getPassword().equals(password)){
                return 1+"";
            }
        }
        return 0+"";  
    }
    /**
     * Retrieves representation of an instance of ws.Utils
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of Utils
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
    
    
    public static void main(String[] args){
        Utils u = new Utils();
        u.checkClient("zouheir", "banou");
    }
}
