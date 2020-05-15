package com.example.myapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompositionInfo {

    private int compositionId;

    private String compositionName;

    private String authorName;
}
