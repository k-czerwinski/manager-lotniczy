<div class="modal-body" id="messageModal">
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
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Zamknij
    </button>
</div>