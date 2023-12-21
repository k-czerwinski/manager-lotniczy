<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../fragments/meta-info-and-styling.ftl">
    <script type="text/javascript" src="/js/sorting.js"></script>
    <title>Linie Lotnicze - Manager Lotniczy[ADMIN]</title>
</head>

<body onload="applySortingArrow('${parametrSortowania.htmlId}', '${kierunekSortowania}');">
<#include "../fragments/admin-menu.ftl">
<div class="container mt-5" style="margin-top: 15px !important;">
    <#import "../fragments/alerty.ftl" as alerts>
    <#if dodawanieLiniSuccessMessage??>
        <@alerts.info_message
        tytul=tytul
        wiadomosc=dodawanieLiniSuccessMessage/>
    </#if>
    <#if dodawanieLiniErrorMessage??>
        <@alerts.error_message
        tytul=tytul
        wiadomosc=dodawanieLiniErrorMessage/>
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
        tableContent=linie_lotnicze
        />
    </div>
    <div class="row md-3" style="margin-top: 15px !important;">
        <button type="button" class="btn btn-primary bg-dark" data-bs-toggle="modal" data-bs-target="#addAirportModal">
            Dodaj linie lotniczą
        </button>
        <!-- Modal -->
        <div class="modal fade" id="addAirportModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="addAirportModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="addAirportModalLabel">Formularz-dodaj linię lotniczą</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="/admin/linie-lotnicze">
                            <div class="form-row">
                                <label for="linia-lotnicza-nazwa">Nazwa: </label>
                                <input type="text" id="linia-lotnicza-nazwa" class="form-control" minlength="3"
                                       maxlength="30"
                                       name="nazwa" value="${linia_lotnicza.nazwa!''}" required>
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