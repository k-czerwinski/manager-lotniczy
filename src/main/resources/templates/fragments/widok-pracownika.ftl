<div class="modal-body" id="widokPracownikaId" style="display: flex; flex-direction: column;">
    <div>
        <h2>${pracownik.imie} ${pracownik.drugieImie!''} ${pracownik.nazwisko}</h2>
        <h4>Funkcje: </h4>
        <p>${pracownik.pracownikLini!''}</p>
        <p>${pracownik.pracownikSklepu!''}</p>
        <p>${pracownik.pracownikLotniska!''}</p>
        <h4>Dostępy:</h4>
        <div class="container centered-container">
            <table id="flight-table" class="table table-striped table-dark table-bordered" style="width:100%">
                <thead>
                <th>Lotnisko</th>
                <th>Strefa kontroli bezpieczenstwa</th>
                <th>Strefa emigracyjna</th>
                <th>Płyta lotniska</th>
                <th>Airside</th>
                </thead>
                <tbody>
                <#assign locked='<svg xmlns="http://www.w3.org/2000/svg" height="16" width="14" viewBox="0 0 448 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.--><path fill="#ffffff" d="M144 144v48H304V144c0-44.2-35.8-80-80-80s-80 35.8-80 80zM80 192V144C80 64.5 144.5 0 224 0s144 64.5 144 144v48h16c35.3 0 64 28.7 64 64V448c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V256c0-35.3 28.7-64 64-64H80z"/></svg>'>
                <#assign unlocked='<svg xmlns="http://www.w3.org/2000/svg" height="16" width="18" viewBox="0 0 576 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.--><path fill="#ffffff" d="M352 144c0-44.2 35.8-80 80-80s80 35.8 80 80v48c0 17.7 14.3 32 32 32s32-14.3 32-32V144C576 64.5 511.5 0 432 0S288 64.5 288 144v48H64c-35.3 0-64 28.7-64 64V448c0 35.3 28.7 64 64 64H384c35.3 0 64-28.7 64-64V256c0-35.3-28.7-64-64-64H352V144z"/></svg>'>
                <#foreach d in dostepy>
                    <tr>
                        <td>${d.lotniskoICAO}</td>
                        <td><#if d.strefaKontroliBezpieczenstwa>${unlocked}<#else>${locked}</#if></td>
                        <td><#if d.strefaEmigracyjna>${unlocked}<#else>${locked}</#if></td>
                        <td><#if d.plytaLotniska>${unlocked}<#else>${locked}</#if></td>
                        <td><#if d.airSide>${unlocked}<#else>${locked}</#if></td>
                    </tr>
                </#foreach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Zamknij
        </button>
        <button type="submit" class="btn btn-primary bg-dark" onclick="pracownikDodajDostep(${pracownik.id?c})">Dodaj dostep</button>
    </div>
    <script>
        function pracownikDodajDostep(id) {
            var data = "pracownikId=" + parseInt(id);
            $.ajax({
                type: 'get',
                url: '/admin/pracownik/dostep',
                data: data,

                success: function (daneDostepu) {
                    $('#widokPracownikaModalContent').html(daneDostepu);
                },
            })
        }
    </script>
</div>