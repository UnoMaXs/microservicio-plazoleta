package com.plazoleta.application.handler;

import com.plazoleta.application.dto.PlatoAppRequestDto;
import com.plazoleta.application.dto.PlatoUpdateRequestDto;
import com.plazoleta.application.dto.PlatoUpdateResponseDto;
import com.plazoleta.application.mapper.IPlatoAppRequestMapper;
import com.plazoleta.application.mapper.IPlatoAppResponseMapper;
import com.plazoleta.domain.api.IPlatoServicePort;
import com.plazoleta.domain.model.Plato;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PlatoAppHandler implements IPlatoAppHandler {

    private final IPlatoServicePort platoServicePort;
    private final IPlatoAppRequestMapper iPlatoAppRequestMapper;
    private final IPlatoAppResponseMapper iPlatoAppResponseMapper;


    @Override
    public void savePlatoInPlatoApp(PlatoAppRequestDto platoAppRequestDto) {
        Plato plato = new Plato();
        plato.setNombrePlato(platoAppRequestDto.getNombrePlato());
        plato.setDescripcionPlato(platoAppRequestDto.getDescripcionPlato());
        plato.setPrecioPlato(platoAppRequestDto.getPrecioPlato());
        plato.setUrlPlato(platoAppRequestDto.getUrlPlato());
        plato.setCategoriaPlato(platoAppRequestDto.getCategoriaPlato());
        plato.setActivoPlato(true);
        plato.setIdRestaurante(platoAppRequestDto.getIdRestaurante());
        plato.setIdUsuario(platoAppRequestDto.getIdUsuario());

        platoServicePort.savePlato(plato);
    }

    @Override
    public PlatoUpdateResponseDto updatePlatoInPlatoApp(Long platoId, PlatoUpdateRequestDto platoUpdateRequestDto, Long usuarioId) {
        Plato platoModificado = iPlatoAppRequestMapper.toPlato(platoUpdateRequestDto);
        Plato platoActualizado = platoServicePort.updatePlato(platoId, platoModificado, usuarioId);

        return iPlatoAppResponseMapper.toPlatoUpdateResponseDto(platoActualizado);
    }

}
