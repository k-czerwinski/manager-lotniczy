<#macro paginated_table thValues tableContent parametrSortowania kierunekSortowania currentPage totalPages>
    <div class="container centered-container">
        <table id="flight-table" class="table table-striped table-dark table-bordered" style="width:100%">
            <thead>
            <#foreach col in thValues>
                <th id="${col.htmlId}">
                    <#if parametrSortowania.htmlId==col.htmlId && kierunekSortowania=="ASC">
                        <#assign newSortDir="DESC">
                    <#else>
                        <#assign newSortDir="ASC">
                    </#if>
                    <a style="text-decoration: none; color: white;"
                       href="?page=${currentPage}&sortPar=${col}&sortDir=${newSortDir}">
                        ${col.columnLabel}
                    </a>
                </th>
            </#foreach>
            </thead>
            <tbody>
            <#foreach row in tableContent>
                <tr>
                    ${row}
                </tr>
            </#foreach>
            </tbody>
        </table>
    </div>
<#--    PAGINATION-->
    <div class="container centered-container">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <#if currentPage gt 1>
                    <li class="page-item">
                        <a class="page-link"
                           href="?page=${currentPage - 1}&sortPar=${parametrSortowania}&sortDir=${kierunekSortowania}"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </#if>

                <#list (currentPage - 1)..(currentPage + 1) as page>
                    <#if page gt 0 && page <= totalPages>
                        <#if page == currentPage>
                            <li class="page-item active" aria-current="page">
                                <span class="page-link">${page} <span class="visually-hidden">(current)</span></span>
                            </li>
                        <#else>
                            <li class="page-item">
                                <a class="page-link"
                                   href="?page=${page}&sortPar=${parametrSortowania}&sortDir=${kierunekSortowania}">${page}</a>
                            </li>
                        </#if>
                    </#if>
                </#list>

                <#if currentPage < totalPages>
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage + 1}&sortPar=${parametrSortowania}&sortDir=${kierunekSortowania}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </#if>
            </ul>
        </nav>
    </div>
</#macro>