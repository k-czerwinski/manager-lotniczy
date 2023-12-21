<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <script type="text/javascript" src="/js/sorting.js"></script>
    <#include "../fragments/meta-info-and-styling.ftl">
    <title>Lotniska - Manager Lotniczy[ADMIN]</title>
</head>
<body onload="applySortingArrow('${parametrSortowania.htmlId}', '${kierunekSortowania}');">
<#include "../fragments/admin-menu.ftl">
<div class="container mt-5" style="margin-top: 15px !important;">
    <#import "../fragments/alerty.ftl" as alerts>
    <#if dodawanieLotuSuccessMessage??>
        <@alerts.info_message
        tytul=tytul
        wiadomosc=dodawanieLotuSuccessMessage/>
    </#if>
    <#if dodawanieLotuErrorMessage??>
        <@alerts.error_message
        tytul=tytul
        wiadomosc=dodawanieLotuErrorMessage/>
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
        tableContent=loty
        />
    </div>
    <div class="row md-3" style="margin-top: 15px !important;">
        <button type="button" class="btn btn-primary bg-dark" data-bs-toggle="modal" data-bs-target="#addAirportModal">
            Dodaj lot
        </button>
        <!-- Modal -->
        <div class="modal fade" id="addAirportModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="addAirportModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="addAirportModalLabel">Formularz-dodaj lot</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="form-row">
                            <label for="liniaLotniczaId">Linia lotnicza</label>
                            <select class="form-select col-md-3" aria-label=".form-select-lg example"
                                    id="liniaLotniczaId" name="liniaLotniczaId" onchange="rejsSelected()" required>
                                <#foreach linia in linie_lotnicze>
                                    <option name="liniaLotniczaId" value="${linia.id}">
                                        ${linia.nazwa!''}</option>
                                </#foreach>
                            </select>
                        </div>
                        <script>
                            function rejsSelected() {
                                var val = $("#liniaLotniczaId").val();
                                var data = "liniaLotniczaId=" + val;
                                $.ajax({
                                    type: 'get',
                                    url: '/admin/znajdzDlaLini',
                                    data: data,

                                    success: function (samoloty) {
                                        $('#myTestDiv').html(samoloty);
                                    },
                                })
                            }
                        </script>
                        <div id="myTestDiv" class="myTestDiv"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>