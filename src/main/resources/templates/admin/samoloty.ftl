<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../fragments/meta-info-and-styling.ftl">
    <script type="text/javascript" src="/js/sorting.js"></script>
    <title>Samoloty - Manager Lotniczy[ADMIN]</title>
</head>

<body onload="applySortingArrow('${parametrSortowania.htmlId}', '${kierunekSortowania}');">
<#include "../fragments/admin-menu.ftl">
<div class="container mt-5" style="margin-top: 15px !important;">
    <#import "../fragments/alerty.ftl" as alerts>
    <#if successMessage??>
        <@alerts.info_message
        tytul=tytul
        wiadomosc=successMessage/>
    </#if>
    <#if errorMessage??>
        <@alerts.error_message
        tytul=tytul
        wiadomosc=errorMessage/>
    </#if>

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
        tableContent=samoloty
        />
    </div>
    <div class="row md-3" style="margin-top: 15px !important;">
        <button type="button" class="btn btn-primary bg-dark" data-bs-toggle="modal" data-bs-target="#addAirplaneModal">
            Dodaj samolot
        </button>
        <!-- Modal -->
        <div class="modal fade" id="addAirplaneModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="addAirplaneModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="addAirplaneModalLabel">Formularz-dodaj samolot</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="/admin/dodajSamolot">
                            <div class="form-row">
                                <label for="samolot-numer">Numer samolotu: </label>
                                <input type="text" id="samolot-numer" class="form-control" minlength="6" maxlength="6"
                                       name="numer" style="text-transform:uppercase" required>
                                <label for="samolot-model">Model samolotu: </label>
                                <input type="text" id="samolot-model" class="form-control" maxlength="30"
                                       name="model" required>
                                <label for="liczba-pasazerow-klasa-1">Liczba pasazerow w klasie 1: </label>
                                <input type="number" id="liczba-pasazerow-klasa-1" class="form-control" min="0"
                                       name="liczbaPasazerowKlasa1" required>
                                <label for="liczba-pasazerow-klasa-2">Liczba pasazerow w klasie 2: </label>
                                <input type="number" id="liczba-pasazerow-klasa-2" class="form-control" min="0"
                                       name="liczbaPasazerowKlasa2" required>
                                <label for="liczba-zalogi">Liczba załogi: </label>
                                <input type="number" id="liczba-zalogi" class="form-control" min="0"
                                       name="liczbaZalogi" required>
                                <label for="linie-lotnicze">Linie lotnicze: </label>
                                <select class="form-select col-md-3" aria-label=".form-select-lg example" id="linie-lotnicze"
                                        name="liniaLotniczaId" required>
                                    <#list linieLotnicze as idLini, nazwaLini>
                                        <option value="${idLini?c}">${nazwaLini}</option>
                                    </#list>
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