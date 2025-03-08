package com.globant.automation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPetResponseDTO {
    private long id;
    private CategoryDTO category;
    private String name;
    private List<String> photoUrls;
    private List<TagDTO> tags;
    private String status;
}
