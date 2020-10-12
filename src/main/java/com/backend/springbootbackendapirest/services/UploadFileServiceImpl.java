package com.backend.springbootbackendapirest.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadFileServiceImpl implements IUploadFileService {

    private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
    private final static String DIRECTORIO_UPLOAD = "uploads";

    @Override
    public Resource cargar(String nombreFoto) throws MalformedURLException {

        // obtengo la ruta donde se encuentra la foto enviada en la url

        Path rutaArchivo = getPath(nombreFoto);

        // muestro la ruta en el log
        log.info(rutaArchivo.toString());

        // creo el recurso que me permite retornar la foto asignandole la ruta
        Resource recurso = null;

        recurso = new UrlResource(rutaArchivo.toUri());

        if (!recurso.exists() && !recurso.isReadable()) {

            // si no encuentra la imagen devuelvo la foto predeterminada
            rutaArchivo = Paths.get("src/main/resources/static/images").resolve("sinFoto.png").toAbsolutePath();

            recurso = new UrlResource(rutaArchivo.toUri());

            log.error("Error no se pudo cargar la imagen: " + nombreFoto);

        }
        return recurso;
    }

    @Override
    public String copiar(MultipartFile archivo) throws IOException {
                    // agrego un valor aleatorio al nombre de la foto para que sea un nombre unico
                    String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
                    Path rutaArchivo = getPath(nombreArchivo);
        
                    // muestro la ruta en el log
                    log.info(rutaArchivo.toString());
                  
                        // copio el archivo del cliente al servidor
                        Files.copy(archivo.getInputStream(), rutaArchivo);
            
        return nombreArchivo;
    }

    @Override
    public boolean eliminar(String nombreFoto) {
        if (nombreFoto != null && nombreFoto.length() > 0) {
            Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
            File archivoFotoAnterior = rutaFotoAnterior.toFile();
            if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                archivoFotoAnterior.delete();
                return true;
            }

        }
        return false;
    }

    @Override
    public Path getPath(String nombreFoto) {

        return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
    }

}
