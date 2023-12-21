package com.krzysiek.manager_lotniczy.service;

import com.krzysiek.manager_lotniczy.model.Bilet;
import com.krzysiek.manager_lotniczy.model.KategoriaSklepu;
import com.krzysiek.manager_lotniczy.model.Lotnisko;
import com.krzysiek.manager_lotniczy.model.Message;
import com.krzysiek.manager_lotniczy.repository.BasicEntitiesRepository;
import com.krzysiek.manager_lotniczy.repository.KlientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class KlientService {

    Logger LOGGER = Logger.getLogger(KlientService.class.getName());
    @Autowired
    private final KlientRepository klientRepository;

    public KlientService(KlientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }

    public Message dodajBilet(Bilet bilet) {
        if (bilet.getNumerDowodu().isEmpty()) {
            bilet.setNumerDowodu(null);
        }
        if (bilet.getNumerPaszportu().isEmpty()) {
            bilet.setNumerPaszportu(null);
        }
        try {
            if(!klientRepository.sprawdzDostepnoscLotu(bilet.getKlasaSiedzenia(), bilet.getNumerLotu())) {
                return new Message(Message.Type.ERROR, "Bilet w wybranej klasie nie jest dostępny.",
                        "Bilet w wybranej przez Ciebie klasie nie jest dostępny, spróbuj ponownie z inną klasą");
            }
            klientRepository.dodajBilet(bilet);
        } catch (Exception e) {
            String message = "Pasazer %s %s nie kupił biletu.".formatted(bilet.getImie(), bilet.getNazwisko());
            LOGGER.warning(message);
            LOGGER.warning("Status błędu: " + e.getMessage());
            return new Message(Message.Type.ERROR, "Bilet nie został kupiony!",message + "\n" + "Status błędu:" + e.getMessage());
        }
        String message = "Pasazer %s %s pomyślnie kupił biletu.".formatted(bilet.getImie(), bilet.getNazwisko());
        LOGGER.info(message);
        return new Message(Message.Type.INFO, "Bilet został kupiony!", message);
    }
}
