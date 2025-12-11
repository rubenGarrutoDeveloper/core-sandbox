package com.spring_compendium.core_sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Un semplice DTO (Data Transfer Object).
 * Spring userà Jackson per convertire questa classe in JSON:
 * {
 * "message": "...",
 * "sender": "..."
 * }
 */
@Data
@AllArgsConstructor
public class Greeting {
    private String message;
    private String sender;
}
