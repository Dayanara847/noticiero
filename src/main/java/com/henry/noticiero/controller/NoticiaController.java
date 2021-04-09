package com.henry.noticiero.controller;

import com.henry.noticiero.model.Noticia;
import com.henry.noticiero.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticia")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @PostMapping
    public void addNoticia(@RequestBody Noticia noticia) {
        noticiaService.addNoticia(noticia);
    }

    @GetMapping
    public List<Noticia> getAll() {
        return noticiaService.getAll();
    }

    @GetMapping("/{id}")
    public Noticia findById(@PathVariable Integer id) {
        return noticiaService.findNoticiaById(id);
    }

    @GetMapping("/writer/{id}")
    public List<Noticia> findByWriter(@PathVariable Integer id) {
        return noticiaService.findByWriter(id);
    }

    @PutMapping
    public String editById(@RequestBody Noticia noticia) {
        return noticiaService.editById(noticia);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Integer id) {
        return noticiaService.deleteById(id);
    }

}
