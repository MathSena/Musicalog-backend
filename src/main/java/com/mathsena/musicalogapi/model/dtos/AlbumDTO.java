package com.mathsena.musicalogapi.model.dtos;

import com.mathsena.musicalogapi.model.enums.Type;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlbumDTO {

  private String id;

  @NotBlank(message = "Title is required")
  private String title;

  @NotBlank(message = "Artist name is required")
  private String artistName;

  @NotNull(message = "Type is required (CD or VINYL)")
  private Type type;

  @Min(value = 0, message = "Stock cannot be negative")
  private int stock;

  private String coverImage;
}
