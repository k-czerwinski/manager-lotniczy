package com.krzysiek.manager_lotniczy.service;

import com.krzysiek.manager_lotniczy.model.*;
import com.krzysiek.manager_lotniczy.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AdminService {
    private Logger LOGGER = Logger.getLogger(AdminService.class.getName());
    @Autowired
    private final AdminRepository adminRepository;
    @Autowired
    private final BasicService basicService;

    public AdminService(AdminRepository adminRepository, BasicService basicService) {
        this.adminRepository = adminRepository;
        this.basicService = basicService;
    }

    public Message dodajLotnisko(Lotnisko lotnisko) {
        try {
            lotnisko.setICAO(lotnisko.getICAO().toUpperCase());
            lotnisko.setIATA(lotnisko.getIATA().toUpperCase());
            adminRepository.dodajLotnisko(lotnisko);
        } catch (Exception e) {
            Message message = new Message(Message.Type.ERROR, "Lotnisko nie zostało dodane!", "Lotnisko o ICAO: " + lotnisko.getICAO() + " nie zostało dodane" + "\n" + "Status błędu: " + e.getMessage());
            LOGGER.warning("Lotnisko o ICAO: " + lotnisko.getICAO() + " nie zostało dodane.");
            LOGGER.warning("Status błędu: " + e.getMessage());
            return message;
        }
        basicService.reloadLotniskoUIList();
        Message message = new Message(Message.Type.INFO, "Lotnisko zostało dodane!", "Lotnisko o ICAO: " + lotnisko.getICAO() + " zostało dodane");
        LOGGER.info(message.message());
        return message;
    }

    public Message dodajLinie(LiniaLotnicza liniaLotnicza) {
        try {
            adminRepository.dodajLinieLotnicza(liniaLotnicza);
        } catch (Exception e) {
            Message message = new Message(Message.Type.ERROR, "Linia lotnicza nie została dodana!", "Linia lotnicza o nazwie: " + liniaLotnicza.getNazwa() + " nie została dodana" + "\n" + "Status błędu: " + e.getMessage());
            LOGGER.warning("Linia lotnicza o nazwie: " + liniaLotnicza.getNazwa() + " nie została dodana.");
            LOGGER.warning("Status błędu: " + e.getMessage());
            return message;
        }
        basicService.reloadLotniskoUIList();
        Message message = new Message(Message.Type.INFO, "Linia lotnicza została dodana!", "Linia lotnicza o nazwie: " + liniaLotnicza.getNazwa() + " została dodana.");
        LOGGER.info(message.message());
        return new Message(Message.Type.INFO, "Linia lotnicza została dodana!", message.message());
    }

    public Message dodajRejs(Rejs rejs) {
        try {
            rejs.setNumerRejsu(rejs.getNumerRejsu().toUpperCase());
            adminRepository.dodajRejs(rejs);
        } catch (Exception e) {
            LOGGER.warning("Rejs o numerze: " + rejs.getNumerRejsu() + " nie został dodany.");
            LOGGER.warning("Status błędu: " + e.getMessage());
            return new Message(Message.Type.ERROR, "Rejs nie został dodany!", "Rejs o numerze: " + rejs.getNumerRejsu() + " nie został dodany." + "\n" + "Status błędu: " + e.getMessage());
        }
        Message message = new Message(Message.Type.INFO, "Rejs został dodany!", "Rejs o numerze: " + rejs.getNumerRejsu() + " został dodany.");
        LOGGER.info(message.message());
        return message;
    }

    public Message dodajSklep(Sklep sklep, int kategoriaId) {
        try {
            adminRepository.dodajSklep(sklep, kategoriaId);
        } catch (Exception e) {
            LOGGER.warning("Sklep o nazwie: " + sklep.nazwa() + " nie został dodany.");
            LOGGER.warning("Status błędu: " + e.getMessage());
            return new Message(Message.Type.ERROR, "Rejs nie został dodany!", "Sklep o nazwie: " + sklep.nazwa() + " nie został dodany." + "\n" + "Status błędu: " + e.getMessage());
        }
        Message message = new Message(Message.Type.INFO, "Sklep został dodany!", "Sklep o nazwie: " + sklep.nazwa() + " został dodany.");
        LOGGER.info(message.message());
        return message;
    }

    public Message dodajLot(LotAdmin lot) {
        try {
            adminRepository.dodajLot(lot);
        } catch (Exception e) {
            LOGGER.warning("Lot związany z numerem rejsu: " + lot.getNumerRejsu() + " nie został dodany.");
            LOGGER.warning("Status błędu: " + e.getMessage());
            return new Message(Message.Type.ERROR, "Lot nie został dodany!", "Lot związany z numerem rejsu: " + lot.getNumerRejsu() + " nie został dodany.\n" +
                    "Status błędu: " + e.getMessage());
        }
        LOGGER.info("Lot związany z numerem rejsu: " + lot.getNumerRejsu() + " został dodany.");
        return new Message(Message.Type.INFO, "Lot został dodany!", "Lot związany z numerem rejsu: " +  lot.getNumerRejsu() + " został dodany.");
    }

    public Message dodajDostep(DostepPracownika dostep) {
        try {
            adminRepository.dodajDostep(dostep);
        } catch (Exception e) {
            LOGGER.warning("Dostep dla pracownika: " + dostep.getPracownikId() + " i lotniska: " + dostep.getLotniskoICAO() + " nie został dodany.");
            LOGGER.warning("Status błędu: " + e.getMessage());
            return new Message(Message.Type.ERROR, "Dostęp nie został dodany!", "Dostep dla pracownika: " + dostep.getPracownikId() + " i lotniska: " + dostep.getLotniskoICAO() + " nie został dodany." + "\n" + "Status błędu:" + e.getMessage());
        }
        LOGGER.info("Dostep dla pracownika: " + dostep.getPracownikId() + " i lotniska: " + dostep.getLotniskoICAO() + " został dodany.");
        return new Message(Message.Type.INFO, "Dostęp został dodany!", "Dostep dla pracownika: " + dostep.getPracownikId() + " i lotniska: " + dostep.getLotniskoICAO() + " został dodany.");
    }

    public Message dodajPracownika(PracownikDTO pracownik) {
        try {
            adminRepository.dodajPracownika(pracownik);
        } catch (Exception e) {
            String message = "Pracownik nie %s %s został dodany do kategorii %s i miejsca pracy o id: %s".formatted(pracownik.getImie(), pracownik.getNazwisko(), pracownik.getTypPracownika().label, pracownik.getMiejscePracyId());
            LOGGER.warning(message);
            LOGGER.warning("Status błędu: " + e.getMessage());
            return new Message(Message.Type.ERROR, "Pracownik nie został dodany!",message + "\n Status błędu:" + e.getMessage());
        }
        String message = "Pracownik %s %s został dodany do kategorii %s i miejsca pracy o id: %s".formatted(pracownik.getImie(), pracownik.getNazwisko(), pracownik.getTypPracownika().label, pracownik.getMiejscePracyId());
        LOGGER.info(message);
        return new Message(Message.Type.INFO, "Pracownik został dodany!", message);
    }

    public Message dodajSamolot(Samolot samolot) {
        try {
            samolot.setNumer(samolot.getNumer().toUpperCase());
            adminRepository.dodajSamolot(samolot);
        } catch (Exception e) {
            String message = "Samolot %s o numerze %s nie został dodany".formatted(samolot.getModel(), samolot.getNumer());
            LOGGER.warning(message);
            LOGGER.warning("Status błędu: " + e.getMessage());
            return new Message(Message.Type.ERROR, "Samolot nie został dodany!",message + "\n Status błędu:" + e.getMessage());
        }
        String message = "Samolot %s o numerze %s został dodany".formatted(samolot.getModel(), samolot.getNumer());
        LOGGER.info(message);
        return new Message(Message.Type.INFO, "Samolot został dodany!", message);
    }
}
