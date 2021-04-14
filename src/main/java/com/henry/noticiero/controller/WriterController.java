package com.henry.noticiero.controller;

import com.henry.noticiero.converter.WriterToWriterDTOConvert;
import com.henry.noticiero.model.Writer;
import com.henry.noticiero.model.dto.WriterDTO;
import com.henry.noticiero.service.WriterService;
import com.henry.noticiero.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/writer")
public class WriterController {

    @Autowired
    private WriterService writerService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private WriterToWriterDTOConvert writerToWriterDTOConvert;

    @GetMapping("/{id}")
    public WriterDTO getWriter(@PathVariable Integer id) {
        return conversionService.convert(writerService.getWriter(id), WriterDTO.class);
    }

    @GetMapping
    public List<WriterDTO> getAllWriter() {
        return conversionService.convert(writerService.getAllWriter(), List.class);
    }

    @PostMapping
    public PostResponse addWriter(@RequestBody Writer writer) {
        return writerService.addWriter(writer);
    }

    @PutMapping("/{id}/noticias/{noticiaID}")
    private void addNoticia(@PathVariable Integer id, @PathVariable Integer noticiaID) {
        writerService.addNoticia(id, noticiaID);
    }

}
