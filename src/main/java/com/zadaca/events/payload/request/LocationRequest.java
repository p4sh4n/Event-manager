package com.zadaca.events.payload.request;

import lombok.Data;

@Data
public class LocationRequest {
    private String name;
    private String description;
    private String pictureUrl;
}
