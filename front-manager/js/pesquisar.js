
function pesquisarBike() {
  // Declare variables 
  var input, filter, table, tr, td, i;
  input = document.getElementById("pesquisar");
  filter = input.value.toUpperCase();
  table = document.getElementById("table-bikes") || document.getElementById("table-condominios");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td") ; 
    for(j=0 ; j<td.length ; j++)
    {
      let tdata = td[j] ;
      if (tdata) {
        if (tdata.innerHTML.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
          break ; 
        } else {
          tr[i].style.display = "none";
          
        }
      } 
    }
  }

  if (tr === " " || tr === null){
    alert("nada encontrado")
  }
}
  

