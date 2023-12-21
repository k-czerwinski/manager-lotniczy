<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../fragments/meta-info-and-styling.ftl">
    <title>Raport Linie Lotnicze - Manager Lotniczy[ADMIN]</title>
</head>

<body>
<#include "../fragments/admin-menu.ftl">
<div class="container mt-5" style="margin-top: 15px !important;">
</div>
<div style="display: flex; justify-content: center;">
    <table id="raport-linie-lotnicze-table" class="table table-striped table-dark table-bordered" style="width: 94%; justify-self: center;">
        <thead>
        <th>ID</th>
        <th>Nazwa</th>
        <th>Liczba Samolotów</th>
        <th>Mechanik Lotniczy - Awionika</th>
        <th>Mechanik Lotniczy - Silnik</th>
        <th>Obsługa Biletów</th>
        <th>Obsługa Klienta</th>
        <th>Personel Pokładowy</th>
        <th>Pilot - Pierwszy Oficer</th>
        <th>Pilot - Kapitan</th>
        <th>Sprzedaż Biletów</th>
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