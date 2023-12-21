<div>
    <label for="pracownik-imie">Imię: </label>
    <input type="text" id="pracownik-imie" class="form-control" minlength="2"
           maxlength="30"
           name="imie" value="${nowyPracownik.imie!''}" required>

    <label for="pracownik-drugie-imie">Drugie imię: </label>
    <input type="text" id="pracownik-drugie-imie" class="form-control" minlength="2"
           maxlength="30"
           name="drugieImie" value="${nowyPracownik.drugieImie!''}">

    <label for="pracownik-nazwisko">Nazwisko: </label>
    <input type="text" id="pracownik-nazwisko" class="form-control" minlength="2"
           maxlength="30"
           name="nazwisko" value="${nowyPracownik.nazwisko!''}" required>

    <label for="pracownik-funkcja">Funkcja: </label>
    <select class="form-select col-md-3" aria-label=".form-select-lg example" id="pracownik-funkcja"
            name="funkcjaId" required>
        <#list funkcje as id, nazwaFunkcji>
            <option value="${id?c}">${nazwaFunkcji}</option>
        </#list>
    </select>

    <label for="pracownik-pracodawca">Pracodawca: </label>
    <select class="form-select col-md-3" aria-label=".form-select-lg example" id="pracownik-pracodawca"
            name="miejscePracyId" required>
        <#list pracodawcy as idPracodawcy, nazwaPracodawcy>
            <option value="<#if idPracodawcy?is_number>${idPracodawcy?c}<#else>${idPracodawcy}</#if>">${nazwaPracodawcy}</option>
        </#list>
    </select>
</div>