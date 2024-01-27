package com.mathsena.musicalogapi.service;

import com.mathsena.musicalogapi.model.Album;
import com.mathsena.musicalogapi.model.dtos.AlbumDTO;
import com.mathsena.musicalogapi.model.mappers.AlbumMapper;
import com.mathsena.musicalogapi.repository.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AlbumServiceTest {
    @Mock
    AlbumRepository albumRepository;
    @Mock
    AlbumMapper albumMapper;
    @InjectMocks
    AlbumService albumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test Save Album")
    void testSave(){
        Album album = new Album();
        AlbumDTO albumDTO = new AlbumDTO();

        when(albumMapper.albumDTOToAlbum(any(AlbumDTO.class))).thenReturn(album);
        when(albumRepository.save(any(Album.class))).thenReturn(album);
        when(albumMapper.albumToAlbumDTO(any(Album.class))).thenReturn(albumDTO);

        AlbumDTO result = albumService.save(new AlbumDTO());

        assertNotNull(result);
        verify(albumRepository).save(any(Album.class));
    }

    @Test
    @DisplayName("Test Find Album by ID")
    void testFindById(){
        Optional<Album> optionalAlbum = Optional.of(new Album());
        when(albumRepository.findById(anyString())).thenReturn(optionalAlbum);
        when(albumMapper.albumToAlbumDTO(any())).thenReturn(new AlbumDTO());

        AlbumDTO result = albumService.findById("id");

        assertNotNull(result);
        verify(albumRepository).findById("id");
    }

    @Test
    @DisplayName("Test Find All Albums")
    void testFindAll(){
        when(albumRepository.findAll()).thenReturn(List.of(new Album()));
        when(albumMapper.albumToAlbumDTO(any(Album.class))).thenReturn(new AlbumDTO());

        List<AlbumDTO> result = albumService.findAll();

        assertFalse(result.isEmpty());
        verify(albumRepository).findAll();
    }

    @Test
    @DisplayName("Test Delete Album by ID")
    void testDeleteById(){
        albumService.deleteById("id");
        verify(albumRepository).deleteById("id");
    }

    @Test
    @DisplayName("Test Update Album")
    void testUpdateAlbum(){
        Album existingAlbum = new Album();
        AlbumDTO updatedAlbumDTO = new AlbumDTO();
        Optional<Album> optionalAlbum = Optional.of(existingAlbum);

        when(albumRepository.findById(anyString())).thenReturn(optionalAlbum);
        when(albumRepository.save(any(Album.class))).thenReturn(existingAlbum);
        when(albumMapper.albumToAlbumDTO(any(Album.class))).thenReturn(updatedAlbumDTO);

        AlbumDTO result = albumService.updateAlbum("id", new AlbumDTO());

        assertNotNull(result);
        verify(albumRepository).findById("id");
        verify(albumRepository).save(any(Album.class));
    }

    @Test
    @DisplayName("Test Find Album by Title or Artist Name")
    void testFindByTitleOrArtistName(){
        when(albumRepository.findByTitleContainingOrArtistNameContaining(anyString(), anyString())).thenReturn(List.of(new Album()));
        when(albumMapper.albumToAlbumDTO(any(Album.class))).thenReturn(new AlbumDTO());

        List<AlbumDTO> result = albumService.findByTitleOrArtistName("title", "artistName");

        assertFalse(result.isEmpty());
        verify(albumRepository).findByTitleContainingOrArtistNameContaining("title", "artistName");
    }
}
