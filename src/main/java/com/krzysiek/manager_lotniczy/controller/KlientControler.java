package com.krzysiek.manager_lotniczy.controller;

import com.krzysiek.manager_lotniczy.model.*;
import com.krzysiek.manager_lotniczy.repository.KlientRepository;
import com.krzysiek.manager_lotniczy.service.BasicService;
import com.krzysiek.manager_lotniczy.service.KlientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("client")
public class KlientControler {
    public static final int MAX_REKORDOW_NA_STRONIE = 9;
    private static final Logger LOGGER = Logger.getLogger(KlientControler.class.getName());
    @Autowired
    private KlientRepository klientRepository;
    @Autowired
    private KlientService klientService;

    @Autowired
    private BasicService basicService;

    @RequestMapping(value = "/")
    public String mainPage() {
        LOGGER.info("Użytkownik przegląda stronę główną");
        klientRepository.ustawRoleUzytkownika();
        return "client/index";
    }

    @PostMapping(value = "/shops")
    public String shopsPageForFilter(Model model,
                                     @RequestParam(name = "sortDir") Optional<KierunekSortowania> kierunekSortowania,
                                     @RequestParam(name = "sortPar") Optional<Sklep.Kolumna> parametrSortowania,
                                     @RequestParam(name = "lotnisko") Optional<String> lotniskoICAO,
                                     @RequestParam(name = "kategoria-sklepu") Optional<Integer> kategoriaId) {

        Sklep.FILTR.setLotniskoICAO(lotniskoICAO.orElse(""));
        Sklep.FILTR.setKategoriaId(kategoriaId.orElse(null));
        return shopsPageForSortingAndPagination(model, Optional.of(1), kierunekSortowania, parametrSortowania);
    }

    @GetMapping(value = "/shops")
    public String shopsPageForSortingAndPagination(Model model, @RequestParam(name = "page") Optional<Integer> pageNum,
                                                   @RequestParam(name = "sortDir") Optional<KierunekSortowania> kierunekSortowania,
                                                   @RequestParam(name = "sortPar") Optional<Sklep.Kolumna> parametrSortowania) {
        int numerStrony = pageNum.orElse(1);
        Sklep.Kolumna nowyParametrSortowania = parametrSortowania.orElse(Sklep.Kolumna.LOTNISKO);
        KierunekSortowania nowyKierunekSortowania = kierunekSortowania.orElse(KierunekSortowania.ASC);

        model.addAttribute("tableVal", Arrays.stream(Sklep.Kolumna.values()).toList());
        model.addAttribute("currentPage", numerStrony);
        model.addAttribute("parametrSortowania", nowyParametrSortowania);
        model.addAttribute("kierunekSortowania", kierunekSortowania);
        model.addAttribute("totalPages", Math.ceilDiv(klientRepository.znajdzLiczbeSklepow(Sklep.FILTR), MAX_REKORDOW_NA_STRONIE));
        model.addAttribute("sklepy", klientRepository.znajdzSklepy(Sklep.FILTR, nowyParametrSortowania.queryLabel, nowyKierunekSortowania, MAX_REKORDOW_NA_STRONIE, numerStrony)
                .stream().map(Sklep::toHTMLRow).toList());
        model.addAttribute("filtr", Sklep.FILTR);
        model.addAttribute("lotniska", basicService.getLotniskoUIList());
        model.addAttribute("kategorieSklepow", basicService.getKategoriaSklepuUIList());
        model.addAttribute("wszystkieLotniska", Lotnisko.WSZYSTKIE_LOTNISKA);
        model.addAttribute("wszystkieKategorieSklepow", KategoriaSklepu.WSZYSTKIE_KATEGORIE);
        LOGGER.info("Użytkownik przegląda stronę sklepów, numer:" + numerStrony);
        return "/client/sklepy";
    }

    @PostMapping(value = "/flights")
    public RedirectView flightsPageForFilter(Optional<LotKlient.Filter> filter){
        if (filter.isPresent()) {
            LotKlient.FILTER.updateValues(filter.get());
        }
        return new RedirectView("/client/flights");
    }

    @GetMapping(value = "/flights")
    public String flightsPageForSortAndPagination(Model model, @RequestParam(name = "page") Optional<Integer> pageNum,
                                     @RequestParam(name = "sortDir") Optional<KierunekSortowania> kierunekSortowania,
                                     @RequestParam(name = "sortPar") Optional<LotKlient.Kolumna> parametrSortowania) {
        model.addAttribute("tableVal", Arrays.stream(LotKlient.Kolumna.values()).toList());
        model.addAttribute("currentPage", pageNum.orElse(1));
        model.addAttribute("parametrSortowania", parametrSortowania.orElse(LotKlient.Kolumna.DZIEN_ODLOTU));
        model.addAttribute("kierunekSortowania", kierunekSortowania.orElse(KierunekSortowania.ASC));

        model.addAttribute("totalPages",
                Math.ceilDiv(klientRepository.znajdzLiczbeLotow(LotKlient.FILTER), MAX_REKORDOW_NA_STRONIE));
        model.addAttribute("loty", klientRepository.znajdzLoty(
                LotKlient.FILTER,
                parametrSortowania.orElse(LotKlient.Kolumna.DZIEN_ODLOTU).queryLabel,
                kierunekSortowania.orElse(KierunekSortowania.ASC),
                MAX_REKORDOW_NA_STRONIE,
                pageNum.orElse(1)).stream().collect(LinkedHashMap::new, (map, item) -> map.put(item.toHTMLRow(), item.numerLotu()), Map::putAll));
        model.addAttribute("filter", LotKlient.FILTER);
        LOGGER.info("Użytkownik przegląda stronę rejsów, numer: " + pageNum.orElse(1));
        return "/client/loty";
    }

    @GetMapping("/flight")
    public ModelAndView formularzDodajBilet( @RequestParam(name = "numerLotu") int numerLotu) {
        ModelAndView mv = new ModelAndView("/fragments/formularz-bilet");
        mv.addObject("daneLotu", klientRepository.znajdzDaneLotu(numerLotu));
        mv.addObject("bilet", new Bilet(numerLotu));
        return mv;
    }

    @PostMapping("/flights/addTicket")
    public RedirectView dodajBilet(RedirectAttributes attributes, Bilet bilet) {
        Message message = klientService.dodajBilet(bilet);
        if (message.type().equals(Message.Type.ERROR)) {
            attributes.addFlashAttribute("errorMessage", message.message());
        } else {
            attributes.addFlashAttribute("infoMessage", message.message());
        }
        attributes.addFlashAttribute("tytul", message.title());
        return new RedirectView("/client/flights");
    }
}
