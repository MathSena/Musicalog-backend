package com.mathsena.musicalogapi.repository;

import com.mathsena.musicalogapi.model.Album;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface AlbumRepository extends MongoRepository<Album, String> {
  List<Album> findByTitleContainingOrArtistNameContaining(String title, String artistName);
}
