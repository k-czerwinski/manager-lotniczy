package com.krzysiek.manager_lotniczy.service;

import com.krzysiek.manager_lotniczy.model.KategoriaSklepu;
import com.krzysiek.manager_lotniczy.model.LiniaLotnicza;
import com.krzysiek.manager_lotniczy.model.Lotnisko;
import com.krzysiek.manager_lotniczy.repository.BasicEntitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicService {
    @Autowired
    private final BasicEntitiesRepository basicEntitiesRepository;
    private List<KategoriaSklepu> kategoriaSklepuUIList;
    private List<Lotnisko> lotniskoUIList;
    private List<LiniaLotnicza> liniaLotniczaUIList;

    public BasicService(BasicEntitiesRepository basicEntitiesRepository) {
        this.basicEntitiesRepository = basicEntitiesRepository;
    }

    public List<KategoriaSklepu> getKategoriaSklepuUIList() {
        if (kategoriaSklepuUIList == null) {
            reloadKategoriaSklepowUIList();
        }
        return kategoriaSklepuUIList;
    }

    public void reloadKategoriaSklepowUIList(){
        kategoriaSklepuUIList = basicEntitiesRepository.znajdzWszystkieKategorieSklepow();
    }

    public List<Lotnisko> getLotniskoUIList() {
        if (lotniskoUIList == null) {
            reloadLotniskoUIList();
        }
        return lotniskoUIList;
    }

    public void reloadLotniskoUIList() {
        lotniskoUIList = basicEntitiesRepository.znajdzWszystkieLotniska();
    }

    public List<LiniaLotnicza> getLiniaLotniczaUIList() {
        if (liniaLotniczaUIList == null) {
            reloadLiniaLotniczaUIList();
        }
        return liniaLotniczaUIList;
    }

    public void reloadLiniaLotniczaUIList() {
        liniaLotniczaUIList = basicEntitiesRepository.znajdzWszystkieLinieLotnicze();
    }
}
