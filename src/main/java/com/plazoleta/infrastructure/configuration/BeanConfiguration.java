package com.plazoleta.infrastructure.configuration;


import com.plazoleta.domain.api.IPlatoServicePort;
import com.plazoleta.domain.spi.IPlatoPersistencePort;
import com.plazoleta.domain.usecase.PlatoUseCase;
import com.plazoleta.infrastructure.output.jpa.adapter.PlatoJpaAdapter;
import com.plazoleta.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import com.plazoleta.infrastructure.output.jpa.repository.IPlatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IPlatoRepository platoRepository;
    private final IPlatoEntityMapper platoEntityMapper;

@Bean
public IPlatoPersistencePort platoPersistencePort(){
        return new PlatoJpaAdapter(platoRepository,platoEntityMapper);
    }

    @Bean
    public IPlatoServicePort platoServicePort(){
        return new PlatoUseCase(platoPersistencePort());
    }

    }

