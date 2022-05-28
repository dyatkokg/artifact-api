package me.dyatkokg.artefactapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtifactSearchDTO {

    private Search search;

    private List<OrderDTO> orderDTO;

    @Data
    public static class Search {

        private String category;
        private String userId;
        private String description;
        private String comment;

    }

    @Data
    public static class OrderDTO {

        private String field;

        private String direction;

    }
}
