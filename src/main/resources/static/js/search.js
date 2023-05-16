function searchFunction(tableId) {
    // Declare variables
    var input, filter, table, tr, td, i, j, txtValue;
    input = document.getElementById("inpSearch");
    filter = input.value.toUpperCase();
    table = document.getElementById(tableId);
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows starting from the second row, and hide those that don't match the search query
    for (i = 1; i < tr.length; i++) {
        // Skip the first row
        if (i === 0) {
            continue;
        }

        var rowMatches = false; // Flag to determine if any cell in the row matches the search query

        td = tr[i].getElementsByTagName("td");
        for (j = 0; j < td.length; j++) {
            txtValue = td[j].textContent || td[j].innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                rowMatches = true;
                break;
            }
        }

        if (rowMatches) {
            tr[i].style.display = "";
        } else {
            tr[i].style.display = "none";
        }
    }
}
