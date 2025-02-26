package com.propietario.infrastructure.configuration;


import com.propietario.domain.api.IPlatoServicePort;
import com.propietario.domain.spi.IPlatoPersistencePort;
import com.propietario.domain.usecase.PlatoUseCase;
import com.propietario.infrastructure.output.jpa.adapter.PlatoJpaAdapter;
import com.propietario.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import com.propietario.infrastructure.output.jpa.repository.IPlatoRepository;
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

