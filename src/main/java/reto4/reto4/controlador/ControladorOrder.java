package reto4.reto4.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reto4.reto4.modelo.ModeloOrder;
import reto4.reto4.servicios.ServiciosOrder;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class ControladorOrder {
    
    @Autowired
    private ServiciosOrder orderService;

    @GetMapping("/all")
    public List<ModeloOrder> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<ModeloOrder> getOrder(@PathVariable("id") int id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ModeloOrder create(@RequestBody ModeloOrder order) {
        return orderService.create(order);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ModeloOrder update(@RequestBody ModeloOrder order) {
        return orderService.update(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return orderService.delete(id);
    }

    //Ordenes de pedido asosciadas a los asesores
    @GetMapping("/zona/{zona}")
    public List<ModeloOrder> findByZone(@PathVariable("zona") String zona){
       return orderService.findByZone(zona);
    }
}
