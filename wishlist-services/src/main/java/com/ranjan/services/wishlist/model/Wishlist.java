package com.ranjan.services.wishlist.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wishlist {

    private String username;

    private String foodName;

    private String servingUnit;

    private Integer servingQty;

    private String tagId;

    private String tagName;

    private String commonType;

    private String photoUrl;

    private String locale;

}
