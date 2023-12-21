<form method="post" action="/admin/loty" onsubmit="return validatePiloci()">
    <div class="form-row">
        <div class="form-group col-md-3" style="width: 100%;">
            <label for="rejs">Rejs</label>
            <select class="form-select col-md-3" aria-label=".form-select-lg example" id="rejs"
                    name="numerRejsu" required>
                <#list rejsy as numer_rejsu, dane_rejsu>
                    <option name="numerRej" value="${numer_rejsu}">${dane_rejsu}</option>
                </#list>
            </select>
        </div>
        <div class="form-group col-md-3" style="width: 100%;">
            <label for="samolot">Samolot</label>
            <select class="form-select col-md-3" aria-label=".form-select-lg example" id="samolot"
                    name="numerSamolotu" required>
                <#list samoloty as numer_samolotu, model_samolotu>
                    <option name="numerSam" value="${numer_samolotu}">${numer_samolotu}
                        : ${model_samolotu}</option>
                </#list>
            </select>
        </div>
        <div class="form-group col-md-3" style="width: 100%;">
            <label for="pilot1">Pilot 1(inny niż Pilot2)</label>
            <select class="form-select col-md-3" aria-label=".form-select-lg example" id="pilot1"
                    name="pilot1" required>
                <#list piloci as id, dane>
                    <option value="${id}">${dane}</option>
                </#list>
            </select>
        </div>

        <div class="form-group col-md-3" style="width: 100%;">
            <label for="pilot2">Pilot 2(inny niż Pilot 1)</label>
            <select class="form-select col-md-3" aria-label=".form-select-lg example" id="pilot2"
                    name="pilot2" required>
                <#list piloci as id, dane>
                    <option value="${id}">${dane}</option>
                </#list>
            </select>
        </div>

        <div class="form-group col-md-3" style="width: 100%;">
            <label for="dataOdlotu">Data Odlotu</label>
            <input type="date" class="form-control" id="dataOdlotu" name="dataOdlotu"
                   value="${nowyLot.dataOdlotu!''}" required>
        </div>
        <div class="form-group col-md-3" style="width: 100%;">
            <label for="dataPrzylotu">Data Przylotu</label>
            <input type="date" class="form-control" id="dataPrzylotu" name="dataPrzylotu"
                   value="${nowyLot.dataPrzylotu!''}" required>
        </div>
        <div class="form-group col-md-3" style="width: 100%;">
            <label for="cenaBiletu1klasa">Cena Biletu 1 klasa</label>
            <input type="number" step="1" class="form-control" id="cenaBiletu1klasa" name="cenaBiletu1klasa"
                   value="${nowyLot.cenaBiletu1klasa!''}" min="0" required>
        </div>
        <div class="form-group col-md-3" style="width: 100%;">
            <label for="cenaBiletu2klasa">Cena Biletu 2 klasa</label>
            <input type="number" step="1" class="form-control" id="cenaBiletu2klasa" name="cenaBiletu2klasa"
                   value="${nowyLot.cenaBiletu2klasa!''}" min="0" required>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odrzuć i
            zamknij
        </button>
        <button type="submit" class="btn btn-primary bg-dark">Zapisz</button>
    </div>
</form>
<script>
    function validatePiloci() {
        if ($('#pilot1').val()==$('#pilot2').val()) {
            alert("Musisz wybrać dwóch różnych pilotów!");
            return false;
        }
    }
</script>



