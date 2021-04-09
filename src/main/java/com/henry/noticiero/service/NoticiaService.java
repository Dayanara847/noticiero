package com.henry.noticiero.service;

import com.henry.noticiero.model.Noticia;
import com.henry.noticiero.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    public void addNoticia(Noticia noticia) {
        noticiaRepository.save(noticia);
    }

    public List<Noticia> getAll() {
        return noticiaRepository.findAll();
    }

    public Noticia findNoticiaById(Integer id) {
        return noticiaRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public String editById(Noticia noticia) {
        if(noticiaRepository.existsById(noticia.getId())) {
            noticiaRepository.save(noticia);
            return "La nueva noticia ha sido guardada exitosamente.";
        } else {
            return "La noticia que intenta editar no existe.";
        }
    }

    public String deleteById(Integer id) {
        try {
            noticiaRepository.deleteById(id);
            return "La noticia ha sido eliminada exitosamente.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "La noticia que quiere eliminar no existe.";
        }
    }

    public List<Noticia> findByWriter(Integer id) {
        List<Noticia> noticiasByWriter = new ArrayList<>();
        for(Noticia noticia : noticiaRepository.findAll()) {
            if(!Objects.isNull(noticia.getWriter()) && noticia.getWriter().getId() == id) {
                noticiasByWriter.add(noticia);
            }
        }
        return noticiasByWriter;
    }

}
