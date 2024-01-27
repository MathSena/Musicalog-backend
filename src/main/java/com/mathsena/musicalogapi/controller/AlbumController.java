package com.mathsena.musicalogapi.controller;

import com.mathsena.musicalogapi.model.dtos.AlbumDTO;
import com.mathsena.musicalogapi.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.mathsena.musicalogapi.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("v1/api/albums")
public class AlbumController {

  @Autowired
  private AlbumService albumService;

  @Operation(summary = "Get a list of all albums")
  @GetMapping
  public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
    List<AlbumDTO> albums = albumService.findAll();
    return ResponseEntity.ok(albums);
  }

  @Operation(summary = "Search albums by title and/or artist name")
  @GetMapping("/search")
  public ResponseEntity<List<AlbumDTO>> searchAlbums(
          @Parameter(description = "Album title") @RequestParam(required = false) String title,
          @Parameter(description = "Artist name") @RequestParam(required = false) String artistName) {
    List<AlbumDTO> albums = albumService.findByTitleOrArtistName(title, artistName);
    return ResponseEntity.ok(albums);
  }

  @Operation(summary = "Create a new album")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully created album",
                  content = @Content(mediaType = "application/json",
                          schema = @Schema(implementation = AlbumDTO.class))),
          @ApiResponse(responseCode = "400", description = "Invalid input",
                  content = @Content)
  })
  @PostMapping
  public ResponseEntity<AlbumDTO> createAlbum(@Valid @RequestBody AlbumDTO albumDTO) {
    AlbumDTO newAlbum = albumService.save(albumDTO);
    return ResponseEntity.ok(newAlbum);
  }

  @Operation(summary = "Update an existing album")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully updated album",
                  content = @Content(mediaType = "application/json",
                          schema = @Schema(implementation = AlbumDTO.class))),
          @ApiResponse(responseCode = "404", description = "Album not found",
                  content = @Content)
  })
  @PutMapping("/{id}")
  public ResponseEntity<AlbumDTO> updateAlbum(
          @Parameter(description = "Album ID") @PathVariable String id,
          @Valid @RequestBody AlbumDTO albumDTO) {
    AlbumDTO updatedAlbum = albumService.updateAlbum(id, albumDTO);
    if (updatedAlbum == null) {
      throw new NotFoundException("Album with id " + id + " not found");
    }
    return ResponseEntity.ok(updatedAlbum);
  }

  @Operation(summary = "Delete an album")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully deleted album",
                  content = @Content),
          @ApiResponse(responseCode = "404", description = "Album not found",
                  content = @Content)
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAlbum(@Parameter(description = "Album ID") @PathVariable String id) {
    if (albumService.findById(id) == null) {
      throw new NotFoundException("Album with id " + id + " not found");
    }
    albumService.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
