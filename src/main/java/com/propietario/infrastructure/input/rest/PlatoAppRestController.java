package com.propietario.infrastructure.input.rest;

import com.propietario.application.dto.PlatoAppRequestDto;
import com.propietario.application.handler.IPlatoAppHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platoApp")
@RequiredArgsConstructor
public class PlatoAppRestController {

private final IPlatoAppHandler platoAppHandler;

@PostMapping("/save")
    public ResponseEntity<Void> saveUsuarioInUsuarioApp(PlatoAppRequestDto platoAppRequestDto){
    platoAppHandler.savePlatoInPlatoApp(platoAppRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
