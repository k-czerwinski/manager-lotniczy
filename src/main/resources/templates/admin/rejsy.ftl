<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../fragments/meta-info-and-styling.ftl">
    <script type="text/javascript" src="/js/sorting.js"></script>
    <title>Rejsy - Manager Lotniczy[ADMIN]</title>
</head>

<body onload="applySortingArrow('${parametrSortowania.htmlId}', '${kierunekSortowania}');">
<#include "../fragments/admin-menu.ftl">
<div class="container mt-5" style="margin-top: 15px !important;">
    <#import "../fragments/alerty.ftl" as alerts>
    <#if dodawanieRejsuSuccessMessage??>
        <@alerts.info_message
        tytul=tytul
        wiadomosc=dodawanieRejsuSuccessMessage/>
    </#if>
    <#if dodawanieRejsuErrorMessage??>
        <@alerts.error_message
        tytul=tytul
        wiadomosc=dodawanieRejsuErrorMessage/>
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
        tableContent=rejsy
        />
    </div>
    <div class="row md-3" style="margin-top: 15px !important;">
        <button type="button" class="btn btn-primary bg-dark" data-bs-toggle="modal" data-bs-target="#addAirportModal">
            Dodaj rejs
        </button>
        <!-- Modal -->
        <div class="modal fade" id="addAirportModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="addAirportModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="addAirportModalLabel">Formularz-dodaj rejs</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="/admin/rejsy" onsubmit="return validateLotniska();">
                            <div class="form-row">
                                <div class="form-group col-md-3" style="width: 100%;">
                                    <label for="rejs-numer">Numer rejsu: </label>
                                    <input type="text" id="rejs-numer" name="numerRejsu" class="form-control"
                                           minlength="5" maxlength="5"
                                           name="numerRejsu" style="text-transform:uppercase" required>
                                </div>
                                <div class="form-group col-md-3" style="width: 100%;">
                                    <label for="lotnisko-odlotu">Lotnisko odlotu</label>
                                    <select class="form-select col-md-3" aria-label=".form-select-lg example"
                                            id="lotnisko-odlotu" name="lotniskoOdlotuICAO" required>
                                        <#foreach lotnisko in lotniska>
                                            <option name="lotnisko-odlotu" value="${lotnisko.ICAO}">
                                                ${lotnisko.ICAO!''}/${lotnisko.IATA!''}
                                                - ${lotnisko.miasto!''} ${lotnisko.nazwa!''}</option>
                                        </#foreach>
                                    </select>
                                </div>
                                <div class="form-group col-md-3" style="width: 100%;">
                                    <label for="lotnisko-przylotu">Lotnisko przylotu</label>
                                    <select class="form-select col-md-3" aria-label=".form-select-lg example"
                                            id="lotnisko-przylotu" name="lotniskoPrzylotuICAO" required>
                                        <#foreach lotnisko in lotniska>
                                            <option name="lotniskoPrzylotu" value="${lotnisko.ICAO}">
                                                ${lotnisko.ICAO!''}/${lotnisko.IATA!''}
                                                - ${lotnisko.miasto!''} ${lotnisko.nazwa!''}</option>
                                        </#foreach>
                                    </select>
                                </div>
                                <div class="form-group col-md-3" style="width: 100%;">
                                    <label for="godzinaOdlotu">Godzina Odlotu: </label>
                                    <input type="time" class="form-control" id="godzinaOdlotu" name="godzinaOdlotu"
                                           value="${rejs.godzinaOdlotu!''}" required>
                                </div>
                                <div class="form-group col-md-3" style="width: 100%;">
                                    <label for="godzinaPrzylotu">Godzina Przylotu: </label>
                                    <input type="time" class="form-control" id="godzinaPrzylotu" name="godzinaPrzylotu" required>
                                </div>
                                <div class="form-group col-md-3" style="width: 100%;">
                                    <label for="liniaLotnicza">Linia lotnicza</label>
                                    <select class="form-select col-md-3" aria-label=".form-select-lg example"
                                            id="liniaLotnicza" name="liniaLotniczaId" required>
                                        <#foreach linia in linie_lotnicze>
                                            <option name="liniaLotniczaId" value="${linia.id}">
                                                ${linia.nazwa!''}</option>
                                        </#foreach>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odrzuć i
                                    zamknij
                                </button>
                                <button type="submit" class="btn btn-primary bg-dark">Zapisz</button>
                            </div>
                        </form>
                        <script>
                            function validateLotniska() {
                                if ($('#lotnisko-odlotu').val() == $('#lotnisko-przylotu').val()) {
                                    alert("Lotniska przylotu i odlotu muszą być inne!");
                                    return false;
                                }
                            }
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>