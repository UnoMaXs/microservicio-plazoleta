package com.plazoleta.infrastructure.input.rest;

import com.plazoleta.application.dto.PlatoAppRequestDto;
import com.plazoleta.application.dto.PlatoUpdateRequestDto;
import com.plazoleta.application.dto.PlatoUpdateResponseDto;
import com.plazoleta.application.handler.IPlatoAppHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/platoApp")
@RequiredArgsConstructor
public class PlatoAppRestController {

private final IPlatoAppHandler platoAppHandler;

    @PostMapping("/save")
    public ResponseEntity<Void> savePlatoInPlatoApp(PlatoAppRequestDto platoAppRequestDto){
    platoAppHandler.savePlatoInPlatoApp(platoAppRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{idPlato}/{idUsuario}")
    public ResponseEntity<PlatoUpdateResponseDto> updatePlatoInPlatoApp(
            @PathVariable("idPlato") Long idPlato,
            @PathVariable("idUsuario") Long idUsuario,
            @RequestBody PlatoUpdateRequestDto platoUpdateRequestDto) {
        PlatoUpdateResponseDto updatedPlato = platoAppHandler.updatePlatoInPlatoApp(idPlato, platoUpdateRequestDto, idUsuario);
        return ResponseEntity.ok(updatedPlato);
    }


}
