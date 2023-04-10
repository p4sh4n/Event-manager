package com.zadaca.events.payload.request;

import com.zadaca.events.domains.Category;
import com.zadaca.events.domains.Location;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventRequest {
    private String name;
    private String description;
    private String pictureUrl;
    private LocalDateTime date;
    private Long locationId;
    private Long categoryId;
}
