package com.koombea.shopifyintegrator.repositories;

import com.koombea.shopifyintegrator.models.Parameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends CrudRepository<Parameter, Long> {

    Parameter findFirstBy();

}
