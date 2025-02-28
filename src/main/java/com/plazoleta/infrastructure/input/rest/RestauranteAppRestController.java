package com.plazoleta.infrastructure.input.rest;


import com.plazoleta.application.dto.RestauranteAppRequestDto;
import com.plazoleta.application.handler.IRestauranteAppHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restauranteApp")
@RequiredArgsConstructor
public class RestauranteAppRestController {

    private final IRestauranteAppHandler restauranteAppHandler;

    @PostMapping("/save")
    public ResponseEntity<Void> saveRestauranteInRestauranteApp(RestauranteAppRequestDto restauranteAppRequestDto){
        restauranteAppHandler.saveRestauranteInRestauranteApp(restauranteAppRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
