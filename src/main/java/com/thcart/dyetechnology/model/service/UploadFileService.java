package com.thcart.dyetechnology.model.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//ESTE METODO PERMITE SUBIR Y ELIMINAR IMAGEN QUE YA ESTE EN LA BASE DE DATOS.
@Service
public class UploadFileService {

    // TIENE LA UBICACION DEL PROYECTO DONDE SE VAN A CARGAR LAS IMAGENES.
    private String folder="src/resources/static/img//";

    public String saveImage(MultipartFile file) throws IOException{
        if(!file.isEmpty()){
            byte [] bytes=file.getBytes();
            Path path = Paths.get(folder+file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getOriginalFilename();
        }
        return "default.png";
    }

    public void deleteImage(String nombreImg){
        String ruta = "img//";
        File file = new File(ruta+nombreImg);
        file.delete();
    }
    
}
