<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../fragments/meta-info-and-styling.ftl">
    <script type="text/javascript" src="/js/sorting.js"></script>
    <title>Lotniska - Manager Lotniczy[ADMIN]</title>
</head>

<body onload="applySortingArrow('${parametrSortowania.htmlId}', '${kierunekSortowania}');">
<#include "../fragments/admin-menu.ftl">
<div class="container mt-5" style="margin-top: 15px !important;">
    <#import "../fragments/alerty.ftl" as alerts>
    <#if dodawanieLotniskaSuccessMessage??>
        <@alerts.info_message
            tytul=tytul
            wiadomosc=dodawanieLotniskaSuccessMessage/>
    </#if>
    <#if dodawanieLotniskaErrorMessage??>
        <@alerts.error_message
            tytul=tytul
            wiadomosc=dodawanieLotniskaErrorMessage/>
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
        tableContent=lotniska
        />
    </div>
    <div class="row md-3" style="margin-top: 15px !important;">
        <button type="button" class="btn btn-primary bg-dark" data-bs-toggle="modal" data-bs-target="#addAirportModal">
            Dodaj lotnisko
        </button>
        <!-- Modal -->
        <div class="modal fade" id="addAirportModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="addAirportModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="addAirportModalLabel">Formularz-dodaj lotnisko</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="/admin/lotniska">
                            <div class="form-row">
                                <label for="lotnisko-icao">ICAO: </label>
                                <input type="text" id="lotnisko-icao" class="form-control" minlength="4" maxlength="4"
                                       value="${lotnisko.ICAO!''}"
                                       name="ICAO" style="text-transform:uppercase" required>
                                <label for="lotnisko-iata">IATA: </label>
                                <input type="text" id="lotnisko-iata" class="form-control" minlength="3" maxlength="3"
                                       value="${lotnisko.IATA!''}"
                                       name="IATA" style="text-transform:uppercase" required>
                                <label for="lotnisko-nazwa">Nazwa: </label>
                                <input type="text" id="lotnisko-nazwa" class="form-control" minlength="5" maxlength="50"
                                       name="nazwa" value="${lotnisko.nazwa!''}" required>
                                <label for="lotnisko-miasto">Miasto: </label>
                                <input type="text" id="lotnisko-miasto" class="form-control" minlength="3"
                                       maxlength="30"
                                       name="miasto" value="${lotnisko.miasto!''}" required>
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