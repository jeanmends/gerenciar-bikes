let geral = [];
let tableBikes = document.getElementById("table-bikes");
let html = '';
let quantidadeDeDados = document.getElementById("quantidade-api");
function getDados(){
  
  let row = '';
  document.getElementById("Customdata").innerHTML = ''; 
    fetch("http://localhost:8080/bike")
    .then(response => response.json())
    .then(json => populateMain(json)) 
    .catch((e) => console.error(e));
}

getDados();

function populateMain(json){
    quantidadeDeDados.innerHTML = json.length;
    geral = json;
    let row = '';
    document.getElementById("Customdata").innerHTML = ''; 
    json.forEach(element => {
        row += `
            <tr>
                <td>${element.idBike}</td>
                <td>${element.codigoSerie}</td>
                <td>${element.numeroPatrimonio}</td>
                <td><i class="fa fa-pencil-square-o btn btn-outline-success" aria-hidden="true" onclick="openModal('modal-1', ${element.idBike})"></i></td>                
                <td><i class="fa fa-trash-o btn btn-outline-danger" onclick="deletarBike(${element.idBike})"aria-hidden="true"></i></td>
            </tr>
        `;
    });
    html = document.getElementById("Customdata").innerHTML + row;
    document.getElementById("Customdata").innerHTML = html;
    console.log(json);
}

async function cadastrarNovaBike() {
    console.log("funcionou");
      // Associate the FormData object with the form element
      var form = document.getElementById('cadastrar-nova-bike');
      var data = new FormData(form);
      var object = {};
      data.forEach((value, key) => object[key] = value);
      var json = JSON.stringify(object);

      console.log(json);
      try {
        const response = await fetch("http://localhost:8080/bike", {
          method: "POST",
          headers: {"Content-type": "application/json; charset=UTF-8"},
          // Set the FormData instance as the request body
          body: json
        }) 
  
      let responseFinal = response.status;
      if (responseFinal === 200){
        Swal.fire({
            position: "top-end",
            icon: "success",
            title: "Bike cadastada!",
            showConfirmButton: true,
           // timer: 1000
          });
          getDados();

      }else{
        alert("Algo deu errado na requisição!");
      }

      } catch (e) {
        console.error(e);
      }
    }

    async function deletarBike(id) {

          try {
            const response = await fetch("http://localhost:8080/bike/"+id, {
              method: "DELETE",
              headers: {"Content-type": "application/json; charset=UTF-8"},
              // Set the FormData instance as the request body
            
            }) 
      
          let responseFinal = response.status;
          if (responseFinal === 200){
            Swal.fire({
                position: "top-center",
                icon: "success",
                title: "Bike excuída!",
                showConfirmButton: true,
                //timer: 2000
              });
              getDados();
    
           
          }else{
            alert("Algo deu errado na requisição!");
          }
    
          } catch (e) {
            console.error(e);
          }
        }
    
/*
function novoPost(titulo, mensagem){
    let data = {
        titulo: `${titulo}`,
        mensagem: `${mensagem}`
      }   
    fetch('http://localhost:8080/forum', {
        method: "POST",
        body: JSON.stringify(data),
        headers: {"Content-type": "application/json; charset=UTF-8"}
      })
    .then(async response => {
        const isJson = response.headers.get('content-type')?.includes('application/json');
        const data = isJson && await response.json();

        // check for error response
        if (!response.ok) {
            // get error message from body or default to response status
            const error = (data && data.message) || response.status;
            return Promise.reject(error);
        }else{
            main.innerHTML = '';
            getDados();
        }

    })
    .catch(error => {
        element.parentElement.innerHTML = `Error: ${error}`;
        console.error('There was an error!', error);
    });
}
/*
document.querySelectorAll("button")[0].addEventListener("click", ()=>{
    let titulo = document.getElementById("postagem-titulo");
    let mensagem = document.getElementById("postagem-mensagem");
    novoPost(titulo.value, mensagem.value);
    
})

*/

function getTiposCondominios(tipo){
  fetch("http://localhost:8080/bike/"+tipo)
  .then(response => response.json())
  .then(json => populateMainComCondominios(json)) 
  .catch((e) => console.error(e));
}

//getDados();

function populateMainComCondominios(json){
   let row = '';
   quantidadeDeDados.innerHTML = json.length;
  document.getElementById("Customdata").innerHTML = '';
  json.forEach(element => {
      row += `
          <tr>
              <td>${element.idBike}</td>
              <td>${element.codigoSerie}</td>
              <td>${element.numeroPatrimonio}</td>
              <td><i class="fa fa-pencil-square-o btn btn-outline-success" aria-hidden="true" onclick="openModal('modal-1', ${element.idBike})"></i></td>                
              <td><i class="fa fa-trash-o btn btn-outline-danger" onclick="deletarBike(${element.idBike})"aria-hidden="true"></i></td>
          </tr>
      `;
  });
  html = document.getElementById("Customdata").innerHTML + row;
  document.getElementById("Customdata").innerHTML = html;
  console.log(json);
}

async function mostrarBike(id) {
  console.log("o id dentro do mostrarBike: " + id );
  try {
    fetch("http://localhost:8080/bike/"+id)
    .then(response => response.json())
    .then(json => mostrarDetalhesDasBikes(json))
    .catch((e) => console.error(e));

  } catch (e) {
    console.error(e);
  }
}
  
function mostrarDetalhesDasBikes(json){
  getDadosCondominiosParaAlterar(json.idCond);
  document.getElementById('img-bike').src = "http://127.0.0.1:1977/images_bikes/"+json.imagem;
  document.getElementById('codigoSerie').value = json.codigoSerie;
  document.getElementById('numeroPatrimonio').value = json.numeroPatrimonio;
  document.getElementById('descricao1').value = json.descricao;
  
}