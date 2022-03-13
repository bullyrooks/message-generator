package com.bullyrooks.messagegenerator.controller.mapper;

import com.bullyrooks.messagegenerator.controller.dto.MessageResponseDTO;
import com.bullyrooks.messagegenerator.service.model.MessageModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageResponseDTOMapper {
    MessageResponseDTOMapper INSTANCE = Mappers.getMapper(MessageResponseDTOMapper.class);

    MessageResponseDTO modelToDTO(MessageModel model);
}
