package com.plazoleta.infrastructure.configuration;


import com.plazoleta.domain.api.IPlatoServicePort;
import com.plazoleta.domain.api.IRestauranteServicePort;
import com.plazoleta.domain.api.IUsuarioServicePort;
import com.plazoleta.domain.spi.IPlatoPersistencePort;
import com.plazoleta.domain.spi.IRestaurantePersistencePort;
import com.plazoleta.domain.usecase.PlatoUseCase;
import com.plazoleta.domain.usecase.RestauranteUseCase;
import com.plazoleta.infrastructure.output.jpa.adapter.PlatoJpaAdapter;
import com.plazoleta.infrastructure.output.jpa.adapter.RestauranteJpaAdapter;
import com.plazoleta.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import com.plazoleta.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import com.plazoleta.infrastructure.output.jpa.repository.IPlatoRepository;
import com.plazoleta.infrastructure.output.jpa.repository.IRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IPlatoRepository platoRepository;
    private final IPlatoEntityMapper platoEntityMapper;
    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;
    private final IUsuarioServicePort usuarioServicePort;

    @Bean
    public IPlatoPersistencePort platoPersistencePort(){
        return new PlatoJpaAdapter(platoRepository, platoEntityMapper);
    }

    @Bean
    public IPlatoServicePort platoServicePort(){
        return new PlatoUseCase(platoPersistencePort());
    }

    @Bean
    public IRestaurantePersistencePort restaurantePersistencePort(){
        return new RestauranteJpaAdapter(restauranteRepository, restauranteEntityMapper);
    }

    @Bean
    public IRestauranteServicePort restauranteServicePort(){
        return new RestauranteUseCase(restaurantePersistencePort(), usuarioServicePort);
    }
}


