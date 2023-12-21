<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../fragments/meta-info-and-styling.ftl">
    <script type="text/javascript" src="/js/sorting.js"></script>
    <title>Sklepy- Manager Lotniczy[ADMIN]</title>
</head>

<body onload="applySortingArrow('${parametrSortowania.htmlId}', '${kierunekSortowania}');">
<#include "../fragments/admin-menu.ftl">
<div class="container mt-5" style="margin-top: 15px !important;">
    <#import "../fragments/alerty.ftl" as alerts>
    <#if infoMessage??>
        <@alerts.info_message
        tytul=tytul
        wiadomosc=infoMessage/>
    </#if>
    <#if errorMessage??>
        <@alerts.error_message
        tytul=tytul
        wiadomosc=errorMessage/>
    </#if>
</div>
<div class="container mt-5" style="margin-top: 15px !important;">
    <form method="post" action="/admin/sklepy/filtr">
        <!-- Cena Biletu 1 klasa -->
        <div class="form-row horizontal-flex">
            <div class="form-group col-mb-6 horizontal-padding">
                <label for="lotnisko">Lotnisko</label>
                <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" id="lotnisko"
                        name="lotnisko">
                    <option value="${wszystkieLotniska.ICAO}"
                            <#if filtr.lotniskoICAO=="">selected</#if>>${wszystkieLotniska.nazwa}</option>
                    <#foreach lotnisko in lotniska>
                        <option name="lotnisko" value="${lotnisko.ICAO}"
                                <#if filtr.lotniskoICAO == lotnisko.ICAO>selected</#if>>
                            ${lotnisko.ICAO!''}/${lotnisko.IATA!''}
                            - ${lotnisko.miasto!''} ${lotnisko.nazwa!''}</option>
                    </#foreach>
                </select>
            </div>
            <div class="form-group col-mb-6 horizontal-padding">
                <label for="kategoria-sklepu">Kategoria sklepu</label>
                <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example"
                        id="kategoria-sklepu" name="kategoria-sklepu">
                    <option value="${wszystkieKategorieSklepow.id()!''}"
                            <#if !filtr.kategoriaId??>selected</#if>>${wszystkieKategorieSklepow.nazwa()}</option>
                    <#foreach kat in kategorieSklepow>
                        <option value="${kat.id()!''}"
                                <#if filtr.kategoriaId?? && filtr.kategoriaId == kat.id()>selected</#if>>${kat.nazwa()}</option>
                    </#foreach>
                </select>
            </div>
        </div>
        <button type="submit" class="btn btn-primary bg-dark" style="justify-self: center;">Submit</button>
    </form>
</div>
<div class="container">
    <div class="row mt-5" style="margin-top: 15px !important;">
        <#import "../fragments/tablica-z-paginacja.ftl" as tab>
        <@tab.paginated_table
        thValues=tableVal
        parametrSortowania=parametrSortowania
        kierunekSortowania=kierunekSortowania
        currentPage=currentPage
        totalPages=totalPages
        tableContent=sklepy
        />
    </div>
    <div class="row md-3" style="margin-top: 15px !important;">
        <button type="button" class="btn btn-primary bg-dark" data-bs-toggle="modal" data-bs-target="#addSklepModal">
            Dodaj sklep
        </button>
        <!-- Modal -->
        <div class="modal fade" id="addSklepModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="addSklepModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="addSklepModalLabel">Formularz-dodaj sklep</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="/admin/sklepy/dodajSklep">
                            <div class="form-row">
                                <label for="nazwa-sklepu">Nazwa sklepu: </label>
                                <input type="text" id="nazwa-sklepu" class="form-control" minlength="3"
                                       maxlength="50"
                                       name="nazwaSklepu" required>

                                <label for="kategoria">Kategoria sklepu</label>
                                <select class="form-select col-md-3" aria-label=".form-select-lg example" id="kategoria" name="kategoriaSklepu" required>
                                    <#foreach kat in kategorieSklepow>
                                        <option name="kategoriaSklepu" value="${kat.id()}">
                                            ${kat.nazwa()!''}</option>
                                    </#foreach>
                                </select>

                                <label for="lotnisko">Lotnisko</label>
                                <select class="form-select col-md-3" aria-label=".form-select-lg example" id="lotnisko" name="lotnisko" required>
                                    <#foreach l in lotniska>
                                        <option name="lotnisko" value="${l.ICAO}">
                                            ${l.ICAO!''}/${l.IATA!''} - ${l.miasto!''} ${l.nazwa!''}</option>
                                    </#foreach>
                                </select>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odrzuć i
                                    zamknij
                                </button>
                                <button type="submit" class="btn btn-primary bg-dark">Zapisz</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>