function getDadosCondominiosParaAlterar(id){
    fetch("http://localhost:8080/condominios")
    .then(response => response.json())
    .then(json => populateSelectionAlterar(json, id)) 
    .catch((e) => console.error(e));
}

//getDadosCondominiosParaAlterar();

function populateSelectionAlterar(json, id){
    let select = document.getElementById('select-condominios-alterar');
    console.log("id do condomionio slecionado para alterar "+id);
    select.innerHTML = '';
    json.forEach(element => {
        let option = document.createElement('option');
        option.value = element.idCondomonio;
        option.innerHTML = element.nomeCondomonio;
        if(element.idCondomonio === id){
            console.log("id selecionado: " + element.nomeCondomonio);
            option.selected = true;
        }
        
        select.appendChild(option);
    });


}