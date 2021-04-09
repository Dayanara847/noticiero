package com.henry.noticiero.controller;

import com.henry.noticiero.model.Writer;
import com.henry.noticiero.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/writer")
public class WriterController {

    @Autowired
    private WriterService writerService;

    @GetMapping("/{id}")
    public Writer getWriter(@PathVariable Integer id) {
        return writerService.getWriter(id);
    }

    @PostMapping
    public void addWriter(@RequestBody Writer writer) {
        writerService.addWriter(writer);
    }

    @PutMapping("/{id}/noticias/{noticiaID}")
    private void addNoticia(@PathVariable Integer id, @PathVariable Integer noticiaID) {
        writerService.addNoticia(id, noticiaID);
    }

}
