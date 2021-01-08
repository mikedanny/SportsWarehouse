package com.app.controller;

import com.app.model.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Parameter;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SportController {

    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory(){
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml").addAnnotatedClass(Item.class);
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

    //TODO rename com.app into com.SportShop. Renaming database would help too.
    @RequestMapping("/Products/{id}")
    public String getItemDescription(@PathVariable("id") int id) throws JsonProcessingException {
        String itemAsString = "";
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();
            Item item = (Item) sessionObj.createQuery(" from Item where id=" + id).list().get(0);
            ObjectMapper objectMapper = new ObjectMapper();
            itemAsString = objectMapper.writeValueAsString(item);
            sessionObj.getTransaction().commit();
        } catch (Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null){
                sessionObj.close();
            }
        }
        return itemAsString;
    }

    //TODO new route that should be /Products which will display a list of products from the DB in json format.
    //features: 1. sa pot afisa cate produse doreste userul pe o pagina.
    //2. sa pot naviga prin toate paginile. Exemplu: /Products?size=2&page=2
    //TODO read REST and pagination. Pagination of DB too.

    @RequestMapping("/Products")
    public String getPage(@RequestParam int size, @RequestParam int page){
        String itemAsString = "";
        int offset = 1;
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();
            if (page > 1) {
                offset = offset + size;
            }
            List<Item> items = sessionObj.createQuery(" from Item ORDER BY ItemID LIMIT 1 OFFSET 100").list();
            ObjectMapper objectMapper = new ObjectMapper();
            itemAsString = objectMapper.writeValueAsString(items);
            sessionObj.getTransaction().commit();
        } catch (Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null){
                sessionObj.close();
            }
        }
        return itemAsString;
    }

//    @RequestMapping("/Products")
//    public String getProducts(){
//        String itemsAsString = "";
//        try {
//            sessionObj = buildSessionFactory().openSession();
//            sessionObj.beginTransaction();
//            List<Item> items = sessionObj.createQuery(" from Item").list();
//            ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
//            itemsAsString = objectMapper.writeValueAsString(items);
////            itemsAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(items);
//
//            sessionObj.getTransaction().commit();
//        } catch (Exception sqlException) {
//            if(null != sessionObj.getTransaction()) {
//                System.out.println("\n.......Transaction Is Being Rolled Back.......");
//                sessionObj.getTransaction().rollback();
//            }
//            sqlException.printStackTrace();
//        } finally {
//            if (sessionObj != null){
//                sessionObj.close();
//            }
//        }
//        return itemsAsString;
//    }

}
