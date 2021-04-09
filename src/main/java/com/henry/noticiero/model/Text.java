package com.henry.noticiero.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Text extends Noticia {

    @Override
    public NoticiaEnum noticiaEnum() {
        return NoticiaEnum.TEXT;
    }

}
