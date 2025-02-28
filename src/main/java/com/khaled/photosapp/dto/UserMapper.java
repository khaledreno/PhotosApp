package com.khaled.photosapp.dto;

import com.khaled.photosapp.entity.PhotoEntity;
import com.khaled.photosapp.entity.User;
import com.khaled.photosapp.entity.UserRole;
import com.khaled.photosapp.entity.UserRolesValues;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//
//@Mapper(componentModel = "spring")
//public interface UserMapper {
//    @Mapping(target = "role", source = "role")
//    UserDTO toDTO(User user);
//
//    default String map(UserRolesValues role) {
//        return role != null ? role.name() : null;
//    }
//}

//@Mapper(componentModel = "spring")
//public interface UserMapper {
//UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
//
//    UserDTO toDTO(User user);
//
//}