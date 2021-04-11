package com.henry.noticiero.converter;

import com.henry.noticiero.model.Writer;
import com.henry.noticiero.model.dto.WriterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WriterToWriterDTOConvert implements Converter<Writer, WriterDTO> {

    private final ModelMapper modelMapper;

    public WriterToWriterDTOConvert(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public WriterDTO convert (Writer source) {
        return modelMapper.map(source, WriterDTO.class);
    }

}
