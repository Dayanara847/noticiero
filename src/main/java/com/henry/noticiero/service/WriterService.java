package com.henry.noticiero.service;

import com.henry.noticiero.model.Noticia;
import com.henry.noticiero.model.Writer;
import com.henry.noticiero.repository.WriterRepository;
import com.henry.noticiero.utils.EntityURLBuilder;
import com.henry.noticiero.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WriterService {

    private static final String WRITER_PATH = "writer";
    private WriterRepository writerRepository;
    private NoticiaService noticiaService;

    @Autowired
    public WriterService(WriterRepository writerRepository, NoticiaService noticiaService) {
        this.writerRepository = writerRepository;
        this.noticiaService = noticiaService;
    }

    public PostResponse addWriter(Writer writer) {
        final Writer writerSaved = writerRepository.save(writer);

        return PostResponse.builder()
                .status(HttpStatus.CREATED)
                .url(EntityURLBuilder.buildURL(WRITER_PATH, writerSaved.getId().toString()))
                .build();
    }

    public Writer getWriter(Integer id) {
        return writerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Writer> getAllWriter() {
        List<Writer> writersList = writerRepository.findAll();
        if(writersList.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return writersList;
    }

    public void addNoticia(Integer id, Integer noticiaID) {
        Writer writer = getWriter(id);
        Noticia noticia = noticiaService.findNoticiaById(noticiaID);

        noticia.setWriter(writer);
        writerRepository.save(writer);
    }

}
