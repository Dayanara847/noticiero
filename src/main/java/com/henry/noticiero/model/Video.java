package com.henry.noticiero.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.net.URL;

@Data
@NoArgsConstructor
@Entity
public class Video extends Noticia {

    private URL videoUrl;

    @Override
    public NoticiaEnum noticiaEnum() {
        return NoticiaEnum.VIDEO;
    }

}
