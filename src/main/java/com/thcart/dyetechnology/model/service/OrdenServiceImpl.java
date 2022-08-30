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
        //UN ENTERO NUMERO DONDE SE INCREMENTARA EL NUMERO DE LA ORDEN PARA PASARLO A UN STRING MÁS ADELANTE.
        int numero = 0;
        //UNA CADENA NUMERO CONCATENADO, QUE SE UTILIZA PARA DEVOLVER EL STRING CON EL NÚMERO DE LA ORDEN GENERADO.
        //ESTE STRING ESTARA VACIO PARA LUEGO RELLENARLO.
		String numeroConcatenado = "";
		//SE CREA UNA LISTA DE ORDENES Y EL CUAL BUSCA POR JPA TODAS LAS ORDENES QUE SE ENCUENTRA EN LA BASE DE DATOS.
        // utilizando la función del Service: buscarTodos();
		List<Orden> ordenes = buscarTodos();
		//SE REALIZA OTRA LISTA PERO EN ESTE CASO DE TIPO INTEGER O ENTEROS, EL CUAL SE LLAMARA NUMEROS.
		List<Integer> numeros= new ArrayList<Integer>();
		//FUNCIONES DE JAVA 8
        //STREAM(): FLUJO.
        //FOREACH para recorrer de una sola.
        // o: FUNCION ANONIMA.
        // -> funcion de flecha.
        // add(): se agregara lo que tenga el atributo.
		ordenes.stream().forEach(o -> numeros.add( Integer.parseInt( o.getNumero())));
		
        //si ordenes viene vacio, se lo establece a 1 a numero.
		if (ordenes.isEmpty()) {
			numero = 1;
		}else {
            //SE COLOCA EL MAYOR VALOR DEL NUMERO. LLAMANDO A LA LISTA DE NUMEROS SELECCIONANDO EL MAXIMO,
            //Y SE UTILIZA UN COMPARADOR INTEGER Y LUEGO SE OBTIENE EL MAYOR NUMERO DE TODA ESA LISTA CON UN GET().
			numero = numeros.stream().max(Integer::compare).get();
            //Y LUEGO SE INCREMENTA AL ULTIMO NRO DE LA ORDEN.
			numero++;
		}
		
		if (numero<10) { //DE 0 A 9
			numeroConcatenado="000000000"+String.valueOf(numero);
		}else if(numero<100) { //DE 10 A 99
			numeroConcatenado="00000000"+String.valueOf(numero);
		}else if(numero<1000) { //DE 100 A 999
			numeroConcatenado="0000000"+String.valueOf(numero);
		}else if(numero<10000) { //DE 1000 A 9999
			numeroConcatenado="000000"+String.valueOf(numero);
		}else if(numero<100000) { //DE 10000 A 99999
			numeroConcatenado="00000"+String.valueOf(numero);
		}else if(numero<1000000) { //DE 100000 A 999999
			numeroConcatenado="0000"+String.valueOf(numero);
		}else if(numero<10000000) { //DE 1000000 A 9999999
			numeroConcatenado="000"+String.valueOf(numero);
		}else if(numero<100000000) { //DE 10000000 A 99999999
			numeroConcatenado="00"+String.valueOf(numero);
		}else if(numero<1000000000) { //DE 100000000 A 999999999
			numeroConcatenado="0"+String.valueOf(numero);
		}			
		
		return numeroConcatenado;
    }
    

    
}
