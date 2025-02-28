package com.khaled.photosapp.dto;

import com.khaled.photosapp.entity.PhotoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    PhotoDTO toDTO(PhotoEntity photoEntity);

    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    PhotoEntity toEntity(PhotoDTO photoDTO);
}


//@Mapper
//public interface PhotoMapper {
//
//    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);
//
//    // Convert Entity to DTO
//    PhotoDTO toDTO(PhotoEntity photoEntity);
//
//
//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "location", source = "location")
//    @Mapping(target = "uploaderName", source = "uploaderName")
//    @Mapping(target = "status", source = "status")
//    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
//    // Convert DTO to Entity
//    PhotoEntity toEntity(PhotoDTO photoDTO);}
