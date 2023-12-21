<div class="modal-body" id="addAccessModal">
    <h4>Z: ${daneLotu.lotniskoOdlotu()}(${daneLotu.lotniskoOdlotuKod()})</h4>
    <h4>Do: ${daneLotu.lotniskoPrzylotu()}(${daneLotu.lotniskoPrzylotuKod()})</h4>
    <h4>Termin: ${daneLotu.dataOdlotu()} ${daneLotu.czasOdlotu()} -> ${daneLotu.dataPrzylotu()} ${daneLotu.czasPrzylotu()}</h4>
    <form method="post" action="/client/flights/addTicket" id="zakupBiletForm" onsubmit="return validateForm()">
        <input type="hidden" id="numer-lotu" name="numerLotu" value="${bilet.numerLotu?c}"/>
        <label for="pasazer-imie">Imię: </label>
        <input type="text" id="pasazer-imie" class="form-control" minlength="2"
               maxlength="30"
               name="imie" required>

        <label for="pasazer-drugie-imie">Drugie imię: </label>
        <input type="text" id="pasazer-drugie-imie" class="form-control" minlength="2"
               maxlength="30"
               name="drugieImie">

        <label for="pasazer-nazwisko">Nazwisko: </label>
        <input type="text" id="pasazer-nazwisko" class="form-control" minlength="2"
               maxlength="30"
               name="nazwisko" required>

        <label for="numer-dowodu">Numer dowodu osobistego: </label>
        <input type="text" id="numer-dowodu" class="form-control" minlength="9"
               maxlength="9"
               name="numerDowodu">

        <label for="numer-paszportu">Numer paszportu: </label>
        <input type="text" id="numer-paszportu" class="form-control" minlength="9"
               maxlength="9"
               name="numerPaszportu">

        <label for="klasa-siedzenia">Klasa miejsca:</label>
        <select id="klasa-siedzenia" class="form-select col-md-3" name="klasaSiedzenia">
            <option value="1">1</option>
            <option value="2">2</option>
        </select>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odrzuć i
                zamknij
            </button>
            <button type="submit" class="btn btn-primary bg-dark">Zapisz</button>
        </div>
    </form>
    <script>
        function validateForm(){
            if ($("#numer-dowodu").val() == '' && $("#numer-paszportu").val() == '') {
                alert("Musisz podać dane przynajmniej 1 dokumentu tożsamości!");
                return false;
            }
        }
    </script>
</div>




