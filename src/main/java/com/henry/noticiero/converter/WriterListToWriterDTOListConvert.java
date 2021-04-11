package com.henry.noticiero.converter;

import com.henry.noticiero.model.Writer;
import com.henry.noticiero.model.dto.WriterDTO;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
@Setter
public class WriterListToWriterDTOListConvert implements Converter<List<Writer>, List<WriterDTO>> {
    private final ModelMapper modelMapper;

    public WriterListToWriterDTOListConvert(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<WriterDTO> convert(final List<Writer> source) {
        return modelMapper.map(source, new TypeToken<List<WriterDTO>>() {
        }.getType());
    }
}