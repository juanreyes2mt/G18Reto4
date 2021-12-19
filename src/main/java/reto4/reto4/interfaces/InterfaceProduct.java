package reto4.reto4.interfaces;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import reto4.reto4.modelo.ModeloProduct;

public interface InterfaceProduct extends MongoRepository <ModeloProduct, Integer> {
    Optional<ModeloProduct> findByReference(String reference);
    
    public List<ModeloProduct> findByPriceLessThanEqual(double precio);
    public List<ModeloProduct> findByDescriptionLike(String description); 

   
}
