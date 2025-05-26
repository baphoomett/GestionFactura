package com.GestionFactura.GestionFactura.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.GestionFactura.GestionFactura.Model.Factura;
import com.GestionFactura.GestionFactura.Model.UsuarioDTO;
import com.GestionFactura.GestionFactura.Repository.FacturaRepository;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;  
    
    
    @Autowired
    private RestTemplate restTemplate;

    public Factura crearFactura(Factura factura){
        // Llamada al microservicio cliente
        String url = "http://localhost:8081/api/usuario/" + factura.getIdCliente();
        UsuarioDTO cliente = restTemplate.getForObject(url, UsuarioDTO.class);
        if (cliente != null) {
            factura.setNombreCliente(cliente.getNombre() + " " + cliente.getApellido());
            factura.setCorreoCliente(cliente.getEmail());
        }
        
        return facturaRepository.save(factura);
    }


     public List<Factura> encontrarFactura(){
        return facturaRepository.findAll();
    }

    public void eliminarFactura(Factura factura){
        facturaRepository.delete(factura);
        
    }

   /*  public Factura asignarFactura(Long facturaId, Long clienteId) {
        Factura factura = facturaRepository.findById(facturaId)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        factura.setCliente(cliente); // Suponiendo que la entidad Facturacion tiene una relación con Cliente
        return facturaRepository.save(factura);
    }*/
    
}