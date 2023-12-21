<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../fragments/meta-info-and-styling.ftl">
    <script type="text/javascript" src="/js/sorting.js"></script>
    <title>Pracownicy - Manager Lotniczy[ADMIN]</title>
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
<div class="container">
    <div class="row row-cols-xl-1" style="margin-top: 15px !important;">
        <form method="post" action="/admin/pracownicy/filtr">
            <!-- Cena Biletu 1 klasa -->
            <div class="form-row horizontal-flex">
                <div class="col-mb-6 horizontal-padding">
                    <label for="filtr-imie">Imię: </label>
                    <input type="text" id="filtr-imie" class="form-control"
                           maxlength="30"
                           name="imie" value="${filtr.imie!''}">
                </div>
                <div class="col-mb-6 horizontal-padding">
                    <label for="filtr-nazwisko">Nazwisko: </label>
                    <input type="text" id="filtr-nazwisko" class="form-control"
                           maxlength="30"
                           name="nazwisko" value="${filtr.nazwisko!''}">
                </div>
            </div>
            <button type="submit" class="btn btn-primary bg-dark" style="justify-self: center;">Submit</button>
        </form>
        <p>Kliknij na pracownika, aby zobaczyć jego dostępy i dodać nowy dostęp.</p>
        <#import "../fragments/tablica-z-paginacja-klikalna.ftl" as tab>
        <@tab.paginated_clickable_table
        thValues=tableVal
        parametrSortowania=parametrSortowania
        kierunekSortowania=kierunekSortowania
        currentPage=currentPage
        totalPages=totalPages
        tableContent=pracownicy
        functionName='pracownikSelected'
        />
    </div>
    <div class="row md-3" style="margin-top: 15px !important;">
        <button type="button" class="btn btn-primary bg-dark" data-bs-toggle="modal" data-bs-target="#addWorkerModal">
            Dodaj pracownika
        </button>
        <div class="modal fade" id="addWorkerModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="addWorkerModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="addWorkerModalLabel">Formularz-dodaj pracownika</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="/admin/pracownicy/dodaj">
                            <div>
                                <label for="typPracownika">Miejsce pracy</label>
                                <select class="form-select col-md-3" aria-label=".form-select-lg example"
                                        id="typPracownika" name="typPracownika" onchange="typPracySelected()" required>
                                    <option disabled selected value> -- wybierz -- </option>
                                    <#foreach typ in typyPracownikow>
                                        <option name="typPracownika" value="${typ}">
                                            ${typ!''}</option>
                                    </#foreach>
                                </select>
                                <div id="dodaj-pracownika-form-content"></div>
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
        <script>
            function typPracySelected() {
                var data = "typPracownika=" + $('#typPracownika').val()
                $.ajax({
                    type: 'get',
                    url: 'http://localhost:8080/admin/pracownik/formularz',
                    data: data,

                    success: function (formularz) {
                        $('#dodaj-pracownika-form-content').html(formularz);
                    },
                })
            }

            function pracownikSelected(id) {
                var data = "pracownikId=" + parseInt(id);
                $.ajax({
                    type: 'get',
                    url: 'http://localhost:8080/admin/pracownik',
                    data: data,

                    success: function (danePracownika) {
                        $('#widokPracownikaModalContent').html(danePracownika);
                    },
                })
                $('#displayWorkerModal').modal('show');
            }

        </script>
        <div class="modal fade" id="displayWorkerModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="addWorkerModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="addWorkerModalLabel">Pracownik</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div id="widokPracownikaModalContent">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>