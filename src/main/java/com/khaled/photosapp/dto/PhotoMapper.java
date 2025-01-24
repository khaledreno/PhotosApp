package com.khaled.photosapp.dto;

import com.khaled.photosapp.entity.PhotoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhotoMapper {

    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);
    PhotoResponse toPhotoResponse(PhotoEntity photoEntity);
}
