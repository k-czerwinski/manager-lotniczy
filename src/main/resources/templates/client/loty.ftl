<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../fragments/meta-info-and-styling.ftl">
    <script type="text/javascript" src="/js/sorting.js"></script>
    <title>Znajdz loty</title>
</head>

<body onload="applySortingArrow('${parametrSortowania.htmlId}', '${kierunekSortowania}');">
<#include "../fragments/client-navbar-menu.ftl">
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
    <form method="post" action="/client/flights">
        <!-- Lotnisko Odlotu -->
        <div class="form-row horizontal-flex">
            <div class="form-group col-md-3 horizontal-padding">
                <label for="lotniskoOdlotuICAO">ICAO Lotnisko Odlotu</label>
                <input type="text" maxlength="50" class="form-control" id="lotniskoOdlotuICAO" name="lotniskoOdlotuICAO" value="${filter.lotniskoOdlotuICAO!''}">
            </div>
            <div class="form-group col-md-3 horizontal-padding">
                <label for="lotniskoOdlotuIATA">IATA Lotnisko Odlotu</label>
                <input type="text" maxlength="50" class="form-control" id="lotniskoOdlotuIATA" name="lotniskoOdlotuIATA" value="${filter.lotniskoOdlotuIATA!''}">
            </div>
            <div class="form-group col-md-3 horizontal-padding">
                <label for="lotniskoOdlotuNazwa">Nazwa Lotniska Odlotu</label>
                <input type="text" maxlength="50" class="form-control" id="lotniskoOdlotuNazwa" name="lotniskoOdlotuNazwa" value="${filter.lotniskoOdlotuNazwa!''}">
            </div>
            <div class="form-group col-md-3 horizontal-padding">
                <label for="lotniskoOdlotuMiasto">Miasto Lotniska Odlotu</label>
                <input type="text" maxlength="50" class="form-control" id="lotniskoOdlotuMiasto" name="lotniskoOdlotuMiasto" value="${filter.lotniskoOdlotuMiasto!''}">
            </div>
        </div>

        <!-- Lotnisko Przylotu -->
        <div class="form-row horizontal-flex">
            <div class="form-group col-md-3 horizontal-padding">
                <label for="lotniskoPrzylotuICAO">ICAO Lotnisko Przylotu</label>
                <input type="text" maxlength="50" class="form-control" id="lotniskoPrzylotuICAO" name="lotniskoPrzylotuICAO" value=${filter.lotniskoPrzylotuICAO!''}>
            </div>
            <div class="form-group col-md-3 horizontal-padding">
                <label for="lotniskoPrzylotuIATA">IATA Lotnisko Przylotu</label>
                <input type="text" maxlength="50" class="form-control" id="lotniskoPrzylotuIATA" name="lotniskoPrzylotuIATA" value="${filter.lotniskoPrzylotuIATA!''}">
            </div>
            <div class="form-group col-md-3 horizontal-padding">
                <label for="lotniskoPrzylotuNazwa">Nazwa Lotniska Przylotu</label>
                <input type="text" maxlength="50" class="form-control" id="lotniskoPrzylotuNazwa" name="lotniskoPrzylotuNazwa" value="${filter.lotniskoPrzylotuNazwa!''}">
            </div>
            <div class="form-group col-md-3 horizontal-padding">
                <label for="lotniskoPrzylotuMiasto">Miasto Lotniska Przylotu</label>
                <input type="text" maxlength="50" class="form-control" id="lotniskoPrzylotuMiasto" name="lotniskoPrzylotuMiasto" value="${filter.lotniskoPrzylotuMiasto!''}">
            </div>
        </div>

        <!-- Data Odlotu -->
        <div class="form-row flex-box horizontal-flex">
            <div class="form-group col-md-3 horizontal-padding">
                <label for="dataOdlotuOd">Data Odlotu (Od)</label>
                <input type="date" class="form-control" id="dataOdlotuOd" name="dataOdlotuOd" value="${filter.dataOdlotuOd!''}">
            </div>
            <div class="form-group col-md-3 horizontal-padding">
                <label for="dataOdlotuDo">Data Odlotu (Do)</label>
                <input type="date" class="form-control" id="dataOdlotuDo" name="dataOdlotuDo" value="${filter.dataOdlotuDo!''}">
            </div>
            <div class="form-group col-md-3 horizontal-padding">
                <label for="godzinaOdlotuOd">Godzina Odlotu (Od)</label>
                <input type="time" class="form-control" id="godzinaOdlotuOd" name="godzinaOdlotuOd" value="${filter.godzinaOdlotuOd!''}">
            </div>
            <div class="form-group col-md-3 horizontal-padding">
                <label for="godzinaOdlotuDo">Godzina Odlotu (Do)</label>
                <input type="time" class="form-control" id="godzinaOdlotuDo" name="godzinaOdlotuDo" value="${filter.godzinaOdlotuDo!''}">
            </div>
        </div>

        <!-- Cena Biletu 1 klasa -->
        <div class="form-row horizontal-flex">
            <div class="form-group col-md-3 horizontal-padding">
                <label for="cenaBiletu1klasaOd">Cena Biletu 1 klasa (Od)</label>
                <input type="number" step="1" class="form-control" id="cenaBiletu1klasaOd" name="cenaBiletu1klasaOd" value="${filter.cenaBiletu1klasaOd!''}">
            </div>
            <div class="form-group col-md-3 horizontal-padding">
                <label for="cenaBiletu1klasaDo">Cena Biletu 1 klasa (Do)</label>
                <input type="number" step="1" class="form-control" id="cenaBiletu1klasaDo" name="cenaBiletu1klasaDo" value="${filter.cenaBiletu1klasaDo!''}">
            </div>
            <div class="form-group col-md-3 horizontal-padding">
                <label for="cenaBiletu2klasaOd">Cena Biletu 2 klasa (Od)</label>
                <input type="number" step="1" class="form-control" id="cenaBiletu2klasaOd" name="cenaBiletu2klasaOd" value="${filter.cenaBiletu2klasaOd!''}">
            </div>
            <div class="form-group col-md-3 horizontal-padding">
                <label for="cenaBiletu2klasaDo">Cena Biletu 2 klasa (Do)</label>
                <input type="number" step="1" class="form-control" id="cenaBiletu2klasaDo" name="cenaBiletu2klasaDo" value="${filter.cenaBiletu2klasaDo!''}">
            </div>
        </div>

        <button type="submit" class="btn btn-primary bg-dark" style="justify-self: center;">Submit</button>
    </form>
</div>
<div class="container">
    <p>Kliknij na lot, aby kupić bilet.</p>
    <div class="row mt-5" style="margin-top: 15px !important;">
            <#import "../fragments/tablica-z-paginacja-klikalna.ftl" as tab>
            <@tab.paginated_clickable_table
                thValues=tableVal
                parametrSortowania=parametrSortowania
                kierunekSortowania=kierunekSortowania
                currentPage=currentPage
                totalPages=totalPages
                tableContent=loty
                functionName='lotSelected'
            />
    </div>
    <script>
    function lotSelected(numerLotu) {
    var data = "numerLotu=" + parseInt(numerLotu);
    $.ajax({
    type: 'get',
    url: 'http://localhost:8080/client/flight',
    data: data,

    success: function (daneKlienta) {
    $('#zakupLotuModalContent').html(daneKlienta);
    },
    })
    $('#zakupBiletModal').modal('show');
    }
    </script>
    <div class="modal fade" id="zakupBiletModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="zakupBiletModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="zakupBiletModalLabel">Zakup biletu</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div id="zakupLotuModalContent"></div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>