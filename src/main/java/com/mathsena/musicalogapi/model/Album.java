package com.mathsena.musicalogapi.model;

import com.mathsena.musicalogapi.model.enums.Type;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Data
public class Album implements Serializable {

  @Id
  private String id;
  private String title;
  private String artistName;
  private Type type;
  private int stock;
  private String coverImage;
}
