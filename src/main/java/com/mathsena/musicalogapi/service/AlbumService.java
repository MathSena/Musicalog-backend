package com.mathsena.musicalogapi.service;

import com.mathsena.musicalogapi.model.Album;
import com.mathsena.musicalogapi.model.dtos.AlbumDTO;
import com.mathsena.musicalogapi.model.mappers.AlbumMapper;
import com.mathsena.musicalogapi.repository.AlbumRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AlbumService {

  @Autowired private AlbumRepository albumRepository;

  @Autowired private AlbumMapper albumMapper;

  public AlbumService(AlbumRepository albumRepository, AlbumMapper albumMapper) {
    this.albumRepository = albumRepository;
    this.albumMapper = albumMapper;
  }

  public AlbumDTO save(AlbumDTO albumDTO) {
    log.info("Saving new album: {}", albumDTO.getTitle());
    Album album = albumMapper.albumDTOToAlbum(albumDTO);
    Album savedAlbum = albumRepository.save(album);
    return albumMapper.albumToAlbumDTO(savedAlbum);
  }

  public AlbumDTO findById(String id) {
    log.info("Finding album by id: {}", id);
    return albumRepository.findById(id).map(albumMapper::albumToAlbumDTO).orElse(null);
  }

  public List<AlbumDTO> findAll() {
    log.info("Finding all albums");
    return albumRepository.findAll().stream()
        .map(albumMapper::albumToAlbumDTO)
        .collect(Collectors.toList());
  }

  public void deleteById(String id) {
    log.info("Deleting album by id: {}", id);
    albumRepository.deleteById(id);
  }

  public AlbumDTO updateAlbum(String id, AlbumDTO albumDTO) {
    log.info("Updating album by id: {}", id);
    return albumRepository
        .findById(id)
        .map(
            album -> {
              album.setTitle(albumDTO.getTitle());
              album.setArtistName(albumDTO.getArtistName());
              album.setType(albumDTO.getType());
              album.setStock(albumDTO.getStock());
              album.setCoverImage(albumDTO.getCoverImage());
              return albumMapper.albumToAlbumDTO(albumRepository.save(album));
            })
        .orElse(null);
  }

  public List<AlbumDTO> findByTitleOrArtistName(String title, String artistName) {
    log.info("Finding album by title: {} or artist name: {}", title, artistName);
    return albumRepository.findByTitleContainingOrArtistNameContaining(title, artistName).stream()
        .map(albumMapper::albumToAlbumDTO)
        .collect(Collectors.toList());
  }
}
