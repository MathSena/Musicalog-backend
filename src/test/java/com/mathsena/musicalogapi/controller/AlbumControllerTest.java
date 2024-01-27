package com.mathsena.musicalogapi.controller;

import com.mathsena.musicalogapi.exception.NotFoundException;
import com.mathsena.musicalogapi.model.dtos.AlbumDTO;
import com.mathsena.musicalogapi.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AlbumControllerTest {
  @Mock private AlbumService albumService;

  @InjectMocks private AlbumController albumController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Get All Albums")
  void testGetAllAlbums() {
    when(albumService.findAll()).thenReturn(List.of(new AlbumDTO()));

    ResponseEntity<List<AlbumDTO>> result = albumController.getAllAlbums();

    assertNotNull(result);
    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertFalse(Objects.requireNonNull(result.getBody())
            .isEmpty());
  }

  @Test
  @DisplayName("Search Albums")
  void testSearchAlbums() {
    when(albumService.findByTitleOrArtistName(anyString(), anyString()))
        .thenReturn(List.of(new AlbumDTO()));

    ResponseEntity<List<AlbumDTO>> result = albumController.searchAlbums("title", "artistName");

    assertNotNull(result);
    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertFalse(Objects.requireNonNull(result.getBody())
            .isEmpty());
  }

  @Test
  @DisplayName("Create Album")
  void testCreateAlbum() {
    when(albumService.save(any(AlbumDTO.class))).thenReturn(new AlbumDTO());

    ResponseEntity<AlbumDTO> result = albumController.createAlbum(new AlbumDTO());

    assertNotNull(result);
    assertEquals(HttpStatus.OK, result.getStatusCode());
  }



  @Test
  @DisplayName("Delete Album - Album Not Found")
  void testDeleteAlbum_NotFound() {
    doThrow(new NotFoundException("Album with id id not found"))
        .when(albumService)
        .deleteById(anyString());

    assertThrows(
        NotFoundException.class,
        () -> {
          albumController.deleteAlbum("id");
        });
  }
}
