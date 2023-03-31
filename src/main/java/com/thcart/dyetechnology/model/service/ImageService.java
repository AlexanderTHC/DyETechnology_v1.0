package com.thcart.dyetechnology.model.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImageService 
{
    private final String folder = "assets//";
    
    public String saveImage(MultipartFile file) throws IOException
    {
        if(!file.isEmpty())
        {
            // Generar nombre de imagen
            final String ext = getFileExtension(file.getOriginalFilename()).get(); // Extraemos la extensión de la imagen
            final String filename = UUID.randomUUID().toString() + "." + ext; // Creamos un string unico + extension
            
            // Guardar imagen en los assets
            byte[] bytes = file.getBytes();
            Path path = Paths.get(folder + filename);
            Files.write(path, bytes);

            return filename;
        }
        return "default.jpg";
    }

    public void deleteImage(String name)
    {
        File file = new File(folder + name);
        file.delete();
    }

    public Optional<String> getFileExtension(String filename) 
    {
        return Optional.ofNullable(filename)
          .filter(f -> f.contains("."))
          .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}