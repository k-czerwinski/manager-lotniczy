<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../fragments/meta-info-and-styling.ftl">
    <script type="text/javascript" src="/js/sorting.js"></script>
    <title>Pracownik - Manager Lotniczy[ADMIN]</title>
</head>

<body onload="applySortingArrow('${parametrSortowania.htmlId}', '${kierunekSortowania}');">
<#include "../fragments/admin-menu.ftl">
<div class="container mt-5" style="margin-top: 15px !important;">
    <#import "../fragments/alerty.ftl" as alerts>
    <#if dodawanieRejsuSuccessMessage??>
        <@alerts.info_message
        tytul="Rejs został dodany!"
        wiadomosc=dodawanieRejsuSuccessMessage/>
    </#if>
    <#if dodawanieRejsuErrorMessage??>
        <@alerts.error_message
        tytul="Rejs nie został dodany!"
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
</div>
</body>
</html>