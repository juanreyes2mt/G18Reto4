package reto4.reto4.repositorio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import reto4.reto4.interfaces.InterfaceOrder;
import reto4.reto4.modelo.ModeloOrder;

@Repository
public class RepositorioOrder {

    @Autowired
    private InterfaceOrder OrderCRUDRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<ModeloOrder> getAll() {
        return (List<ModeloOrder>) OrderCRUDRepository.findAll();
    }

    public Optional<ModeloOrder> getOrder(int id) {
        return OrderCRUDRepository.findById(id);
    }
    
    public ModeloOrder create (ModeloOrder order) {
        return OrderCRUDRepository.save(order);
    }

    public void update (ModeloOrder order) {
        OrderCRUDRepository.save(order);
    }

    public void delete (ModeloOrder order) {
        OrderCRUDRepository.delete(order);
    }

    public List<ModeloOrder> findByZone(String zona) {
        return OrderCRUDRepository.findByZone(zona);
    }

    public Optional<ModeloOrder> lastUserId() {
        return OrderCRUDRepository.findTopByOrderByIdDesc();
    }

    public List<ModeloOrder> orderSalesManByDate(String dateStr, Integer id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        Query query = new Query();
        Criteria dateCriteria = Criteria.where("registerDay")
            .get(LocalDate.parse(dataStr, dtf).minusDays(1).atStartOfDay())
            .lt(LocalDate.parse(dataStr, dtf).plusDays(2).atStartOfDay())
            .and("salesMan.id"). is (id);
        
        query.addCriteria(dateCriteria);
        List<ModeloOrder> orders = mongoTemplate.find(query, ModeloOrder.class);
        return orders;
    }

    public List<ModeloOrder> orderSalesManByState(String dateStr, Integer id){
        Query query = new Query();
        Criteria dateCriteria = Criteria.where("salesMan.id").is(id)
            .and("status").is(state);
            
        query.addCriteria(dateCriteria);
        List<ModeloOrder> orders = mongoTemplate.find(query, ModeloOrder.class);
        return orders;
    }
}