package com.GestionFactura.GestionFactura.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GestionFactura.GestionFactura.Model.Factura;
import com.GestionFactura.GestionFactura.Service.FacturaService;

@RestController
@RequestMapping("api/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

   @GetMapping
    public ResponseEntity<List<Factura>> getFactura(){
        if (facturaService.encontrarFactura().isEmpty()){
            return new ResponseEntity<>(facturaService.encontrarFactura(), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(facturaService.encontrarFactura(), HttpStatus.OK);
        }

    }

    @PostMapping
    public ResponseEntity<Factura> postFactura(@RequestBody Factura factura){
        Factura nuevaFactura = facturaService.crearFactura(factura);
        if (nuevaFactura == null){
            return new ResponseEntity<>(facturaService.crearFactura(nuevaFactura), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(facturaService.crearFactura(nuevaFactura), HttpStatus.CREATED);
    }


    /*@DeleteMapping("/eliminarFactura={alumnoId}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Integer alumnoId){
        facturaService.eliminarFactura(alumnoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
        
    
    
}
