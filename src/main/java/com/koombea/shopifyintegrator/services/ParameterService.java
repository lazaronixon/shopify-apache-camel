package com.koombea.shopifyintegrator.services;

import static com.google.common.base.MoreObjects.firstNonNull;
import com.koombea.shopifyintegrator.models.Parameter;
import com.koombea.shopifyintegrator.repositories.ParameterRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.transaction.Transactional;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service @Transactional
public class ParameterService {

    private static final int DAYS_TO_FETCH = 15;

    @Autowired private ParameterRepository parameterRepository;

    public LocalDateTime getLastRefresh() {
        return firstNonNull(getParametro().getProductsRefreshedAt(), defaultLastRefresh());
    }

    public void updateLastRefresh(@Header("CamelTimerFiredTime") Date date) {
        getParametro().setProductsRefreshedAt(convertToLocalDateTime(date));
    }

    private Parameter getParametro() {
        return parameterRepository.findFirstBy();
    }

    private LocalDateTime defaultLastRefresh() {
        return LocalDateTime.now().minusDays(DAYS_TO_FETCH);
    }

    private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
