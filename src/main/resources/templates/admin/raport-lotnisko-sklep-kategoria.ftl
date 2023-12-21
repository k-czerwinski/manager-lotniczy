<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../fragments/meta-info-and-styling.ftl">
    <title>Raport lotnisko-pracownicy - Manager Lotniczy[ADMIN]</title>
</head>

<body>
<#include "../fragments/admin-menu.ftl">
<div class="container mt-5" style="margin-top: 15px !important;">
</div>
<div style="display: flex; justify-content: center;">
    <table id="raport-lotnisko-pracownicy-table" class="table table-striped table-dark table-bordered" style="width: 94%; justify-self: center;">
        <thead>
        <th>ICAO</th>
        <th>Nazwa Lotniska</th>
        <th>Odzież Podróżna</th>
        <th>Sklep z Pamiątkami</th>
        <th>Księgarnia Lotnicza</th>
        <th>Elektronika Podróżna</th>
        <th>Kawiarnia Lotnicza</th>
        <th>Restauracja z Widokiem na Lotnisko</th>
        <th>Sklep z Gadżetami Lotniczymi</th>
        <th>Sklep z Elektroniką Podróżną</th>
        <th>Sklep z Alkoholami na Wynos</th>
        <th>Sklep z Kosmetykami Podróżnymi</th>
        <th>Sklep z Akcesoriami Podróżnymi</th>
        <th>Sklep z Prasą i Czasopismami</th>
        <th>Sklep z Biżuterią Podróżną</th>
        <th>Sklep z Artykułami dla Dzieci</th>
        <th>Sklep z Obuwiem Wygodnym do Podróży</th>
        </thead>
        <tbody>
        <#foreach row in raportLotnisk>
            <tr>
                ${row}
            </tr>
        </#foreach>
        </tbody>
    </table>
</div>
</body>
</html>