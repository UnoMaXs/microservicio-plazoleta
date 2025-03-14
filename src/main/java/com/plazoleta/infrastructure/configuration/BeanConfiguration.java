package com.plazoleta.infrastructure.configuration;


import com.plazoleta.domain.api.IPedidoServicePort;
import com.plazoleta.domain.api.IPlatoServicePort;
import com.plazoleta.domain.api.IRestauranteServicePort;
import com.plazoleta.domain.api.IUsuarioServicePort;
import com.plazoleta.domain.spi.IPedidoPersistencePort;
import com.plazoleta.domain.spi.IPlatoPersistencePort;
import com.plazoleta.domain.spi.IRestaurantePersistencePort;
import com.plazoleta.domain.usecase.PedidoUseCase;
import com.plazoleta.domain.usecase.PlatoUseCase;
import com.plazoleta.domain.usecase.RestauranteUseCase;
import com.plazoleta.infrastructure.output.jpa.adapter.PedidoJpaAdapter;
import com.plazoleta.infrastructure.output.jpa.adapter.PlatoJpaAdapter;
import com.plazoleta.infrastructure.output.jpa.adapter.RestauranteJpaAdapter;
import com.plazoleta.infrastructure.output.jpa.mapper.IPedidoEntityMapper;
import com.plazoleta.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import com.plazoleta.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import com.plazoleta.infrastructure.output.jpa.repository.IPedidoRepository;
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
    private final IPedidoRepository pedidoRepository;
    private final IPedidoEntityMapper pedidoEntityMapper;

    @Bean
    public IPlatoPersistencePort platoPersistencePort(){
        return new PlatoJpaAdapter(platoRepository, platoEntityMapper);
    }

    @Bean
    public IPlatoServicePort platoServicePort(){
        return new PlatoUseCase(platoPersistencePort(),restaurantePersistencePort(), usuarioServicePort);
    }

    @Bean
    public IRestaurantePersistencePort restaurantePersistencePort(){
        return new RestauranteJpaAdapter(restauranteRepository, restauranteEntityMapper);
    }

    @Bean
    public IRestauranteServicePort restauranteServicePort(){
        return new RestauranteUseCase(restaurantePersistencePort(), usuarioServicePort);
    }

    @Bean
    public IPedidoPersistencePort pedidoPersistencePort(){
        return new PedidoJpaAdapter(pedidoRepository, pedidoEntityMapper);
    }

    @Bean
    public IPedidoServicePort pedidoServicePort(){
        return new PedidoUseCase(pedidoPersistencePort(), usuarioServicePort, restaurantePersistencePort());
    }

}


