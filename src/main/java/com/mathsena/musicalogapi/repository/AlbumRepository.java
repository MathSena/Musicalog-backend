package com.mathsena.musicalogapi.repository;

import com.mathsena.musicalogapi.model.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {}
