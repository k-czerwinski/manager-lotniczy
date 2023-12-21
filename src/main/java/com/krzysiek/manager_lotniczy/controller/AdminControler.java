package com.krzysiek.manager_lotniczy.controller;

import com.krzysiek.manager_lotniczy.model.*;
import com.krzysiek.manager_lotniczy.repository.AdminRepository;
import com.krzysiek.manager_lotniczy.repository.BasicEntitiesRepository;
import com.krzysiek.manager_lotniczy.repository.KlientRepository;
import com.krzysiek.manager_lotniczy.service.AdminService;
import com.krzysiek.manager_lotniczy.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin")
public class AdminControler {
    public static final int MAX_REKORDOW_NA_STRONIE = 9;

    @Autowired
    private final BasicEntitiesRepository basicEntitiesRepository;
    @Autowired
    private final AdminRepository adminRepository;
    @Autowired
    private final AdminService adminService;
    @Autowired
    private final BasicService basicService;
    @Autowired
    private final KlientRepository klientRepository;

    public static final Logger LOGGER = Logger.getLogger(AdminControler.class.getName());

    public AdminControler(BasicEntitiesRepository basicEntitiesRepository, AdminRepository adminRepository, AdminService adminService, BasicService basicService, KlientRepository klientRepository) {
        this.basicEntitiesRepository = basicEntitiesRepository;
        this.adminRepository = adminRepository;
        this.adminService = adminService;
        this.basicService = basicService;
        this.klientRepository = klientRepository;
    }

    @GetMapping("/")
    public String mainAdminPage() {
        adminRepository.ustawRoleAdmina();
        return "/admin/index";
    }
    @GetMapping("/lotniska")
    public String lotniskoWidok(Model model, @RequestParam(name = "page") Optional<Integer> pageNum,
                                @RequestParam(name = "sortDir") Optional<KierunekSortowania> kierunekSortowania,
                                @RequestParam(name = "sortPar") Optional<Lotnisko.Kolumna> parametrSortowania) {
        model.addAttribute("tableVal", Arrays.stream(Lotnisko.Kolumna.values()).toList());
        model.addAttribute("currentPage", pageNum.orElse(1));
        model.addAttribute("parametrSortowania", parametrSortowania.orElse(Lotnisko.Kolumna.ICAO));
        model.addAttribute("kierunekSortowania", kierunekSortowania.orElse(KierunekSortowania.ASC));

        model.addAttribute("totalPages",
                Math.ceilDiv(adminRepository.znajdzLiczbeLotnisk(), MAX_REKORDOW_NA_STRONIE));
        model.addAttribute("lotniska", adminRepository.znajdzPosortowaneLotniska(
                        parametrSortowania.orElse(Lotnisko.Kolumna.ICAO),
                        kierunekSortowania.orElse(KierunekSortowania.ASC),
                        MAX_REKORDOW_NA_STRONIE,
                        pageNum.orElse(1))
                .stream().map(Lotnisko::toHTMLRow).toList());
        model.addAttribute("lotnisko", new Lotnisko());

        LOGGER.info("Admin przegląda stronę: lotniska, numer: " + pageNum.orElse(1));
        return "admin/lotniska";
    }

    @PostMapping("/lotniska")
    public RedirectView dodajLotnisko(RedirectAttributes attributes, Lotnisko lotnisko) {
        lotnisko.setICAO(lotnisko.getICAO().toUpperCase());
        lotnisko.setIATA(lotnisko.getIATA().toUpperCase());
        Message message = adminService.dodajLotnisko(lotnisko);
        if (message.type().equals(Message.Type.ERROR)) {
            attributes.addFlashAttribute("dodawanieLotniskaErrorMessage", message.message());
        } else {
            attributes.addFlashAttribute("dodawanieLotniskaSuccessMessage", message.message());
        }
        attributes.addFlashAttribute("tytul", message.title());
        return new RedirectView("/admin/lotniska");
    }

    @GetMapping("/linie-lotnicze")
    public String liniaLotniczaWidok(Model model, @RequestParam(name = "page") Optional<Integer> pageNum,
                                @RequestParam(name = "sortDir") Optional<KierunekSortowania> kierunekSortowania,
                                @RequestParam(name = "sortPar") Optional<LiniaLotnicza.Kolumna> parametrSortowania) {
        model.addAttribute("tableVal", Arrays.stream(LiniaLotnicza.Kolumna.values()).toList());
        model.addAttribute("currentPage", pageNum.orElse(1));
        model.addAttribute("parametrSortowania", parametrSortowania.orElse(LiniaLotnicza.Kolumna.ID));
        model.addAttribute("kierunekSortowania", kierunekSortowania.orElse(KierunekSortowania.ASC));

        model.addAttribute("totalPages",
                Math.ceilDiv(adminRepository.znajdzLiczbeLini(), MAX_REKORDOW_NA_STRONIE));
        model.addAttribute("linie_lotnicze", adminRepository.znajdzPosortowaneLinie(
                        parametrSortowania.orElse(LiniaLotnicza.Kolumna.ID),
                        kierunekSortowania.orElse(KierunekSortowania.ASC),
                        MAX_REKORDOW_NA_STRONIE,
                        pageNum.orElse(1))
                .stream().map(LiniaLotnicza::toHTMLRow).toList());
        model.addAttribute("linia_lotnicza", new LiniaLotnicza());

        LOGGER.info("Admin przegląda linie lotnicze, strona:  " + pageNum.orElse(1));
        return "/admin/linie-lotnicze";
    }

    @PostMapping("/linie-lotnicze")
    public RedirectView dodajLinie(RedirectAttributes attributes, LiniaLotnicza liniaLotnicza) {
        Message message = adminService.dodajLinie(liniaLotnicza);
        if (message.type().equals(Message.Type.ERROR)) {
            attributes.addFlashAttribute("dodawanieLiniErrorMessage", message.message());
        } else {
            attributes.addFlashAttribute("dodawanieLiniSuccessMessage", message.message());
        }
        attributes.addFlashAttribute("tytul", message.title());
        return new RedirectView("/admin/linie-lotnicze");
    }

    @GetMapping("/rejsy")
    public String rejsWidok(Model model, @RequestParam(name = "page") Optional<Integer> pageNum,
                                     @RequestParam(name = "sortDir") Optional<KierunekSortowania> kierunekSortowania,
                                     @RequestParam(name = "sortPar") Optional<Rejs.Kolumna> parametrSortowania) {
        int numerStrony = pageNum.orElse(1);
        Rejs.Kolumna nowyParametrSortowania = parametrSortowania.orElse(Rejs.Kolumna.NUMER_REJSU);
        KierunekSortowania nowyKierunekSortowania = kierunekSortowania.orElse(KierunekSortowania.ASC);

        model.addAttribute("tableVal", Arrays.stream(Rejs.Kolumna.values()).toList());
        model.addAttribute("currentPage", numerStrony);
        model.addAttribute("parametrSortowania", nowyParametrSortowania);
        model.addAttribute("kierunekSortowania", nowyKierunekSortowania);
        model.addAttribute("totalPages", Math.ceilDiv(adminRepository.znajdzLiczbeRejsow(), MAX_REKORDOW_NA_STRONIE));
        model.addAttribute("rejsy", adminRepository.znajdzPosortowaneRejsy(nowyParametrSortowania, nowyKierunekSortowania, MAX_REKORDOW_NA_STRONIE, numerStrony)
                .stream().map(Rejs::toHTMLRow).toList());
        model.addAttribute("lotniska", basicEntitiesRepository.znajdzWszystkieLotniska());
        model.addAttribute("linie_lotnicze", basicEntitiesRepository.znajdzWszystkieLinieLotnicze());
        model.addAttribute("rejs", new Rejs());
        LOGGER.info("Admin przegląda rejsy, strona:  " + numerStrony);
        return "admin/rejsy";
    }

    @PostMapping("/rejsy")
    public RedirectView dodajRejs(RedirectAttributes attributes, Rejs rejs) {
        Message message = adminService.dodajRejs(rejs);
        if (message.type().equals(Message.Type.ERROR)) {
            attributes.addFlashAttribute("dodawanieRejsuErrorMessage", message.message());
        } else {
            attributes.addFlashAttribute("dodawanieRejsuSuccessMessage", message.message());
        }
        attributes.addFlashAttribute("tytul", message.title());
        return new RedirectView("/admin/rejsy");
    }

    @GetMapping("/raportLotniskoPracownicy")
    public String raportLotniskoPracownicy(Model model) {
        model.addAttribute("raportLotnisk", adminRepository.raportLotniskoPracownicy().stream().map(RaportLotniskoPracownicy::toHtmlString).toList());
        return "admin/pracownicy-lotnisko-raport";
    }

    @GetMapping("/raportLotniskoSklepyKategoria")
    public String raportLotniskoSklepKategoria(Model model) {
        model.addAttribute("raportLotnisk", adminRepository.raportLotniskoSklepKategoria().stream().map(RaportLotniskoSklepKategoria::toHtmlString).toList());
        return "admin/raport-lotnisko-sklep-kategoria";
    }

    @GetMapping("/raportLiniaLotnicza")
    public String raportLiniaLotnicza(Model model) {
        model.addAttribute("raportLotnisk", adminRepository.raportLiniaLotnicza().stream().map(RaportLiniaLotnicza::toHtmlString).toList());
        return "admin/raport-linia-lotnicza";
    }

    @PostMapping(value = "/sklepy/filtr")
    public RedirectView filtrujSklepy(@RequestParam(name = "lotnisko") Optional<String> lotniskoICAO,
                                      @RequestParam(name = "kategoria-sklepu") Optional<Integer> kategoriaId) {
        Sklep.FILTR.setKategoriaId(kategoriaId.orElse(null));
        Sklep.FILTR.setLotniskoICAO(lotniskoICAO.orElse(""));
        return new RedirectView("/admin/sklepy");
    }

    @RequestMapping(value = "/sklepy")
    public String shopsPageForSortingAndPagination(Model model, @RequestParam(name = "page") Optional<Integer> pageNum,
                                                   @RequestParam(name = "sortDir") Optional<KierunekSortowania> kierunekSortowania,
                                                   @RequestParam(name = "sortPar") Optional<Sklep.Kolumna> parametrSortowania) {
        int numerStrony = pageNum.orElse(1);
        Sklep.Kolumna nowyParametrSortowania = parametrSortowania.orElse(Sklep.Kolumna.LOTNISKO);
        KierunekSortowania nowyKierunekSortowania = kierunekSortowania.orElse(KierunekSortowania.ASC);
        model.addAttribute("tableVal", Arrays.stream(Sklep.Kolumna.values()).toList());
        model.addAttribute("currentPage", numerStrony);
        model.addAttribute("parametrSortowania", nowyParametrSortowania);
        model.addAttribute("kierunekSortowania", nowyKierunekSortowania);
        model.addAttribute("totalPages", Math.ceilDiv(klientRepository.znajdzLiczbeSklepow(Sklep.FILTR), MAX_REKORDOW_NA_STRONIE));
        model.addAttribute("sklepy", klientRepository.znajdzSklepy(Sklep.FILTR, nowyParametrSortowania.queryLabel, nowyKierunekSortowania, MAX_REKORDOW_NA_STRONIE, numerStrony)
                .stream().map(Sklep::toHTMLRow).toList());
        model.addAttribute("filtr", Sklep.FILTR);
        model.addAttribute("lotniska", basicService.getLotniskoUIList());
        model.addAttribute("kategorieSklepow", basicService.getKategoriaSklepuUIList());
        model.addAttribute("wszystkieLotniska", Lotnisko.WSZYSTKIE_LOTNISKA);
        model.addAttribute("wszystkieKategorieSklepow", KategoriaSklepu.WSZYSTKIE_KATEGORIE);
        LOGGER.info("Admin przegląda sklepy, strona:" + numerStrony);
        return "admin/sklepy";
    }

    @PostMapping(value = "/sklepy/dodajSklep")
    public RedirectView dodajSklep(RedirectAttributes attributes, @RequestParam String nazwaSklepu, @RequestParam int kategoriaSklepu, @RequestParam String lotnisko) {
        Message message = adminService.dodajSklep(new Sklep(nazwaSklepu, "", lotnisko), kategoriaSklepu);
        if (message.type().equals(Message.Type.ERROR)) {
            attributes.addFlashAttribute("errorMessage", message.message());
        } else {
            attributes.addFlashAttribute("infoMessage", message.title());
        }
        attributes.addFlashAttribute("tytul", message.title());
        return new RedirectView("/admin/sklepy");
    }

    @GetMapping(value = "/loty")
    public String flightsPageForSortingAndPagination(Model model, RedirectAttributes attributes, @RequestParam(name = "page") Optional<Integer> pageNum,
                                                     @RequestParam(name = "sortDir") Optional<KierunekSortowania> kierunekSortowania,
                                                     @RequestParam(name = "sortPar") Optional<LotAdmin.Kolumna> parametrSortowania) {
        int numeStrony = pageNum.orElse(1);
        LotAdmin.Kolumna nowyParametrSortowania = parametrSortowania.orElse(LotAdmin.Kolumna.DATA_ODLOTU);
        KierunekSortowania nowyKierunekSortowania = kierunekSortowania.orElse(KierunekSortowania.ASC);

        model.addAllAttributes(attributes.getFlashAttributes());
        model.addAttribute("tableVal", Arrays.stream(LotAdmin.Kolumna.values()).toList());
        model.addAttribute("currentPage", numeStrony);
        model.addAttribute("parametrSortowania", nowyParametrSortowania);
        model.addAttribute("kierunekSortowania", nowyKierunekSortowania);
        model.addAttribute("totalPages", Math.ceilDiv(adminRepository.znajdzLiczbeWszystkichLotow(), MAX_REKORDOW_NA_STRONIE));
        model.addAttribute("loty", adminRepository.znajdzLoty(nowyParametrSortowania, nowyKierunekSortowania, MAX_REKORDOW_NA_STRONIE, numeStrony)
                .stream().map(LotAdmin::toHTMLRow).toList());
        model.addAttribute("linie_lotnicze", basicService.getLiniaLotniczaUIList());

        LOGGER.info("Admin przegląda loty, strona:" + numeStrony);
        return "admin/loty";
    }

    @GetMapping("/znajdzDlaLini")
    public ModelAndView znajdzLotyISamolotyDlaLini(@RequestParam(name = "liniaLotniczaId") int liniaLotniczaId)
    {
        ModelAndView mv= new ModelAndView("/fragments/formularz-dodaj-lot");
        mv.addObject("rejsy", adminRepository.znajdzWszystkieRejsyDlaLiniLotniczej(liniaLotniczaId));
        mv.addObject("samoloty", adminRepository.znajdzWszystkieSamolotyDlaLiniLotniczej(liniaLotniczaId));
        mv.addObject("piloci", adminRepository.znajdzPilotowLiniLotniczej(liniaLotniczaId));
        mv.addObject("nowyLot", new LotAdmin(liniaLotniczaId));
        return mv;
    }

    @PostMapping(value = "/loty")
    public RedirectView dodajLot(LotAdmin lot, RedirectAttributes attributes) {
        Message message = adminService.dodajLot(lot);
        if (message.type().equals(Message.Type.INFO)) {
            attributes.addFlashAttribute("dodawanieLotuSuccessMessage", message.message());
        } else {
            attributes.addFlashAttribute("dodawanieLotuErrorMessage", message.message());
        }
        attributes.addFlashAttribute("tytul", message.title());
        return new RedirectView("/admin/loty");
    }

    @GetMapping(value = "/pracownicy")
    public String pracownicySortowaniePaginacja(Model model, @RequestParam(name = "page") Optional<Integer> pageNum,
                                                     @RequestParam(name = "sortDir") Optional<KierunekSortowania> kierunekSortowania,
                                                     @RequestParam(name = "sortPar") Optional<Pracownik.Kolumna> parametrSortowania) {
        model.addAttribute("tableVal", Arrays.stream(Pracownik.Kolumna.values()).toList());
        model.addAttribute("currentPage", pageNum.orElse(1));
        model.addAttribute("parametrSortowania", parametrSortowania.orElse(Pracownik.Kolumna.ID));
        model.addAttribute("kierunekSortowania", kierunekSortowania.orElse(KierunekSortowania.ASC));
        model.addAttribute("filtr", Pracownik.FILTR);
        model.addAttribute("totalPages",
                Math.ceilDiv(adminRepository.znajdzLiczbePracownikow(Pracownik.FILTR), MAX_REKORDOW_NA_STRONIE));
        model.addAttribute("pracownicy", adminRepository.znajdzPracownikow(
                    Pracownik.FILTR,
                    parametrSortowania.orElse(Pracownik.Kolumna.ID),
                    kierunekSortowania.orElse(KierunekSortowania.ASC),
                    MAX_REKORDOW_NA_STRONIE,
                    pageNum.orElse(1))
                .stream().collect(LinkedHashMap::new,
                        (map, item) -> map.put(item.toHTMLRow(), item.getId()),
                        Map::putAll));
        model.addAttribute("nowyPracownik", new PracownikDTO());
        model.addAttribute("typyPracownikow", PracownikDTO.TypPracownika.values());
        LOGGER.info("User is viewing admin page: workers, page:" + pageNum.orElse(1));
        return "admin/pracownicy";
    }

    @PostMapping("/pracownicy/filtr")
    public RedirectView filtrujPracownikow(Pracownik.Filtr filtr) {
        Pracownik.FILTR.setImie(filtr.getImie());
        Pracownik.FILTR.setNazwisko(filtr.getNazwisko());
        return new RedirectView("/admin/pracownicy");
    }

    @GetMapping("/pracownik")
    public ModelAndView zobaczPracownika(@RequestParam(name = "pracownikId") int pracownikId) {
        ModelAndView mv= new ModelAndView("/fragments/widok-pracownika");
        mv.addObject("pracownik", adminRepository.znajdzDanePracownika(pracownikId));
        mv.addObject("dostepy", adminRepository.znajdzDostepyPracownika(pracownikId));
        return mv;
    }

    @GetMapping("/pracownik/dostep")
    public ModelAndView formularzDodajDostep(@RequestParam(name = "pracownikId") int pracownikId) {
        ModelAndView mv= new ModelAndView("/fragments/formularz-dostep-pracownika");
        mv.addObject("pracownik", adminRepository.znajdzDanePracownika(pracownikId));
        mv.addObject("lotniska", adminRepository.znajdzLotniskaBezUstalonychDostepow(pracownikId));
        mv.addObject("dostep", new DostepPracownika(pracownikId));
        return mv;
    }

    @PostMapping("/pracownik/dodaj-dostep")
    public RedirectView dodajDostep(RedirectAttributes attributes, DostepPracownika dostepPracownika) {
        Message message = adminService.dodajDostep(dostepPracownika);
        if (message.type().equals(Message.Type.ERROR)) {
            attributes.addFlashAttribute("errorMessage", message.message());
        } else {
            attributes.addFlashAttribute("infoMessage", message.message());
        }
        attributes.addFlashAttribute("tytul", message.title());
        return new RedirectView("/admin/pracownicy");
    }

    @GetMapping("/pracownik/formularz")
    public ModelAndView formularzDodajPracownika(Model model, @RequestParam(name = "typPracownika") PracownikDTO.TypPracownika typPracownika) {
        ModelAndView mv = new ModelAndView("/fragments/formularz-dodaj-pracownika");
        switch (typPracownika) {
            case LINIA_LOTNICZA -> {
                model.addAttribute("pracodawcy", basicService.getLiniaLotniczaUIList()
                        .stream().collect(Collectors.toMap(LiniaLotnicza::getId, LiniaLotnicza::getNazwa)));
                model.addAttribute("funkcje", adminRepository.znajdzFunkcjePracownikaLiniaLotnicza());
            }
            case SKLEP -> {
                model.addAttribute("pracodawcy", adminRepository.znajdzNazwyWszystkichSklepow());
                model.addAttribute("funkcje", adminRepository.znajdzFunkcjePracownikaSklep());
            }
            case LOTNISKO -> {
                model.addAttribute("pracodawcy", basicService.getLotniskoUIList()
                        .stream().collect(Collectors.toMap(Lotnisko::getICAO, Lotnisko::getNazwa)));
                model.addAttribute("funkcje", adminRepository.znajdzFunkcjePracownikaLotnisko());
            }
        }

        PracownikDTO pracownik = new PracownikDTO();
        pracownik.setTypPracownika(typPracownika);
        mv.addObject("nowyPracownik", pracownik);
        return mv;
    }

    @PostMapping("/pracownicy/dodaj")
    public RedirectView dodajPracownika(RedirectAttributes attributes, PracownikDTO nowyPracownik) {
        Message message = adminService.dodajPracownika(nowyPracownik);
        if (message.type().equals(Message.Type.ERROR)) {
            attributes.addFlashAttribute("errorMessage", message.message());
        } else {
            attributes.addFlashAttribute("infoMessage", message.message());
        }
        attributes.addFlashAttribute("tytul", message.title());
        return new RedirectView("/admin/pracownicy");
    }

    @GetMapping("/samoloty")
    public String samolotySortowanieIPaginacja(Model model, @RequestParam(name = "page") Optional<Integer> pageNum,
                                               @RequestParam(name = "sortDir") Optional<KierunekSortowania> kierunekSortowania,
                                               @RequestParam(name = "sortPar") Optional<Samolot.Kolumna> parametrSortowania) {
        int numerStrony = pageNum.orElse(1);
        KierunekSortowania nowyKierunekSortowania = kierunekSortowania.orElse(KierunekSortowania.ASC);
        Samolot.Kolumna nowyParametrSortowania = parametrSortowania.orElse(Samolot.Kolumna.MODEL);

        model.addAttribute("tableVal", Arrays.stream(Samolot.Kolumna.values()).toList());
        model.addAttribute("samoloty", adminRepository
                .znajdzSamoloty(nowyKierunekSortowania, nowyParametrSortowania, MAX_REKORDOW_NA_STRONIE, numerStrony)
                .stream().map(Samolot::toHtmlString).toList());
        model.addAttribute("nowySamolot", new Samolot());
        model.addAttribute("linieLotnicze", basicService.getLiniaLotniczaUIList().stream()
                .collect(Collectors.toMap(LiniaLotnicza::getId, LiniaLotnicza::getNazwa)));
        model.addAttribute("parametrSortowania", nowyParametrSortowania);
        model.addAttribute("kierunekSortowania", nowyKierunekSortowania);
        model.addAttribute("currentPage", numerStrony);
        model.addAttribute("totalPages", adminRepository.znajdzLiczbeSamolotow());
        LOGGER.info("Admin przegląda stronę: samoloty, numer:" + numerStrony);
        return "/admin/samoloty";
    }

    @PostMapping("/dodajSamolot")
    public RedirectView dodajSamolot(RedirectAttributes attributes, Samolot samolot) {
        Message message = adminService.dodajSamolot(samolot);
        if (message.type().equals(Message.Type.ERROR)) {
            attributes.addFlashAttribute("errorMessage", message.message());
        } else {
            attributes.addFlashAttribute("successMessage", message.message());
        }
        attributes.addFlashAttribute("tytul", message.title());
        return new RedirectView("/admin/samoloty");
    }
}
