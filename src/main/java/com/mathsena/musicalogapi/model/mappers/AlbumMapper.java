package com.mathsena.musicalogapi.model.mappers;

import com.mathsena.musicalogapi.model.Album;
import com.mathsena.musicalogapi.model.dtos.AlbumDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AlbumMapper {
    AlbumDTO albumToAlbumDTO(Album album);
    Album albumDTOToAlbum(AlbumDTO albumDTO);
}

