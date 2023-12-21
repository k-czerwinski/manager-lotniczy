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
        <table id="raport-lotnisko-pracownicy-table" class="table table-striped table-dark table-bordered" style="width: 94%;">
            <thead>
                <th>Nazwa Lotniska</th>
                <th>Kierownik Sklepu</th>
                <th>Barman</th>
                <th>Kelner</th>
                <th>Kucharz</th>
                <th>Personel Sprzątający</th>
                <th>Sprzedawca</th>
                <th>Agent Obsługi Naziemnej Odprawa Biletowa</th>
                <th>Agent Obsługi Naziemnej Obsługa Bagażu</th>
                <th>Agent Obsługi Naziemnej Informacyjne</th>
                <th>Agent Obsługi Naziemnej Pomoc dla Pasażerów</th>
                <th>Ochrona</th>
                <th>Specjalista ds. Bezpieczeństwa</th>
                <th>Pracownik Biurowy</th>
                <th>Personel Administracyjny</th>
                <th>Kontroler Bezpieczeństwa</th>
                <th>Planista Lotów</th>
                <th>Agent Obsługi Naziemnej Zarządzanie Lotami</th>
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