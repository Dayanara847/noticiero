package com.henry.noticiero.service;

import com.henry.noticiero.model.Noticia;
import com.henry.noticiero.model.Writer;
import com.henry.noticiero.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

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
        List<Noticia> noticiasList = noticiaRepository.findAll();
        if(noticiasList.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return noticiasList;
    }

    public Noticia findNoticiaById(Integer id) {
        return noticiaRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public String editById(Noticia noticia) {
        try {
            Noticia noticiaToEdit = noticiaRepository.findById(noticia.getId()).orElseThrow(null);
            Writer writer = noticiaToEdit.getWriter();
            noticia.setWriter(writer);
            noticiaRepository.save(noticia);
            return "La nueva noticia ha sido guardada exitosamente.";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La noticia que intenta editar no existe.", e);
        }
    }

    public String deleteById(Integer id) {
        try {
            noticiaRepository.deleteById(id);
            return "La noticia ha sido eliminada exitosamente.";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La noticia que quiere eliminar no existe.", e);
        }
    }

    public List<Noticia> findByWriter(Integer id) {
        List<Noticia> noticiasByWriter = new ArrayList<>();
        for(Noticia noticia : noticiaRepository.findAll()) {
            if(!Objects.isNull(noticia.getWriter()) && noticia.getWriter().getId() == id) {
                noticiasByWriter.add(noticia);
            }
        }
        if(noticiasByWriter.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return noticiasByWriter;
    }

}
