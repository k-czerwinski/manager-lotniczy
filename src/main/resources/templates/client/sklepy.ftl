<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../fragments/meta-info-and-styling.ftl">
    <script type="text/javascript" src="/js/sorting.js"></script>
    <title>Przegląd Sklepów - Manager Lotniczy</title>
</head>

<body onload="applySortingArrow('${parametrSortowania.htmlId}', '${kierunekSortowania}');">
<#include "../fragments/client-navbar-menu.ftl">
<div class="container mt-5" style="margin-top: 15px !important;">
    <form method="post" action="/client/shops">
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
</div>

</body>
</html>