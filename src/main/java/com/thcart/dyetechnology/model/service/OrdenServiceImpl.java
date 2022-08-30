package com.thcart.dyetechnology.model.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thcart.dyetechnology.model.entities.Orden;
import com.thcart.dyetechnology.model.repository.IOrdenRepository;


@Service
public class OrdenServiceImpl implements IOrdenService {

    @Autowired
    IOrdenRepository ordenRepo;

    @Override
    public List<Orden> buscarTodos() {
        return ordenRepo.findAll();
    }

    @Override
    public List<Orden> buscarPor(String criterio) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Orden buscarPorId(Long id) {
        return ordenRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Orden orden) {
        ordenRepo.save(orden);
        
    }

    @Override
    public void borrarPorId(Long id) {
        ordenRepo.deleteById(id);
    }

    @Override
    public void activo(long id) {
        ordenRepo.activo(id);
        
    }

    //GENERACIÓN DEL NRO DE ORDEN:
    public String generarNumeroOrden(){
        int numero=0;
		String numeroConcatenado="";
		
		List<Orden> ordenes = buscarTodos();
		
		List<Integer> numeros= new ArrayList<Integer>();
		
		ordenes.stream().forEach(o -> numeros.add( Integer.parseInt( o.getNumero())));
		
		if (ordenes.isEmpty()) {
			numero=1;
		}else {
			numero= numeros.stream().max(Integer::compare).get();
			numero++;
		}
		
		if (numero<10) { //0000001000
			numeroConcatenado="000000000"+String.valueOf(numero);
		}else if(numero<100) {
			numeroConcatenado="00000000"+String.valueOf(numero);
		}else if(numero<1000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
		}else if(numero<10000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
		}		
		
		return numeroConcatenado;



        return "";
    }
    

    
}
