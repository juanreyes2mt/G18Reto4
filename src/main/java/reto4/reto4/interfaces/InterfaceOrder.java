package reto4.reto4.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import reto4.reto4.modelo.ModeloOrder;

public interface InterfaceOrder extends MongoRepository <ModeloOrder, Integer> {
    //Retorna las ordenes de pedido que coincidad con la zona recibida como parametro
    @Query("{'salesMan.zone':?0}")
    List<ModeloOrder> findByZone (final String country);

    //Retorna las ordenes de pedido por estado
    @Query("{'status':?0}")
    List<ModeloOrder> findByStatus (final String status);

    //Seleccionar la orden con el ID mayor
    Optional<ModeloOrder> findTopByOrderByIdDesc();
}
