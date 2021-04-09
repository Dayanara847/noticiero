package com.henry.noticiero.service;

import com.henry.noticiero.model.Noticia;
import com.henry.noticiero.model.Writer;
import com.henry.noticiero.repository.WriterRepository;
import com.henry.noticiero.utils.EntityURLBuilder;
import com.henry.noticiero.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class WriterService {

    //@Autowired --> Creamos el constructor, es como poner un Autowired a cada una de las líneas que están abajo: 15 y 16
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
        return writerRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void addNoticia(Integer id, Integer noticiaID) {
        Writer writer = getWriter(id);
        Noticia noticia = noticiaService.findNoticiaById(noticiaID);

        noticia.setWriter(writer);
        //writer.getNoticiaList().add(noticia);
        writerRepository.save(writer);
    }

}
