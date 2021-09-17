package com.example.laba;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Kurs {
    String year;
    Double USD;
    Double EUR;
    Double RUB;
}
