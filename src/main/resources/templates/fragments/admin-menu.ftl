<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/admin/">Manager Lotniczy</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/admin/">Strona główna</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/lotniska">Lotniska</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/linie-lotnicze" methods="get">Linie lotnicze</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link btn btn-secondary btn-dark dropdown-toggle" href="#" role="button" id="dropdownMenuRaport" data-bs-toggle="dropdown" aria-expanded="false">Raporty</a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuRaport">
                        <li>
                            <a class="dropdown-item" href="/admin/raportLotniskoPracownicy">Raport lotnisko-pracownicy</a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="/admin/raportLiniaLotnicza">Raport linia lotnicza</a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="/admin/raportLotniskoSklepyKategoria">Raport lotnisko-kategorie sklepów</a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/rejsy">Rejsy</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/loty">Loty</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/pracownicy">Pracownicy-funkcje</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/sklepy">Sklepy</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/samoloty">Samoloty</a>
                </li>
            </ul>
            <ul class="navbar-nav navbar-right">
                <li class="nav-item"><a class="nav-link" href="/logout">Wyloguj się</a></li>
            </ul>
        </div>
    </div>
</nav>