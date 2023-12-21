<#macro info_message tytul wiadomosc>
    <div class="container mt-5">
        <div class="alert alert-info">
            <h4 class="alert-heading">${tytul}</h4>
            <p class="mb-0">${wiadomosc}</p>
        </div>
    </div>
</#macro>
<#macro error_message tytul wiadomosc>
    <div class="container mt-5">
        <div class="alert alert-danger">
            <h4 class="alert-heading">${tytul}</h4>
            <p class="mb-0">${wiadomosc}</p>
        </div>
    </div>
</#macro>