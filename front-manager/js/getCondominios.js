function getDados(){
    fetch("http://localhost:8080/condominios/detalhes")
    .then(response => response.json())
    .then(json => populateMain(json)) 
    .catch((e) => console.error(e));
}

getDados();

function populateMain(json){
    geral = json;
    let row = '';
    
    json.forEach(element => {
        row += `
            <tr>
                <td>${element.idCondomonio}</td>
                <td>${('0000'+element.ahreasCode).slice(-4)}</td>
                <td>${(element.nomeCondomonio).toUpperCase()}</td>
                <td>${element.bikes.length}</td>
                <td><i class="fa fa-pencil-square-o btn btn-outline-success" aria-hidden="true"></i></td>                
                <td><i class="fa fa-trash-o btn btn-outline-danger" onclick="deletarCondominio(${element.idCondomonio})"aria-hidden="true"></i></td>
            </tr>
        `;
    });
    html = document.getElementById("Customdata").innerHTML + row;
    document.getElementById("Customdata").innerHTML = html;
    console.log(json);
}

async function cadastrarNovaCondominio() {
  console.log("funcionou");
    // Associate the FormData object with the form element
    var form = document.getElementById('cadastrar-novo-condominio');
    console.log(form);
    var formData = new FormData(form);
    const json = {
      ahreasCode: formData.get('ahreasCode'),
      nomeCondomonio: formData.get('nomeCondomonio'),      
      enderecoList: [{
          cep: formData.get('cep'),
          logradouro: formData.get('logradouro'),
          complimento: formData.get('complimento'),
          bairro: formData.get('bairro'),
          cidade: formData.get('cidade'),
          UF: formData.get('uf')
      }]
  };

    try {
      const response = await fetch("http://localhost:8080/condominios", {
        method: "POST",
        headers: {"Content-type": "application/json; charset=UTF-8"},
        // Set the FormData instance as the request body
        body: JSON.stringify(json)
      }) 

    let responseFinal = response.status;
    if (responseFinal === 200){
      Swal.fire({
          position: "top-end",
          icon: "success",
          title: "Bike cadastada!",
          showConfirmButton: false,
          timer: 99000
        });
      setTimeout(() => {
          window.location = window.location;
        }, "1000");

     
    }else{
      alert("Algo deu errado na requisição!");
    }

    } catch (e) {
      console.error(e);
    }
  }

/*
async function deletarCondominio(id) {

    try {
      const response = await fetch("http://localhost:8080/condominios/"+id, {
        method: "DELETE",
        headers: {"Content-type": "application/json; charset=UTF-8"},
        // Set the FormData instance as the request body
      
      }) 

    let responseFinal = response.status;
    if (responseFinal === 200){
      Swal.fire({
          position: "top-center",
          icon: "success",
          title: "Condomínio excuída!",
          showConfirmButton: false,
          timer: 1000
        });
      setTimeout(() => {
          window.location = window.location;
        }, "1000");

     
    }else{
      alert("Algo deu errado na requisição!");
    }

    } catch (e) {
      console.error(e);
    }
  }
    ]
  
*/

