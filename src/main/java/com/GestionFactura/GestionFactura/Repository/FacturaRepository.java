package com.GestionFactura.GestionFactura.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GestionFactura.GestionFactura.Model.Factura;



@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer>{
    
}
