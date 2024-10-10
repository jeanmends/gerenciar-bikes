function getDadosCondominios(){
    fetch("http://localhost:8080/condominios")
    .then(response => response.json())
    .then(json => populateSelection(json)) 
    .catch((e) => console.error(e));
}

getDadosCondominios();

function populateSelection(json){
    let select = document.getElementById('select-condominios');
    
    
    json.forEach(element => {
        let option = document.createElement('option');
        option.value = element.idCondomonio;
        option.innerHTML = element.nomeCondomonio;
        
        select.appendChild(option);
    });


}