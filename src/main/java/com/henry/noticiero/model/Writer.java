package com.henry.noticiero.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
//import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private String avatarChuck;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JoinColumn(name = "noticia_id")
    //private List<Noticia> noticiaList;

}
