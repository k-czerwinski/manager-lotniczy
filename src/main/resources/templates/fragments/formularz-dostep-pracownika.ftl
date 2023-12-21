<div class="modal-body" id="addAccessModal">
    <h3>Dodaj dostęp dla: </h3>
    <h4>${pracownik.imie} ${pracownik.drugieImie!''} ${pracownik.nazwisko}</h4>
    <form method="post" action="/admin/pracownik/dodaj-dostep" id="dodajDostepForm">
        <input type="hidden" id="pracownik-id" name="pracownikId" value="${dostep.pracownikId?c}"/>
        <div class="form-row">
            <div class="form-group col-md-3" style="width: 100%;">
                <label for="lotnisko">Lotnisko</label>
                <select class="form-select col-md-3" aria-label=".form-select-lg example" id="lotnisko"
                        name="lotniskoICAO">
                    <#list lotniska as id, nazwa>
                        <option name="lotnisko" value="${id}">
                            ${nazwa}</option>
                    </#list>
                </select>
            </div>
            <h5>Dostępy</h5>
            <div class="form-group form-check form-switch col-md-3" style="width: 100%;">
                    <input class="form-check-input" type="checkbox" id="strefa-kontroli-bezpieczenstwa"
                           name="strefaKontroliBezpieczenstwa" ${dostep.strefaKontroliBezpieczenstwa?string('check','')}>
                    <label class="form-check-label" for="strefa-kontroli-bezpieczenstwa">Strefa kontroli bezpieczeństwa</label>
            </div>
            <div class="form-group col-md-3" style="width: 100%;">
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" id="strefa-emigracyjna"
                           name="strefaEmigracyjna" ${dostep.strefaEmigracyjna?string('check','')}>
                    <label class="form-check-label" for="strefa-emigracyjna">Strefa emigracyjna</label>
                </div>
            </div>
            <div class="form-group col-md-3" style="width: 100%;">
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" id="plyta-lotniska"
                           name="plytaLotniska" ${dostep.plytaLotniska?string('check','')}>
                    <label class="form-check-label" for="plyta-lotniska">Płyta lotniska</label>
                </div>
            </div>
            <div class="form-group col-md-3" style="width: 100%;">
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" id="airSide"
                           name="airSide" ${dostep.airSide?string('check','')}>
                    <label class="form-check-label" for="airSide">Airside</label>
                </div>
            </div>

        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odrzuć i
                zamknij
            </button>
            <button type="submit" class="btn btn-primary bg-dark">Zapisz</button>
        </div>
    </form>
</div>




