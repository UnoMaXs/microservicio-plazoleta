package com.propietario.application.handler;

import com.propietario.application.dto.PlatoAppRequestDto;
import com.propietario.domain.api.IPlatoServicePort;
import com.propietario.domain.model.Plato;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PlatoAppHandler implements IPlatoAppHandler {

    private final IPlatoServicePort platoServicePort;


    @Override
    public void savePlatoInPlatoApp(PlatoAppRequestDto platoAppRequestDto) {
        Plato plato = new Plato();
        plato.setNombrePlato(platoAppRequestDto.getNombrePlato());
        plato.setDescripcionPlato(platoAppRequestDto.getDescripcionPlato());
        plato.setPrecioPlato(platoAppRequestDto.getPrecioPlato());
        plato.setUrlPlato(platoAppRequestDto.getUrlPlato());
        plato.setCategoriaPlato(platoAppRequestDto.getCategoriaPlato());

        platoServicePort.savePlato(plato);
    }
}
