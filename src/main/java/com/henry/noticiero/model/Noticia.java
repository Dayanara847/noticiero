package com.henry.noticiero.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, property = "noticiaEnum")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Video.class, name = "VIDEO"),
        @JsonSubTypes.Type(value = Image.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = Text.class, name = "TEXT")
})
@Entity
public abstract class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String title;
    @NotNull
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id")
    private Writer writer;

    @AccessType(AccessType.Type.PROPERTY)
    public abstract NoticiaEnum noticiaEnum();

}

